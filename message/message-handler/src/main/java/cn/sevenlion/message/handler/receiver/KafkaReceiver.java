package cn.sevenlion.message.handler.receiver;

import cn.sevenlion.message.common.domain.TaskInfo;
import cn.sevenlion.message.common.enums.ChannelType;
import cn.sevenlion.message.common.enums.MessageType;
import cn.sevenlion.message.handler.pending.Task;
import cn.sevenlion.message.handler.pending.TaskPendingHolder;
import cn.sevenlion.message.handler.utils.GroupIdMappingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2022-01-19
 */
@Slf4j
public class KafkaReceiver {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    TaskPendingHolder taskPendingHolder;

    @KafkaListener
    public void consumer() {
        List<TaskInfo> TaskInfoList = new ArrayList<>();
        String messageGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(TaskInfoList.get(0));

        for (TaskInfo taskInfo : TaskInfoList) {
            Task task = applicationContext.getBean(Task.class).setTaskInfo(taskInfo);
            //得到当前任务对应的线程池，然后提交到线程池中执行
            taskPendingHolder.route(messageGroupId).execute(task);
        }
    }


}

package cn.sevenlion.message.handler.pending;

import cn.sevenlion.message.handler.config.ThreadPoolConfig;
import cn.sevenlion.message.handler.utils.GroupIdMappingUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author: qimeiwen
 * @create: 2022-01-19
 * 存储每种消息与TaskPending的关系
 */
@Component
public class TaskPendingHolder {

    private Integer coreSize = 3;
    private Integer maxSize = 3;
    private Integer queueSize = 100;
    private Map<String, ExecutorService> taskPendingHolder = new HashMap<>(32);

    /**
     * 消息渠道与类型的排列组合
     */
    private static List<String> groupIds = GroupIdMappingUtils.getAllGroupIds();

    /**
     * 初始化各种消息处理的线程池
     */
    @PostConstruct
    public void init() {
        for (String groupId : groupIds) {
            taskPendingHolder.put(groupId, ThreadPoolConfig.getThreadPool(coreSize, maxSize, queueSize));
        }
    }

    public ExecutorService route(String groupId) {
        return taskPendingHolder.get(groupId);
    }
}

package cn.sevenlion.message.api.model;

import cn.sevenlion.message.api.domain.MessageParam;
import cn.sevenlion.message.common.domain.TaskInfo;
import cn.sevenlion.message.support.pipeline.ProcessModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2021-12-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;

    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;
}

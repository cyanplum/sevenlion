package cn.sevenlion.message.api.domain;

import lombok.Data;

/**
 * @author: qimeiwen
 * @create: 2021-12-08
 */
@Data
public class SendRequest {
    /**
     * 执行业务类型
     */
    private String code;

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     */
    private MessageParam messageParam;

}

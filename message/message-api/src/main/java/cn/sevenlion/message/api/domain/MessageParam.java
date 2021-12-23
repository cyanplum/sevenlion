package cn.sevenlion.message.api.domain;

import lombok.Data;

import java.util.Map;

/**
 * @author: qimeiwen
 * @create: 2021-12-08
 */
@Data
public class MessageParam {

    /**
     * @Description: 接收者
     * 多个用,逗号号分隔开
     * 必传
     */
    private String receiver;

    /**
     * @Description: 消息内容中的可变部分
     * 可选
     */
    private Map<String, String> variables;

    /**
     * @Description: 扩展参数
     * 可选
     */
    private Map<String,String> extra;
}

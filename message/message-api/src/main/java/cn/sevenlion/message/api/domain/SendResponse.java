package cn.sevenlion.message.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: qimeiwen
 * @create: 2021-12-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendResponse {

    /**
     * 响应状态
     */
    private String code;

    /**
     * 响应编码
     */
    private String msg;
}

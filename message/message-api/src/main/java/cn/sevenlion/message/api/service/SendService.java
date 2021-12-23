package cn.sevenlion.message.api.service;

import cn.sevenlion.message.api.domain.SendRequest;
import cn.sevenlion.message.api.domain.SendResponse;

/**
 * @author: qimeiwen
 * @create: 2021-12-08
 */
public interface SendService {

    /**
     * 单文案发送接口
     * @param sendRequest
     * @return
     */
    SendResponse send(SendRequest sendRequest);

}

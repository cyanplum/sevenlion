package cn.sevenlion.message.api.service.impl;

import cn.sevenlion.common.response.model.CommonResult;
import cn.sevenlion.message.api.domain.BatchSendRequest;
import cn.sevenlion.message.api.domain.SendRequest;
import cn.sevenlion.message.api.domain.SendResponse;
import cn.sevenlion.message.api.model.SendTaskModel;
import cn.sevenlion.message.api.service.SendService;
import cn.sevenlion.message.common.enums.RespStatusEnum;
import cn.sevenlion.message.common.vo.BasicResultVo;
import cn.sevenlion.message.support.pipeline.ProcessContext;
import cn.sevenlion.message.support.pipeline.ProcessController;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: qimeiwen
 * @create: 2021-12-08
 */
@Service
@Slf4j
public class SendServiceImpl implements SendService {

    @Autowired
    private ProcessController processController;

    @Override
    public SendResponse send(SendRequest sendRequest) {
        //构建发送消息，和模板
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Lists.newArrayList(sendRequest.getMessageParam()))
                .build();

        ProcessContext processContext = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(CommonResult.success()).build();
        //通过执行流程器进行流程控制执行
        //执行实际
        ProcessContext process = processController.process(processContext);
        return new SendResponse(process.getResponse().getCode(), process.getResponse().getMsg());
    }

    @Override
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(batchSendRequest.getMessageTemplateId())
                .messageParamList(batchSendRequest.getMessageParamList())
                .build();

        ProcessContext processContext = ProcessContext.builder()
                .code(batchSendRequest.getCode())
                .processModel(sendTaskModel)
                .build();
        ProcessContext process = processController.process(processContext);
        return new SendResponse(process.getCode(), process.getResponse().getMsg());
    }
}

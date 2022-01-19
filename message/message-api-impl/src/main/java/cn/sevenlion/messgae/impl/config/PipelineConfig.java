package cn.sevenlion.messgae.impl.config;

import cn.sevenlion.message.api.enums.BusinessCode;
import cn.sevenlion.message.support.pipeline.BusinessProcess;
import cn.sevenlion.message.support.pipeline.ProcessController;
import cn.sevenlion.messgae.impl.action.AfterParamCheckAction;
import cn.sevenlion.messgae.impl.action.AssembleAction;
import cn.sevenlion.messgae.impl.action.PreParamCheckAction;
import cn.sevenlion.messgae.impl.action.SendAction;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/12/8 8:49 下午
 */
@Configuration
public class PipelineConfig {


    /**
     * 注入流程控制器
     * @return
     */
    @Bean
    public ProcessController processController() {
        ProcessController processController = new ProcessController();
        Multimap<String, BusinessProcess> businessProcessMap = HashMultimap.create();
        businessProcessMap.putAll(BusinessCode.COMMON_SEND.getCode(), commonSendBusiness());
        return processController;
    }

    /**
     * 普通发送执行流程
     * 1. 前置参数校验
     * 2. 组装参数
     * 3. 后置参数校验
     * 4. 发送消息至MQ
     * @return
     */
    @Bean("commonSendBusiness")
    public List<BusinessProcess> commonSendBusiness() {
        List<BusinessProcess> businessProcessList = Lists.newArrayList();
        // TODO: 2021/12/8 添加普通发送的责任链
        businessProcessList.add(preParamCheckAction());
        businessProcessList.add(assembleAction());
        businessProcessList.add(afterParamCheckAction());
        businessProcessList.add(sendAction());
        return businessProcessList;
    }

    /**
     * 后置参数检查
     * @return
     */
    @Bean
    public AfterParamCheckAction afterParamCheckAction() {
        return new AfterParamCheckAction();
    }

    /**
     * 拼装参数
     * @return
     */
    @Bean
    public AssembleAction assembleAction() {
        return new AssembleAction();
    }

    /**
     * 前置参数检查
     * @return
     */
    @Bean
    public PreParamCheckAction preParamCheckAction() {
        return new PreParamCheckAction();
    }

    /**
     * 发送消息
     * @return
     */
    @Bean
    public SendAction sendAction() {
        return new SendAction();
    }

}

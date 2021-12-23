package cn.sevenlion.message.api.config;

import cn.sevenlion.message.api.enums.BusinessCode;
import cn.sevenlion.message.support.pipeline.BusinessProcess;
import cn.sevenlion.message.support.pipeline.ProcessController;
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

    @Bean("commonSendBusiness")
    public List<BusinessProcess> commonSendBusiness() {
        List<BusinessProcess> businessProcessList = Lists.newArrayList();
        // TODO: 2021/12/8 添加普通发送的责任链
        return businessProcessList;
    }
}

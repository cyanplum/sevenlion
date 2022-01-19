package cn.sevenlion.message.support.pipeline;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.sevenlion.common.response.model.CommonResult;
import cn.sevenlion.message.common.enums.RespStatusEnum;
import cn.sevenlion.message.common.vo.BasicResultVo;
import com.google.common.collect.ArrayListMultimap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/12/8 8:38 下午
 * 流程控制器
 */
@Data
@Slf4j
public class ProcessController {


    private ArrayListMultimap<String, BusinessProcess> businessProcess = ArrayListMultimap.create();

    public ProcessContext process(ProcessContext processContext) {
        // TODO: 2021/12/8 检查上下文
        /**
         * 前置检查
         */
        if (!preCheck(processContext)) {
            return processContext;
        }
        /**
         * 遍历流程节点
         */
        List<BusinessProcess> businessProcesses = businessProcess.get(processContext.getCode());
        for (BusinessProcess process : businessProcesses) {
            process.process(processContext);
            //如果有中断标识，中断责任链执行流程
            if (processContext.getNeedBreak()) {
                break;
            }
        }

        return processContext;
    }

    private Boolean preCheck(ProcessContext context) {
        // 上下文
        if (context == null) {
            context = new ProcessContext();
            context.setResponse(CommonResult.failed(RespStatusEnum.CONTEXT_IS_NULL.getCode(),RespStatusEnum.CONTEXT_IS_NULL.getMsg()));
            return false;
        }

        // 业务代码
        String businessCode = context.getCode();
        if (StrUtil.isBlank(businessCode)) {
            context.setResponse(CommonResult.failed(RespStatusEnum.BUSINESS_CODE_IS_NULL.getCode(), RespStatusEnum.BUSINESS_CODE_IS_NULL.getMsg()));
            return false;
        }

        // 执行模板
        List<BusinessProcess> businessProcesses = businessProcess.get(businessCode);
        if (CollUtil.isEmpty(businessProcesses)) {
            context.setResponse(CommonResult.failed(RespStatusEnum.PROCESS_TEMPLATE_IS_NULL.getCode(), RespStatusEnum.PROCESS_TEMPLATE_IS_NULL.getMsg()));
            return false;
        }

        return true;
    }
}

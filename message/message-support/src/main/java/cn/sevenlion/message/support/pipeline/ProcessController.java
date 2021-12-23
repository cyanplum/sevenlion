package cn.sevenlion.message.support.pipeline;


import com.google.common.collect.ArrayListMultimap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
}

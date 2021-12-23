package cn.sevenlion.message.support.pipeline;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/12/8 8:38 下午
 */
public interface BusinessProcess {


    /**
     * 处理执行逻辑
     * @param processContext
     */
    void process(ProcessContext processContext);
}

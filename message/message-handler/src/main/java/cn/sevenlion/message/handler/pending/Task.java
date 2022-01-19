package cn.sevenlion.message.handler.pending;

import cn.sevenlion.message.common.domain.TaskInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: qimeiwen
 * @create: 2022-01-19
 */
@Data
@Accessors(chain = true)
@Slf4j
public class Task  implements Runnable{

    private TaskInfo taskInfo;


    @Override
    public void run() {

    }
}

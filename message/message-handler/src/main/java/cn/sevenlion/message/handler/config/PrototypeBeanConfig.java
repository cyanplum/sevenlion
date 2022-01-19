package cn.sevenlion.message.handler.config;

import cn.sevenlion.message.handler.pending.Task;
import cn.sevenlion.message.handler.receiver.KafkaReceiver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author: qimeiwen
 * @create: 2022-01-19
 *
 * Handler模块的配置信息
 */
public class PrototypeBeanConfig {


    /**
     * 定义多例的Receiver
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public KafkaReceiver receiver() {
        return new KafkaReceiver();
    }

    /**
     * 定义多例的Task
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Task task() {
        return new Task();
    }
}

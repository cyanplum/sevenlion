package cn.sevenlion.common.response.processor;

import cn.sevenlion.common.response.enums.ColumnType;
import cn.sevenlion.common.response.provider.TableFieldProviderFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author: qimeiwen
 * @create: 2021-12-02
 */
public class MoneyFieldProcessor  implements TableFieldProcessor, InitializingBean {
    @Override
    public ColumnType getFieldType() {
        return ColumnType.MONEY;
    }

    @Override
    public Object serialize(Object target) {
        return null;
    }

    @Override
    public Object deSerialize(Object target) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        TableFieldProviderFactory.registerProcessor(this.getFieldType(),this);
    }
}

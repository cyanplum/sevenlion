package cn.sevenlion.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author: qimeiwen
 * @create: 2021-12-02
 */
public class MoneySerializer extends JsonSerializer<Number> {

    private final BigDecimal radix = new BigDecimal(100);

    /**
     * 格式化 保留两位小数
     */
    private static DecimalFormat priceFormat = new DecimalFormat("#0.00");

    @Override
    public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (number == null) {
            return;
        }
        try {
            jsonGenerator.writeNumber(priceFormat.format(new BigDecimal(number.toString()).divide(radix).doubleValue()));
        } catch (Exception e) {
            return;
        }
    }
}

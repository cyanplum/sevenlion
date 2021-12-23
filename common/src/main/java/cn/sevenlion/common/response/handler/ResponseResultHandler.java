package cn.sevenlion.common.response.handler;

import cn.sevenlion.common.annotation.ColumnField;
import cn.sevenlion.common.response.model.CommonResult;
import cn.sevenlion.common.response.model.CommonResultPage;
import cn.sevenlion.common.response.processor.TableFieldProcessor;
import cn.sevenlion.common.response.provider.TableFieldProviderFactory;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.Collection;
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
 * @date 2021/7/21 2:34 下午
 */

@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Class<?> parameterType = methodParameter.getParameterType();
        if (parameterType.equals(CommonResult.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Multimap<String, Object> map = HashMultimap.create();
        if (o instanceof CommonResult) {
            CommonResult commonResult = (CommonResult) o;
            Object data = commonResult.getData();
            if (data instanceof Collection) {
                for (Object object : ((List) data)) {
                    parseData(object, map);
                }
            }else {
                parseData(data, map);
            }
            return CommonResult.success(map.values());
        }
        if (o instanceof CommonResultPage) {
            CommonResultPage commonResultPage = (CommonResultPage) o;
            Object data = commonResultPage.getData();
            if (data instanceof Collection) {
                for (Object object : ((List) data)) {
                    parseData(object, map);
                }
            }else {
                parseData(data, map);
            }
            return CommonResultPage.success(map.values(), commonResultPage.getPn(), commonResultPage.getPageSize(), commonResultPage.getPages(), commonResultPage.getTotal());
        }
        return null;
    }

    public ResponseResultHandler() {
    }

    public void parseData(Object data, Multimap<String, Object> map) {
        Field[] fields = data.getClass().getDeclaredFields();
        Map<String, Object> childMap = Maps.newHashMap();
        for (Field column : fields) {
            String name = column.getName();
            Object fieldValue = getFieldValue(column, data);
            if (fieldValue instanceof  Collection) {
                Multimap<String, Object> hashMultimap = HashMultimap.create();
                parseData(fieldValue,hashMultimap);
                childMap.put(name,hashMultimap);
            }
            if (column.isAnnotationPresent(ColumnField.class)) {
                ColumnField declaredAnnotation = column.getDeclaredAnnotation(ColumnField.class);
                List<TableFieldProcessor> processor = TableFieldProviderFactory.getProcessorByType(declaredAnnotation.type());
                for (TableFieldProcessor tableFieldProcessor : processor) {
                    Object value = tableFieldProcessor.serialize(fieldValue);
                    childMap.put(name, value);
                }
            }else {
                childMap.put(name, fieldValue);
            }
        }
        map.put(data.getClass().getSimpleName(),childMap);
    }

    public Object getFieldValue(Field column,Object data) {
        column.setAccessible(true);
        try {
            return column.get(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

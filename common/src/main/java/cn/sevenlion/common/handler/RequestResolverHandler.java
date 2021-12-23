package cn.sevenlion.common.handler;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * @author: qimeiwen
 * @create: 2021-12-02
 */
public class RequestResolverHandler implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
//        Class<?> parameterType = methodParameter.getParameterType();
//        Field[] declaredFields = parameterType.getDeclaredFields();
//        if (declaredFields.length == 0) {
//            return false;
//        }
//        for (Field declaredField : declaredFields) {
//            boolean annotationPresent = declaredField.isAnnotationPresent(ColumnField.class);
//            if (annotationPresent) {
//                return true;
//            }
//        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return null;
    }
}

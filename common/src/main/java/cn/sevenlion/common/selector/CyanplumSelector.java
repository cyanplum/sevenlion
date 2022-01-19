package cn.sevenlion.common.selector;


import cn.sevenlion.common.annotation.EnableCyanplum;
import cn.sevenlion.common.config.*;
import cn.sevenlion.common.response.handler.ResponseResultHandler;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: qimeiwen
 * @create: 2021-11-30
 */
public class CyanplumSelector implements ImportSelector {

    public static HashMap<String, List<String>> map = new HashMap<>(16);
    static {
        map.put("baseConfig", Lists.newArrayList(BaseConfig.class.getName()));
        map.put("knife4j", Lists.newArrayList(Knife4jConfig.class.getName()));
        map.put("swagger", Lists.newArrayList(Swagger2Config.class.getName()));
        map.put("oss", Lists.newArrayList(OssConfig.class.getName()));
        map.put("redis", Lists.newArrayList(RedisConfig.class.getName()));
        map.put("resultHandler", Lists.newArrayList(ResponseResultHandler.class.getName()));
    }

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        List<String> result = new ArrayList<>();
        Map<String, Object> annotationConfigMap = annotationMetadata.getAnnotationAttributes(EnableCyanplum.class.getName());
        for (String key : annotationConfigMap.keySet()) {
            if (annotationConfigMap.get(key).equals(Boolean.TRUE)) {
                result.addAll(map.get(key));
            }
        }
        return result.toArray(new String[0]);
    }
}

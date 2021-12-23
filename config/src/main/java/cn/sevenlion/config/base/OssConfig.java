package cn.sevenlion.config.base;
import cn.sevenlion.config.oss.MinioTemplate;
import cn.sevenlion.config.oss.OssTemplate;
import cn.sevenlion.config.properties.OssProperties;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: qimeiwen
 * @create: 2021-11-30
 */
@Configuration
@EnableConfigurationProperties(OssProperties.class)
@Slf4j
public class OssConfig {

    @Autowired
    private OssProperties properties;


    @Bean
    @SneakyThrows
    @ConditionalOnProperty(prefix = "oss",name = "minio")
    public OssTemplate minioTemplate() {
        OssTemplate ossTemplate = new MinioTemplate(new MinioClient(
                properties.getUrl(),
                properties.getAccessKey(),
                properties.getSecretKey()
        ));
        log.info("minioTemplate服务启动成功");
        return ossTemplate;
    }
}

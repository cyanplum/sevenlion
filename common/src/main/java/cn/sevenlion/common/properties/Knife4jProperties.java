package cn.sevenlion.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: qimeiwen
 * @create: 2021-10-09
 */
@Data
@ConfigurationProperties(prefix = "cyanplum.swagger", ignoreInvalidFields = true)
public class Knife4jProperties {

    private String scanUrl;

    private boolean enable;

    private String title;

    private String description;

    private String version;
}

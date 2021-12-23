package cn.sevenlion.config.oss.utils;

import cn.sevenlion.common.utils.SpringApplicationContext;
import cn.sevenlion.config.oss.OssFileMetaData;
import cn.sevenlion.config.oss.OssTemplate;
import cn.sevenlion.config.oss.model.FileInfoVo;
import cn.sevenlion.config.oss.model.FileNameDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 4:28 下午
 */
@Slf4j
public class OssUtils {
    /**
     * 解析数据库中存储的名称
     *
     * @param databaseName
     * @return
     */
    public static FileNameDTO resolveDatabaseName(String databaseName) throws Exception {
        String[] split = StringUtils.split(databaseName, "-", 3);
        if (split.length != 3) {
            throw new RuntimeException("数据库存储格式错误!");
        }
        FileNameDTO dto = new FileNameDTO();
        dto.setBucketName(split[0]);
        dto.setMd5(split[1]);
        dto.setOriginName(split[2]);
        return dto;
    }

    public static String getUrlFromDatabaseName(String name) {
        if (name != null) {
            OssTemplate template = SpringApplicationContext.getBean(OssTemplate.class);
            if (template == null) {
                log.error("未注册bean!");
                return null;
            }
            String url = null;
            try {
                url = template.getObjectUrlFromDatabaseName(name);
            } catch (Exception e) {
                log.error("获取图片url失败：" + e.getMessage());
            }
            return url;
        } else {
            return null;
        }
    }


    public static FileInfoVo getFileInfoResult(FileNameDTO dto, Boolean isPublic) {
        try {
            OssTemplate template = SpringApplicationContext.getBean(OssTemplate.class);
            if (template == null) {
                log.error("未注册bean!");
                return null;
            }
            String url = template.getObjectURL(dto, isPublic);
            OssFileMetaData objectInfo = template.getObjectInfo(dto);
            return new FileInfoVo(dto.getOriginName(), dto.getDatabaseName(), url, objectInfo.getLength());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static FileInfoVo getFileInfoResult(String databaseName, Boolean isPublic) {
        try {
            return getFileInfoResult(resolveDatabaseName(databaseName), isPublic);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static FileInfoVo getFileInfoResult(String databaseName) {
        return getFileInfoResult(databaseName, true);
    }

}

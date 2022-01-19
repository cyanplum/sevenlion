package cn.sevenlion.message.handler.utils;

import cn.sevenlion.message.common.domain.TaskInfo;
import cn.sevenlion.message.common.enums.ChannelType;
import cn.sevenlion.message.common.enums.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2022-01-19
 *
 * 标识着每一个消费者组
 */
public class GroupIdMappingUtils {

    /**
     * 根据TaskInfo获取当前消息的groupId
     * @param taskInfo
     * @return
     */
    public static String getGroupIdByTaskInfo(TaskInfo taskInfo) {
        String channelCodeEn = ChannelType.getEnumByCode(taskInfo.getSendChannel()).name();
        String msgCodeEn = MessageType.getEnumByCode(taskInfo.getMsgType()).name();
        return channelCodeEn + "." + msgCodeEn;
    }

    public static List<String> getAllGroupIds() {
        List<String> groupIds = new ArrayList<>();
        for (ChannelType channelType : ChannelType.values()) {
            for (MessageType messageType : MessageType.values()) {
                groupIds.add(channelType.name() + "." + messageType.name());
            }
        }
        return groupIds;
    }
}

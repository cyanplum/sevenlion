package cn.sevenlion.common.response.processor;


import cn.sevenlion.common.response.enums.ColumnType;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/21 3:27 下午
 */
public interface TableFieldProcessor {

    /**
     * 得到字段的类型
     * @return
     */
    ColumnType getFieldType();

    /**
     * 解析
     * @param target
     * @return
     */
    Object serialize(Object target);

    /**
     * 反解析
     * @param target
     * @return
     */
    Object deSerialize(Object target);
}

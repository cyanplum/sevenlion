package cn.sevenlion.common.response.provider;

import cn.sevenlion.common.response.enums.ColumnType;
import cn.sevenlion.common.response.processor.TableFieldProcessor;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/21 3:32 下午
 */
public class TableFieldProviderFactory {

    public static ArrayListMultimap<ColumnType, TableFieldProcessor> tableFieldProcessorMap = ArrayListMultimap.create();

    public static List<TableFieldProcessor> getProcessorByType(ColumnType columnType) {
        if (tableFieldProcessorMap.size() == 0) {
            return Lists.newArrayList();
        }
        return tableFieldProcessorMap.get(columnType);
    }

    public static void registerProcessor(ColumnType columnFieldEnum, TableFieldProcessor processor) {
        tableFieldProcessorMap.put(columnFieldEnum, processor);
    }

    public TableFieldProviderFactory() {
    }
}

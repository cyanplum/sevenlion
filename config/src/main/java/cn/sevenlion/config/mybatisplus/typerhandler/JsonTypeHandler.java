//package cn.sevenlion.config.mybatisplus.typerhandler;
//
//import cn.sevenlion.utils.basic.utils.JsonUtils;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import lombok.SneakyThrows;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
///**
// * @author create by:
// * *      ____        ___  ___       __          __
// * *    /  _  \     /   |/   |      | |        / /
// * *   | | | |     / /|   /| |     | |  __   / /
// * *  | | | |     / / |__/ | |    | | /  | / /
// * * | |_| |_    / /       | |   | |/   |/ /
// * * \_______|  /_/        |_|  |___/|___/
// * @date 2021/12/2 9:29 下午
// */
//public class JsonTypeHandler<T> extends BaseTypeHandler<T> {
//
//    private Class<T> type;
//
//    public JsonTypeHandler(Class<T> type) {
//        this.type = type;
//    }
//
//    @SneakyThrows(Exception.class)
//    @Override
//    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) {
//        preparedStatement.setString(i, JsonUtils.obj2Str(t));
//    }
//
//    @Override
//    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
//        return parse(resultSet.getString(s));
//    }
//
//    @Override
//    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
//        return parse(resultSet.getString(i));
//    }
//
//    @Override
//    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
//        return parse(callableStatement.getString(i));
//    }
//
//    private T  parse(String s) {
//        if (StringUtils.isBlank(s)) {
//            return null;
//        } else if (type.isAssignableFrom(List.class)){
//            return JsonUtils.str2List(s, type);
//        } else {
//            return JsonUtils.str2Obj(s, type);
//        }
//    }
//}

package cn.sevenlion.common.response.model;

import cn.sevenlion.common.response.enums.ResultCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/6 11:09 δΈε
 */
@Data
@NoArgsConstructor
@SuppressWarnings("unchecked")
public class CommonResultPage<T>{

    private String code;

    private String msg;

    private Collection<T> data;

    private boolean success;

    private long pn;

    private long pageSize;

    private long pages;

    private long total;

    public CommonResultPage(String code, String msg, Collection<T> data, boolean success, long pn, long pageSize, long pages, long total) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = success;
        this.pn = pn;
        this.pageSize = pageSize;
        this.pages = pages;
        this.total = total;
    }

    public static <T> CommonResultPage success(String code, String msg, Collection<T> data, long pn, long pageSize, long pages, long total) {
        return new CommonResultPage(code, msg, data, true, pn, pageSize, pages, total);
    }

    public static <T> CommonResultPage<T> success(String msg, Collection<T> data, long pn, long pageSize, long pages,long total) {
       return success(ResultCode.SUCCESS.getCode(), msg, data, pn, pageSize, pages, total);
    }

    public static <T> CommonResultPage<T> success(Collection<T> data, long pn, long pageSize, long pages, long total) {
        return success( "ζε", data, pn, pageSize, pages, total);
    }
    public static <T> CommonResultPage<T> success(){
        return success(null,1L,10L,0L,0L);
    }
    public static <T> CommonResultPage<T> success(IPage<T> page) {
        return success(page.getRecords(), page.getCurrent(), page.getSize(), page.getPages(), page.getTotal());
    }

    public static <T> CommonResultPage<T> success(IPage page, List<T> result) {
        return success(result, page.getCurrent(), page.getSize(), page.getPages(), page.getTotal());
    }

    public static <T> CommonResultPage<T> failed(String code, String msg) {
        return new CommonResultPage(code, msg, null, false, 1L, 10L,0L, 0L);
    }

    public static <T> CommonResultPage<T> failed(String msg) {
        return failed(ResultCode.FAILED.getCode(), msg);
    }

    public static <T> CommonResultPage<T> failed() {
        return failed("ε€±θ΄₯!");
    }
}

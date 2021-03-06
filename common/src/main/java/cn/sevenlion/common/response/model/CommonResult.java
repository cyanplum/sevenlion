package cn.sevenlion.common.response.model;

import cn.sevenlion.common.response.enums.ResultCode;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2020/8/9 10:03 δΈε
 */
@SuppressWarnings("unchecked")
public class CommonResult<T> {

    private String code;

    private String msg;

    private T data;

    private boolean success;

//    public CommonResult(long code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }
//
//    public CommonResult(T data) {
//        this.data = data;
//    }

    public CommonResult(String code, String msg, T data, boolean success) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = success;
    }


    public static <T> CommonResult<T> success(String code, String msg, T data) {
        return new CommonResult(code, msg, data, true);
    }

    public static <T> CommonResult<T> success(String msg, T data) {
        return success(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> CommonResult<T> success(T data) {
        return success("ζε", data);
    }
    public static <T> CommonResult<T> success(){
        return success(null);
    }

    public static <T> CommonResult<T> failed(String code, String msg, T data) {
        return new CommonResult(code, msg, data, false);
    }

    public static <T> CommonResult<T> failed(String msg, T data) {
        return failed(ResultCode.FAILED.getCode(), msg, data);
    }

    public static <T> CommonResult<T> failed(String code, String msg) {
        return failed(code, msg, null);
    }

    public static <T> CommonResult<T> failed(String msg) {
       return failed(msg, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

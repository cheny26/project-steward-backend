package com.cheny.projectsteward.common;

/**
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 返回的数据
     * @param <T> 数据类型
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> error(int errorCode, String message) {
        return new BaseResponse<>(errorCode, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}

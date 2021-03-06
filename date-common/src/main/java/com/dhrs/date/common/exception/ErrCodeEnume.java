package com.dhrs.date.common.exception;

/***
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *      001：参数格式校验
 *  11: 商品
 *  12: 订单
 *  13: 购物车
 *  14: 物流
 *
 *
 */
public enum ErrCodeEnume {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    USER_EXIST_EXCEPTION(15001,"用户已存在"),
    SYSTEM_BUSY(15003,"系统繁忙，请稍后再试"),
    LOGINACCT_PASSWORD_INVAILD_EXCEPTION(15004,"账号密码错误"),
    PHONE_INVAILD_EXCEPTION(15005,"电话号码错误"),
    CHECK_CODE_INVAILD_EXCEPTION(15006,"验证码错误"),
    USER_NOT_EXEIST(15011,"用户不存在"),
    MESSGAE_SEND_FAIL(15010,"短信未知错误，请重试"),
    TOKEN_AUTH_FAIL(15020,"令牌过期或错误，请重新登录"),
    FILE_UPLOAD_FILE(15030,"文件上传失败"),
    WITHOUT_PERMISSION(15021,"无操作权限");

    private int code;
    private String msg;
    ErrCodeEnume(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

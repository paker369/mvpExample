
package com.pakersite.example.app;



import com.pakersite.example.app.api.ApiConstants;
import com.pakersite.example.app.utils.SPUtils;

import java.io.Serializable;


/**
 * ================================================
 * 如果你服务器返回的数据格式固定为这种方式(这里只提供思想,服务器返回的数据格式可能不一致,可根据自家服务器返回的格式作更改)
 * 指定范型即可改变 {@code data} 字段的类型, 达到重用 {@link BaseResponse}, 如果你实在看不懂, 请忽略
 * <p>
 * Created by JessYan on 26/09/2016 15:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */

public class BaseResponse<T> implements Serializable {


    private T data;
    private int code;
    private String msg;
    private long serverTime;
    private int subCode;
    private String subMsg="";
    public boolean success;

    public boolean isSuccess() {
        if(msg.contains("未登录")){
            SPUtils.getInstance().remove(AppConstants.TOKEN);
            SPUtils.getInstance().remove(AppConstants.ISWXLOGIN);
            SPUtils.getInstance().remove(AppConstants.PHONE);
            SPUtils.getInstance().put(AppConstants.WXLOGIN, false);
        }
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isCodeSuccess() {
        return code == ApiConstants.RequestSuccess && subCode == ApiConstants.RequestSuccess;
    }

    /**
     * 获取返回信息
     * @return
     */
    public String getMessage() {
//        if (code != ApiConstants.RequestSuccess && subCode != ApiConstants.RequestSuccess) {
//            return subMsg;
//        } else if (code != ApiConstants.RequestSuccess) {
//            return msg;
//        } else if (subCode != ApiConstants.RequestSuccess) {
//            return subMsg;
//        } else {
//            return "";
//        }
        if(isSuccess()){
            return msg;
        }else{
                return subMsg;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public int getSubCode() {
        return subCode;
    }

    public void setSubCode(int subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }
}

package com.dymao.vo;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/10 14:28
 */
public class BaseMessage {

    public BaseMessage(){
        returnCode = 200;
        returnMsg = "保存成功";
    }

    private Integer returnCode;

    private String returnMsg;

    private Object data;

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

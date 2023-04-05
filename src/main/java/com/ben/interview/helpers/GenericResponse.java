package com.ben.interview.helpers;

public class GenericResponse {
    private Integer returnCode;
    private String returnMessage;
    private Object returnData;

    public GenericResponse() {
    }

    public GenericResponse(Integer returnCode, String returnMessage, Object returnData) {
        this.setReturnCode(returnCode);
        this.setReturnMessage(returnMessage);
        this.setReturnData(returnData);
    }

    public GenericResponse(Integer returnCode, String returnMessage){
        this.setReturnCode(returnCode);
        this.setReturnMessage(returnMessage);
    }


    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public void setReturnCodeAndMessage(Integer returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }
}
package com.kuriss.train.common.resp;

import lombok.Setter;

@Setter
public class CommonResp<T> {

    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T content;

    public CommonResp() {
    }

    public CommonResp(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }

    public CommonResp(T content) {
        this.content = content;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CommonResp{");
        sb.append("success=").append(success);
        sb.append(", message='").append(message).append('\'');
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}

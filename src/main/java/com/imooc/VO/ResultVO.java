package com.imooc.VO;

import lombok.Data;

@Data
public class ResultVO<T> {
    //错误码
    private Integer code;
    //提示信息
    private String message;
    //具体内容
    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

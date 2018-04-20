package com.qk.party.bean;

/**
 * 作者：Think
 * 创建于 2017/10/17 16:35
 */

public class Result <T>{
    private int result_code;
    private T result_data;
    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public int getResult_code() {
        return result_code;
    }

    public T getResult_data() {
        return result_data;
    }

    public void setResult_data(T result_data) {
        this.result_data = result_data;
    }
}

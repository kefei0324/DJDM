package com.qk.party.viewinterface;

/**
 * @author ：Think
 * 创建于 2017/10/17 16:40
 */

public interface LoginView <T>{
    void success(T t);
    void error(String error);
}

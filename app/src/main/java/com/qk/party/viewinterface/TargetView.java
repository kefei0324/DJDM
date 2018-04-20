package com.qk.party.viewinterface;

/**
 * 作者：Think
 * 创建于 2017/11/2 17:49
 */

public interface TargetView<T,E>{
    void successTarget(E t);
    void success(T t);
    void error(String error);
}

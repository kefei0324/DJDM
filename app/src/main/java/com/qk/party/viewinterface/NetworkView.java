package com.qk.party.viewinterface;

/**
 * @author ：Think
 * 创建于 2017/10/18 09:05
 */

public interface NetworkView<T> {
    void success(T t);
    void error(String error);
}

package com.qk.party.viewinterface;

/**
 * 作者：Think
 * 创建于 2017/11/9 17:45
 */

public interface MissionView<E,T> extends NetworkView<T> {
    void successMessage(E t);
}

package com.example.testrxjava.rx;

/**
 * @author Liupengfei
 * @describe 取消订阅
 * @date on 2019/10/22 11:17
 */
public interface Disposable {

    //取消订阅
    void dispose();

    //获取是否取消订阅
    boolean isDisposed();
}

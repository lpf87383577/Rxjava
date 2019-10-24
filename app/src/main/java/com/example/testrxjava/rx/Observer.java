package com.example.testrxjava.rx;


/**
 * @author Liupengfei
 * @describe 观察者
 * @date on 2019/10/22 11:16
 */
public interface Observer<T> {

    void onSubscribe(Disposable d);

    void onNext(T value);

    void onError(Throwable e);

    void onComplete();
}

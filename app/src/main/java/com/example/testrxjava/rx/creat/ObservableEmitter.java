package com.example.testrxjava.rx.creat;

import com.example.testrxjava.rx.Disposable;

/**
 * @author Liupengfei
 * @describe 中间件接口
 * @date on 2019/10/22 11:21
 */
public interface ObservableEmitter<T> {

    void setDisposable(Disposable d);

    boolean isDisposed();

    void onNext(T value);

    void onError(Throwable error);

    void onComplete();

}

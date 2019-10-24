package com.example.testrxjava.rx.creat;

/**
 * @author Liupengfei
 * @describe TODO
 * @date on 2019/10/22 10:54
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(ObservableEmitter<T> e) throws Exception;
}

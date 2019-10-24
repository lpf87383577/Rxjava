package com.example.testrxjava.rx;

import com.example.testrxjava.rx.Observer;

/**
 * @author Liupengfei
 * @describe 被观察者顶级接口
 * @date on 2019/10/24 10:48
 */
public interface ObservableSource<T> {

    void subscribe(Observer<? super T> observer);
}

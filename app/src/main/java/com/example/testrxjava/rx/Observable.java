package com.example.testrxjava.rx;

import com.example.testrxjava.rx.creat.ObservableCreate;
import com.example.testrxjava.rx.creat.ObservableOnSubscribe;
import com.example.testrxjava.rx.map.Function;
import com.example.testrxjava.rx.map.ObservableMap;

/**
 * @author Liupengfei
 * @describe 被观察者
 * @date on 2019/10/22 10:52
 */
public abstract class Observable<T> implements ObservableSource<T> {


    //creat操作符
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {

        return new ObservableCreate<T>(source);
    }

    //map操作符
    public final <R> Observable<R> map(Function<? super T, ? extends R> mapper) {

        return new ObservableMap<T,R>(this, mapper);
    }


    @Override
    public final void subscribe(Observer<? super T> observer) {

        subscribeActual(observer);
    }


    public abstract void subscribeActual(Observer<? super T> observer);



}

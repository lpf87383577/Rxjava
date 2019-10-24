package com.example.testrxjava;


import com.example.testrxjava.rx.Disposable;
import com.example.testrxjava.rx.Observable;
import com.example.testrxjava.rx.creat.ObservableEmitter;
import com.example.testrxjava.rx.creat.ObservableOnSubscribe;
import com.example.testrxjava.rx.Observer;
import com.example.testrxjava.rx.map.Function;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Disposable disposable;
    @Test
    public void addition_isCorrect() {

        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("----自定义观察者模式-----");
                emitter.onNext("连载1");
                System.out.println("发送事件--连载1");
                emitter.onNext("连载2");
                System.out.println("发送事件--连载2");
                disposable.dispose();
                emitter.onNext("连载3");
                System.out.println("发送事件--连载3");
                emitter.onComplete();
                System.out.println("发送事件--已完成");

            }
        });

        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                System.out.println("----onSubscribe-----");
            }

            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        observable.subscribe(observer);

    }

    @Test
    public void creat() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "lpf--"+integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

    }
}
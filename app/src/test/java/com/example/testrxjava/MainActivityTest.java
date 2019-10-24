package com.example.testrxjava;

import android.util.Log;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * @author Liupengfei
 * @describe TODO
 * @date on 2019/10/22 9:30
 */
public class MainActivityTest {

    Disposable mDisposable;

    @Test
    public void onCreate() {

        //被观察者
        Observable novel= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("连载1");
                System.out.println("发送事件--连载1");
                emitter.onNext("连载2");
                System.out.println("发送事件--连载2");
                emitter.onNext("连载3");
                System.out.println("发送事件--连载3");
                emitter.onComplete();
                System.out.print("发送事件--已完成");
                emitter.onNext("连载4");
            }
        });



        //观察者
        Observer<String> reader=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable=d;
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                if ("2".equals(value)){
                    mDisposable.dispose();
                    return;
                }
                System.out.println("onNext:"+value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError="+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete()");
            }
        };

        //被观察者订阅观察者
        novel.subscribe(reader);

    }
}
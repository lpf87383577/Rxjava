package com.example.testrxjava.operator;

import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Liupengfei
 * @describe 创建操作符
 * @date on 2019/10/23 10:13
 */
public class Create {

    Observer<String> mObserver = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String value) {
            System.out.println("onNext--"+value);

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            System.out.println("onComplete");
        }

    };


    Observer mObserver2 = new Observer<Long>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Long value) {
            System.out.println("onNext--"+value);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            System.out.println("onComplete--");
        }
    };

    public void just(){
        Observable.just("lpf", "GY").subscribe(mObserver);
    }

    public void from(){
        Observable.fromArray("lpf", "GY").subscribe(mObserver);
    }

    String TAG = "--lpf--";
    public void interval(){
        final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                        mCompositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(Long value) {

                        Log.e(TAG,"onNext--" + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"onComplete--" );
                    }
                });

    }




    public void repeat(){

        Observable.range(0, 3).repeat(2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                System.out.println("--onNext--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }




}

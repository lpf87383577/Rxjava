package com.example.testrxjava.operator;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author Liupengfei
 * @describe 变换操作符
 * @date on 2019/10/23 14:54
 */
public class Transform {

    public void map(){

        Observable.just(1,2,3,4)
                .map(new Function<Integer, Boolean>() {

                    @Override
                    public Boolean apply(Integer integer) throws Exception {

                        return integer%2==0;

                    }
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                System.out.println("onNext--"+value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void flatMap(){

        Observable.just(1,2,3,4).flatMap(new Function<Integer, ObservableSource<String>>() {


            @Override
            public ObservableSource<String> apply(Integer s) throws Exception {
                return Observable.just("lpf--"+s);
            }
        }).subscribe(new Observer<String>() {
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

            }
        });
    }

    public void concatMap(){

        Observable.just(1,2,3,4).concatMap(new Function<Integer, ObservableSource<String>>() {


            @Override
            public ObservableSource<String> apply(Integer s) throws Exception {
                return Observable.just("lpf--"+s);
            }
        }).subscribe(new Observer<String>() {
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

            }
        });
    }

    public void cast(){
        //cast操作符，将类对象进行转换
        Object[] objectsArr = {"1", "2", "3"};
        Observable.just(objectsArr).cast(String.class).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

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

package com.example.testrxjava.rx.creat;

import com.example.testrxjava.rx.Disposable;
import com.example.testrxjava.rx.Observable;
import com.example.testrxjava.rx.Observer;

/**
 * @author Liupengfei
 * @describe 被观察者实现类
 * @date on 2019/10/22 10:56
 */
public class ObservableCreate<T> extends Observable<T> {

    final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    public void subscribeActual(Observer<? super T> observer){
        //创建ObservableEmitter的实现类
        CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        //调用observer的onSubscribe的方法，表示订阅开始了
        observer.onSubscribe(parent);

        try {
            //调用ObservableOnSubscribe的subscribe()方法，
            source.subscribe(parent);
        } catch (Throwable ex) {
            parent.onError(ex);
        }
    }

    //ObservableEmitter Disposable的实现类
    static final class CreateEmitter<T> implements ObservableEmitter<T>, Disposable {

        final Observer<? super T> observer;

        boolean isDispose = false;

        CreateEmitter(Observer<? super T> observer) {
            this.observer = observer;
        }

        //在自身onNext方法里面调用observer.onNext(t);
        @Override
        public void onNext(T t) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            if (!isDisposed()) {
                observer.onNext(t);
            }
        }

        @Override
        public void onError(Throwable t) {
            if (t == null) {
                t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (!isDisposed()) {
                try {
                    observer.onError(t);
                } finally {
                    dispose();
                }
            }
        }

        @Override
        public void onComplete() {
            if (!isDisposed()) {
                try {
                    observer.onComplete();
                } finally {
                    dispose();
                }
            }
        }

        @Override
        public void setDisposable(Disposable d) {
            isDispose = d.isDisposed();
        }


        @Override
        public void dispose() {
            isDispose = true;
        }

        @Override
        public boolean isDisposed() {
            return isDispose;
        }

    }


}

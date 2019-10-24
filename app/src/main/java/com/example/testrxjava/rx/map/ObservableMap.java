package com.example.testrxjava.rx.map;

import com.example.testrxjava.rx.Disposable;
import com.example.testrxjava.rx.Observable;
import com.example.testrxjava.rx.ObservableSource;
import com.example.testrxjava.rx.Observer;


/**
 * @author Liupengfei
 * @describe map的被观察者
 * @date on 2019/10/24 9:27U
 */
public class ObservableMap<T, U> extends Observable<U> {

    final Function<? super T, ? extends U> function;

    ObservableSource source;

    public ObservableMap(ObservableSource<T> source, Function<? super T, ? extends U> function) {
        this.source = source;
        this.function = function;
    }


    //1.先将传进来的observer封装成MapObserver,
    //2.再将之前传入的Observable.subscribe(MapObserver)
    @Override
    public void subscribeActual(Observer<? super U> observer) {
        source.subscribe(new MapObserver<T, U>(observer, function));
    }

    static final class MapObserver<T, U> implements Observer<T>  {

        final Function<? super T, ? extends U> mapper;

        Observer<? super U> actual;

        MapObserver(Observer<? super U> actual, Function<? super T, ? extends U> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        @Override
        public void onSubscribe(Disposable d) {
            actual.onSubscribe(d);
        }

        @Override
        public void onNext(T t) {

            U u = null;
            try {
                u = mapper.apply(t);
            } catch (Exception e) {
                e.printStackTrace();
            }

            actual.onNext(u);
        }

        @Override
        public void onError(Throwable e) {
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }

    }




}

package com.example.testrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testrxjava.operator.Create;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    String TAG = "--lpf--";


    Observable<Long> longObservable;
    Observer observer;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longObservable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observer = new Observer<Long>() {
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
        };

    }

    public void disposable(View v){
        mCompositeDisposable.clear();
    }

    public void interval(View v){
        Log.e(TAG,"interval");
        longObservable.subscribe(observer);
    }


}

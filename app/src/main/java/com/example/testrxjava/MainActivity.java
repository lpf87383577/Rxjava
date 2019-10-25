package com.example.testrxjava;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.testrxjava.operator.Create;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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

    //申请权限，返回一个结果，全部同意放回true，一个不同意放回false
    public void permission1(View v){

        RxPermissions rxPermissions=new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE,Manifest.permission.INTERNET).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){
                    //申请的权限全部允许
                    Toast.makeText(MainActivity.this, "允许了权限!", Toast.LENGTH_SHORT).show();

                }else{
                    //只要有一个权限被拒绝，就会执行
                    Toast.makeText(MainActivity.this, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    //申请权限，返回多个结果，不同权限不同结果
    public void permission2(View v) {

        RxPermissions rxPermissions = new RxPermissions(this);

        //监听单一权限
        rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission value) throws Exception {
                        if (value.granted) {
                            Log.e("lpf--111",value.granted+value.name);
                        } else if (value.shouldShowRequestPermissionRationale) {

                            Log.e("lpf--222",value.shouldShowRequestPermissionRationale+value.name);
                        } else {
                            Log.e("lpf--333", value.name);
                        }
                    }
                });
    }
    
}

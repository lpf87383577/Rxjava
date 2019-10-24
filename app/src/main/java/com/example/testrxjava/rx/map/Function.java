package com.example.testrxjava.rx.map;

/**
 * @author Liupengfei
 * @describe 转换器，将T装换成R
 * @date on 2019/10/24 9:27
 */
public interface Function<T, R> {

    R apply(T t) throws Exception;
}

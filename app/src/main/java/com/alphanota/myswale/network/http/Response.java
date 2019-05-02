package com.alphanota.myswale.network.http;

public interface Response<T> {
    int code();
    boolean isSuccessful();
    T body();
    Object errorBody();
    String message();
}

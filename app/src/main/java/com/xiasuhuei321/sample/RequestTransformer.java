package com.xiasuhuei321.sample;

import android.content.Context;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 封装了『后台请求 -> 主线程更新UI』类型的线程调度
 * @param <T>
 */
public class RequestTransformer<T> implements Observable.Transformer<T, T> {

    private Subscriber<T> subscriber;

    public RequestTransformer() {
    }

    public RequestTransformer(Subscriber<T> subscriber) {
        this.subscriber = subscriber;
    }

    public RequestTransformer(Context context) {
        if (context != null) {
            subscriber = new RxLoadingDialog<T>(context).subscribe();
        }
    }

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        Observable<T> resultObservable = tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        if (subscriber != null) {
            resultObservable.subscribe(subscriber);
        }

        return resultObservable;
    }
}

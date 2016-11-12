package com.xiasuhuei321.sample;

import android.content.Context;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import rx.Subscriber;

/**
 * Created by zhangll on 2016/11/7.
 * desc:
 */
public class RxLoadingDialog<T> extends LoadingDialog implements CustomSubscriber<T> {

    public RxLoadingDialog(Context context) {
        super(context);
        setLoadingText("加载中...");
    }

    @Override
    public Subscriber<T> subscribe() {
        show();

        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                loadSuccess();
            }

            @Override
            public void onError(Throwable e) {
                loadFailed();
            }

            @Override
            public void onNext(T o) {

            }
        };
    }
}

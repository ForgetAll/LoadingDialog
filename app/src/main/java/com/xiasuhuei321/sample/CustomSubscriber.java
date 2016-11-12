package com.xiasuhuei321.sample;

import rx.Subscriber;

/**
 * Created by zhangll on 2016/11/7.
 */

public interface CustomSubscriber<T> {
    Subscriber<T> subscribe();
}

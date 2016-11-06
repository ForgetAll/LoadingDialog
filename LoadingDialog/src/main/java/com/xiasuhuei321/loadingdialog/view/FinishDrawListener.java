package com.xiasuhuei321.loadingdialog.view;

import android.view.View;

/**
 * Created by Luo_xiasuhuei321@163.com on 2016/11/6.
 * desc:
 */
public interface FinishDrawListener {
    /**
     * 分发绘制完成事件
     *
     * @param v 绘制完成的View
     */
    void dispatchFinishEvent(View v);
}

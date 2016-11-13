package com.xiasuhuei321.sample;

import android.app.Application;

import com.xiasuhuei321.loadingdialog.manager.StyleManager;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

/**
 * Created by Luo_xiasuhuei321@163.com on 2016/11/12.
 * desc:
 */

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StyleManager s = new StyleManager();

        //在这里调用方法设置s的属性
        //code here...
        s.Anim(false).repeatTime(0).contentSize(-1).intercept(true);

        LoadingDialog.initStyle(s);
    }
}

package com.xiasuhuei321.sample.base;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;


/**
 * Created by xiasuhuei321 on 2019/4/9.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */

public class BaseLoadingActivity extends AppCompatActivity {

    protected LoadingDialog ld;

    // will remove all callback in onDestroy method
    @SuppressWarnings("HandlerLeak")
    protected Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMessage(msg);
        }
    };

    protected void handleMessage(Message msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeCallbacksAndMessages(null);
    }

}

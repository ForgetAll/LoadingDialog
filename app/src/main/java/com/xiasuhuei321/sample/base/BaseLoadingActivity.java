package com.xiasuhuei321.sample.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;


/**
 * Created by xiasuhuei321 on 2019/4/9.
 * author:luo
 * e-mail:xiasuhuei321@163.com
 */

public class BaseLoadingActivity extends AppCompatActivity {

    protected LoadingDialog ld;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ld = new LoadingDialog(this)
        .setInterceptBack(false)
        .setDimissListener(new LoadingDialog.DismissListener() {
            @Override
            public void dimiss() {
                h.removeCallbacksAndMessages(null);
            }
        });
    }

    // will remove all callback in onDestroy method
    @SuppressWarnings("HandlerLeak")
    protected Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            BaseLoadingActivity.this.handleMessage(msg);
        }
    };

    protected void handleMessage(Message msg) {

    }

    protected void showLoading() {
        ld.show();
    }

    protected void dismissLoading() {
        ld.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeCallbacksAndMessages(null);
    }

}

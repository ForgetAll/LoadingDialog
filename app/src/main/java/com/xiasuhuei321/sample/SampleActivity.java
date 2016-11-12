package com.xiasuhuei321.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;
import com.xiasuhuei321.loadingdialog.view.SizeUtils;

/**
 * Created by Luo_xiasuhuei321@163.com on 2016/11/12.
 * desc:
 */

public class SampleActivity extends AppCompatActivity implements View.OnClickListener {

    private LoadingDialog l;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        findViewById(R.id.btn_show1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        l = new LoadingDialog(this);
        l.setTextSize(22);
        l.show();
        h.sendEmptyMessageDelayed(1, 3000);
    }

    private Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            l.loadSuccess();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeCallbacksAndMessages(null);
    }
}

package com.xiasuhuei321.sample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LoadingDialog ld;

    public static final int LOAD_SUCCESS = 1;
    public static final int LOAD_FAILED = 2;
    public static final int LOADING = 3;
    public static final int LOAD_WITHOUT_ANIM_SUCCESS = 4;
    public static final int LOAD_WITHOUT_ANIM_FAILED = 5;
    public static final int SAVE_YOU = 6;

    LoadingDialog.Speed speed = LoadingDialog.Speed.SPEED_TWO;
    private long delayedTime = 1000L;
    private int repeatTime = 0;
    private boolean intercept_back_event = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
    }

    @SuppressWarnings("all")
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_SUCCESS:
                    ld.loadSuccess();
                    break;
                case LOAD_FAILED:
                    ld.loadFailed();
                    break;
                case LOADING:
                    ld.show();
                    break;
                case LOAD_WITHOUT_ANIM_FAILED:
                    ld.loadFailed();
                    break;
                case LOAD_WITHOUT_ANIM_SUCCESS:
                    ld.loadSuccess();
                    break;
                case SAVE_YOU:
                    ld.close();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                ld = new LoadingDialog(this);
                ld.setInterceptBack(intercept_back_event)
                        .setLoadingText("加载中...");
                h.sendEmptyMessage(LOADING);
                saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
                break;

            case R.id.btn2:
                ld = new LoadingDialog(this);
                ld.setLoadingText("加载中")
                        .setSuccessText("加载成功")
                        .setInterceptBack(intercept_back_event)
                        .setLoadSpeed(speed)
                        .setRepeatCount(repeatTime)
                        .show();
                h.sendEmptyMessageDelayed(LOAD_SUCCESS, delayedTime);
                saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
                break;

            case R.id.btn3:
                ld = new LoadingDialog(this);
                ld.setLoadingText("加载中")
                        .setFailedText("加载失败")
                        .setInterceptBack(intercept_back_event)
                        .setLoadSpeed(speed)
                        .setRepeatCount(repeatTime)
                        .show();
                h.sendEmptyMessageDelayed(LOAD_FAILED, delayedTime);
                saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
                break;

            case R.id.btn4:
                ld = new LoadingDialog(this);
                ld.setLoadingText("加载中")
                        .setSuccessText("加载成功")
                        .setInterceptBack(intercept_back_event)
                        .setLoadSpeed(speed)
                        .closeSuccessAnim()
                        .setRepeatCount(repeatTime)
                        .show();
                h.sendEmptyMessageDelayed(LOAD_WITHOUT_ANIM_SUCCESS, delayedTime);
                saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
                break;

            case R.id.btn5:
                ld = new LoadingDialog(this);
                ld.setLoadingText("加载中")
                        .setFailedText("加载失败")
                        .setInterceptBack(intercept_back_event)
                        .setLoadSpeed(speed)
                        .closeFailedAnim()
                        .setRepeatCount(repeatTime)
                        .show();
                h.sendEmptyMessageDelayed(LOAD_WITHOUT_ANIM_FAILED, delayedTime);
                saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
                break;
        }
    }

    private void saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(boolean intercept_back_event) {
        if (intercept_back_event) {
            Toast.makeText(this, "don't be worried,this dialog will be closed " +
                    "after several seconds later", Toast.LENGTH_LONG).show();
            h.sendEmptyMessageDelayed(SAVE_YOU, 8000L);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delay:
                delayedTime = delayedTime == 1000L ? 4000L : 1000L;
                Toast.makeText(this, "the delayed time now is:" + delayedTime + " ms", Toast.LENGTH_SHORT).show();
                break;

            case R.id.speed:
                speed = speed == LoadingDialog.Speed.SPEED_ONE ?
                        LoadingDialog.Speed.SPEED_TWO : LoadingDialog.Speed.SPEED_ONE;
                int speedInfo = 1;
                if (speed == LoadingDialog.Speed.SPEED_ONE) {
                    speedInfo = 1;
                } else {
                    speedInfo = 2;
                }
                Toast.makeText(this, "the speed now is:" + speedInfo, Toast.LENGTH_SHORT).show();
                break;

            case R.id.intercept:
                intercept_back_event = !intercept_back_event;
                Toast.makeText(this, "now the dialog will intercept the back event", Toast.LENGTH_SHORT).show();
                break;
            case R.id.repeat:
                repeatTime = repeatTime == 0 ? 1 : 0;
                Toast.makeText(this, "now the loading callback will be draw:" + (repeatTime + 1) + " times", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}

package com.xiasuhuei321.sample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;
import com.xiasuhuei321.loadingdialog.view.SizeUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;


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
    private int color = Color.argb(255, 255, 255, 255);
    private int style = LoadingDialog.STYLE_LINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        startActivity(new Intent(this,TempActivity.class));
    }

    private void initView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
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
                        .setLoadingText("加载中...")
                        .setLoadStyle(style)
                        .show();

//                h.sendEmptyMessage(LOADING);

                saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
                break;

            case R.id.btn2:
                ld = new LoadingDialog(this);
                ld.setOnFinishListener(new LoadingDialog.OnFinshListener() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "绘制完毕", Toast.LENGTH_SHORT).show();
                    }
                });
                ld.setLoadingText("加载中")
                        .setSuccessText("加载成功")
                        .setInterceptBack(intercept_back_event)
                        .setLoadSpeed(speed)
                        .setRepeatCount(repeatTime)
//                        .setDrawColor(color)
                        .setLoadStyle(style)
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
//                        .setDrawColor(color)
                        .setShowTime(5000)//延时5秒自动关闭，默认1秒
                        .setLoadStyle(style)
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
//                        .setDrawColor(color)
                        .setRepeatCount(repeatTime)
                        .setLoadStyle(style)
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
//                        .setDrawColor(color)
                        .setSize(SizeUtils.dip2px(this, 100))
                        .setRepeatCount(repeatTime)
                        .setLoadStyle(style)
                        .show();
                h.sendEmptyMessageDelayed(LOAD_WITHOUT_ANIM_FAILED, delayedTime);
                saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
                break;

            case R.id.btn6:
                testRx();
                break;

            case R.id.btn7:
                startActivity(new Intent(this, SampleActivity.class));
                break;
        }
    }

    /**
     * 本来是想借此给拦截了back导致无法返回用户界面的情况，给出一种解决方法，
     * 已写入api中，你可以调用setShowTime()来指定反馈dialog展示的时长（自完全绘制完毕开始计算），
     * 但是需要注意的是loading界面仍然可能导致用户无法与你的界面交互。
     */
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
                Toast.makeText(this, "the delayed time now is:" + delayedTime + " ms", Toast.LENGTH_LONG).show();
                break;

            case R.id.speed:
                speed = speed == LoadingDialog.Speed.SPEED_ONE ?
                        LoadingDialog.Speed.SPEED_TWO : LoadingDialog.Speed.SPEED_ONE;
                int speedInfo;
                if (speed == LoadingDialog.Speed.SPEED_ONE) {
                    speedInfo = 1;
                } else {
                    speedInfo = 2;
                }
                Toast.makeText(this, "the speed now is:" + speedInfo, Toast.LENGTH_LONG).show();
                break;

            case R.id.intercept:
                intercept_back_event = !intercept_back_event;
                if (intercept_back_event)
                    Toast.makeText(this, "now the dialog will intercept the back event", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(this, "now the dialog will accept the back event", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.repeat:
                repeatTime = repeatTime == 0 ? 1 : 0;
                Toast.makeText(this, "now the loading callback will be draw:" + (repeatTime + 1) + " times", Toast.LENGTH_LONG).show();
                break;
            case R.id.color:
                color = color == Color.argb(255, 255, 255, 255) ? Color.BLUE : Color.argb(255, 255, 255, 255);
                Toast.makeText(this, "now the color is:" + color, Toast.LENGTH_LONG).show();
                break;
            case R.id.style:
                style = style == LoadingDialog.STYLE_LINE ? LoadingDialog.STYLE_RING : LoadingDialog.STYLE_LINE;
                Toast.makeText(this, "now the style is changed", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void testRx() {
        Observable.just("test after 2 seconds")
                .delay(2, TimeUnit.SECONDS)
                .compose(new RequestTransformer<>(this))
//                .compose(new RequestTransformer<>(new RxLoadingDialog<>(this).subscribe()))
//                .subscribe(System.out::println);
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Object s) {
                        System.out.println((String) s);
                    }
                });
    }
}

package xyz.buzhidao.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends BaseActivity {
    //最小显示时间
    private static final int SHOW_TIME_MIN = 3000;

    private Handler mMainHandler = new Handler() {};

    Runnable goToMainActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
            //淡入 淡出
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏导航栏
        getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        //加载页面内容
        setContentView(R.layout.splash);

        mMainHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN);
    }
}

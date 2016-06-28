package xyz.buzhidao.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends BaseActivity {
    //��С��ʾʱ��
    private static final int SHOW_TIME_MIN = 3000;

    private Handler mMainHandler = new Handler() {};

    Runnable goToMainActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
            //���� ����
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //���ر�����
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //���ص�����
        getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        //����ҳ������
        setContentView(R.layout.splash);

        mMainHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN);
    }
}

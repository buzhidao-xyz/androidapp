package xyz.buzhidao.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends CommonActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main_activity);
    }

    //map点击事件
    public void goToMap(View v) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        MainActivity.this.startActivity(intent);
//        MainActivity.this.finish();
        //左滑移入 右滑移出
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}

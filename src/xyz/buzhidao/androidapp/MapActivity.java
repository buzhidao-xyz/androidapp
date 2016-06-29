package xyz.buzhidao.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MapActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.map_activity);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
    }

    //跳转到首页
    public void goToMain(View v) {
        Intent intent = new Intent(MapActivity.this, MainActivity.class);
        MapActivity.this.startActivity(intent);
        MapActivity.this.finish();

        //左滑移入 右滑移出
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}

package xyz.buzhidao.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CustomtitleActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GCViews();
    }

    private void GCViews() {
        super.setContentView(R.layout.customtitle_activity);
    }

    //标题栏 - 设置标题
    public void setHeadTitle(int title) {
        TextView textHeadTitleTitle = (TextView)findViewById(R.id.textHeadTitleTitle);

        textHeadTitleTitle.setText(title);
    }

    //标题栏 - 首页跳转
    public void goToMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();

        //左滑移入 右滑移出
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}

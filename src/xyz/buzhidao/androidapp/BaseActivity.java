package xyz.buzhidao.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class BaseActivity extends Activity {


    //标题栏 - 设置标题
    public void setHeadTitle(int title) {
        TextView textHeadTitleTitle = (TextView)findViewById(R.id.textHeadTitleTitle);

        textHeadTitleTitle.setText(title);
    }

    //按键 - 返回上一页
    public boolean OnKeyDown(int keyCode,KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent=new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return false;
    }

    //标题栏 - 返回上一页
    public void goToBack(View v) {
//        Intent intent=new Intent();
//        intent.setClass(this, MainActivity.class);
//        startActivity(intent);
        this.finish();
    }

    //标题栏 - 返回首页
    public void goToMain(View v) {
//        Intent intent=new Intent();
//        intent.setClass(this, MainActivity.class);
//        startActivity(intent);
        this.finish();

        //左滑移入 右滑移出
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}

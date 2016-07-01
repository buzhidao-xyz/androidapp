package xyz.buzhidao.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class BaseActivity extends Activity {


    //������ - ���ñ���
    public void setHeadTitle(int title) {
        TextView textHeadTitleTitle = (TextView)findViewById(R.id.textHeadTitleTitle);

        textHeadTitleTitle.setText(title);
    }

    //���� - ������һҳ
    public boolean OnKeyDown(int keyCode,KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent=new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return false;
    }

    //������ - ������һҳ
    public void goToBack(View v) {
//        Intent intent=new Intent();
//        intent.setClass(this, MainActivity.class);
//        startActivity(intent);
        this.finish();
    }

    //������ - ������ҳ
    public void goToMain(View v) {
//        Intent intent=new Intent();
//        intent.setClass(this, MainActivity.class);
//        startActivity(intent);
        this.finish();

        //������ �һ��Ƴ�
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}

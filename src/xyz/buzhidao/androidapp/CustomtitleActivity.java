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

    //������ - ���ñ���
    public void setHeadTitle(int title) {
        TextView textHeadTitleTitle = (TextView)findViewById(R.id.textHeadTitleTitle);

        textHeadTitleTitle.setText(title);
    }

    //������ - ��ҳ��ת
    public void goToMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();

        //������ �һ��Ƴ�
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}

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

        //���ر�����
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main_activity);
    }

    //map����¼�
    public void goToMap(View v) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        MainActivity.this.startActivity(intent);
//        MainActivity.this.finish();
        //������ �һ��Ƴ�
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}

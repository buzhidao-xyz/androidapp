package xyz.buzhidao.androidapp;

import android.os.Bundle;
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

        setContentView(R.layout.main);

        //bdmap点击事件

    }

    //bdmap点击事件

}

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

        //Òþ²Ø±êÌâÀ¸
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main);
    }
}

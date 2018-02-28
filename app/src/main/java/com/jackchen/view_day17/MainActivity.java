package com.jackchen.view_day17;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置Activity全屏
        StatusBarUtils.setActivityTranslucent(this);
        // 给我们的 Activity 的状态栏设置颜色
        StatusBarUtils.setStatusBarColor(this, Color.RED);
    }
}

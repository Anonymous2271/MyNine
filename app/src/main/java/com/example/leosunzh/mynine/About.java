package com.example.leosunzh.mynine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by leosunzh on 2015/11/17.
 */
public class About extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("关于：");
    }

    /**
     * 关于活动启动方式
     * @param context
     */
    public static void actionStart(Context context){
        Intent intent = new Intent(context,About.class);
        context.startActivity(intent);
    }
}

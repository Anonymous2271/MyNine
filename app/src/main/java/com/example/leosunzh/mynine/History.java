package com.example.leosunzh.mynine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.io.File;
import java.io.FileInputStream;

/**
 * 历史记录界面
 * Created by leosunzh on 2015/11/17.
 */
public class History extends Activity implements View.OnClickListener{
    ListView list_old;
    Button clean_b;//清除历史按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *设置全屏
         */
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

        setContentView(R.layout.activity_history);
        setTitle("历史记录");

        list_old = (ListView) findViewById(R.id.list_old);
        clean_b = (Button) findViewById(R.id.clean_b);
        clean_b.setOnClickListener(this);
        readHistory();
    }

    /**
     * 从内部存储读取历史记录并显示在ListView
     */
    public void readHistory(){
        String content = "";
        String[] lines = {};
        try{
            FileInputStream fis = openFileInput("Nine_data.txt");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            content = new String(buffer);
            lines = content.split("\\r?\\n");//按空行符把字符串拆开
        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lines);
        list_old.setAdapter(adapter);
    }

    /**
     * 历史记录活动启动方式
     * @param context
     */
    public static void actionStart(Context context){
        Intent intent = new Intent(context,History.class);
        context.startActivity(intent);
    }

    /**
     * 清除所有历史记录
     */
    @Override
    public void onClick(final View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示：");
        dialog.setMessage("是否删除全部历史记录？");
        dialog.setCancelable(true);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               if (v.getId() == R.id.clean_b) {
                    File file = new File(getFilesDir(),"Nine_data.txt");
                    if(file.exists()) {
                        file.delete();
                    }
                    readHistory();//刷新界面
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //什么也不做
            }
        });
        dialog.show();

    }
}

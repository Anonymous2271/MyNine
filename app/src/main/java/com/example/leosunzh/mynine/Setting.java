//package com.example.leosunzh.mynine;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ListView;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by leosunzh on 2015/11/18.
// */
//public class Setting extends Activity {
//    public List<Option> optionList = new ArrayList<Option>();
//    public ListView list_setting;
//    static public OptionAdapter adapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        /**
//         *设置全屏
//         */
//        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        //获得当前窗体对象
//        Window window=Setting.this.getWindow();
//        //设置当前窗体为全屏显示
//        window.setFlags(flag, flag);
//
//        setContentView(R.layout.activity_setting);
//        this.initOption();
//        adapter = new OptionAdapter(Setting.this,R.layout.option_item,optionList);
//        list_setting = (ListView) findViewById(R.id.list_setting);
//        list_setting.setAdapter(adapter);
//    }
//
//    /**
//     * 设置活动启动方式
//     * @param context
//     */
//    public static void actionStart(Context context){
//        Intent intent = new Intent(context,Setting.class);
//        context.startActivity(intent);
//    }
//
//
//    /**
//     * 初始化选项
//     */
//    public  void initOption(){
////        Option zuni = new Option(" ",R.drawable.theme);
////        optionList.add(zuni);
////        Option hang = new Option("红",R.drawable.hong);
////        optionList.add(hang);
////        Option huang = new Option("黄",R.drawable.huang);
////        optionList.add(huang);
////        Option lan = new Option("蓝",R.drawable.lan);
////        optionList.add(lan);
////        Option lv = new Option("绿",R.drawable.lv);
////        optionList.add(lv);
////        Option an = new Option("暗",R.drawable.an);
////        optionList.add(an);
//        Option developer = new Option(" ",R.drawable.developer);
//        optionList.add(developer);
//        Option leosunzh = new Option(" ",R.drawable.leosunzh);
//        optionList.add(leosunzh);
//        Option Email = new Option(" ",R.drawable.email);
//        optionList.add(Email);
//        Option adress = new Option("leosunzh@outlook.com",R.drawable.kong);
//        optionList.add(adress);
//    }
//
//}

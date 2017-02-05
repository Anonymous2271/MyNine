package com.example.leosunzh.mynine;

import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

/**
 * 初始化一些个人设置信息
 * 初始化图片内容，界面背景等
 * 其他按钮的监听类
 * Created by leosunzh on 2015/11/18.
 */
public class Starter implements View.OnClickListener{
    Button start_b;//开始按钮，在主活动
    Button old_b;//历史记录按钮，在主活动


    MyNine myNine;
    ExChanger exChanger;

    /**
     * 构造方法
     * @param myNine
     */
    Starter(MyNine myNine, ExChanger exChanger){
        this.myNine = myNine;
        this.exChanger = exChanger;
        this.start_b = myNine.start_b;
        this.old_b = myNine.old_b;


        start_b.setOnClickListener(this);
        old_b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_b:
                initialize();
                myNine.chronometer.setBase(SystemClock.elapsedRealtime());myNine.chronometer.start();
                myNine.isFinishing();
                break;
            case R.id.old_b:History.actionStart(myNine);break;
            default:break;
        }
    }

    /**
     * 开始拼图游戏
     * 设置9个ImageView可见（原定义为不可见）
     * 设置他们所属的布局背景为白色（原定义为原图）
     */
    public void initialize(){
        myNine.im1.setOnClickListener(myNine);myNine.im2.setOnClickListener(myNine);myNine.im3.setOnClickListener(myNine);
        myNine.im4.setOnClickListener(myNine);myNine.im5.setOnClickListener(myNine);myNine.im6.setOnClickListener(myNine);
        myNine.im7.setOnClickListener(myNine);myNine.im8.setOnClickListener(myNine);myNine.im9.setOnClickListener(myNine);

        myNine.im1.setVisibility(View.VISIBLE); myNine.im2.setVisibility(View.VISIBLE); myNine.im3.setVisibility(View.VISIBLE);
        myNine.im4.setVisibility(View.VISIBLE); myNine.im5.setVisibility(View.VISIBLE); myNine.im6.setVisibility(View.VISIBLE);
        myNine.im7.setVisibility(View.VISIBLE); myNine.im8.setVisibility(View.VISIBLE); myNine.im9.setVisibility(View.VISIBLE);

        myNine.layout_nine.setBackgroundColor(myNine.layout_nine.getResources().getColor(R.color.baise));

        exChanger.toMass(); //打乱排列
        myNine.n = 0;    //步数归零
        myNine.steps.setText(Integer.toString(myNine.n));
        exChanger.isFinish(); //万一运气好呢
    }
}

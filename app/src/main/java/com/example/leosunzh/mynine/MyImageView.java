package com.example.leosunzh.mynine;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自定义ImageView,实现编号功能
 * Created by leosunzh on 2015/11/17.
 */
public class MyImageView extends ImageView{
    public int id_number;//图片编号，当编号与MyImageView都一一对应时，判定游戏完成！

    public MyImageView(Context context) {
        super(context);
    }
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getId_number() { // 获取id_number
        return id_number;
    }
    public void setId_number(int id_number) { //设置id_number
        this.id_number = id_number;
    }
}

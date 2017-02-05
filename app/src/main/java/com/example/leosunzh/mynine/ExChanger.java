package com.example.leosunzh.mynine;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by leosunzh on 2015/11/15.
 *包含一系列处理方法
 */
public class ExChanger {
    MyNine myNine;

    static int size; //ImageView的大小s

    static MyImageView blank; //指向空白格的指针
    static int x9;static int y9; //空白格的坐标

    static int [] location_white = new int[2];
    static int x;static int y; //当前点击的ImageView坐标

    /**
     * 构造方法
     * 监听im9绘制完成后初始化blank的坐标
     * 得到从MyNine传入的ImageView的大小size
     * * @param
     */
    ExChanger(int width,  final MyImageView blank, MyNine myNine){
        this.size = width;
        this.blank = blank;
        this.myNine = myNine;

        ViewTreeObserver vto = blank.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int[] location_blank = new int[2];
                blank.getLocationInWindow(location_blank);
                x9 = location_blank[0];
                y9 = location_blank[1];
                // 成功调用一次后，移除 Hook 方法，防止被反复调用
                // removeGlobalOnLayoutListener() 方法在 API 16 后不再使用
                // 使用新方法 removeOnGlobalLayoutListener() 代替
                blank.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    /**
     * isMove
     * 判断是否能移动并移动（交换图片）,若移动则返回true调用计数器和isFinish()
     */
    public static boolean isMove(MyImageView white){
        white.getLocationInWindow(location_white);
        x = location_white[0];y = location_white[1];

        if (x==x9&y-y9==size){  //被点击的ImageView在空白格正下方
            //上移
            Drawable temp = white.getDrawable();  //交换图片
            white.setImageDrawable(blank.getDrawable());
            blank.setImageDrawable(temp);
            int id_temp = white.getId_number();  //交换编号
            white.setId_number(blank.getId_number());
            blank.setId_number(id_temp);
            y9 += size;    //跟踪空白格坐标（其实空白格并没有移动，只是图片换了）
            blank = white; //指向空白格
            return true;
        }
        if(x9==x&y9-y==size){
            //下移
            Drawable temp = white.getDrawable();
            white.setImageDrawable(blank.getDrawable());
            blank.setImageDrawable(temp);
            int id_temp = white.getId_number();
            white.setId_number(blank.getId_number());
            blank.setId_number(id_temp);
            y9 -= size;
            blank = white;
            return true;
        }
        if(x-x9==size&y==y9){
            //左移
            Drawable temp = white.getDrawable();
            white.setImageDrawable(blank.getDrawable());
            blank.setImageDrawable(temp);
            int id_temp = white.getId_number();
            white.setId_number(blank.getId_number());
            blank.setId_number(id_temp);
            x9 += size;
            blank = white;
            return true;
        }
        if(x9-x==size&y9==y){
            //右移
            Drawable temp = white.getDrawable();
            white.setImageDrawable(blank.getDrawable());
            blank.setImageDrawable(temp);
            int id_temp = white.getId_number();
            white.setId_number(blank.getId_number());
            blank.setId_number(id_temp);
            x9 -= size;
            blank = white;
            return true;
        }
        return false;
    }

    /**
     * toMass
     * 打乱排列
     * 循环n次，每次产生一个随机数，调动任意一个MyImageView的isMove（）
     */
    public  void toMass(){

        for(int i=0;i<=1000;i++) {
            Random random = new Random();
            int r = random.nextInt(9);
            switch (r) {
                case 1:isMove(myNine.im1);break;
                case 2:isMove(myNine.im2);break;
                case 3:isMove(myNine.im3);break;
                case 4:isMove(myNine.im4);break;
                case 5:isMove(myNine.im5);break;
                case 6:isMove(myNine.im6);break;
                case 7:isMove(myNine.im7);break;
                case 8:isMove(myNine.im8);break;
                case 0:isMove(myNine.im9);break;
                default:break;
            }
        }
        isFinish();
    }


    /**
     * isFinish
     * 判定是否完成游戏，若完成则返回true调用Dialog对话框
     */
    public  boolean isFinish() {
        int bar = 0; //进度条的进度
        if (myNine.im1.getId_number() == 1) {
            bar++;
        }
        if (myNine.im2.getId_number() == 2) {
            bar++;
        }
        if (myNine.im3.getId_number() == 3) {
            bar++;
        }
        if (myNine.im4.getId_number() == 4) {
            bar++;
        }
        if (myNine.im5.getId_number() == 5) {
            bar++;
        }
        if (myNine.im6.getId_number() == 6) {
            bar++;
        }
        if (myNine.im7.getId_number() == 7) {
            bar++;
        }
        if (myNine.im8.getId_number() == 8) {
            bar++;
        }

        if (bar == 8) {
            myNine.progressBar.setProgress(bar);
            return true;
        } else {
            myNine.progressBar.setProgress(bar);
            return false;
        }
    }


}

/**
 *   if(mynine.im1.getId_number()==1
 &&mynine.im2.getId_number()==2
 &&mynine.im3.getId_number()==3
 &&mynine.im4.getId_number()==4
 &&mynine.im5.getId_number()==5
 &&mynine.im6.getId_number()==6
 &&mynine.im7.getId_number()==7
 &&mynine.im8.getId_number()==8
 &&mynine.im9.getId_number()==9){
 return true;
 }

 return false;
 }
 */

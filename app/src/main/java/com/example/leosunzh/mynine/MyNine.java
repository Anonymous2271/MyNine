package com.example.leosunzh.mynine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * 游戏UI
 * 包含UI一些参数的初始化
 */
public class MyNine extends Activity implements View.OnClickListener{
    int width;//屏幕的宽
    int n = 0;//计数器，累加步数，在text_step显示

    GridLayout layout_nine;//传给Starter换背景
    Button start_b;Button old_b;

    MyImageView im1; MyImageView im2; MyImageView im3; MyImageView im4;
    MyImageView im5; MyImageView im6; MyImageView im7; MyImageView im8;
    MyImageView im9;

    TextView steps;
    Chronometer chronometer;
    ProgressBar progressBar;

    ExChanger exChanger;
    Starter starter;

    /**
     * 构造方法
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        /**
         *设置全屏
         */
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=MyNine.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_my_nine);

        start_b = (Button) findViewById(R.id.start_b);
        old_b = (Button) findViewById(R.id.old_b);
        chronometer = (Chronometer) findViewById(R.id.timer);
        steps = (TextView) findViewById(R.id.text_step);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        im1 = (MyImageView) findViewById(R.id.im1);im2 = (MyImageView) findViewById(R.id.im2);
        im3 = (MyImageView) findViewById(R.id.im3);im4 = (MyImageView) findViewById(R.id.im4);
        im5 = (MyImageView) findViewById(R.id.im5);im6 = (MyImageView) findViewById(R.id.im6);
        im7 = (MyImageView) findViewById(R.id.im7);im8 = (MyImageView) findViewById(R.id.im8);
        im9 = (MyImageView) findViewById(R.id.im9);

        layout_nine = (GridLayout) findViewById(R.id.nine_grid);
        register(); //给图片编号，图片交换时，编号随之交换
        width = this.setSize(); //自定义的方法，获取width值，同时设置ImageView为正方形
        exChanger = new ExChanger(width,im9,this);
        starter = new Starter(this,this.exChanger);
    }

//    /**
//     * 菜单
//     */
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.nine_menu,menu);
//        return true;
//    }
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.setting_item:
//                Setting.actionStart(this);break;
//            default:
//        }
//        return true;
//    }

    /**
     * 初始化MyImageView的id_number属性
     * 依次设置为1-9
     */
    public void register(){
        im1.setId_number(1);
        im2.setId_number(2);
        im3.setId_number(3);
        im4.setId_number(4);
        im5.setId_number(5);
        im6.setId_number(6);
        im7.setId_number(7);
        im8.setId_number(8);
        im9.setId_number(9);
    }

    /**
     * setSize
     * 获取width值并返回，同时设置ImageView为正方形
     * @return
     */
    public int setSize(){
        /**
         * 获取屏幕宽度
         * 设置拼图高等于宽
         */
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        width = (int) (size.x/3);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        width = dm.widthPixels/3;

        im1.getLayoutParams().width=width;
        im1.getLayoutParams().height = width;
        im2.getLayoutParams().width=width;
        im2.getLayoutParams().height = width;
        im3.getLayoutParams().width=width;
        im3.getLayoutParams().height = width;
        im4.getLayoutParams().width=width;
        im4.getLayoutParams().height = width;
        im5.getLayoutParams().width=width;
        im5.getLayoutParams().height = width;
        im6.getLayoutParams().width=width;
        im6.getLayoutParams().height = width;
        im7.getLayoutParams().width=width;
        im7.getLayoutParams().height = width;
        im8.getLayoutParams().width=width;
        im8.getLayoutParams().height = width;
        im9.getLayoutParams().width=width;
        im9.getLayoutParams().height = width;

        return width;
    }

    /**
     * 点击拼图
     * @param v
     */
    @Override
    public void onClick(View v) {
        MyImageView view = (MyImageView)v;
        if(exChanger.isMove(view)){
            n++;
            steps.setText(Integer.toString(n));
            if(exChanger.isFinish()){
                showFinishDialog(n);
                writeHistory(n);//存储步数
            }
        }
    }

    /**
     *完成拼图对话框
     */
    public void showFinishDialog(int n){
        String miaoshu;
        switch (n/50){
            case 0:miaoshu = "登峰造极！";break;
            case 1:miaoshu = "还不错哦~";break;
            case 2:miaoshu = "还不错哦~";break;
            case 3:miaoshu = "还不错哦~";break;
            case 4:miaoshu = "还需努力呀！";break;
            case 5:miaoshu = "唉，怎么说你好...";break;
            case 6:miaoshu = "智商堪忧...";break;
            case 7:miaoshu = "智商堪忧...";break;
            case 8:miaoshu = "建议返校念书。";break;
            default:miaoshu = "智商鉴定非人类级别，已报警！";break;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示：");
        dialog.setMessage("游戏完成！您用了" + Integer.toString(n)+"步，"+ miaoshu);
        dialog.setCancelable(true);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.setNegativeButton("查看历史", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                History.actionStart(MyNine.this);
            }
        });
        dialog.show();
    }

    /**
     *把步数和时间写入内部存储
     * @param n
     */
    public void writeHistory(int n){
        //获取系统时间戳
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sDateFormat.format(new java.util.Date());

        String timeRecord = chronometer.getText().toString();

        String saveinfo = time +"         "+ Integer.toString(n) +"          "+timeRecord +"\n";
        FileOutputStream fos;
        try{
            fos = openFileOutput("Nine_data.txt", Context.MODE_APPEND);
            fos.write(saveinfo.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

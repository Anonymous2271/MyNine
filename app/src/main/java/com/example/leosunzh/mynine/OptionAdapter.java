//package com.example.leosunzh.mynine;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import java.util.List;
//
///**
// * 设置的选项条目适配器
// * Created by leosunzh on 2015/11/19.
// */
//public class OptionAdapter extends ArrayAdapter<Option>{
//    public int resourceId;
//    public OptionAdapter(Context context, int textViewResourceTd, List<Option> objects) {
//        super(context,textViewResourceTd,objects);
//        resourceId = textViewResourceTd;
//    }
//
//    public View getView(int position,View convertView,ViewGroup parent){
//        Option option = getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
//        ImageView option_image = (ImageView)view.findViewById(R.id.option_image);
//        TextView option_name = (TextView)view.findViewById(R.id.option_name);
//        option_name.setText(option.getName());
//        option_image.setImageResource(option.getImageId());
//        return view;
//    }
//}

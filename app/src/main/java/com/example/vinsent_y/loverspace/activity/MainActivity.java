package com.example.vinsent_y.loverspace.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinsent_y.loverspace.R;

/**
* FileName: MainActivity
* Author: Vinsent_Y
* Date: 2019/5/8
* Description: 主界面
*/


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_head;
    //TODO img_love类型 是SurfaceView吗？
    private Button btn_left_list;
    private TextView tv_love_val;
    private Button btn_right_moment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //TODO 监听器按钮注册
        }
    }
}

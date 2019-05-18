package com.example.vinsent_y.loverspace.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private Button btn_left_schedule;
    private TextView tv_love_val;
    private Button btn_right_moment;
    private ImageView iv_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        iv_setting = findViewById(R.id.iv_setting);
        btn_left_schedule = findViewById(R.id.btn_left_schedule);
        btn_right_moment = findViewById(R.id.btn_right_moment);
        btn_left_schedule.setOnClickListener(this);
        btn_right_moment.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_schedule:
                startActivity(new Intent(this,ScheduleActivity.class));
                break;
            case R.id.btn_right_moment:
                startActivity(new Intent(this,MomentActivity.class));
                break;
            case R.id.iv_setting:
                startActivity(new Intent(this,SettingActivity.class));
                break;
        }
    }
}

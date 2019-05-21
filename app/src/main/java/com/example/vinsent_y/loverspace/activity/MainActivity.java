package com.example.vinsent_y.loverspace.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.util.L;

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
    private SeekBar sb_control_view;
    private View fill_after_view;
    private View fill_before_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        sb_control_view.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                L.e(progress +"");
                runOnUiThread(() -> {
                    fill_after_view.setMinimumHeight(progress);
                    fill_before_view.setMinimumHeight(fill_before_view.getHeight()-progress);
                    L.e(fill_before_view.getHeight() + "");
                });

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initView() {
        iv_setting = findViewById(R.id.iv_setting);
        btn_left_schedule = findViewById(R.id.btn_left_schedule);
        btn_right_moment = findViewById(R.id.btn_right_moment);
        sb_control_view = findViewById(R.id.sb_control_view);
        fill_after_view = findViewById(R.id.fill_after_view);
        fill_before_view = findViewById(R.id.fill_before_view);
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

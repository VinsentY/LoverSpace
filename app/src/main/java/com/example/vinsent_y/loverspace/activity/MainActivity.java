package com.example.vinsent_y.loverspace.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vinsent_y.loverspace.R;

/**
* FileName: MainActivity
* Author: Vinsent_Y
* Date: 2019/5/8
* Description: 主界面
*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LottieAnimationView wave;
    private Button btn_left_schedule;
    private TextView tv_love_val;
    private Button btn_right_moment;
    private ImageView iv_setting;
    private SeekBar sb_control_view;
    private View bottom_fill_view;

    private int offset_px = 0;
    private float total_px = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    private void initData() {
        offset_px = getResources().getDimensionPixelSize(R.dimen.offset_height);
        total_px = getResources().getDimensionPixelSize(R.dimen.total_height);
    }

    private void initView() {
        iv_setting = findViewById(R.id.iv_setting);
        btn_left_schedule = findViewById(R.id.btn_left_schedule);
        btn_right_moment = findViewById(R.id.btn_right_moment);
        sb_control_view = findViewById(R.id.sb_control_view);
        bottom_fill_view = findViewById(R.id.bottom_fill_view);
        btn_left_schedule.setOnClickListener(this);
        btn_right_moment.setOnClickListener(this);
        iv_setting.setOnClickListener(this);
        wave = findViewById(R.id.wave);

        //TODO 动画适配
        wave.scrollBy(0,-offset_px);
        sb_control_view.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                L.e(progress +"");
                runOnUiThread(() -> {
                    wave.scrollTo(0,-offset_px + dealPx(progress));
                    ViewGroup.LayoutParams lp = bottom_fill_view.getLayoutParams();
                    lp.height = dealPx(progress);
                    bottom_fill_view.setLayoutParams(lp);
                });

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            private int dealPx(int progress) {
                return (int) (progress * 1.0 / 100 * total_px);
            }
        });
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

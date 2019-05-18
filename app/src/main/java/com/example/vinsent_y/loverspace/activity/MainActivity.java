package com.example.vinsent_y.loverspace.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.entity.Moment;

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
            case R.id.btn_left_list:
                startActivity(new Intent(this,ListActivity.class));
                break;
            case R.id.btn_right_moment:
                startActivity(new Intent(this,MomentActivity.class));
                break;
        }
    }
}

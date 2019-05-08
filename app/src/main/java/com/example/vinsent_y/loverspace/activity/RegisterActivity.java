package com.example.vinsent_y.loverspace.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.vinsent_y.loverspace.R;

/**
* FileName: RegisterActivity
* Author: Vinsent_Y
* Date: 2019/5/8
* Description: 注册流程界面
*/


public class RegisterActivity extends AppCompatActivity {


    private LinearLayout ll_user_info;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    /**
     * 检查注册信息合法性
     */
    public void check() {
        //TODO 检查注册信息合法性
    }

    /**
     * 提交注册表单
     */
    public void submit() {
        //TODO 提交注册表单
    }
}

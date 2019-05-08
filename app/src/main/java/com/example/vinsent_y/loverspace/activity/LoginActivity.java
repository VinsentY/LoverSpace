package com.example.vinsent_y.loverspace.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.vinsent_y.loverspace.R;

/**
* FileName: LoginActivity
* Author: Vinsent_Y
* Date: 2019/5/8
* Description: 登陆页面
*/


public class LoginActivity extends AppCompatActivity {
    
    private EditText edit_username;
    private EditText edit_password;
    private Button btn_submit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * 检查账号合法性
     */
    public void check() {
        //TODO 检查账号合法性
    }
}

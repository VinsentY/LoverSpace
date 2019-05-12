package com.example.vinsent_y.loverspace.activity.register;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.vinsent_y.loverspace.R;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
* FileName: PhoneNumberActivity
* Author: Vinsent_Y
* Date: 2019/5/8
* Description: 注册界面，输入用户手机号进行验证码验证
*/


public class PhoneNumberActivity extends AppCompatActivity {

    private ImageButton btn_back;

    //TODO EditText的UI怎么回事？
    private EditText edit_phone;
    private Button btn_submit;
    private EditText edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initView();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        edit_phone = findViewById(R.id.edit_phone);
        btn_submit = findViewById(R.id.btn_submit);
        edit_password = findViewById(R.id.edit_password);
    }

    public void sendSMS() {
        final String phoneNumber = edit_phone.getText().toString().trim();
        final String password = edit_password.getText().toString().trim();

        if (!TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(password) ) {
            if (password.length() > 6) {
                BmobSMS.requestSMSCode(phoneNumber, "", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId, BmobException e) {
                        if (e == null) {
                            Snackbar.make(btn_submit,"发送验证码成功，短信ID：" + smsId,BaseTransientBottomBar.LENGTH_LONG).show();
                            VerificationActivity.actionStart(PhoneNumberActivity.this, phoneNumber, password);
                        } else {
                            Snackbar.make(btn_submit,"发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage(),BaseTransientBottomBar.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                Snackbar.make(btn_submit,"输入密码不合法！",BaseTransientBottomBar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(btn_submit,"手机号或密码不能为空！",BaseTransientBottomBar.LENGTH_LONG).show();
        }

    }
}

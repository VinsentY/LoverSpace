package com.example.vinsent_y.loverspace.activity.register;

import android.content.Context;
import android.content.Intent;

import com.example.vinsent_y.loverspace.view.PhoneCode;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.entity.MyUser;

import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
* FileName: VerificationActivity
* Author: Vinsent_Y
* Date: 2019/5/8
* Description: 输入验证码页面
*/



public class VerificationActivity extends AppCompatActivity {

    private PhoneCode phoneCode;
    private Button btn_submit;
    private Button btn_resend;

    private String phoneNumber;
    private String password;

    public static void actionStart(Context context, String phoneNumber, String password) {
        Intent intent = new Intent(context, VerificationActivity.class);
        intent.putExtra("param1", phoneNumber);
        intent.putExtra("param2", password);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        //TODO 小键盘没有自动弹出，并且验证码为6位
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("param1");
        password = intent.getStringExtra("param2");

        initView();
        //注册事件回调（根据实际需要，可写，可不写）
        phoneCode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSuccess(String code) {
               checkLegal(code);
            }

            @Override
            public void onInput() {
            }
        });

        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_resend.setClickable(false);
                BmobSMS.requestSMSCode(phoneNumber, "", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId, BmobException e) {
                        if (e == null) {
                            Snackbar.make(btn_submit,"发送验证码成功，短信ID：" + smsId,BaseTransientBottomBar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(btn_submit,"发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage(),BaseTransientBottomBar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        btn_resend.setClickable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO 验证码倒计时 颜色变化
                int time = 60;
                while (time != 0) {
                    final int finalTime = time;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btn_resend.setText("获取验证码" + finalTime);
                        }
                    });
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        time--;
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                btn_resend.setClickable(true);
            }
        }).start();
    }

    private void initView() {
        phoneCode = findViewById(R.id.phoneCode);
        btn_submit = findViewById(R.id.btn_submit);
        btn_resend = findViewById(R.id.btn_resend);
    }

    public void checkLegal(String verificationCode) {
        String code = phoneCode.getPhoneCode();
        BmobSMS.verifySmsCode(phoneNumber, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Snackbar.make(btn_submit,"验证码验证成功，您可以在此时进行绑定操作！",BaseTransientBottomBar.LENGTH_LONG).show();

                    MyUser user = new MyUser();
                    user.setUsername(phoneNumber);
                    user.setMobilePhoneNumber(phoneNumber);
                    user.setMobilePhoneNumberVerified(true);
                    user.setPassword(password);

                    user.signUp(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if (e == null) {
                                Snackbar.make(btn_submit,"绑定手机号码成功", BaseTransientBottomBar.LENGTH_LONG).show();
                                startActivity(new Intent(VerificationActivity.this, UserInformationActivity.class));
                            } else {
                                Snackbar.make(btn_submit,"绑定手机号码失败：" + e.getErrorCode() + "-" + e.getMessage(),BaseTransientBottomBar.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Snackbar.make(btn_submit,"验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage(),BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
        });
    }

}

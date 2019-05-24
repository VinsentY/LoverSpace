package com.example.vinsent_y.loverspace.activity.register;

import android.content.Context;
import android.content.Intent;

import com.example.vinsent_y.loverspace.util.KeyboardHelper;
import com.example.vinsent_y.loverspace.view.PhoneCode;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

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
    private Button btn_resend;
    private ImageButton btn_back;

    private String phoneNumber;
    private String password;
    private EditText edit_input;
    private KeyboardHelper helper =  KeyboardHelper.getInstance();

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

        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("param1");
        password = intent.getStringExtra("param2");

        initView();
        helper.openKeyBoard(edit_input);
        //注册事件回调（根据实际需要，可写，可不写）
        phoneCode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSuccess(String code) {
                helper.hideKeyBoard(edit_input);
                checkLegal(code);
            }

            @Override
            public void onInput() {
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                            Snackbar.make(btn_resend,"发送验证码成功，短信ID：" + smsId,Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(btn_resend,"发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage(),Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        btn_resend.setClickable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn_resend.setText("获取验证码");
                    }
                });
                btn_resend.setClickable(true);
            }
        }).start();
    }

    private void initView() {
        phoneCode = findViewById(R.id.phoneCode);
        btn_resend = findViewById(R.id.btn_resend);
        edit_input = phoneCode.findViewById(R.id.edit_input);
        btn_back = findViewById(R.id.btn_back);
    }

    public void checkLegal(String verificationCode) {
        String code = phoneCode.getPhoneCode();
        BmobSMS.verifySmsCode(phoneNumber, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Snackbar.make(btn_resend,"绑定手机号码成功",Snackbar.LENGTH_LONG).show();

                    MyUser user = new MyUser();
                    user.setUsername(phoneNumber);
                    user.setMobilePhoneNumber(phoneNumber);
                    user.setMobilePhoneNumberVerified(true);
                    user.setPassword(password);

                    UserInformationActivity.actionStart(VerificationActivity.this, user);
                } else {
                    Snackbar.make(btn_resend,"验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage(),Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

}

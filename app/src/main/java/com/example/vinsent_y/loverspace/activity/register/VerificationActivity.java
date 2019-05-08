package com.example.vinsent_y.loverspace.activity.register;

import android.content.Context;
import android.content.Intent;

import com.example.vinsent_y.loverspace.view.PhoneCode;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.entity.MyUser;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
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

    private String phoneNumber;

    public static void actionStart(Context context, String phoneNumber) {
        Intent intent = new Intent(context, VerificationActivity.class);
        intent.putExtra("param1", phoneNumber);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        phoneNumber = getIntent().getStringExtra("param1");

        initView();

    }

    private void initView() {
//        edit_code_part_1 = findViewById(R.id.edit_code_part_1);
//        edit_code_part_2 = findViewById(R.id.edit_code_part_2);
//        edit_code_part_3 = findViewById(R.id.edit_code_part_3);
//        edit_code_part_4 = findViewById(R.id.edit_code_part_4);
    }

    public boolean checkLegal(String verificationCode) {
        //TODO 检测验证码是否正确
        String code = getCode();
        BmobSMS.verifySmsCode(phoneNumber, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Snackbar.make(btn_submit,"验证码验证成功，您可以在此时进行绑定操作！",BaseTransientBottomBar.LENGTH_LONG).show();

                    //TODO 之前还需要注册账户
                    MyUser user = BmobUser.getCurrentUser(MyUser.class);
                    user.setMobilePhoneNumber(phoneNumber);
                    user.setMobilePhoneNumberVerified(true);
                    user.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Snackbar.make(btn_submit,"绑定手机号码成功",BaseTransientBottomBar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(btn_submit,"绑定手机号码失败：" + e.getErrorCode() + "-" + e.getMessage(),BaseTransientBottomBar.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    //TODO 下一次验证时间间隔问题
                    Snackbar.make(btn_submit,"验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage(),BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
        });
        return false;
    }


    /**
     *
     * TODO 得到验证码
     */
    private String getCode() {
       return  null;
    }
}

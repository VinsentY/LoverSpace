package com.example.vinsent_y.loverspace.activity.register;

import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.activity.MainActivity;
import com.example.vinsent_y.loverspace.entity.MyUser;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
* FileName: UserInformationActivity
* Author: Vinsent_Y
* Date: 2019/5/8
* Description: 填写用户注册基本信息，如真实姓名，性别，生日，所在地等
*/


public class UserInformationActivity extends AppCompatActivity {

    /**
     * -edit_name
     * -radiogroup_gender
     * -calendar
     * -spinner_address
     */

    private EditText edit_real_name;
    private RadioGroup radioGroup_gender;
    //TODO 生日和地址使用什么控件获取

    private Button btn_submit;

    boolean isMale = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbt_boy) {
                    isMale = true;
                } else {
                    isMale = false;
                }
            }
        });
    }

    public void check() {
        //TODO 检测信息合法性并提交
        String real_name = edit_real_name.getText().toString();

        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        user.setMale(isMale);
        user.setReal_name(real_name);
        //....
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Snackbar.make(btn_submit,"绑定手机号码成功", BaseTransientBottomBar.LENGTH_LONG).show();
                    startActivity(new Intent(UserInformationActivity.this, MainActivity.class));
                } else {
                    Snackbar.make(btn_submit,"绑定手机号码成功", BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
        });
    }
}

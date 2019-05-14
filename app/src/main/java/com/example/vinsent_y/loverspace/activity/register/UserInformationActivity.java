package com.example.vinsent_y.loverspace.activity.register;

import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.activity.MainActivity;
import com.example.vinsent_y.loverspace.entity.MyUser;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

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
    //TODO RadioButton选中颜色有问题

    private EditText edit_real_name;
    private RadioGroup radioGroup_gender;
    private EditText spinner_address;
    private EditText calendar;
    //TODO 生日和地址使用什么控件获取（先暂时使用EditText

    private Button btn_submit;

    boolean isMale = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initView();
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

    private void initView() {
        edit_real_name = findViewById(R.id.edit_real_name);
        radioGroup_gender = findViewById(R.id.radioGroup_gender);
        btn_submit = findViewById(R.id.btn_submit);
        spinner_address = findViewById(R.id.spinner_address);
        calendar = findViewById(R.id.calendar);
    }

    public void check() {

        String real_name = edit_real_name.getText().toString();
        String address = spinner_address.getText().toString();
        String date = calendar.getText().toString();

        //TODO 检测信息是否需要依次监测
        if (!TextUtils.isEmpty(real_name) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(date)) {
            MyUser user = BmobUser.getCurrentUser(MyUser.class);
            user.setMale(isMale);
            user.setReal_name(real_name);
            user.setAddress(address);
            user.setBirthday(new Date());   //TODO 生日是Date类型
            user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Snackbar.make(btn_submit,"注册成功", BaseTransientBottomBar.LENGTH_LONG).show();
                        startActivity(new Intent(UserInformationActivity.this, MainActivity.class));
                    } else {
                        Snackbar.make(btn_submit,"注册失败", BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Snackbar.make(btn_submit,"信息不能为空", BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }
}

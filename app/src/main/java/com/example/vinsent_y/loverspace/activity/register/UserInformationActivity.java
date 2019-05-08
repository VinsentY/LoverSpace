package com.example.vinsent_y.loverspace.activity.register;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.vinsent_y.loverspace.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    public void check() {
        //TODO 检测信息合法性并提交
    }
}

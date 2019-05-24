package com.example.vinsent_y.loverspace.activity.register;

import androidx.appcompat.app.AppCompatActivity;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.activity.MainActivity;
import com.example.vinsent_y.loverspace.entity.MyUser;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: UserInformationActivity
 * Author: Vinsent_Y
 * Date: 2019/5/8
 * Description: 填写用户注册基本信息，如真实姓名，性别，生日，所在地等
 */


public class UserInformationActivity extends AppCompatActivity {

    private EditText edit_real_name;
    private RadioGroup radioGroup_gender;
    private EditText spinner_address;
    private TextView calendar;

    private Button btn_submit;

    private boolean isMale = false;
    private Date mDate;

    private TimePickerView pvTime;
    private MyUser user;

    public static void actionStart(Context context, MyUser user) {
        Intent intent = new Intent(context, UserInformationActivity.class);
        intent.putExtra("param1",user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        user = (MyUser) getIntent().getSerializableExtra("param1");

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

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });
    }


    private void initView() {
        edit_real_name = findViewById(R.id.edit_real_name);
        radioGroup_gender = findViewById(R.id.radioGroup_gender);
        btn_submit = findViewById(R.id.btn_submit);
        spinner_address = findViewById(R.id.spinner_address);
        calendar = findViewById(R.id.calendar);
        mDate = new Date();
        calendar.setText(new SimpleDateFormat("yyyy-MM-dd").format(mDate));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                calendar.setText(format.format(date));
                mDate = date;
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .isCenterLabel(false)
                .build();
    }

    public void check() {
        String real_name = edit_real_name.getText().toString();
        String address = spinner_address.getText().toString();

        //TODO 检测信息是否需要依次监测
        if (!TextUtils.isEmpty(real_name) && !TextUtils.isEmpty(address)) {

            user.setMale(isMale);
            user.setReal_name(real_name);
            user.setAddress(address);
            user.setBirthday(mDate);
            user.signUp(new SaveListener<MyUser>() {
                @Override
                public void done(MyUser user, BmobException e) {
                    if (e == null) {
                        Snackbar.make(btn_submit, "注册成功", Snackbar.LENGTH_LONG).show();
                        startActivity(new Intent(UserInformationActivity.this, MainActivity.class));
                    } else {
                        Snackbar.make(btn_submit, "注册失败", Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Snackbar.make(btn_submit, "信息不能为空", Snackbar.LENGTH_LONG).show();
        }
    }
}

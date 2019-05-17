package com.example.vinsent_y.loverspace.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.example.vinsent_y.loverspace.R;
import com.example.vinsent_y.loverspace.entity.MyUser;
import com.example.vinsent_y.loverspace.view.NbButton;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
* FileName: LoginActivity
* Author: Vinsent_Y
* Date: 2019/5/8
* Description: 登陆页面
*/


public class LoginActivity extends AppCompatActivity {

    private EditText edit_username;
    private EditText edit_password;
    private RelativeLayout rl_content;
    private LoadingButton btn_submit;
    private Handler handler;
    private Animator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BmobUser.logOut(); //清空应用缓存

        //获取缓存的用户信息，缓存的有效期为1年。
        if (BmobUser.isLogin()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        setContentView(R.layout.activity_login);

        initView();

        handler = new Handler();
        btn_submit.setOnClickListener(v -> {
                check();
            //TODO 动画关闭
//            btn_submit.startAnim();
//            handler.postDelayed(this::gotoNew,0);
        });

    }

    private void initView() {
        //TODO 界面编写
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        btn_submit = findViewById(R.id.btn_submit);
    }

    /**
     * 检查账号合法性
     */
    public void check() {
        //TODO 检查账号合法性
        String username = edit_username.getText().toString().trim();
        String password = edit_password.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            BmobUser.loginByAccount(username, password, new LogInListener<MyUser>() {
                @Override
                public void done(MyUser user, BmobException e) {
                    if (e == null) {
//                        User user = BmobUser.getCurrentUser(User.class); 获取当前用户实例的方法
                        //TODO Snackbar 参数view
                        Snackbar.make(LoginActivity.this.btn_submit, "登录成功：" + user.getUsername(), Snackbar.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Snackbar.make(LoginActivity.this.btn_submit, "登录失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            //TODO 关于字符串资源问题
            Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
        }
    }
}

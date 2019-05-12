package com.example.vinsent_y.loverspace.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vinsent_y.loverspace.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 类：PhoneCode
 * 作者： qxc
 * 日期：2018/3/14.
 */
public class PhoneCode extends RelativeLayout {
    private Context context;

    public static final int NUM_OF_CODE = 6;

    private TextView tv_code1;
    private TextView tv_code2;
    private TextView tv_code3;
    private TextView tv_code4;
    private TextView tv_code5;
    private TextView tv_code6;

    private View v1;
    private View v2;
    private View v3;
    private View v4;
    private View v5;
    private View v6;
    private EditText edit_input;
    private List<String> codes = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();
    private List<View> views = new ArrayList<>();
    private InputMethodManager imm;

    public PhoneCode(Context context) {
        super(context);
        this.context = context;
        loadView();
    }

    public PhoneCode(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        loadView();
    }

    private void loadView() {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.phone_code, this);
        initView(view);
        initEvent();
    }

    private void initView(View view) {
        tv_code1 = view.findViewById(R.id.tv_code1);
        tv_code2 = view.findViewById(R.id.tv_code2);
        tv_code3 = view.findViewById(R.id.tv_code3);
        tv_code4 = view.findViewById(R.id.tv_code4);
        tv_code5 = view.findViewById(R.id.tv_code5);
        tv_code6 = view.findViewById(R.id.tv_code6);
        textViews.add(tv_code1);
        textViews.add(tv_code2);
        textViews.add(tv_code3);
        textViews.add(tv_code4);
        textViews.add(tv_code5);
        textViews.add(tv_code6);


        edit_input = view.findViewById(R.id.edit_input);
        v1 = view.findViewById(R.id.v1);
        v2 = view.findViewById(R.id.v2);
        v3 = view.findViewById(R.id.v3);
        v4 = view.findViewById(R.id.v4);
        v5 = view.findViewById(R.id.v5);
        v6 = view.findViewById(R.id.v6);
        views.add(v1);
        views.add(v2);
        views.add(v3);
        views.add(v4);
        views.add(v5);
        views.add(v6);
    }

    private void initEvent() {
        //验证码输入
        edit_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    //TODO 可能出现Bug
                    edit_input.setText("");
                    if (codes.size() < NUM_OF_CODE) {
                        codes.add(editable.toString());
                        showCode();
                    }
                }
            }
        });
        // 监听验证码删除按键
        edit_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN && codes.size() > 0) {
                    codes.remove(codes.size() - 1);
                    showCode();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 显示输入的验证码
     */
    private void showCode() {
        for(int i = 0; i < NUM_OF_CODE; i++) {
            if (i < codes.size()) {
                textViews.get(i).setText(codes.get(i));
            } else {
                textViews.get(i).setText("");
            }
        }
        setColor();//设置高亮颜色
        callBack();//回调
    }

    /**
     * 设置高亮颜色
     */
    private void setColor() {
        int color_default = Color.parseColor("#999999");
        int color_focus = Color.parseColor("#3F8EED");
        for(int i = 0; i < NUM_OF_CODE; i++) {
            views.get(i).setBackgroundColor(color_default);
        }
        for(int i = 0; i < NUM_OF_CODE; i++) {
            if (codes.size() == NUM_OF_CODE) {
                break;
            } else {
                views.get(i).setBackgroundColor(color_focus);
            }
        }
    }

    /**
     * 回调
     */
    private void callBack() {
        if (onInputListener == null) {
            return;
        }
        if (codes.size() == NUM_OF_CODE) {
            onInputListener.onSuccess(getPhoneCode());
        } else {
            onInputListener.onInput();
        }
    }

    //定义回调
    public interface OnInputListener {
        void onSuccess(String code);

        void onInput();
    }

    private OnInputListener onInputListener;

    public void setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
    }

    /**
     * 显示键盘
     */
    public void showSoftInput() {
        //显示软键盘
        if (imm != null && edit_input != null) {
            edit_input.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imm.showSoftInput(edit_input, 0);
                }
            }, 200);
        }
    }

    /**
     * 获得手机号验证码
     *
     * @return 验证码
     */
    public String getPhoneCode() {
        StringBuilder sb = new StringBuilder();
        for (String code : codes) {
            sb.append(code);
        }
        return sb.toString();
    }
}
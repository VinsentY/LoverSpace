package com.example.vinsent_y.loverspace.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.vinsent_y.loverspace.application.BaseApplication;

import java.util.Timer;
import java.util.TimerTask;

public class KeyboardHelper {

    static KeyboardHelper instance;

    private KeyboardHelper() {
        // construct
    }

    public static KeyboardHelper getInstance() {
        if (instance == null) {
            instance = new KeyboardHelper();
        }
        return instance;
    }

    /**
     * 隐藏软键盘
     *
     * @param editText
     */
    public void hideKeyBoard(EditText editText) {


        InputMethodManager imm =
                (InputMethodManager)
                        BaseApplication.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 弹起软键盘
     *
     * @param editText
     */
    public void openKeyBoard(final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override

            public void run() {
                InputMethodManager imm =
                        (InputMethodManager)
                                BaseApplication.getApplication()
                                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, 0);
                editText.setSelection(editText.getText().length());
            }
        }, 200);


    }
}
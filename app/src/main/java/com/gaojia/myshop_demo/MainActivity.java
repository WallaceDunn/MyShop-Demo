package com.gaojia.myshop_demo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.gaojia.myshop_demo.base.BaseActivity;
import com.gaojia.myshop_demo.user.LoginFragment;
import com.gaojia.myshop_demo.user.RegisterFragment;
import com.gaojia.myshop_demo.base.ShowWhatFragmentListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EMOptions options = new EMOptions();
        //是否自动登录
        options.setAutoLogin(false);
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(true);
//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }


}

package com.gaojia.myshop_demo.goodsload;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.gaojia.myshop_demo.R;
import com.gaojia.myshop_demo.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class GoodsLoadActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsload);
        ButterKnife.bind(this);
    }
}

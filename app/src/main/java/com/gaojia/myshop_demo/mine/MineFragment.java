package com.gaojia.myshop_demo.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaojia.myshop_demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class MineFragment extends Fragment{
    @BindView(R.id.text_service)
    TextView service_tv;
    @Nullable
    public static MineFragment newInstance(){
        return new MineFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_mine,container,false);
        ButterKnife.bind(this,view);
        return view;

    }
    @OnClick(R.id.text_service)
    public void OnClick(View view ){
        switch (view.getId()){
            case R.id.text_service:
                break;
        }
    }
}

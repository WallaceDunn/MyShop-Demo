package com.gaojia.myshop_demo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.gaojia.myshop_demo.base.BaseActivity;
import com.gaojia.myshop_demo.base.TestFragment;
import com.gaojia.myshop_demo.category.CategoryFragment;
import com.gaojia.myshop_demo.find.FindFragment;
import com.gaojia.myshop_demo.home.HomeFragment;
import com.gaojia.myshop_demo.mine.MineFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements OnTabSelectListener{
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    private MineFragment mMineFragment;
    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private FindFragment mFindFragment;
    private Fragment mCurrentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        // 可以看一下Fragmentmanager里面是不是已经有了这些Fragment
        retrieveFragment();

        // alt+enter
        // 设置导航选择的监听事件
        mBottomBar.setOnTabSelectListener(this);
    }

    private void retrieveFragment() {
        FragmentManager manager = getSupportFragmentManager();
        mMineFragment = (MineFragment) manager.findFragmentByTag(MineFragment.class.getName());
        mHomeFragment = (HomeFragment) manager.findFragmentByTag(HomeFragment.class.getName());
        mCategoryFragment = (CategoryFragment) manager.findFragmentByTag(CategoryFragment.class.getName());
        mFindFragment = (FindFragment) manager.findFragmentByTag(FindFragment.class.getName());
    }

    private void switchfragment(Fragment target) {
        // add show hide的方式

        if (mCurrentFragment==target) return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mCurrentFragment!=null){
            transaction.hide(mCurrentFragment);
        }
        if (target.isAdded()){
            // 如果已经添加到FragmentManager里面，就展示
            transaction.show(target);
        }else {
            // 为了方便找到Fragment，我们是可以设置Tag
            String tag;
            if (target instanceof TestFragment){
                tag = ((TestFragment)target).getArgumentText();
            }else {

                // 把类名作为tag
                tag = target.getClass().getName();
            }

            // 添加Fragment并设置Tag
            transaction.add(R.id.layout_container,target,tag);
        }

        transaction.commit();
        mCurrentFragment=target;
    }


    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId){
            case R.id.tab_home:
                if(mHomeFragment==null){
                    mHomeFragment = HomeFragment.newInstance();
                }
                switchfragment(mHomeFragment);
                break;
            case R.id.tab_mine:
                if (mMineFragment==null){
                    mMineFragment = MineFragment.newInstance();
                }
                // 切换Fragment
                switchfragment(mMineFragment);
                break;
            case R.id.tab_category:
                if(mCategoryFragment==null){
                    mCategoryFragment = CategoryFragment.newInstance();
                }
                switchfragment(mCategoryFragment);
                break;
            case R.id.tab_find:
                if(mFindFragment==null){
                    mFindFragment = FindFragment.newInstance();
                }
                switchfragment(mFindFragment);
                break;
        }
    }
}

package com.smarter.LoveLog.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.smarter.LoveLog.R;
import com.smarter.LoveLog.fragment.SelfFragment;
import com.smarter.LoveLog.fragment.YwFragment;
import com.smarter.LoveLog.fragment.hqFragment;
import com.smarter.LoveLog.fragment.HomeFragment;

/**
 * Created by Administrator on 2015/11/30.
 */
public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {
    Activity mActivity;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ImageView imgflash, imghq, imgself, imgjw;
    private LinearLayout main_ll_flash, main_ll_yw, main_ll_hq,main_ll_self, main_zt_color;
    private int cuntenpage = 0;
    Fragment  fragment_flash_main,fragment_jw,fragment_kxthq,fragment_self;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        mActivity=this;
        setContentView(R.layout.activity_main);

        init();
        setListen();
        setTabSelection(0);
    }

    private void setListen() {
        main_ll_flash.setOnClickListener(this);
        main_ll_hq.setOnClickListener(this);
        main_ll_self.setOnClickListener(this);
        main_ll_yw.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void init() {
        fragmentManager = getSupportFragmentManager();
        main_zt_color = (LinearLayout) findViewById(R.id.main_zt_color);
        main_ll_flash = (LinearLayout) findViewById(R.id.mian_ll_flash);
        main_ll_hq = (LinearLayout) findViewById(R.id.mian_ll_hq);
        main_ll_self = (LinearLayout) findViewById(R.id.mian_ll_self);
        main_ll_yw = (LinearLayout) findViewById(R.id.mian_ll_yw);
        imgflash = (ImageView) findViewById(R.id.imgflash);
        imghq = (ImageView) findViewById(R.id.imghq);
        imgself = (ImageView) findViewById(R.id.imgself);
        imgjw = (ImageView) findViewById(R.id.imgjw);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mian_ll_flash:
                setTabSelection(0);
                break;
            case R.id.mian_ll_yw:
                setTabSelection(1);
                break;
            case R.id.mian_ll_hq:
                setTabSelection(2);
                break;
            case R.id.mian_ll_self:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }
    private void setTabSelection(int index) {
        // 重置按钮
        resetBtn();
        // 开启一个Fragment事务
        transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                imgflash.setSelected(true);
                cuntenpage = 0;
                if (fragment_flash_main == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragment_flash_main = new HomeFragment();
                    transaction.add(R.id.frame_content, fragment_flash_main);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment_flash_main);
                }
                break;
            case 1:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                imgjw.setSelected(true);
                cuntenpage = 1;
                if (fragment_jw == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragment_jw = new YwFragment();
                    transaction.add(R.id.frame_content, fragment_jw);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment_jw);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                imghq.setSelected(true);
                cuntenpage = 2;
                if (fragment_kxthq == null) {
                    fragment_kxthq = new hqFragment();
                    transaction.add(R.id.frame_content, fragment_kxthq);
                } else {
                    transaction.show(fragment_kxthq);
                }

                break;

            case 3:
                imgself.setSelected(true);
                cuntenpage = 3;
                if (fragment_self == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    fragment_self = new SelfFragment();
                    transaction.add(R.id.frame_content, fragment_self);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(fragment_self);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (fragment_flash_main != null) {
            transaction.hide(fragment_flash_main);
        }
        if (fragment_kxthq != null) {
            transaction.hide(fragment_kxthq);
        }
        if (fragment_jw != null) {
            transaction.hide(fragment_jw);
        }
        if (fragment_self != null) {
            transaction.hide(fragment_self);
        }

    }
    private void resetBtn() {
        imgflash.setSelected(false);
        imghq.setSelected(false);
        imgself.setSelected(false);
        imgjw.setSelected(false);
    }
}

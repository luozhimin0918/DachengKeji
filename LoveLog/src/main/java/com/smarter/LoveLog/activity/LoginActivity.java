package com.smarter.LoveLog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smarter.LoveLog.R;
import com.smarter.LoveLog.ui.popwindow.BabyPopWindow;
import com.smarter.LoveLog.ui.productViewPager.AutoLoopViewPager;
import com.smarter.LoveLog.ui.productViewPager.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class LoginActivity extends BaseFragmentActivity implements View.OnClickListener{

    /*@Bind(R.id.pager)
    AutoLoopViewPager pager;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_view);
        ButterKnife.bind(this);
        getDataIntent();

        intData();
        setListen();

    }

    private void setListen() {
    }

    private void intData() {

    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if(intent!=null){
            String  str = intent.getStringExtra("ObjectData");
            Toast.makeText(this,str+"",Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public void onClick(View v) {
         switch (v.getId()){
           /*  case R.id.buy_now:
                 break;*/

         }
    }



}

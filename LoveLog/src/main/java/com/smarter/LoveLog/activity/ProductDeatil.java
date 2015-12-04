package com.smarter.LoveLog.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.smarter.LoveLog.R;

/**
 * Created by Administrator on 2015/11/30.
 */
public class ProductDeatil extends BaseFragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_deatil);
       getDataIntent();

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

    }
}

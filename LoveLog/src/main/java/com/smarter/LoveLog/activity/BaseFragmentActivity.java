package com.smarter.LoveLog.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.smarter.LoveLog.R;
import com.smarter.LoveLog.common.AppManager;

/**
 * Created by Administrator on 2015/11/30.
 */
public class BaseFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                                                                  //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);

        // 透明状态栏
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        this.setTheme(R.style.BrowserThemeDefault);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }


}

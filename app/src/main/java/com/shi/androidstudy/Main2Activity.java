package com.shi.androidstudy;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shi.manager.OnRetryListener;

public class Main2Activity extends MyBaseActivity {

    @Override
    public void initView() {
        builder.setContentLayoutResId(R.layout.activity_main2)
                .setOnRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        slManager.showLoading();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                SystemClock.sleep(2000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        slManager.showContent();
                                    }
                                });
                            }
                        }).start();
                    }
                });
        slManager = builder.create();
        linearLayout_main.addView(slManager.getRootLayout(),1);
    }

    @Override
    public void initData() {

    }

}

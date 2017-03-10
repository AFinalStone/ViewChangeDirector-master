package com.shi.androidstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Jack Wang on 2016/8/5.
 */

public class IndicatorActivity extends MyBaseActivity{

    private AVLoadingIndicatorView avi;

    @Override
    public View initContentView() {
        return View.inflate(mContext, R.layout.activity_indicator, null);
    }

    @Override
    public void initData() {
        String indicator=getIntent().getStringExtra("indicator");
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);
    }

    public void hideClick(View view) {
         avi.smoothToHide();
//        or avi.hide();
    }

    public void showClick(View view) {
        avi.smoothToShow();
        // or avi.show();
    }
}

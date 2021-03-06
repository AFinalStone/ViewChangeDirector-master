package com.shi.androidstudy;

import android.content.Intent;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.shi.manager.OnRetryListener;

public class MainActivity extends MyBaseActivity {

    @Override
    public View initContentView() {
       setReturnStatus(true);
       return  View.inflate(mContext, R.layout.activity_main, null);
    }

    @Override
    public void initData() {

        slManager.showLoading();
        linearLayout_main.postDelayed(new Runnable() {
            @Override
            public void run() {
                slManager.showContent();
            }
        },1000);
    }

    public void retryGetData(){
        super.retryGetData();
        linearLayout_main.postDelayed(new Runnable() {
            @Override
            public void run() {
                slManager.showContent();
            }
        },1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_contents:
                slManager.showContent();
                break;
            case R.id.action_loading:
                slManager.showLoading();
                break;
            case R.id.action_error:
                slManager.showError();
                break;
            case R.id.action_networkError:
                slManager.showNetWorkError();
                break;
            case R.id.action_emptyData:
                slManager.showEmptyData();
                break;
            case R.id.action_startActivity:
                startActivity(new Intent(this,RecyclerViewActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.shi.androidstudy;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shi.manager.OnRetryListener;
import com.shi.manager.StatesLayoutManager;

public class MainActivity extends MyBaseActivity {

    @Override
    public void initView() {

        builder.setContentLayoutResId(R.layout.activity_main)
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
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item)  {
//                //在这里执行我们的逻辑代码
//                switch (item.getItemId()){
//                    case R.id.action_contents:
//                        slManager.showContent();
//                        break;
//                    case R.id.action_loading:
//                        slManager.showLoading();
//                        break;
//                    case R.id.action_error:
//                        slManager.showError();
//                        break;
//                    case R.id.action_networkError:
//                        slManager.showNetWorkError();
//                        break;
//                    case R.id.action_emptyData:
//                        slManager.showEmptyData();
//                        break;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public void initData() {

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
                startActivity(new Intent(this,Main2Activity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

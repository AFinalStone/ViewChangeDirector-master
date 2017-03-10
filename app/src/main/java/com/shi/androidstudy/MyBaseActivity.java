package com.shi.androidstudy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shi.manager.OnRetryListener;
import com.shi.manager.StateLayoutManagerBuilder;
import com.shi.manager.StatesLayoutManager;


/***
 * @author SHI
 *         所有Activity的父类
 *         2016-2-1 11:41:42
 */
public abstract class MyBaseActivity extends AppCompatActivity {
    /**
     * 当前Activity对象
     **/
    public FragmentActivity mContext = this;
    /**
     * 当前设备宽度
     **/
    public int displayDeviceWidth;
    /**
     * 当前设备高度
     **/
    public int displayDeviceHeight;
    /**
     * 返回键状态标记
     **/
    private boolean returnStatus = false;

    /**
     * 初始化布局文件
     **/
    public abstract View initContentView();

    /**
     * 初始化页面数据
     **/
    public abstract void initData();

    public void retryGetData(){
        slManager.showLoading();
    }


    protected long previewBackTime;

    protected StateLayoutManagerBuilder builder;
    protected LinearLayout linearLayout_main;
    protected StatesLayoutManager slManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linearLayout_main = (LinearLayout) findViewById(R.id.linearLayout_main);
        displayDeviceWidth = getResources().getDisplayMetrics().widthPixels;
        displayDeviceHeight = getResources().getDisplayMetrics().heightPixels;
        builder = new StateLayoutManagerBuilder(this)
                .setContentView(initContentView())
                .setLoadingLayoutResId(R.layout.activity_loading)
                .setContentEmptyLayoutResId(R.layout.activity_emptydata)
                .setContentErrorResId(R.layout.activity_error)
                .setNetWorkErrorLayoutResId(R.layout.activity_networkerror)
                .setRetryViewId(R.id.button_try)
                .setOnRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        retryGetData();
                    }
                });
        slManager = builder.create();
        linearLayout_main.addView(slManager.getRootLayout(),1);
        initData();
    }

    /**
     * 设置返回键状态
     **/
    protected void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }


	//关闭当前Activity时
	@Override
	public void onBackPressed() {
		if(returnStatus){
            long currentTime = System.currentTimeMillis();
           if(previewBackTime != 0 && currentTime - previewBackTime < 300){
               finish();
           }else{
               previewBackTime = currentTime;
               Toast.makeText(mContext,"再次返回退出软件",Toast.LENGTH_SHORT).show();
           }
		}else{
			super.onBackPressed();
		}
	}


    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


}

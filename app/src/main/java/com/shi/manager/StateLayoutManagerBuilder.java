package com.shi.manager;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.ViewStub;

/**
 * Created by SHI on 2017/3/9 16:11
 */
public class StateLayoutManagerBuilder {

     Context context;
    //不同情况的页面内容资源ID和ViewStub
     int loadingLayoutResId;
     int contentLayoutResId;
     ViewStub netWorkErrorVs;
     ViewStub emptyDataVs;
     ViewStub errorVs;
     //不同情况重试控件资源ID
     int netWorkErrorRetryViewId;
     int emptyDataRetryViewId;
     int errorRetryViewId;
     int retryViewId;

     OnRetryListener onRetryListener;

    public StateLayoutManagerBuilder(Context context) {
        this.context = context;
    }

    //加载页面资源ID
    public StateLayoutManagerBuilder setLoadingLayoutResId(@LayoutRes int loadingLayoutResId) {
        this.loadingLayoutResId = loadingLayoutResId;
        return this;
    }

    //正文内容资源ID
    public StateLayoutManagerBuilder setContentLayoutResId(@LayoutRes int contentLayoutResId) {
        this.contentLayoutResId = contentLayoutResId;
        return this;
    }

    //网络错误
    public StateLayoutManagerBuilder setNetWorkErrorLayoutResId(@LayoutRes int newWorkErrorId) {
        netWorkErrorVs = new ViewStub(context);
        netWorkErrorVs.setLayoutResource(newWorkErrorId);
        return this;
    }

    //页面内容为空
    public StateLayoutManagerBuilder setContentEmptyLayoutResId(@LayoutRes int noDataViewId) {
        emptyDataVs = new ViewStub(context);
        emptyDataVs.setLayoutResource(noDataViewId);
        return this;
    }


    //页面错误
    public StateLayoutManagerBuilder setContentErrorResId(@LayoutRes int errorViewId) {
        errorVs = new ViewStub(context);
        errorVs.setLayoutResource(errorViewId);
        return this;
    }

    //重试按钮ID
    public StateLayoutManagerBuilder setRetryViewId(@IdRes int retryViewId) {
        this.retryViewId = retryViewId;
        return this;
    }

    //网络异常重试按钮ID
    public StateLayoutManagerBuilder setNetWorkErrorRetryViewId(@IdRes int netWorkErrorRetryViewId) {
        this.netWorkErrorRetryViewId = netWorkErrorRetryViewId;
        return this;
    }

    //数据为空重试按钮ID
    public StateLayoutManagerBuilder setEmptyDataRetryViewId(@IdRes int emptyDataRetryViewId) {
        this.emptyDataRetryViewId = emptyDataRetryViewId;
        return this;
    }

    //页面错误重试按钮ID
    public StateLayoutManagerBuilder setErrorRetryViewId(@IdRes int errorRetryViewId) {
        this.errorRetryViewId = errorRetryViewId;
        return this;
    }

    public StateLayoutManagerBuilder setOnRetryListener(OnRetryListener onRetryListener) {
        this.onRetryListener = onRetryListener;
        return this;
    }

    public  StatesLayoutManager create(){
        return  new StatesLayoutManager(this);
    }
}

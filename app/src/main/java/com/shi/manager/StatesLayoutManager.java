package com.shi.manager;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

/**
 * Created by SHI on 2017/3/9 15:48
 * 这个对象既是建造模式管理者，又是建造模式要组合生成的最终对象
 */
public class StatesLayoutManager {

    final View contentView;
    //不同情况的页面内容资源ID和ViewStub
    final int loadingLayoutResId;
    final ViewStub netWorkErrorVs;
    final int netWorkErrorRetryViewId;
    final ViewStub emptyDataVs;
    final int emptyDataRetryViewId;
    final ViewStub errorVs;
    final int errorRetryViewId;
    final int retryViewId;
    final OnRetryListener onRetryListener;

    final RootFrameLayout rootFrameLayout;

    public StatesLayoutManager(StateLayoutManagerBuilder builder) {

        this.loadingLayoutResId = builder.loadingLayoutResId;
        this.contentView = builder.contentView;
        this.netWorkErrorVs = builder.netWorkErrorVs;
        this.netWorkErrorRetryViewId = builder.netWorkErrorRetryViewId;
        this.emptyDataVs = builder.emptyDataVs;
        this.emptyDataRetryViewId = builder.emptyDataRetryViewId;
        this.errorVs = builder.errorVs;
        this.errorRetryViewId = builder.errorRetryViewId;
        this.retryViewId = builder.retryViewId;
        onRetryListener = builder.onRetryListener;


        rootFrameLayout = new RootFrameLayout(builder.context);
        rootFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootFrameLayout.setStatusLayoutManager(this);
    }

    /**
     *  显示loading
     */
    public void showLoading() {
        rootFrameLayout.showLoading();
    }

    /**
     *  显示内容
     */
    public void showContent() {
        rootFrameLayout.showContent();
    }

    /**
     *  显示空数据
     */
    public void showEmptyData() {
        rootFrameLayout.showEmptyData();
    }

    /**
     *  显示网络异常
     */
    public void showNetWorkError() {
        rootFrameLayout.showNetWorkError();
    }

    /**
     *  显示异常
     */
    public void showError() {
        rootFrameLayout.showError();
    }

    /**
     *  得到root 布局
     */
    public View getRootLayout() {
        return rootFrameLayout;
    }
}

package com.shi.manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by SHI on 2016/12/15.
 */
public class RootFrameLayout extends FrameLayout {

    /**
     *  loading 加载id
     */
    public static final int LAYOUT_LOADING_ID = 1;

    /**
     *  内容id
     */
    public static final int LAYOUT_CONTENT_ID = 2;

    /**
     *  异常id
     */
    public static final int LAYOUT_ERROR_ID = 3;

    /**
     *  网络异常id
     */
    public static final int LAYOUT_NETWORK_ERROR_ID = 4;

    /**
     *  空数据id
     */
    public static final int LAYOUT_EMPTYDATA_ID = 5;

    /**
     *  存放布局集合
     */
    private SparseArray<View> layoutSparseArray = new SparseArray();

    /**
     *  布局管理器
     */
    private StatesLayoutManager mStatesLayoutManager;


    public RootFrameLayout(Context context) {
        super(context);
    }

    public RootFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public RootFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RootFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setStatusLayoutManager(StatesLayoutManager statesLayoutManager) {
        mStatesLayoutManager = statesLayoutManager;
        addAllLayoutToLayout();
    }

    public void addAllLayoutToLayout() {
        if(mStatesLayoutManager.contentView != null)
        {
            layoutSparseArray.put(RootFrameLayout.LAYOUT_CONTENT_ID, mStatesLayoutManager.contentView);
            addView(mStatesLayoutManager.contentView);
        }
        if(mStatesLayoutManager.loadingLayoutResId != 0){

            View resView = LayoutInflater.from(getContext()).inflate(mStatesLayoutManager.loadingLayoutResId, null);
            layoutSparseArray.put(RootFrameLayout.LAYOUT_LOADING_ID, resView);
            resView.setVisibility(GONE);
            addView(resView);
        }
        if(mStatesLayoutManager.emptyDataVs != null)
            addView(mStatesLayoutManager.emptyDataVs);
        if(mStatesLayoutManager.errorVs != null)
            addView(mStatesLayoutManager.errorVs);
        if(mStatesLayoutManager.netWorkErrorVs != null)
            addView(mStatesLayoutManager.netWorkErrorVs);
    }

    /**
     *  显示loading
     */
    public void showLoading() {
        if(layoutSparseArray.get(LAYOUT_LOADING_ID) != null)
            showHideViewById(LAYOUT_LOADING_ID);
    }

    /**
     *  显示内容
     */
    public void showContent() {
        if(layoutSparseArray.get(LAYOUT_CONTENT_ID) != null)
            showHideViewById(LAYOUT_CONTENT_ID);
    }

    /**
     *  显示空数据
     */
    public void showEmptyData() {
        if(inflateLayout(LAYOUT_EMPTYDATA_ID))
            showHideViewById(LAYOUT_EMPTYDATA_ID);
    }

    /**
     *  显示网络异常
     */
    public void showNetWorkError() {
        if(inflateLayout(LAYOUT_NETWORK_ERROR_ID))
            showHideViewById(LAYOUT_NETWORK_ERROR_ID);
    }

    /**
     *  显示异常
     */
    public void showError() {
        if(inflateLayout(LAYOUT_ERROR_ID))
            showHideViewById(LAYOUT_ERROR_ID);
    }

    /**
     *  根据ID显示隐藏布局
     * @param id
     */
    private void showHideViewById(int id) {
        for(int i = 0; i < layoutSparseArray.size(); i++) {
            int key = layoutSparseArray.keyAt(i);
            View valueView = layoutSparseArray.valueAt(i);
            //显示该view
            if(key == id) {
                valueView.setVisibility(View.VISIBLE);
//                if(mStatesLayoutManager.onShowHideViewListener != null) mStatesLayoutManager.onShowHideViewListener.onShowView(valueView, key);
            } else {
                if(valueView.getVisibility() != View.GONE) {
                    valueView.setVisibility(View.GONE);
//                    if(mStatesLayoutManager.onShowHideViewListener != null) mStatesLayoutManager.onShowHideViewListener.onHideView(valueView, key);
                }
            }
        }
    }

//    private void hideAllView(){
//        for(int i = 0; i < layoutSparseArray.size(); i++) {
//            int key = layoutSparseArray.keyAt(i);
//            View valueView = layoutSparseArray.valueAt(i);
//            valueView.setVisibility(GONE);
//        }
//    }

    private boolean inflateLayout(int id) {
        boolean isShow = true;
        if(layoutSparseArray.get(id) != null) return isShow;
        switch (id) {
            case LAYOUT_NETWORK_ERROR_ID:
                if(mStatesLayoutManager.netWorkErrorVs != null) {
                    View view = mStatesLayoutManager.netWorkErrorVs.inflate();
                    retryLoad(view, mStatesLayoutManager.netWorkErrorRetryViewId);
                    layoutSparseArray.put(id, view);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_ERROR_ID:
                if(mStatesLayoutManager.errorVs != null) {
                    View view = mStatesLayoutManager.errorVs.inflate();
                    retryLoad(view, mStatesLayoutManager.errorRetryViewId);
                    layoutSparseArray.put(id, view);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_EMPTYDATA_ID:
                if(mStatesLayoutManager.emptyDataVs != null) {
                    View view = mStatesLayoutManager.emptyDataVs.inflate();
                    retryLoad(view, mStatesLayoutManager.emptyDataRetryViewId);
                    layoutSparseArray.put(id, view);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
        }
        return isShow;
    }

    /**
     *  重试加载
     */
    public void retryLoad(View view, int id) {
        View retryView = view.findViewById(mStatesLayoutManager.retryViewId != 0 ? mStatesLayoutManager.retryViewId : id);
        if(retryView == null || mStatesLayoutManager.onRetryListener == null)
            return;
        retryView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mStatesLayoutManager.onRetryListener.onRetry();
            }
        });
    }
}


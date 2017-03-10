package com.shi.androidstudy;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.shi.manager.OnRetryListener;
import com.wang.avi.AVLoadingIndicatorView;

public class RecyclerViewActivity extends MyBaseActivity {

    private RecyclerView mRecycler;

    @Override
    public View initContentView() {
        return View.inflate(mContext,R.layout.activity_recycler_view, null);
    }

    @Override
    public void initData() {
        builder.setOnRetryListener(new OnRetryListener() {
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

        mRecycler= (RecyclerView) findViewById(R.id.recycler);

        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(new RecyclerView.Adapter<IndicatorHolder>() {
            @Override
            public IndicatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView=getLayoutInflater().inflate(R.layout.item_indicator,parent,false);
                return new IndicatorHolder(itemView);
            }

            @Override
            public void onBindViewHolder(IndicatorHolder holder, final int position) {
                holder.indicatorView.setIndicator(INDICATORS[position]);
                holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(RecyclerViewActivity.this,IndicatorActivity.class);
                        intent.putExtra("indicator",INDICATORS[position]);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return INDICATORS.length;
            }
        });
    }


    final static class IndicatorHolder extends RecyclerView.ViewHolder{

        public AVLoadingIndicatorView indicatorView;
        public View itemLayout;

        public IndicatorHolder(View itemView) {
            super(itemView);
            itemLayout= itemView.findViewById(R.id.itemLayout);
            indicatorView= (AVLoadingIndicatorView) itemView.findViewById(R.id.indicator);
        }
    }

    private static final String[] INDICATORS=new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
            "com.wang.avi.sample.MyCustomIndicator"
    };

}

package com.xiulian.thecara.mvvm.points;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmActivity;
import com.xiulian.thecara.entity.PointsTaskInfo;
import com.xiulian.thecara.mvvm.adapter.PointsExchangeAdapter;
import com.xiulian.thecara.mvvm.adapter.PointsTaskAdapter;
import com.xiulian.thecara.mvvm.common.CommonDecoration;
import com.xiulian.thecara.mvvm.common.DataBindingConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分中心
 *
 * @author wzh
 * @date 2020/10/15
 */
public class PointsCenterActivity extends MvvmActivity {

    private PointsCenterViewModel mViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.iv_back).setOnClickListener(v -> finish());
        findViewById(R.id.tv_see_all_task).setOnClickListener(v -> startActivity(new Intent(PointsCenterActivity.this, PointsTaskListActivity.class)));
        findViewById(R.id.tv_subtitle).setOnClickListener(v -> startActivity(new Intent(PointsCenterActivity.this, PointsRecordActivity.class)));

        RecyclerView rvPointsTask = findViewById(R.id.rv_points_task);
        RecyclerView rvPointsExchange = findViewById(R.id.rv_points_exchange);

        //更多积分
        rvPointsTask.setLayoutManager(new GridLayoutManager(this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPointsTask.setFocusable(false);
        rvPointsTask.setNestedScrollingEnabled(false);
        rvPointsTask.addItemDecoration(new CommonDecoration(12, 8, 0, 0, 0, 0));
        List<PointsTaskInfo> pointsTaskList = new ArrayList<>();
        pointsTaskList.add(new PointsTaskInfo());
        pointsTaskList.add(new PointsTaskInfo());
        pointsTaskList.add(new PointsTaskInfo());
        pointsTaskList.add(new PointsTaskInfo());
        rvPointsTask.setAdapter(new PointsTaskAdapter(pointsTaskList));


        //积分兑换
        rvPointsExchange.setLayoutManager(new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPointsExchange.setFocusable(false);
        rvPointsExchange.setNestedScrollingEnabled(false);
        rvPointsExchange.addItemDecoration(new CommonDecoration(12, 8, 0, 0, 0, 0));
        List<PointsTaskInfo> pointsList = new ArrayList<>();
        pointsList.add(new PointsTaskInfo());
        pointsList.add(new PointsTaskInfo());
        pointsList.add(new PointsTaskInfo());
        pointsList.add(new PointsTaskInfo());
        PointsExchangeAdapter exchangeAdapter = new PointsExchangeAdapter(pointsList);
        exchangeAdapter.setOnItemClickListener((adapter, view, position) -> startActivity(new Intent(PointsCenterActivity.this, PointsExchangeActivity.class)));
        rvPointsExchange.setAdapter(exchangeAdapter);
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(PointsCenterViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_points_center, BR.vm, mViewModel);
    }
}

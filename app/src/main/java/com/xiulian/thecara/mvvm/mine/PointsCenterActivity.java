package com.xiulian.thecara.mvvm.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseActivity;
import com.xiulian.thecara.entity.PointsTaskInfo;
import com.xiulian.thecara.mvvm.common.CommonDecoration;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分中心
 * @author wzh
 * @date 2020/10/15
 */
public class PointsCenterActivity extends BaseActivity {
    @Override
    public int setupContentLayoutId() {
        return R.layout.activity_points_center;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        findViewById(R.id.iv_back).setOnClickListener(v -> finish());
        findViewById(R.id.tv_see_all_task).setOnClickListener(v->startActivity(new Intent(PointsCenterActivity.this,PointsTaskListActivity.class)));

        RecyclerView rvPointsTask = findViewById(R.id.rv_points_task);
        RecyclerView rvPointsExchange = findViewById(R.id.rv_points_exchange);

        rvPointsTask.setLayoutManager(new GridLayoutManager(this,3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPointsTask.setFocusable(false);
        rvPointsTask.setNestedScrollingEnabled(false);
        rvPointsTask.addItemDecoration(new CommonDecoration(12,8,0,0,0,0));
        List<PointsTaskInfo> pointsTaskList = new ArrayList<>();
        pointsTaskList.add(new PointsTaskInfo());
        pointsTaskList.add(new PointsTaskInfo());
        pointsTaskList.add(new PointsTaskInfo());
        pointsTaskList.add(new PointsTaskInfo());
        rvPointsTask.setAdapter(new PointsTaskAdapter(pointsTaskList));


        rvPointsExchange.setLayoutManager(new GridLayoutManager(this,2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPointsExchange.setFocusable(false);
        rvPointsExchange.setNestedScrollingEnabled(false);
        rvPointsExchange.addItemDecoration(new CommonDecoration(12,8,0,0,0,0));
        List<PointsTaskInfo> pointsList = new ArrayList<>();
        pointsList.add(new PointsTaskInfo());
        pointsList.add(new PointsTaskInfo());
        pointsList.add(new PointsTaskInfo());
        pointsList.add(new PointsTaskInfo());
        rvPointsExchange.setAdapter(new PointsTaskAdapter(pointsList));
    }

    @Override
    public void setupPresenter() {

    }
}

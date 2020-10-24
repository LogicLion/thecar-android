package com.xiulian.thecara.mvvm.points;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmActivity;
import com.xiulian.thecara.entity.PointsTaskInfo;
import com.xiulian.thecara.mvvm.adapter.PointsTaskAdapter;
import com.xiulian.thecara.mvvm.common.CommonDecoration;
import com.xiulian.thecara.mvvm.common.DataBindingConfig;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分任务列表
 *
 * @author wzh
 * @date 2020/10/16
 */
public class PointsTaskListActivity extends MvvmActivity {

    private PointsTaskListViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(PointsTaskListViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_points_task_list, BR.vm,mViewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.iv_back).setOnClickListener(v -> finish());
        RecyclerView rvPointsTask = findViewById(R.id.rv_points_task);

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
    }
}

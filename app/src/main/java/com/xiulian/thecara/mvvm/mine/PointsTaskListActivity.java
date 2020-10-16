package com.xiulian.thecara.mvvm.mine;

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
 * 积分任务列表
 *
 * @author wzh
 * @date 2020/10/16
 */
public class PointsTaskListActivity extends BaseActivity {
    @Override
    public int setupContentLayoutId() {
        return R.layout.activity_points_task_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

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

    @Override
    public void setupPresenter() {

    }
}

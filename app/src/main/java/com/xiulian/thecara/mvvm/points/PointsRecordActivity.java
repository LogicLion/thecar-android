package com.xiulian.thecara.mvvm.points;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmActivity;
import com.xiulian.thecara.entity.PointsTaskInfo;
import com.xiulian.thecara.mvvm.adapter.PointsRecordAdapter;
import com.xiulian.thecara.mvvm.common.DataBindingConfig;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020/10/16
 */
public class PointsRecordActivity extends MvvmActivity {

    private PointsRecordViewModel mViewModel;


    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(PointsRecordViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_points_task_record, BR.vm,mViewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView rvDetail = findViewById(R.id.rv_points_task_record);
        rvDetail.setLayoutManager(new LinearLayoutManager(this));
        List<PointsTaskInfo> list = new ArrayList<>();
        list.add(new PointsTaskInfo());
        list.add(new PointsTaskInfo());
        list.add(new PointsTaskInfo());
        rvDetail.setAdapter(new PointsRecordAdapter(list));
    }
}

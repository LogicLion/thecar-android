package com.xiulian.thecara.mvvm.mine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiulian.thecara.R;
import com.xiulian.thecara.entity.PointsTaskInfo;

import java.util.List;

/**
 * 更多积分
 * @author wzh
 * @date 2020/10/16
 */
public class PointsTaskAdapter extends BaseQuickAdapter<PointsTaskInfo, BaseViewHolder> {
    public PointsTaskAdapter(@Nullable List<PointsTaskInfo> data) {
        super(R.layout.item_points_tasks, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PointsTaskInfo item) {

    }
}

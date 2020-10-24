package com.xiulian.thecara.mvvm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiulian.thecara.R;
import com.xiulian.thecara.entity.PointsTaskInfo;

import java.util.List;

/**
 * 积分兑换
 * @author wzh
 * @date 2020/10/16
 */
public class PointsExchangeAdapter extends BaseQuickAdapter<PointsTaskInfo, BaseViewHolder>{
    public PointsExchangeAdapter(@Nullable List<PointsTaskInfo> data) {
        super(R.layout.item_points_exchange, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PointsTaskInfo item) {

    }

}

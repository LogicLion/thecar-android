package com.xiulian.thecara.mvvm.points;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseActivity;
import com.xiulian.thecara.base.MvvmActivity;
import com.xiulian.thecara.mvvm.common.DataBindingConfig;

import org.jetbrains.annotations.Nullable;

/**
 * 积分兑换
 * @author wzh
 * @date 2020/10/19
 */
public class PointsExchangeActivity extends MvvmActivity {

    private PointsExchangeViewModel mViewModel;


    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(PointsExchangeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_points_exchange, BR.vm,mViewModel);
    }
}

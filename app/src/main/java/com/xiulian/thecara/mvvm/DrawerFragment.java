package com.xiulian.thecara.mvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmFragment;
import com.xiulian.thecara.mvvm.common.DataBindingConfig;
import com.xiulian.thecara.mvvm.points.PointsCenterActivity;

/**
 * @author wzh
 * @date 2020/10/25
 */
public class DrawerFragment extends MvvmFragment {

    private DrawerViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(DrawerViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.drawer_main, BR.vm,mViewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvSignIn = view.findViewById(R.id.tv_sign_in);
        tvSignIn.setOnClickListener(v -> startActivity(new Intent(getContext(), PointsCenterActivity.class)));

    }

    public static DrawerFragment getInstance() {
        return new DrawerFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("drawer", "可见");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v("drawer", "不可见");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("drawer", "可交互");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("drawer", "不可交互");
    }
}

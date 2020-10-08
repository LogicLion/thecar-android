package com.xiulian.thecara.mvvm.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmFragment;
import com.xiulian.thecara.mvvm.ui.DataBindingConfig;
import com.xiulian.thecara.utils.RxJavaExtKt;

import org.jetbrains.annotations.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 首页
 *
 * @author wzh
 * @date 2020/9/23
 */
public class HomeFragment extends MvvmFragment {

    private CompositeDisposable compositeDisposable;
    private HomeViewModel homeViewModel;


    @Override
    public void initViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        compositeDisposable = new CompositeDisposable();

        Disposable disposable = RxJavaExtKt.handleHttpResult(homeViewModel.getVersionInfo())
                .subscribe(
                        versionInfoBean -> homeViewModel.versionCode.set(versionInfoBean.getAppVersionCode()),
                        error -> {}
                );
        compositeDisposable.add(disposable);
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }



    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Nullable
    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.vm,homeViewModel)
                .addBindingParam(BR.click,new ClickProxy());
    }

    public class ClickProxy {
        public void changeVersionCode() {
            int newCode = homeViewModel.versionCode.get();
            newCode++;
            homeViewModel.versionCode.set(newCode);

        }
    }

}

package com.xiulian.thecara.mvvm.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmFragment;
import com.xiulian.thecara.constant.Const;
import com.xiulian.thecara.entity.BannerInfo;
import com.xiulian.thecara.mvvm.ui.DataBindingConfig;

import org.jetbrains.annotations.Nullable;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 首页
 *
 * @author wzh
 * @date 2020/9/23
 */
public class HomeFragment extends MvvmFragment {

    private CompositeDisposable mDisposable;
    private HomeViewModel homeViewModel;


    @Override
    public void initViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDisposable = new CompositeDisposable();

        mDisposable.add(homeViewModel.getVersion().subscribe(
                versionInfoBean -> homeViewModel.versionCode.set(versionInfoBean.getAppVersionCode()),
                error -> {
                }
        ));
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }


    @Override
    public void onDestroy() {
        mDisposable.clear();
        super.onDestroy();
    }

    @Nullable
    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.vm, homeViewModel)
                .addBindingParam(BR.click, new ClickProxy());
    }

    public class ClickProxy {
        public void changeVersionCode() {
            int newCode = homeViewModel.versionCode.get();
            newCode++;
            homeViewModel.versionCode.set(newCode);
        }

        public void getBanner() {
            mDisposable.add(homeViewModel.getBanner().subscribe(bannerInfoList -> {

                BannerInfo bannerInfo = bannerInfoList.get(0);
                homeViewModel.bannerImage.set(Const.IMAGE_PREFIX + bannerInfo.getImgId().get(0));

            }, error -> {

            }));

        }
    }

}

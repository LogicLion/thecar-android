package com.xiulian.thecara.mvvm.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.willy.ratingbar.ScaleRatingBar;
import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmFragment;
import com.xiulian.thecara.constant.Const;
import com.xiulian.thecara.entity.BannerInfo;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.MainActivity;
import com.xiulian.thecara.mvvm.adapter.HomeNewsAdapter;
import com.xiulian.thecara.mvvm.adapter.NavigationMenuPagerAdapter;
import com.xiulian.thecara.mvvm.adapter.ShopListAdapter;
import com.xiulian.thecara.mvvm.common.CommonViewPagerAdapter;
import com.xiulian.thecara.mvvm.common.DataBindingConfig;
import com.xiulian.thecara.utils.ImageLoader;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.disposables.CompositeDisposable;

/**
 * 首页
 *
 * @author wzh
 * @date 2020/9/23
 */
public class HomeFragment extends MvvmFragment {

    private CompositeDisposable mDisposable=new CompositeDisposable();;
    private HomeViewModel homeViewModel;


    @Override
    public void initViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager vpMenu = view.findViewById(R.id.vp_navigation_menu);
        BGABanner banner = view.findViewById(R.id.banner);
        ViewPager vpHome = view.findViewById(R.id.vp_home);
        FrameLayout flChip = view.findViewById(R.id.fl_chip_content);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        RecyclerView rvNews = view.findViewById(R.id.rv_news);

        View viewChipType1 = View.inflate(getContext(), R.layout.home_chip_type_2, null);
        flChip.addView(viewChipType1);


        banner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner bgaBanner, View view, Object o, int i) {

                ImageLoader.INSTANCE.loadCornerCoverImage(view.getContext(),"", (ImageView) view);
            }
        });
        banner.setData(Arrays.asList("网络图片路径1", "网络图片路径2", "网络图片路径3","网络图片路径4"),null);

        NavigationMenuPagerAdapter pageAdapter = new NavigationMenuPagerAdapter();
        List<List<NewsInfo>> tabs = new ArrayList<>();
        List<NewsInfo> titles1 = new ArrayList<>();
        titles1.add(new NewsInfo());
        titles1.add(new NewsInfo());
        titles1.add(new NewsInfo());
        titles1.add(new NewsInfo());
        titles1.add(new NewsInfo());
        titles1.add(new NewsInfo());
        tabs.add(titles1);

        List<NewsInfo> titles2 = new ArrayList<>();
        titles2.add(new NewsInfo());
        titles2.add(new NewsInfo());
        titles2.add(new NewsInfo());
        tabs.add(titles2);
        pageAdapter.setItems(tabs);
        vpMenu.setAdapter(pageAdapter);


        vpHome.setAdapter(new CommonViewPagerAdapter(2,false,new String[]{"有料推荐","附近门店"}));
        tabLayout.setupWithViewPager(vpHome);
        ArrayList<NewsInfo> list1 = new ArrayList<>();
        list1.add(new NewsInfo("资讯1",1));
        list1.add(new NewsInfo("资讯2",1));
        list1.add(new NewsInfo("资讯3",2));
        list1.add(new NewsInfo("资讯3",1));
        list1.add(new NewsInfo("资讯3",1));
        list1.add(new NewsInfo("资讯3",2));
        list1.add(new NewsInfo("资讯3",2));
        list1.add(new NewsInfo("资讯3",3));
        list1.add(new NewsInfo("资讯3",1));

        rvNews.setAdapter(new HomeNewsAdapter(list1));

        ArrayList<NewsInfo> list2 = new ArrayList<>();
        list2.add(new NewsInfo("门店1"));
        list2.add(new NewsInfo("门店2"));
        list2.add(new NewsInfo("门店3"));
        homeViewModel.shopInfoList.set(list2);

        mDisposable.add(homeViewModel.getVersion().subscribe(
                versionInfoBean -> homeViewModel.versionCode.set(versionInfoBean.getAppVersionCode()),
                error -> {}
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
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.shopAdapter, new ShopListAdapter(getContext()));
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
            }, error -> {}));

        }

        public void openDrawer() {
            if (getContext() instanceof MainActivity) {
                ((MainActivity) getContext()).openDrawer();
            }
        }
    }

}

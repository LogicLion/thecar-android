package com.xiulian.thecara.mvvm.home;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.App;
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
import com.xiulian.thecara.mvvm.mall.MallFragment;
import com.xiulian.thecara.utils.BannerImageLoader;
import com.xiulian.thecara.utils.DisplayUtil;
import com.youth.banner.Banner;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 首页
 *
 * @author wzh
 * @date 2020/9/23
 */
public class HomeFragment extends MvvmFragment {

    private CompositeDisposable mDisposable=new CompositeDisposable();
    private HomeViewModel homeViewModel;

    //记录上一个页面所在的页数
    private int lastPage;

    //设置图片集合
    String imgUrl = "https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/forum/pic/item/7aec54e736d12f2ee585941a58c2d562843568c3.jpg";
    String imgUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603026849103&di=3f60846af61c1a15de869f0d31678d75&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201902%2F07%2F20190207192320_rotkp.jpg";
    String imgUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603027048277&di=dfa764236a62bee9531f08bba5f4ae5c&imgtype=0&src=http%3A%2F%2Fimg.zhangton.com%2Fuploads%2Fzedit%2F2019-12%2F09%2F20191209100022_87536.gif";
    List<String> images = new ArrayList<>();

    private Integer[] indicators = {
            R.drawable.shape_color_white_indicator_selected,
            R.drawable.shape_color_white_indicator_unselected
    };
    private LinearLayout mBannerIndicator;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeNewsAdapter mHomeNewsAdapter;
    private ArrayList<NewsInfo> mInitList;
    private ShopListAdapter mShopListAdapter;
    private ArrayList<NewsInfo> mShopList;

    @Override
    public void initViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager vpMenu = view.findViewById(R.id.vp_navigation_menu);
        Banner banner = view.findViewById(R.id.banner);
        ViewPager vpHome = view.findViewById(R.id.vp_home);
        FrameLayout flChip = view.findViewById(R.id.fl_chip_content);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        RecyclerView rvNews = view.findViewById(R.id.rv_news);
        RecyclerView rvShop = view.findViewById(R.id.rv_shop);
        mBannerIndicator = view.findViewById(R.id.ll_banner_indicator);


        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        MaterialHeader refreshHeader = view.findViewById(R.id.refresh_header);
        refreshHeader.setColorSchemeResources(R.color.color_black_333333);
        //禁止越界回弹
        refreshLayout.setEnableOverScrollBounce(false);

        View viewChipType1 = View.inflate(getContext(), R.layout.view_home_chip_type_2, null);
        flChip.addView(viewChipType1);


        //设置图片加载器
        banner.setImageLoader(new BannerImageLoader());

        images.add(imgUrl);
        images.add(imgUrl1);
        images.add(imgUrl2);
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectIndicator(position);
                lastPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //设置指示点
        setupPageIndicator();

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
        mInitList = new ArrayList<>();
        mInitList.add(new NewsInfo("资讯1",1));
        mInitList.add(new NewsInfo("资讯2",1));
        mInitList.add(new NewsInfo("资讯3",2));
        mInitList.add(new NewsInfo("资讯3",1));
        mInitList.add(new NewsInfo("资讯3",1));
        mInitList.add(new NewsInfo("资讯3",2));
        mInitList.add(new NewsInfo("资讯3",2));
        mInitList.add(new NewsInfo("资讯3",3));
        mInitList.add(new NewsInfo("资讯3",1));

        mHomeNewsAdapter = new HomeNewsAdapter();
        mHomeNewsAdapter.setNewList(mInitList);
        mHomeNewsAdapter.setOnLoadMoreListener(() -> {
            Log.v("加载更多", "加载更多");
            mHandler.sendEmptyMessageDelayed(1, 1000);
        },rvNews);
        mHomeNewsAdapter.setEnableLoadMore(true);
        rvNews.setAdapter(mHomeNewsAdapter);


        mShopList = new ArrayList<>();
        mShopList.add(new NewsInfo("门店1"));
        mShopList.add(new NewsInfo("门店2"));
        mShopList.add(new NewsInfo("门店3"));
        mShopList.add(new NewsInfo("门店4"));
        mShopList.add(new NewsInfo("门店5"));
        mShopList.add(new NewsInfo("门店6"));

        mShopListAdapter = new ShopListAdapter();
        rvShop.setAdapter(mShopListAdapter);
        mShopListAdapter.setNewList(mShopList);

        mShopListAdapter.setEnableLoadMore(true);
        mShopListAdapter.setOnLoadMoreListener(() -> {
            mHandler.sendEmptyMessageDelayed(2, 1000);
        },rvShop);


        mDisposable.add(homeViewModel.getVersion().subscribe(
                versionInfoBean -> homeViewModel.versionCode.set(versionInfoBean.getAppVersionCode()),
                error -> {}
        ));

        AppBarLayout appbarLayout = view.findViewById(R.id.appbarLayout);
        View clTitle = view.findViewById(R.id.cl_title);
        appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.v("滑动距离", verticalOffset+"");
                if (-verticalOffset > DisplayUtil.dpToPx(App.getInstance(), 100)&&clTitle.getVisibility()==View.INVISIBLE) {
                    clTitle.setVisibility(View.VISIBLE);
                }
                else if(-verticalOffset < DisplayUtil.dpToPx(App.getInstance(), 100)&&clTitle.getVisibility()==View.VISIBLE){
                    clTitle.setVisibility(View.INVISIBLE);
                }
            }
        });


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
        return new DataBindingConfig(R.layout.fragment_home_1, BR.vm, homeViewModel)
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
            }, error -> {}));
        }

        public void openDrawer() {
            if (getContext() instanceof MainActivity) {
                ((MainActivity) getContext()).openDrawer();
            }
        }
    }


    /**
     * 设置指示点
     */
    private void setupPageIndicator() {

        for (int i = 0; i < images.size(); i++) {
            ImageView pointView = new ImageView(getContext());
            pointView.setImageResource(indicators[1]);

            int paddingValue= DisplayUtil.dpToPx(App.getInstance(),2);
            pointView.setPadding(paddingValue,0,paddingValue,0);

            mBannerIndicator.addView(pointView);
        }
        selectIndicator(0);
    }


    private void selectIndicator(int page) {
        ImageView  last= (ImageView) mBannerIndicator.getChildAt(lastPage);
        last.setImageResource(indicators[1]);

        ImageView  selected= (ImageView) mBannerIndicator.getChildAt(page);
        selected.setImageResource(indicators[0]);
    }


    public void getMoreData() {
        List<NewsInfo> oldList = mHomeNewsAdapter.getData();
        oldList.addAll(mInitList);
        mHomeNewsAdapter.setNewList(oldList);
        mHomeNewsAdapter.loadMoreComplete();
    }

    public void getMoreShopData() {
        List<NewsInfo> list = mShopListAdapter.getData();
        list.addAll(mShopList);
        mShopListAdapter.setNewList(list);
        mShopListAdapter.loadMoreComplete();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage( Message msg) {
            switch (msg.what) {
                case 1:
                    getMoreData();
                    break;
                case 2:
                    getMoreShopData();
                    break;
            }

        }
    };

    public static class DiffCallback<T> extends DiffUtil.Callback {

        private List<T> oldItems;
        private List<T> newItems;
        public DiffCallback(List<T> oldItems, List<T> newItems ) {
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return oldItems.size();
        }

        @Override
        public int getNewListSize() {
            return newItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItemPosition==newItemPosition;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }
    }
}

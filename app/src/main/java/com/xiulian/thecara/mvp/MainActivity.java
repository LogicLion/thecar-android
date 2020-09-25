package com.xiulian.thecara.mvp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.base.base.MvpFragment;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseActivity;
import com.xiulian.thecara.mvp.car.CarFragment;
import com.xiulian.thecara.mvp.home.HomeFragment;
import com.xiulian.thecara.mvp.investment.InvestmentFragment;
import com.xiulian.thecara.mvp.mall.MallFragment;
import com.xiulian.thecara.mvp.news.NewsFragment;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * @author wzh
 * @date 2020/9/23
 */
public class MainActivity extends BaseActivity {

    public final static String HOME_FRAGMENT_KEY = "homeFragment";
    public final static String NEWS_FRAGMENT_KEY = "newsFragment";
    public final static String MALL_FRAGMENT_KEY = "mallFragment";
    public final static String INVESTMENT_FRAGMENT_KEY = "investmentFragment";
    public final static String CAR_FRAGMENT_KEY = "carFragment";
    public final static String SELECTED = "selected";


    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private MallFragment mallFragment;
    private InvestmentFragment investmentFragment;
    private CarFragment carFragment;
    private ArrayList<MvpFragment> fragmentList;
    private TabLayout tabLayout;
    private FrameLayout frameLayout;
    private int currentPosition;


    @Override
    public void setupPresenter() {

    }

    @Override
    public int setupContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_main);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);

        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_KEY);
        newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_KEY);
        mallFragment = (MallFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_KEY);
        investmentFragment = (InvestmentFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_KEY);
        carFragment = (CarFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_KEY);

        if (homeFragment == null) {
            homeFragment = HomeFragment.getInstance();
        }

        if (newsFragment == null) {
            newsFragment = NewsFragment.getInstance();
        }

        if (mallFragment == null) {
            mallFragment = MallFragment.getInstance();
        }

        if (investmentFragment == null) {
            investmentFragment = InvestmentFragment.getInstance();
        }

        if (carFragment == null) {
            carFragment = CarFragment.getInstance();
        }

        fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(newsFragment);
        fragmentList.add(mallFragment);
        fragmentList.add(investmentFragment);
        fragmentList.add(carFragment);


        //设置tab
        String[] tabTitles = {"首页", "资讯", "商城", "理财", "车管家"};

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                selectFragment(position);
                View tabView = tab.getCustomView();
                ImageView ivTab = tabView.findViewById(R.id.iv_tab);
                TextView tvTab = tabView.findViewById(R.id.tv_tab);
                ivTab.setImageResource(R.drawable.icon_nav_home_active);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                View tabView = tab.getCustomView();
                ImageView ivTab = tabView.findViewById(R.id.iv_tab);
                TextView tvTab = tabView.findViewById(R.id.tv_tab);
                ivTab.setImageResource(R.drawable.icon_nav_home_normal);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        for (int i = 0; i < tabTitles.length; i++) {

            View tabView = LayoutInflater.from(this).inflate(R.layout.item_tab_main, null);
            ImageView ivTab = tabView.findViewById(R.id.iv_tab);
            TextView tvTab = tabView.findViewById(R.id.tv_tab);

            tvTab.setText(tabTitles[i]);
            ivTab.setImageResource(R.drawable.icon_nav_home_normal);

            if (savedInstanceState != null) {
                //activity异常退出时保存的tabLayout位置
                int savePosition = savedInstanceState.getInt(SELECTED);
                if (i == savePosition) {
                    tabLayout.addTab(tabLayout.newTab().setCustomView(tabView), true);
                } else {
                    tabLayout.addTab(tabLayout.newTab().setCustomView(tabView), false);
                }
            } else {
                tabLayout.addTab(tabLayout.newTab().setCustomView(tabView));
            }
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED, currentPosition);
        super.onSaveInstanceState(outState);
    }

    public void selectFragment(int position) {
        currentPosition = position;

        switch (position) {
            case 0:
                showFragment(homeFragment, HOME_FRAGMENT_KEY);
                break;
            case 1:
                showFragment(newsFragment, NEWS_FRAGMENT_KEY);
                break;
            case 2:
                showFragment(mallFragment, MALL_FRAGMENT_KEY);
                break;
            case 3:
                showFragment(investmentFragment, INVESTMENT_FRAGMENT_KEY);
                break;
            case 4:
                showFragment(carFragment, CAR_FRAGMENT_KEY);
                break;
        }

    }

    public void showFragment(Fragment targetFragment, String tag) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(R.id.frame_layout, targetFragment, tag);
        }

        for (Fragment fragment : fragmentList) {
            if (fragment != targetFragment) {
                transaction.hide(fragment);
            }
        }

        transaction.show(targetFragment).commit();
    }
}

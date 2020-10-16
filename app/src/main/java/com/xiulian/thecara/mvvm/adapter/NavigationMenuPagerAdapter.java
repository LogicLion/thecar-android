package com.xiulian.thecara.mvvm.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiulian.thecara.R;
import com.xiulian.thecara.databinding.ItemRecyclerNavigationMenuBinding;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.common.BasePagerAdapter;

import java.util.List;

/**
 * 首页-》导航菜单item
 * @author wzh
 * @date 2020/10/13
 */
public class NavigationMenuPagerAdapter extends BasePagerAdapter<List<NewsInfo>> {

    public NavigationMenuPagerAdapter() {
        super(R.layout.recyclerview_home_navigation_menu);
    }


    @Override
    protected void onBind(View containerView, List<NewsInfo> item, int position) {
        RecyclerView recyclerView = (RecyclerView) containerView;
        recyclerView.setLayoutManager(new GridLayoutManager(containerView.getContext(),5));
        NavigationMenuAdapter adapter = new NavigationMenuAdapter(item);
        recyclerView.setAdapter(adapter);
    }
}

package com.xiulian.thecara.mvvm.mall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseFragment;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.adapter.HomeNewsAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * 商城
 * @author wzh
 * @date 2020/9/23
 */
 public class MallFragment extends BaseFragment {
    @Override
    public int setupContentLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    public void initViewModel() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        RecyclerView rv = findViewById(R.id.rv);
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

        HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(list1);
        View headerView = View.inflate(getContext(), R.layout.header_view, null);
        homeNewsAdapter.addHeaderView(headerView);
        homeNewsAdapter.setOnLoadMoreListener(() -> {
            Log.v("加载更多", "加载更多");
        },rv);
        homeNewsAdapter.openLoadAnimation();
        rv.setAdapter(homeNewsAdapter);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager= (LinearLayoutManager) layoutManager;
                    int visibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    Log.v("可见位置:", visibleItemPosition + "");
                }
            }
        });


    }

   public static MallFragment getInstance() {
      return new MallFragment();
   }

}

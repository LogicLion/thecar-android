package com.xiulian.thecara.mvvm.news;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmFragment;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.common.DataBindingConfig;
import com.xiulian.thecara.mvvm.adapter.NewsListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 资讯
 * @author wzh
 * @date 2020/9/23
 */
 public class NewsFragment extends MvvmFragment {

    private NewsViewModel mViewModel;


    @Override
    public void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_news, BR.vm,mViewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mViewModel.newsInfoList.set(list);
        RecyclerView rvNews = view.findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        List<NewsInfo> list = new ArrayList<>();
        list.add(new NewsInfo());
        list.add(new NewsInfo());
        list.add(new NewsInfo());
        rvNews.setAdapter(new NewsListAdapter(list));

    }

    public static NewsFragment getInstance() {
      return new NewsFragment();
   }


}

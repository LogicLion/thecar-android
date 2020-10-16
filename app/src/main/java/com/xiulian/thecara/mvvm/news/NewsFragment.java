package com.xiulian.thecara.mvvm.news;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.MvvmFragment;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.common.DataBindingConfig;
import com.xiulian.thecara.mvvm.adapter.NewsListAdapter;

import java.util.ArrayList;

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
        return new DataBindingConfig(R.layout.fragment_news, BR.vm,mViewModel)
                .addBindingParam(BR.adapter,new NewsListAdapter(getContext()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<NewsInfo> list = new ArrayList<>();
        list.add(new NewsInfo());
        list.add(new NewsInfo());
        list.add(new NewsInfo());
        mViewModel.newsInfoList.set(list);

    }

    public static NewsFragment getInstance() {
      return new NewsFragment();
   }


}

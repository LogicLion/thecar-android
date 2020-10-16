package com.xiulian.thecara.mvvm.adapter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiulian.thecara.R;
import com.xiulian.thecara.entity.NewsInfo;

import java.util.List;

/**
 * 首页-》有料推荐
 * @author wzh
 * @date 2020/10/14
 */
public class HomeNewsAdapter extends BaseMultiItemQuickAdapter<NewsInfo, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeNewsAdapter(List<NewsInfo> data) {
        super(data);
        addItemType(1, R.layout.item_recycler_home_news_type_1);
        addItemType(2, R.layout.item_recycler_home_news_type_2);
        addItemType(3, R.layout.item_recycler_home_news_type_3);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsInfo item) {

    }
}

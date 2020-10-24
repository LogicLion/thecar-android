package com.xiulian.thecara.mvvm.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiulian.thecara.R;
import com.xiulian.thecara.entity.NewsInfo;

import java.util.List;

/**
 * @author wzh
 * @date 2020/10/10
 */
public class NewsListAdapter extends BaseQuickAdapter<NewsInfo, BaseViewHolder> {

    public NewsListAdapter(@Nullable List<NewsInfo> data) {
        super(R.layout.item_recycler_news_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsInfo item) {

    }
}

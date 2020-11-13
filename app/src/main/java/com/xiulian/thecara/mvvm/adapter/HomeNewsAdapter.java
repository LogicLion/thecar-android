package com.xiulian.thecara.mvvm.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.diff.BaseQuickDiffCallback;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseMultiAdapter;
import com.xiulian.thecara.entity.NewsInfo;

import java.util.List;

/**
 * 首页-》有料推荐
 * @author wzh
 * @date 2020/10/14
 */
public class HomeNewsAdapter extends BaseMultiAdapter<NewsInfo, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     */
    public HomeNewsAdapter() {
        super();
        addItemType(1, R.layout.item_recycler_home_news_type_1);
        addItemType(2, R.layout.item_recycler_home_news_type_2);
        addItemType(3, R.layout.item_recycler_home_news_type_3);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsInfo item) {

    }

    @Override
    public boolean areItemsTheSame(NewsInfo oldItem, NewsInfo newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(NewsInfo oldItem, NewsInfo newItem) {
        return oldItem.equals(newItem);
    }




}

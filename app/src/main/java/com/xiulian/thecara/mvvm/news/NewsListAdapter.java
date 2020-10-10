package com.xiulian.thecara.mvvm.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.xiulian.thecara.R;
import com.xiulian.thecara.databinding.ItemRecyclerNewsListBinding;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.adapter.SimpleDataBindingAdapter;

/**
 * @author wzh
 * @date 2020/10/10
 */
class NewsListAdapter extends SimpleDataBindingAdapter<NewsInfo, ItemRecyclerNewsListBinding> {
    public NewsListAdapter(Context context, int layout, @NonNull DiffUtil.ItemCallback<NewsInfo> diffCallback) {
        super(context, R.layout.item_recycler_news_list, diffCallback);
    }

    @Override
    protected void onBindItem(ItemRecyclerNewsListBinding binding, NewsInfo item, RecyclerView.ViewHolder holder) {
        binding.setNewsInfo(item);
    }
}

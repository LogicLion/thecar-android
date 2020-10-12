package com.xiulian.thecara.mvvm.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.xiulian.thecara.R;
import com.xiulian.thecara.databinding.ItemRecyclerNewsListBinding;
import com.xiulian.thecara.entity.NewsInfo;

/**
 * @author wzh
 * @date 2020/10/10
 */
public class NewsListAdapter extends SimpleDataBindingAdapter<NewsInfo, ItemRecyclerNewsListBinding> {
    public NewsListAdapter(Context context) {
        super(context, R.layout.item_recycler_news_list, DiffUtils.getInstance().getTestMusicItemCallback());
    }

    @Override
    protected void onBindItem(ItemRecyclerNewsListBinding binding, NewsInfo item, RecyclerView.ViewHolder holder) {
        binding.setNewsInfo(item);
    }
}

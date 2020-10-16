package com.xiulian.thecara.mvvm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.xiulian.thecara.R;
import com.xiulian.thecara.databinding.ItemRecyclerNewsListBinding;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.common.DiffUtils;
import com.xiulian.thecara.mvvm.common.SimpleDataBindingAdapter;

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

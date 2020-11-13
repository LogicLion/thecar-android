package com.xiulian.thecara.mvvm.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiulian.thecara.BR;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseAdapter;
import com.xiulian.thecara.entity.NewsInfo;

import java.util.List;

/**
 * @author wzh
 * @date 2020/10/15
 */
public class ShopListAdapter extends BaseAdapter<NewsInfo, BaseViewHolder> {
    public ShopListAdapter() {
        super(R.layout.item_home_shop);
    }

    @Override
    public boolean areItemsTheSame(NewsInfo oldItem, NewsInfo newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(NewsInfo oldItem, NewsInfo newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public void onBindItem(@NonNull BaseViewHolder helper, NewsInfo item, ViewDataBinding binding) {
        binding.setVariable(BR.newsInfo, item);
    }

}

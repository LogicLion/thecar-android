package com.xiulian.thecara.mvvm.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.xiulian.thecara.R;
import com.xiulian.thecara.databinding.ItemHomeShopBinding;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.common.DiffUtils;
import com.xiulian.thecara.mvvm.common.SimpleDataBindingAdapter;

/**
 * @author wzh
 * @date 2020/10/15
 */
public class ShopListAdapter extends SimpleDataBindingAdapter<NewsInfo, ItemHomeShopBinding> {
    public ShopListAdapter(Context context) {
        super(context, R.layout.item_home_shop, DiffUtils.getInstance().getTestMusicItemCallback());
    }

    @Override
    protected void onBindItem(ItemHomeShopBinding binding, NewsInfo item, RecyclerView.ViewHolder holder) {
        binding.ratingBar.setClickable(false);
        binding.setNewsInfo(item);
    }
}

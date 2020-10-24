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
 * @date 2020/10/15
 */
public class ShopListAdapter extends BaseQuickAdapter<NewsInfo, BaseViewHolder> {
    public ShopListAdapter(@Nullable List<NewsInfo> data) {
        super(R.layout.item_home_shop, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsInfo item) {

    }
}

package com.xiulian.thecara.mvvm.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mvp.base.base.BaseAdapter;
import com.mvp.base.base.ViewHolder;
import com.xiulian.thecara.R;
import com.xiulian.thecara.databinding.ItemRecyclerNavigationMenuBinding;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.common.DiffUtils;
import com.xiulian.thecara.mvvm.common.SimpleDataBindingAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author wzh
 * @date 2020/10/13
 */
public class NavigationMenuAdapter extends BaseQuickAdapter<NewsInfo, BaseViewHolder> {
    public NavigationMenuAdapter(List<NewsInfo> list) {
        super(R.layout.item_recycler_navigation_menu,list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsInfo item) {

        TextView tvTitle = helper.getView(R.id.tv_title);
        tvTitle.setText("aaaaa");
    }
}

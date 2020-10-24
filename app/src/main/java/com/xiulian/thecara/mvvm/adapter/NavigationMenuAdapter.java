package com.xiulian.thecara.mvvm.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiulian.thecara.R;
import com.xiulian.thecara.entity.NewsInfo;

import java.util.List;

/**
 * @author wzh
 * @date 2020/10/13
 */
public class NavigationMenuAdapter extends BaseQuickAdapter<NewsInfo, BaseViewHolder> {
    public NavigationMenuAdapter(List<NewsInfo> list) {
        super(R.layout.item_recycler_navigation_menu,list);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsInfo item) {

        TextView tvTitle = helper.getView(R.id.tv_title);
        tvTitle.setText("aaaaa");
    }


}

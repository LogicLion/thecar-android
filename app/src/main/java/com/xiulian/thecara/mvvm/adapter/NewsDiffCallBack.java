package com.xiulian.thecara.mvvm.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.diff.BaseQuickDiffCallback;
import com.xiulian.thecara.entity.NewsInfo;

import java.util.List;

/**
 * @author wzh
 * @date 2020/11/10
 */
public class NewsDiffCallBack extends BaseQuickDiffCallback<NewsInfo> {

    public NewsDiffCallBack(@Nullable List<NewsInfo> newList) {
        super(newList);
    }

    @Override
    protected boolean areItemsTheSame(@NonNull NewsInfo oldItem, @NonNull NewsInfo newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    protected boolean areContentsTheSame(@NonNull NewsInfo oldItem, @NonNull NewsInfo newItem) {
        return oldItem.toString().equals(newItem.toString());
    }
}
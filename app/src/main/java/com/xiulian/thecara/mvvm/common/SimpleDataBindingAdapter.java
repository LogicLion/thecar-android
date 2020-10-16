package com.xiulian.thecara.mvvm.common;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.xiulian.thecara.mvvm.common.BaseDataBindingAdapter;

/**
 * @author wzh
 * @date 2020/10/10
 */
public abstract class SimpleDataBindingAdapter<M, B extends ViewDataBinding> extends BaseDataBindingAdapter<M, B> {

    private final int layout;

    public SimpleDataBindingAdapter(Context context, int layout, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(diffCallback);
        this.layout = layout;
    }

    @Override
    protected @LayoutRes
    int getLayoutResId(int viewType) {
        return this.layout;
    }

}

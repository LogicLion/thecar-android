package com.xiulian.thecara.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.diff.BaseQuickDiffCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020/11/6
 */
public abstract class BaseAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,K> {
    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setNewList(List<T> newList) {
        ArrayList<T> list = new ArrayList<>(newList);
        DiffCallBack diffCallback = new DiffCallBack(list);
        setNewDiffData(diffCallback);
    }

    public abstract boolean areItemsTheSame(T oldItem, T newItem);
    public abstract boolean areContentsTheSame(T oldItem, T newItem);

    public abstract void onBindItem(@NonNull K helper, T item,ViewDataBinding binding);


    /**
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     */
    @Nullable
    protected Object getChangePayload(@NonNull T oldItem, @NonNull T newItem) {
        return null;
    }

    @Override
    protected void convert(@NonNull K helper, T item) {
        ViewDataBinding binding = DataBindingUtil.getBinding(helper.itemView);
        onBindItem(helper, item, binding);

        if (binding != null) {
            binding.executePendingBindings();
        }
    }

    public class DiffCallBack extends BaseQuickDiffCallback<T> {

        public DiffCallBack(@Nullable List<T> newList) {
            super(newList);
        }

        @Override
        protected boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
            return BaseAdapter.this.areItemsTheSame(oldItem, newItem);
        }

        @Override
        protected boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
            return BaseAdapter.this.areContentsTheSame(oldItem, newItem);
        }

        @Nullable
        protected Object getChangePayload(@NonNull T oldItem, @NonNull T newItem) {
            return BaseAdapter.this.getChangePayload(oldItem, newItem);
        }
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        return view;
    }


}

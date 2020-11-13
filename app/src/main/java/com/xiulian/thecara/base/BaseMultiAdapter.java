package com.xiulian.thecara.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.diff.BaseQuickDiffCallback;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.adapter.NewsDiffCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020/11/6
 */
public abstract class BaseMultiAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T, K> {
    public BaseMultiAdapter() {
        super(new ArrayList<>());
    }

    public void setNewList(List<T> newList) {
        ArrayList<T> list = new ArrayList<>(newList);
        DiffCallBack diffCallback = new DiffCallBack(list);
        setNewDiffData(diffCallback);
    }

    public abstract boolean areItemsTheSame(T oldItem, T newItem);

    public abstract boolean areContentsTheSame(T oldItem, T newItem);

    /**
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     */
    @Nullable
    protected Object getChangePayload(@NonNull T oldItem, @NonNull T newItem) {
        return null;
    }


    public class DiffCallBack extends BaseQuickDiffCallback<T> {

        public DiffCallBack(@Nullable List<T> newList) {
            super(newList);
        }

        @Override
        protected boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
            return BaseMultiAdapter.this.areItemsTheSame(oldItem, newItem);
        }

        @Override
        protected boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
            return BaseMultiAdapter.this.areContentsTheSame(oldItem, newItem);
        }

        @Nullable
        protected Object getChangePayload(@NonNull T oldItem, @NonNull T newItem) {
            return BaseMultiAdapter.this.getChangePayload(oldItem, newItem);
        }
    }
}

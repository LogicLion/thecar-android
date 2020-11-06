package com.xiulian.thecara.base;

import android.support.v7.util.DiffUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author wzh
 * @date 2020/11/6
 */
public abstract class BaseAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,K> {
    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }


    public abstract boolean areItemsTheSame(T oldItem, T newItem);
    public abstract boolean areContentsTheSame(T oldItem, T newItem);

    public class DiffCallback extends DiffUtil.Callback {

        private List<T> oldItems;
        private List<T> newItems;
        public DiffCallback(List<T> oldItems, List<T> newItems ) {
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return oldItems.size();
        }

        @Override
        public int getNewListSize() {
            return newItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }
    }
}

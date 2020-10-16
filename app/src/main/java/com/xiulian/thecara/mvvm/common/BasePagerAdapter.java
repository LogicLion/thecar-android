package com.xiulian.thecara.mvvm.common;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020/10/13
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter implements View.OnClickListener {
    public List<T> items = new ArrayList<>();
    private Context mContext;
    private int layoutId;

    public BasePagerAdapter(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mContext = container.getContext();
        View view = LayoutInflater.from(mContext).inflate(layoutId, container, false);
        view.setTag(position);
        view.setOnClickListener(this);
        onBind(view, items.get(position), position);
        container.addView(view);
        return view;
    }

    protected abstract void onBind(View containerView, T item, int position);

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();
        onItemClick(items.get(position), position);
    }

    public void onItemClick(T item, int position) {
    }
}

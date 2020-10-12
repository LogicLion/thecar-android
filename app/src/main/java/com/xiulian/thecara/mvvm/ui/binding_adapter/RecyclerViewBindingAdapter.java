package com.xiulian.thecara.mvvm.ui.binding_adapter;

import android.databinding.BindingAdapter;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

/**
 * @author wzh
 * @date 2020/10/10
 */
public class RecyclerViewBindingAdapter {

    @BindingAdapter(value = {"bindAdapter", "submitList", "autoScrollToTopWhenInsert", "autoScrollToBottomWhenInsert"}, requireAll = false)
    public static void bindList(RecyclerView recyclerView, ListAdapter adapter, List list,
                                boolean autoScrollToTopWhenInsert, boolean autoScrollToBottomWhenInsert) {

        if (recyclerView != null && list != null) {

            if (recyclerView.getAdapter() == null) {

                if (recyclerView.getLayoutManager() != null) {

                    if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                        int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
                        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), spanCount));

                    } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

                    } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                        int spanCount = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
                        int orientation = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).getOrientation();
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, orientation));
                    }
                }

                recyclerView.setAdapter(adapter);

                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        if (autoScrollToTopWhenInsert) {
                            recyclerView.scrollToPosition(0);
                        } else if (autoScrollToBottomWhenInsert) {
                            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount());
                        }
                    }
                });
            }

            adapter.submitList(list);
        }
    }


    @BindingAdapter(value = {"notifyCurrentListChanged"})
    public static void notifyListChanged(RecyclerView recyclerView, boolean notify) {
        if (notify && recyclerView != null && recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}

package com.xiulian.thecara.mvvm.mall;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.diff.BaseQuickDiffCallback;
import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseFragment;
import com.xiulian.thecara.entity.NewsInfo;
import com.xiulian.thecara.mvvm.adapter.HomeNewsAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城
 * @author wzh
 * @date 2020/9/23
 */
 public class MallFragment extends BaseFragment {

    private ArrayList<NewsInfo> mInitList;
    private HomeNewsAdapter mHomeNewsAdapter;
    private ArrayList<NewsInfo> loadList=new ArrayList<>();

    @Override
    public int setupContentLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    public void initViewModel() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        RecyclerView rv = findViewById(R.id.rv);
        mInitList = new ArrayList<>();
        mInitList.add(new NewsInfo("资讯1",1));
        mInitList.add(new NewsInfo("资讯2",2));
        mInitList.add(new NewsInfo("资讯3",3));
        mInitList.add(new NewsInfo("资讯3",3));
        mInitList.add(new NewsInfo("资讯3",2));
        mInitList.add(new NewsInfo("资讯3",1));

        mHomeNewsAdapter = new HomeNewsAdapter(mInitList);
//        HomeNewsAdapter.NewsDiffCallBack newsDiffCallBack = new HomeNewsAdapter.NewsDiffCallBack(mInitList);
//        mHomeNewsAdapter.setNewDiffData(newsDiffCallBack);
        View headerView = View.inflate(getContext(), R.layout.header_view, null);
        mHomeNewsAdapter.addHeaderView(headerView);

        TextView tvHeader = headerView.findViewById(R.id.tv_header);
        tvHeader.setText("真的顶");
        mHomeNewsAdapter.setOnLoadMoreListener(() -> {
            Log.v("加载更多", "加载更多");
            getMoreData();
        },rv);
        mHomeNewsAdapter.setEnableLoadMore(true);
        rv.setAdapter(mHomeNewsAdapter);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//        HomeNewsAdapter.NewsDiffCallBack newsDiffCallBack = new HomeNewsAdapter.NewsDiffCallBack(newList);
//        mHomeNewsAdapter.setNewDiffData(newsDiffCallBack);
            ArrayList<NewsInfo> newList = new ArrayList<>();
            List<NewsInfo> oldList = mHomeNewsAdapter.getData();
            newList.addAll(oldList);
            newList.addAll(mInitList);
            DiffCallback<NewsInfo> diffCallback = new DiffCallback<>(oldList, newList);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
//            mHomeNewsAdapter.setNewData(loadList);
            mHomeNewsAdapter.setNewDiffData(diffResult,newList);
            mHomeNewsAdapter.loadMoreComplete();
        }
    };

   public static MallFragment getInstance() {
      return new MallFragment();
   }


    public void getMoreData() {
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }


    public class DiffCallback<T> extends DiffUtil.Callback {

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
            return oldItemPosition==newItemPosition;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }
    }
}

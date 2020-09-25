package com.xiulian.thecara.mvp.news;

import android.os.Bundle;

import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseFragment;

import org.jetbrains.annotations.Nullable;

/**
 * 资讯
 * @author wzh
 * @date 2020/9/23
 */
 public class NewsFragment extends BaseFragment {
    @Override
    public int setupContentLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void setupPresenter() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

   public static NewsFragment getInstance() {
      return new NewsFragment();
   }


}

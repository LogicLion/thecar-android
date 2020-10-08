package com.xiulian.thecara.mvvm.mall;

import android.os.Bundle;

import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseFragment;

import org.jetbrains.annotations.Nullable;

/**
 * 商城
 * @author wzh
 * @date 2020/9/23
 */
 public class MallFragment extends BaseFragment {
    @Override
    public int setupContentLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    public void initViewModel() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

   public static MallFragment getInstance() {
      return new MallFragment();
   }

}

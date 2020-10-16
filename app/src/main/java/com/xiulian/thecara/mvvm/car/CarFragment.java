package com.xiulian.thecara.mvvm.car;

import android.os.Bundle;

import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseFragment;

import org.jetbrains.annotations.Nullable;

/**
 * 车管家
 * @author wzh
 * @date 2020/9/23
 */
 public class CarFragment extends BaseFragment {
    @Override
    public int setupContentLayoutId() {
        return R.layout.fragment_car;
    }

    @Override
    public void initViewModel() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

   public static CarFragment getInstance() {
      return new CarFragment();
   }

}

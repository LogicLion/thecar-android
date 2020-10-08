package com.xiulian.thecara.mvvm.investment;

import android.os.Bundle;

import com.xiulian.thecara.R;
import com.xiulian.thecara.base.BaseFragment;
import com.xiulian.thecara.mvvm.ui.DataBindingConfig;

import org.jetbrains.annotations.Nullable;

/**
 * 投资
 * @author wzh
 * @date 2020/9/23
 */
 public class InvestmentFragment extends BaseFragment {
    @Override
    public int setupContentLayoutId() {
        return R.layout.fragment_investment;
    }

    @Override
    public void initViewModel() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

   public static InvestmentFragment getInstance() {
       return new InvestmentFragment();
   }


}

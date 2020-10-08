package com.xiulian.thecara.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiulian.thecara.mvvm.ui.DataBindingConfig;
import com.xiulian.thecara.widget.TitleBar;

import org.jetbrains.annotations.Nullable;

/**
 * @author wzh
 * @date 2020/10/8
 */
public abstract class MvvmFragment extends Fragment {

    protected AppCompatActivity mActivity;

    protected TitleBar titleBar;
    protected ViewGroup contentView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
    }

    protected abstract void initViewModel();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DataBindingConfig dataBindingConfig = getDataBindingConfig();


        ViewDataBinding binding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        binding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        return binding.getRoot();
    }





    protected abstract DataBindingConfig getDataBindingConfig();

}

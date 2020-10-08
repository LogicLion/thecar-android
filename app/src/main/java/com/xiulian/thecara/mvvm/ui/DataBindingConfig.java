package com.xiulian.thecara.mvvm.ui;

import android.arch.lifecycle.ViewModel;
import android.util.SparseArray;

/**
 * @author wzh
 * @date 2020/10/8
 */
public class DataBindingConfig {
    private int layout;

    private int vmVariableId;

    private ViewModel stateViewModel;

    private SparseArray bindingParams = new SparseArray();

    public DataBindingConfig(int layout, int vmVariableId, ViewModel stateViewModel) {
        this.layout = layout;
        this.vmVariableId = vmVariableId;
        this.stateViewModel = stateViewModel;
    }

    public int getLayout() {
        return layout;
    }

    public int getVmVariableId() {
        return vmVariableId;
    }

    public ViewModel getStateViewModel() {
        return stateViewModel;
    }

    public SparseArray getBindingParams() {
        return bindingParams;
    }

    public DataBindingConfig addBindingParam(int variableId, Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }

}

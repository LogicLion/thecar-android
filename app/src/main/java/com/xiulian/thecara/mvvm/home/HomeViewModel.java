package com.xiulian.thecara.mvvm.home;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.xiulian.thecara.entity.BaseResponse;
import com.xiulian.thecara.entity.VersionInfoBean;
import com.xiulian.thecara.mvvm.DataRepository;

import io.reactivex.Single;

/**
 * ViewModel必须是public class
 * @author wzh
 * @date 2020/9/24
 */
public class HomeViewModel extends ViewModel {


    public final ObservableInt versionCode = new ObservableInt();
    private DataRepository dataRepository = new DataRepository();

    public Single<BaseResponse<VersionInfoBean>> getVersionInfo() {
        return dataRepository.getAppVersion();
    }

}

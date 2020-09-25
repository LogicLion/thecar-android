package com.xiulian.thecara.mvp.home;

import com.mvp.base.base.BasePresenter;
import com.xiulian.thecara.mvp.DataRepository;

import io.reactivex.disposables.Disposable;

/**
 * @author wzh
 * @date 2020/9/24
 */
class HomePresenter extends BasePresenter<HomeContract> {
    private DataRepository dataRepository = new DataRepository();

    public void getVersionCode() {

        Disposable disposable = dataRepository.getAppVersion()
                .subscribe(versionInfo -> {
                    view.setAppVersion(versionInfo.getAppVersionCode());
                }, throwable -> {

                });

        addToSubscriptions(disposable);
    }

}

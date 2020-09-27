package com.xiulian.thecara.mvp.home;

import com.xiulian.thecara.entity.BaseResponse;
import com.xiulian.thecara.entity.VersionInfoBean;
import com.xiulian.thecara.mvp.DataRepository;

import io.reactivex.Single;

/**
 * @author wzh
 * @date 2020/9/24
 */
class HomeViewModel {
    private DataRepository dataRepository = new DataRepository();

    public Single<BaseResponse<VersionInfoBean>> getVersionCode() {
        return dataRepository.getAppVersion();
    }
}

package com.xiulian.thecara.entity;

import java.util.List;

/**
 * @author wzh
 * @date 2020/10/9
 */
public class BannerInfo {

    /**
     * _id : 5f62ca49db3d957b6bdf8f05
     * updated : 2020-09-30T03:36:28.602Z
     * created : 2020-09-17T02:30:33.026Z
     * focusType : 3
     * title : H5普通链接
     * onlineTime : 2020-08-31T16:00:00.000Z
     * offlineTime : 2020-10-30T16:00:00.000Z
     * internalLink : 34
     * externalLink : https://group.gzywudao.top/php/public/miniapp.php/test/xiulian?thirdParty=thirdParty&appToken=appToken&title=title&id=id&activityId=activityId&activityInfo=activityInfo&userId=userId&phone=mobile&merchant_name=userName&longitude=longitude&latitude=latitude
     * imgId : ["5f6d5bef31b4265cff9e6296"]
     * login : 1
     * platform : ["1"]
     * clickTimes : 55
     * useStatus : 1
     * order : 100
     * thirdParty : bicai
     * excludeCityCode : []
     * cityCode : []
     * bannerType : home
     * __v : 0
     * updatedby : null
     * popupClose : 2
     * popupPosition : 0
     * popupTimes : 2
     */

    private String _id;
    private String updated;
    private String created;
    private int focusType;
    private String title;
    private String onlineTime;
    private String offlineTime;
    private int internalLink;
    private String externalLink;
    private String login;
    private int clickTimes;
    private int useStatus;
    private int order;
    private String thirdParty;
    private String bannerType;
    private int __v;
    private Object updatedby;
    private int popupClose;
    private int popupPosition;
    private int popupTimes;
    private List<String> imgId;
    private List<String> platform;
    private List<?> excludeCityCode;
    private List<?> cityCode;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getFocusType() {
        return focusType;
    }

    public void setFocusType(int focusType) {
        this.focusType = focusType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime;
    }

    public int getInternalLink() {
        return internalLink;
    }

    public void setInternalLink(int internalLink) {
        this.internalLink = internalLink;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getClickTimes() {
        return clickTimes;
    }

    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Object getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(Object updatedby) {
        this.updatedby = updatedby;
    }

    public int getPopupClose() {
        return popupClose;
    }

    public void setPopupClose(int popupClose) {
        this.popupClose = popupClose;
    }

    public int getPopupPosition() {
        return popupPosition;
    }

    public void setPopupPosition(int popupPosition) {
        this.popupPosition = popupPosition;
    }

    public int getPopupTimes() {
        return popupTimes;
    }

    public void setPopupTimes(int popupTimes) {
        this.popupTimes = popupTimes;
    }

    public List<String> getImgId() {
        return imgId;
    }

    public void setImgId(List<String> imgId) {
        this.imgId = imgId;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public void setPlatform(List<String> platform) {
        this.platform = platform;
    }

    public List<?> getExcludeCityCode() {
        return excludeCityCode;
    }

    public void setExcludeCityCode(List<?> excludeCityCode) {
        this.excludeCityCode = excludeCityCode;
    }

    public List<?> getCityCode() {
        return cityCode;
    }

    public void setCityCode(List<?> cityCode) {
        this.cityCode = cityCode;
    }
}

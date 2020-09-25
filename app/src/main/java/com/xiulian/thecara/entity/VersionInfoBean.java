package com.xiulian.thecara.entity;

import java.util.List;

/**
 * @author wzh
 * @date 2020/9/24
 */
public class VersionInfoBean {


    /**
     * downloadLink : /api/owner/aiche/app/apk/download?apkId=5d7a0ef68a54d072e7dd7159
     * _id : 5d7a0f598a54d072e7dd71ec
     * updated : 2019-09-12T09:26:49.076Z
     * created : 2019-09-12T09:26:49.076Z
     * appVersionCode : 32
     * appVersionName :
     * updateContent : 加固后的正式包
     * timer : null
     * apkId : 5d7a0ef68a54d072e7dd7159
     * appVersion : 3.0.0
     * publisher : root
     * __v : 0
     * isStartUp : 0
     * startup : 1
     * publishType : 1
     * isForce : 0
     * appType : 1
     * appVersionArray : []
     * status : active
     */

    private String downloadLink;
    private String _id;
    private String updated;
    private String created;
    private int appVersionCode;
    private String appVersionName;
    private String updateContent;
    private Object timer;
    private String apkId;
    private String appVersion;
    private String publisher;
    private int __v;
    private int isStartUp;
    private int startup;
    private int publishType;
    private int isForce;
    private int appType;
    private String status;
    private List<?> appVersionArray;

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

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

    public int getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(int appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public Object getTimer() {
        return timer;
    }

    public void setTimer(Object timer) {
        this.timer = timer;
    }

    public String getApkId() {
        return apkId;
    }

    public void setApkId(String apkId) {
        this.apkId = apkId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public int getIsStartUp() {
        return isStartUp;
    }

    public void setIsStartUp(int isStartUp) {
        this.isStartUp = isStartUp;
    }

    public int getStartup() {
        return startup;
    }

    public void setStartup(int startup) {
        this.startup = startup;
    }

    public int getPublishType() {
        return publishType;
    }

    public void setPublishType(int publishType) {
        this.publishType = publishType;
    }

    public int getIsForce() {
        return isForce;
    }

    public void setIsForce(int isForce) {
        this.isForce = isForce;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getAppVersionArray() {
        return appVersionArray;
    }

    public void setAppVersionArray(List<?> appVersionArray) {
        this.appVersionArray = appVersionArray;
    }
}

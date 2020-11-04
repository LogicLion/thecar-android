package com.xiulian.thecara.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 资讯
 *
 * @author wzh
 * @date 2020/10/10
 */
public class NewsInfo implements MultiItemEntity {
    String name;
    int type;

    public NewsInfo() {
    }

    public NewsInfo(String name) {
        this.name = name;
    }

    public NewsInfo(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }


    @Override
    public String toString() {
        return "NewsInfo{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}

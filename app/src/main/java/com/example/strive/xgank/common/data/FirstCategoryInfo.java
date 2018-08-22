package com.example.strive.xgank.common.data;

/**
 * Created by strive on 2018/8/22.
 * 闲度一级分类
 */

public class FirstCategoryInfo {

    /**
     * _id : 57c83777421aa97cbd81c74d
     * en_name : wow
     * name : 科技资讯
     * rank : 1
     */

    private String _id;
    private String en_name;
    private String name;
    private int rank;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "FirstCategoryInfo{" +
                "_id='" + _id + '\'' +
                ", en_name='" + en_name + '\'' +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}

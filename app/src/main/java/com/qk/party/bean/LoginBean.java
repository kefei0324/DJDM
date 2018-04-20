package com.qk.party.bean;

import org.litepal.crud.DataSupport;

/**
 * @author ：Think
 * 创建于 2017/10/17 16:36
 */

public class LoginBean extends DataSupport {

    private String access_token;
    private int dzzbm;
    private String xm;
    private String name;
    private String zwjb;
    private String photos;
    private int userId;
    private int sfqy;
    private String zzmc;
    private String sjhm;
    private boolean isgeneraldy;
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getDzzbm() {
        return dzzbm;
    }

    public void setDzzbm(int dzzbm) {
        this.dzzbm = dzzbm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZwjb() {
        return zwjb;
    }

    public void setZwjb(String zwjb) {
        this.zwjb = zwjb;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSfqy() {
        return sfqy;
    }

    public void setSfqy(int sfqy) {
        this.sfqy = sfqy;
    }

    public String getZzmc() {
        return zzmc;
    }

    public void setZzmc(String zzmc) {
        this.zzmc = zzmc;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public boolean isIsgeneraldy() {
        return isgeneraldy;
    }

    public void setIsgeneraldy(boolean isgeneraldy) {
        this.isgeneraldy = isgeneraldy;
    }
}

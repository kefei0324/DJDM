package com.qk.party.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @package： com.qk.party.bean
 * @class: NotifyBean
 * @author:  小飞
 * @date: 2017/10/27 10:02
 * @描述：
 */
public class NotifyBean implements MultiItemEntity {
    private int type;
    private int multiType;
    private int id;
    private String bt;
    private String time;
    private String cjsj;
    private String gxsj;
    private int status;
    private String origin_id;
    private String typeString;
    private String tznr;
    private String source;
    private String person_in_charge;
    private String dept;
    private String hydd;

    public String getHydd() {
        return hydd;
    }

    public void setHydd(String hydd) {
        this.hydd = hydd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMultiType() {
        return multiType;
    }

    public void setMultiType(int multiType) {
        this.multiType = multiType;
    }

    @Override
    public int getItemType() {
        return multiType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(String origin_id) {
        this.origin_id = origin_id;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public String getTznr() {
        return tznr;
    }

    public void setTznr(String tznr) {
        this.tznr = tznr;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPerson_in_charge() {
        return person_in_charge;
    }

    public void setPerson_in_charge(String person_in_charge) {
        this.person_in_charge = person_in_charge;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}

package com.huban.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Lesson {
    private Short lid;

    private String lname;

    private Short parentid;
    @JSONField(serialize=false)
    private Short isdelete;
    @JSONField(serialize=false)
    private Short level;

    public Short getLid() {
        return lid;
    }

    public void setLid(Short lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname == null ? null : lname.trim();
    }

    public Short getParentid() {
        return parentid;
    }

    public void setParentid(Short parentid) {
        this.parentid = parentid;
    }

    public Short getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Short isdelete) {
        this.isdelete = isdelete;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }
}
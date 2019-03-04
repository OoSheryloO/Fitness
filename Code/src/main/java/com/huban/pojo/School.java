package com.huban.pojo;

public class School {
    private Integer id;

    private String schoolname;

    private Short cityId;

    private Short schoolType;

    private String firstPy;

    private String py;

    private Short countyId;

    private Short provinceId;

    private Short status;

    private Short sctype;

    private Short userDefine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname == null ? null : schoolname.trim();
    }

    public Short getCityId() {
        return cityId;
    }

    public void setCityId(Short cityId) {
        this.cityId = cityId;
    }

    public Short getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Short schoolType) {
        this.schoolType = schoolType;
    }

    public String getFirstPy() {
        return firstPy;
    }

    public void setFirstPy(String firstPy) {
        this.firstPy = firstPy == null ? null : firstPy.trim();
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py == null ? null : py.trim();
    }

    public Short getCountyId() {
        return countyId;
    }

    public void setCountyId(Short countyId) {
        this.countyId = countyId;
    }

    public Short getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Short provinceId) {
        this.provinceId = provinceId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getSctype() {
        return sctype;
    }

    public void setSctype(Short sctype) {
        this.sctype = sctype;
    }

    public Short getUserDefine() {
        return userDefine;
    }

    public void setUserDefine(Short userDefine) {
        this.userDefine = userDefine;
    }
}
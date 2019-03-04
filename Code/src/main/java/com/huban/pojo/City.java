package com.huban.pojo;

public class City {
    private Short id;

    private String name;

    private Short parentid;

    private Short level;

    private String first;

    private Integer ismunicipality;

    private Integer hasschool;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Short getParentid() {
        return parentid;
    }

    public void setParentid(Short parentid) {
        this.parentid = parentid;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first == null ? null : first.trim();
    }

    public Integer getIsmunicipality() {
        return ismunicipality;
    }

    public void setIsmunicipality(Integer ismunicipality) {
        this.ismunicipality = ismunicipality;
    }

    public Integer getHasschool() {
        return hasschool;
    }

    public void setHasschool(Integer hasschool) {
        this.hasschool = hasschool;
    }
}
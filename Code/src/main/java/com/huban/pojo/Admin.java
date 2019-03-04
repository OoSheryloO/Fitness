package com.huban.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.huban.util.IdWorker;

import java.util.Date;

/**
 * Created by MeetLucky on 16/5/17.
 */
@SuppressWarnings("unchecked")
public class Admin implements java.io.Serializable{ // 常用地址
    //  属性名称
    public static final String adminClass = "admin";
    public static final String attributeAdminID = "iD";
    public static final String attributeAdminName = "name";
    public static final String attributeAdminPassWord = "passWord";
    public static final String attributeAdminNickName = "nickName";
    public static final String attributeAdminImageID = "imageID";
    public static final String attributeAdminPhone = "phone";
    public static final String attributeAdminLevel = "level";
    public static final String attributeAdminParentID = "parentID";
    public static final String attributeAdminMemo = "memo";
    public static final String attributeAdminCreateTime = "createTime";
    public static final String attributeAdminModifyTime = "modifyTime";
    public static final String attributeAdminVersion = "version";
    public static final String attributeAdminStatus = "status";
    public static final String attributeAdminDeleted = "deleted";

    //  用户信息
    private long iD;//编号
    private String name;//帐号
    private String pkid;
    private String passWord;//密码
    private String nickName;//昵称
    private String imageID;//头像编号
    private String phone;//手机
    private int level;//级别
    private String memo;//备注
    private Long parentID;//创建人
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间

    //  结构信息
    @JSONField(serialize=false)
    private int version;//修改次数
    private int status;//状态信息
    @JSONField(serialize=false)
    private int deleted;//是否删除

    private String ParentName;//是否删除
    private User user;
    private UserInfo userInfo;

    public Admin() {
        super();
        this.iD= IdWorker.CreateNewID();
    }
    public Admin(Long id) {
        super();
        this.iD= id;
    }


    /**d
	 * @return user
	 */
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public long getID() {
        return iD;
    }

    public void setID(long iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getPhone() {
        return (phone==null || phone.equals("null"))?"":phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
	@Override
	public String toString() {
		return "Admin [iD=" + iD + ", name=" + name + ", pkid=" + pkid + ", passWord=" + passWord + ", nickName="
				+ nickName + ", imageID=" + imageID + ", phone=" + phone + ", level=" + level + ", memo=" + memo
				+ ", parentID=" + parentID + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", version="
				+ version + ", status=" + status + ", deleted=" + deleted + ", ParentName=" + ParentName + "]";
	}

}


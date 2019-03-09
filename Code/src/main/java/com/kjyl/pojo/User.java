package com.kjyl.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

/**
 * <p> Entity Class</p>
 * @author sheryl
 */
@Getter
@Setter
@ApiModel(value = "User")
public class User implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_HeadIcon = "HeadIcon";

    public static final String COLUMN_Name = "Name";

    public static final String COLUMN_Sex = "Sex";
    
    public static final String COLUMN_Birthday = "Birthday";

    public static final String COLUMN_Phone = "Phone";

    public static final String COLUMN_QQ = "QQ";

    public static final String COLUMN_MicroBlog = "MicroBlog";

    public static final String COLUMN_QRCode = "QRCode";

    public static final String COLUMN_WeChatOpenId = "WeChatOpenId";

    public static final String COLUMN_Password = "Password";

    public static final String COLUMN_PayPassword = "PayPassword";

    public static final String COLUMN_City = "City";

    public static final String COLUMN_FitClub = "FitClub";

    public static final String COLUMN_Level = "Level";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "用户")
    private String Id;

    @ApiModelProperty(value = "用户头像")
    private String HeadIcon;

    @ApiModelProperty(value = "昵称")
    private String Name;

    @ApiModelProperty(value = "用户性别")
    private Integer Sex;
    
    @ApiModelProperty(value = "生日")
    private Date Birthday;

    @ApiModelProperty(value = "用户手机")
    private String Phone;

    @ApiModelProperty(value = "用户QQ")
    private String QQ;

    @ApiModelProperty(value = "用户微博")
    private String MicroBlog;

    @ApiModelProperty(value = "用户二维码")
    private String QRCode;

    @ApiModelProperty(value = "微信openId")
    private String WeChatOpenId;

    @ApiModelProperty(value = "用户登录密码")
    @JSONField(serialize = false)
    private String Password;

    @ApiModelProperty(value = "用户支付密码")
    @JSONField(serialize = false)
    private String PayPassword;

    @ApiModelProperty(value = "用户城市")
    private String City;

    @ApiModelProperty(value = "俱乐部Id")
    private String FitClub;

    @ApiModelProperty(value = "等级")
    private Integer Level;

    @ApiModelProperty(value = "状态")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','用户']['HeadIcon','用户头像']['Name','昵称']['Sex','用户性别']['Birthday','生日']['Phone','用户手机']['QQ','用户QQ']['MicroBlog','用户微博']['QRCode','用户二维码']['WeChatOpenId','微信openId']['Password','用户登录密码']['PayPassword','用户支付密码']['City','用户城市']['FitClub','俱乐部Id']['Level','等级']['Status','状态']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String UserField;
}

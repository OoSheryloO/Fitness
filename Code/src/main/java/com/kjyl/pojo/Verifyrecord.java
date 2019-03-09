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
@ApiModel(value = "Verifyrecord")
public class Verifyrecord implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_Phone = "Phone";

    public static final String COLUMN_CheckNumber = "CheckNumber";

    public static final String COLUMN_DisableTime = "DisableTime";

    public static final String COLUMN_EnableTime = "EnableTime";

    public static final String COLUMN_Version = "Version";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "短信")
    private String Id;

    @ApiModelProperty(value = "使用Id")
    private String UseId;

    @ApiModelProperty(value = "登录手机")
    private String Phone;

    @ApiModelProperty(value = "验证码")
    private String CheckNumber;

    @ApiModelProperty(value = "禁用开始")
    private Date DisableTime;

    @ApiModelProperty(value = "禁用结束")
    private Date EnableTime;

    @ApiModelProperty(value = "修改次数")
    private Integer Version;

    @ApiModelProperty(value = "状态信息")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','短信']['UseId','使用Id']['Phone','登录手机']['CheckNumber','验证码']['DisableTime','禁用开始']['EnableTime','禁用结束']['Version','修改次数']['Status','状态信息']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String VerifyrecordField;
}

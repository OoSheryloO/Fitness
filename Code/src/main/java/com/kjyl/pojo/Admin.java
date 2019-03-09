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
@ApiModel(value = "Admin")
public class Admin implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_Name = "Name";

    public static final String COLUMN_PassWord = "PassWord";

    public static final String COLUMN_NickName = "NickName";

    public static final String COLUMN_Image = "Image";

    public static final String COLUMN_Phone = "Phone";

    public static final String COLUMN_Level = "Level";

    public static final String COLUMN_Version = "Version";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_ParentId = "ParentId";

    public static final String COLUMN_AreaId = "AreaId";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "管理员")
    private String Id;

    @ApiModelProperty(value = "管理员帐号")
    private String Name;

    @ApiModelProperty(value = "密码")
    @JSONField(serialize = false)
    private String PassWord;

    @ApiModelProperty(value = "昵称")
    private String NickName;

    @ApiModelProperty(value = "头像URL")
    private String Image;

    @ApiModelProperty(value = "联系方式")
    private String Phone;

    @ApiModelProperty(value = "级别")
    private Integer Level;

    @ApiModelProperty(value = "修改次数")
    private Integer Version;

    @ApiModelProperty(value = "备注")
    private String Memo;

    @ApiModelProperty(value = "创建人Id")
    private String ParentId;

    @ApiModelProperty(value = "所属地区Id")
    private String AreaId;

    @ApiModelProperty(value = "状态")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','管理员']['Name','管理员帐号']['PassWord','密码']['NickName','昵称']['Image','头像URL']['Phone','联系方式']['Level','级别']['Version','修改次数']['Memo','备注']['ParentId','创建人Id']['AreaId','所属地区Id']['Status','状态']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String AdminField;
}

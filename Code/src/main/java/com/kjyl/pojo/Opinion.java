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
@ApiModel(value = "Opinion")
public class Opinion implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_LogicId = "LogicId";

    public static final String COLUMN_Phone = "Phone";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Content = "Content";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_Type = "Type";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "意见-对象")
    private String Id;

    @ApiModelProperty(value = "使用Id")
    private String UseId;

    @ApiModelProperty(value = "逻辑Id")
    private String LogicId;

    @ApiModelProperty(value = "手机号")
    private String Phone;

    @ApiModelProperty(value = "标题")
    private String Title;

    @ApiModelProperty(value = "内容")
    private String Content;

    @ApiModelProperty(value = "备注")
    private String Memo;

    @ApiModelProperty(value = "类型")
    private Integer Type;

    @ApiModelProperty(value = "状态信息")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','意见-对象']['UseId','使用Id']['LogicId','逻辑Id']['Phone','手机号']['Title','标题']['Content','内容']['Memo','备注']['Type','类型']['Status','状态信息']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String OpinionField;
}

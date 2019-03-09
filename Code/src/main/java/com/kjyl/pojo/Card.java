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
@ApiModel(value = "Card")
public class Card implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_HeadIcon = "HeadIcon";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Intro = "Intro";

    public static final String COLUMN_Price = "Price";

    public static final String COLUMN_Level = "Level";

    public static final String COLUMN_ValidTime = "ValidTime";

    public static final String COLUMN_Discount = "Discount";

    public static final String COLUMN_Type = "Type";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "卡片")
    private String Id;

    @ApiModelProperty(value = "使用Id")
    private String UseId;

    @ApiModelProperty(value = "图片")
    private String HeadIcon;

    @ApiModelProperty(value = "名称")
    private String Title;

    @ApiModelProperty(value = "介绍")
    private String Intro;

    @ApiModelProperty(value = "价格")
    private String Price;

    @ApiModelProperty(value = "等级")
    private Integer Level;

    @ApiModelProperty(value = "有效时间")
    private String ValidTime;

    @ApiModelProperty(value = "折扣")
    private String Discount;

    @ApiModelProperty(value = "类型")
    private Integer Type;

    @ApiModelProperty(value = "备注")
    private String Memo;

    @ApiModelProperty(value = "状态")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','卡片']['UseId','使用Id']['HeadIcon','图片']['Title','名称']['Intro','介绍']['Price','价格']['Level','等级']['ValidTime','有效时间']['Discount','折扣']['Type','类型']['Memo','备注']['Status','状态']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String CardField;
}

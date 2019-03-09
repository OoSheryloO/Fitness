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
@ApiModel(value = "Goods")
public class Goods implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_LogicId = "LogicId";

    public static final String COLUMN_HeadIcon = "HeadIcon";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Intro = "Intro";

    public static final String COLUMN_Price = "Price";

    public static final String COLUMN_FreeShip = "FreeShip";

    public static final String COLUMN_FreeFirst = "FreeFirst";

    public static final String COLUMN_RefundDay = "RefundDay";

    public static final String COLUMN_Delivery = "Delivery";

    public static final String COLUMN_Type = "Type";

    public static final String COLUMN_Size = "Size";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "商品-俱乐部")
    private String Id;

    @ApiModelProperty(value = "逻辑Id")
    private String LogicId;

    @ApiModelProperty(value = "头像")
    private String HeadIcon;

    @ApiModelProperty(value = "名称")
    private String Title;

    @ApiModelProperty(value = "介绍")
    private String Intro;

    @ApiModelProperty(value = "价格")
    private String Price;

    @ApiModelProperty(value = "免邮")
    private String FreeShip;

    @ApiModelProperty(value = "是否首单包邮")
    private Integer FreeFirst;

    @ApiModelProperty(value = "退货时间/d")
    private Integer RefundDay;

    @ApiModelProperty(value = "配送地区")
    private String Delivery;

    @ApiModelProperty(value = "类型")
    private Integer Type;

    @ApiModelProperty(value = "规格")
    private String Size;

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

	@ApiModelProperty(value = "['Id','商品-俱乐部']['LogicId','逻辑Id']['HeadIcon','头像']['Title','名称']['Intro','介绍']['Price','价格']['FreeShip','免邮']['FreeFirst','是否首单包邮']['RefundDay','退货时间/d']['Delivery','配送地区']['Type','类型']['Size','规格']['Memo','备注']['Status','状态']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String GoodsField;
}

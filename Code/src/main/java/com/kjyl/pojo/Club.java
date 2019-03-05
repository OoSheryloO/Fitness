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
@ApiModel(value = "Club")
public class Club implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Theme = "Theme";

    public static final String COLUMN_Intro = "Intro";

    public static final String COLUMN_Site = "Site";

    public static final String COLUMN_Tel = "Tel";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "俱乐部ID")
    private String Id;

    @ApiModelProperty(value = "用户编号/创始人")
    private String UseId;

    @ApiModelProperty(value = "标题")
    private String Title;

    @ApiModelProperty(value = "主题")
    private String Theme;

    @ApiModelProperty(value = "介绍")
    private String Intro;

    @ApiModelProperty(value = "地址")
    private String Site;

    @ApiModelProperty(value = "座机")
    private String Tel;

    @ApiModelProperty(value = "备注")
    private String Memo;

    @ApiModelProperty(value = "状态信息")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','俱乐部ID']['UseId','用户编号/创始人']['Title','标题']['Theme','主题']['Intro','介绍']['Site','地址']['Tel','座机']['Memo','备注']['Status','状态信息']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String ClubField;
}

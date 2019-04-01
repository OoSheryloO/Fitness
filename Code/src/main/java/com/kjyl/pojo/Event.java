package com.kjyl.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p> Entity Class</p>
 * @author sheryl
 */
@Getter
@Setter
@ApiModel(value = "Event")
public class Event implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_LogicId = "LogicId";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Intro = "Intro";

    public static final String COLUMN_Site = "Site";

    public static final String COLUMN_StartTime = "StartTime";

    public static final String COLUMN_EndTime = "EndTime";

    public static final String COLUMN_StartApply = "StartApply";

    public static final String COLUMN_EndApply = "EndApply";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "活动-俱乐部")
    private String Id;

    @ApiModelProperty(value = "使用Id")
    private String UseId;

    @ApiModelProperty(value = "逻辑Id")
    private String LogicId;
    
    @ApiModelProperty(value = "图片")
    private List<String> Picture;

    @ApiModelProperty(value = "标题")
    private String Title;

    @ApiModelProperty(value = "内容")
    private String Intro;

    @ApiModelProperty(value = "地点")
    private String Site;

    @ApiModelProperty(value = "开始时间")
    private Date StartTime;

    @ApiModelProperty(value = "结束时间")
    private Date EndTime;

    @ApiModelProperty(value = "开始报名")
    private Date StartApply;

    @ApiModelProperty(value = "结束报名")
    private Date EndApply;

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

	@ApiModelProperty(value = "['Id','活动-俱乐部']['UseId','使用Id']['LogicId','逻辑Id']['Title','标题']['Intro','内容']['Site','地点']['StartTime','开始时间']['EndTime','结束时间']['StartApply','开始报名']['EndApply','结束报名']['Memo','备注']['Status','状态']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String EventField;
}

package com.kjyl.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.kjyl.bean.SimpleUser;

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
@ApiModel(value = "Course")
public class Course implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";
    
    public static final String COLUMN_LogicId = "LogicId";
    
    public static final String COLUMN_Picture = "Picture";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Info = "Info";

    public static final String COLUMN_StartTime = "StartTime";

    public static final String COLUMN_EndTime = "EndTime";

    public static final String COLUMN_ApplyTime = "ApplyTime";

    public static final String COLUMN_Apply = "Apply";
    
    public static final String COLUMN_ApplyLimit = "ApplyLimit";

    public static final String COLUMN_Price = "Price";

    public static final String COLUMN_Site = "Site";
    
    public static final String COLUMN_Sort = "Sort";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "课程")
    private String Id;

    @ApiModelProperty(value = "使用Id")
    private String UseId;
    
    @ApiModelProperty(value = "逻辑Id")
    private String LogicId;
    
    @ApiModelProperty(value = "图片")
    private List<String> Picture;

    @ApiModelProperty(value = "标题")
    private String Title;

    @ApiModelProperty(value = "介绍")
    private String Info;

    @ApiModelProperty(value = "开始时间")
    private Date StartTime;

    @ApiModelProperty(value = "结束时间")
    private Date EndTime;

    @ApiModelProperty(value = "预约时间")
    private Date ApplyTime;

    @ApiModelProperty(value = "人数限制")
    private Integer Apply;
    
    @ApiModelProperty(value = "人数限制")
    private Integer ApplyLimit;

    @ApiModelProperty(value = "价格")
    private String Price;

    @ApiModelProperty(value = "地点")
    private String Site;
    
    @ApiModelProperty(value = "排序")
    private Integer Sort;

    @ApiModelProperty(value = "备注")
    private String Memo;

    @ApiModelProperty(value = "预约状态")
    private boolean State;//判断是否预约
    
    @ApiModelProperty(value = "状态")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;
    
    @ApiModelProperty(value = "用户")
    private SimpleUser User;
    
    @ApiModelProperty(value = "身份")
    private Identity Identity;

	@ApiModelProperty(value = "['Id','课程']['UseId','使用Id']['Title','标题']['Info','介绍']['StartTime','开始时间']['EndTime','结束时间']['ApplyTime','预约时间']['Apply','人数限制']['Price','价格']['Site','地点']['Memo','备注']['Status','状态']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String CourseField;
}

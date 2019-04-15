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
@ApiModel(value = "Dict")
public class Dict implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_Value = "Value";

    public static final String COLUMN_Info = "Info";
    
    public static final String COLUMN_Level = "Level";
    
    public static final String COLUMN_LogicId = "LogicId";

    public static final String COLUMN_Type = "Type";
    
    public static final String COLUMN_BelongId = "BelongId";
    
    public static final String COLUMN_Sort = "Sort";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "Id")
    private String Id;

    @ApiModelProperty(value = "字典值")
    private Integer Value;

    @ApiModelProperty(value = "字典说明")
    private String Info;
    
    @ApiModelProperty(value = "级别")
    private Integer Level;
    
    @ApiModelProperty(value = "逻辑Id")
    private String LogicId;

    @ApiModelProperty(value = "字典类型")
    private Integer Type;
    
    @ApiModelProperty(value = "所属Id")
    private String BelongId;
    
    @ApiModelProperty(value = "排序")
    private Integer Sort;

    @ApiModelProperty(value = "删除标示")
    private Integer Status;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','Id']['Value','字典值']['Info','字典说明']['Type','字典类型']['Status','删除标示']['Level','级别']['LogicId','逻辑Id']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String DictField;
}

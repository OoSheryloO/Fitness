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
@ApiModel(value = "Identity")
public class Identity implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_Name = "Name";

    public static final String COLUMN_CardFront = "CardFront";

    public static final String COLUMN_CardVerso = "CardVerso";

    public static final String COLUMN_CardNumber = "CardNumber";

    public static final String COLUMN_Star = "Star";

    public static final String COLUMN_Level = "Level";

    public static final String COLUMN_Strong = "Strong";

    public static final String COLUMN_Info = "Info";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "身份")
    private String Id;

    @ApiModelProperty(value = "姓名")
    private String Name;

    @ApiModelProperty(value = "正面")
    private String CardFront;

    @ApiModelProperty(value = "反面")
    private String CardVerso;

    @ApiModelProperty(value = "号码")
    private String CardNumber;

    @ApiModelProperty(value = "星级")
    private String Star;

    @ApiModelProperty(value = "级别")
    private String Level;

    @ApiModelProperty(value = "特长")
    private String Strong;

    @ApiModelProperty(value = "介绍")
    private String Info;

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

	@ApiModelProperty(value = "['Id','身份']['Name','姓名']['CardFront','正面']['CardVerso','反面']['CardNumber','号码']['Star','星级']['Level','级别']['Strong','特长']['Info','介绍']['Memo','备注']['Status','状态信息']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String IdentityField;
}

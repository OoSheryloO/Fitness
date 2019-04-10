package com.kjyl.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p> Entity Class</p>
 * @author sheryl
 */
@Getter
@Setter
@ApiModel(value = "SimpleUser")
public class SimpleUser implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_HeadIcon = "HeadIcon";

    public static final String COLUMN_Name = "Name";

    public static final String COLUMN_Sex = "Sex";
    

    @ApiModelProperty(value = "用户")
    @JSONField(serialize = false)
    private String Id;

    @ApiModelProperty(value = "用户头像")
    private String HeadIcon;

    @ApiModelProperty(value = "昵称")
    private String Name;

    @ApiModelProperty(value = "用户性别")
    private Integer Sex;

//	@ApiModelProperty(value = "['Id','用户']['HeadIcon','用户头像']['Name','昵称']['Sex','用户性别']")
//	@JSONField(serialize = false)
//	public String UserField;
}

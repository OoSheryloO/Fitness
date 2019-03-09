package com.kjyl.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.kjyl.pojo.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * <p> Entity Class</p>
 * @author sheryl
 */
@Getter
@Setter
@ApiModel(value = "RankList")
public class RankList implements Serializable {

    /** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private String UseId;

    @ApiModelProperty(value = "次数")
    private Integer Number;
    
    @ApiModelProperty(value = "用户")
    private User User;

}

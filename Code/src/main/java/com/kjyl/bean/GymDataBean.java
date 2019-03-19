package com.kjyl.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

import com.kjyl.pojo.Card;
import com.kjyl.pojo.Identity;

/**
 * <p> Entity Class</p>
 * @author sheryl
 */
@Getter
@Setter
@ApiModel(value = "GymDataBean", description="健身数据")
public class GymDataBean implements Serializable {

    /** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;
	
//	@ApiModelProperty(value = "会员卡种类")
//    private String Course;
	
	@ApiModelProperty(value = "身份信息")
	private Identity Identity;
	
	@ApiModelProperty(value = "卡信息")
	private Card Card;

    @ApiModelProperty(value = "课程次数")
    private Integer Course;

    @ApiModelProperty(value = "体重记录")
    private Integer Weight;

    @ApiModelProperty(value = "体能测试")
    private Integer Fianco;
    
    @ApiModelProperty(value = "健身日记")
    private Integer Diary;
}

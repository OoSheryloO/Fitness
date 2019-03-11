package com.kjyl.bean;

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
@ApiModel(value = "GymDataBean", description="健身数据")
public class GymDataBean implements Serializable {

    /** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程次数")
    private Integer Course;

    @ApiModelProperty(value = "体重记录")
    private Integer Weight;

    @ApiModelProperty(value = "体能测试")
    private Integer Fianco;
    
    @ApiModelProperty(value = "健身日记")
    private Integer Diary;
}

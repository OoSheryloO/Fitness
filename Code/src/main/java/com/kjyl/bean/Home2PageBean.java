package com.kjyl.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

import com.kjyl.pojo.Course;
import com.kjyl.pojo.Info;

/**
 * <p> Entity Class</p>
 * @author sheryl
 */
@Getter
@Setter
@ApiModel(value = "Home2PageBean", description="次首页数据")
public class Home2PageBean implements Serializable {

    /** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否打卡")
    private boolean Clock;

    @ApiModelProperty(value = "推荐课程")
    private List<Course> Course;

    @ApiModelProperty(value = "资讯")
    private List<Info> Info;

//    @ApiModelProperty(value = "精英训练营")
//    private List<E> User;

}

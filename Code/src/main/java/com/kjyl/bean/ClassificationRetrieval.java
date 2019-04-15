package com.kjyl.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import com.kjyl.pojo.Card;
import com.kjyl.pojo.Goods;

/**
 * <p> Entity Class</p>
 * @author sheryl
 */
@Getter
@Setter
@ApiModel(value = "ClassificationRetrieval")
public class ClassificationRetrieval implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_Info = "Info";

    public static final String COLUMN_Type = "Type";

    public static final String COLUMN_Level = "Level";

    @ApiModelProperty(value = "Id")
    private String Id;

    @ApiModelProperty(value = "字典说明")
    private String Info;

    @ApiModelProperty(value = "字典类型")
    private Integer Type;

    @ApiModelProperty(value = "级别")
    private Integer Level;
    
    @ApiModelProperty(value = "商品")
    private List<Goods> Goods;
    
    @ApiModelProperty(value = "卡种")
    private List<Card> Card;
}

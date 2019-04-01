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
@ApiModel(value = "Info")
public class Info implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_LogicId = "LogicId";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Content = "Content";

    public static final String COLUMN_Like = "Like";

    public static final String COLUMN_Review = "Review";

    public static final String COLUMN_Share = "Share";
    
    public static final String COLUMN_Collect = "Collect";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "资讯-俱乐部")
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
    private String Content;

    @ApiModelProperty(value = "点赞")
    private Integer Like;

    @ApiModelProperty(value = "评论")
    private Integer Review;

    @ApiModelProperty(value = "分享数")
    private Integer Share;
    
    @ApiModelProperty(value = "收藏数")
    private Integer Collect;

    @ApiModelProperty(value = "状态信息")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;
    
    @ApiModelProperty(value = "其他状态")
    private Status State;

	@ApiModelProperty(value = "['Id','资讯-俱乐部']['UseId','使用Id']['LogicId','逻辑Id']['Title','标题']['Content','内容']['Like','点赞']['Review','评论']['Share','分享数']['Collect','收藏数']['Status','状态信息']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String InfoField;
}

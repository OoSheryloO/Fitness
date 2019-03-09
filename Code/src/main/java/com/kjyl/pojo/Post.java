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
@ApiModel(value = "Post")
public class Post implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_TopicId = "TopicId";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Content = "Content";

    public static final String COLUMN_Like = "Like";

    public static final String COLUMN_Review = "Review";

    public static final String COLUMN_Collect = "Collect";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "帖子-打卡")
    private String Id;

    @ApiModelProperty(value = "使用Id")
    private String UseId;

    @ApiModelProperty(value = "逻辑Id")
    private String TopicId;

    @ApiModelProperty(value = "评论标题")
    private String Title;

    @ApiModelProperty(value = "内容")
    private String Content;

    @ApiModelProperty(value = "点赞数")
    private Integer Like;

    @ApiModelProperty(value = "评论数")
    private Integer Review;

    @ApiModelProperty(value = "收藏数")
    private Integer Collect;

    @ApiModelProperty(value = "状态")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','帖子-打卡']['UseId','使用Id']['TopicId','逻辑Id']['Title','评论标题']['Content','内容']['Like','点赞数']['Review','评论数']['Collect','收藏数']['Status','状态']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String PostField;
}

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
@ApiModel(value = "Picture")
public class Picture implements Serializable {
	/** 
	* @Fields field:field: {todo}() 
	*/ 
	private static final long serialVersionUID = 1L;

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_TypeId = "TypeId";

    public static final String COLUMN_Identity = "Identity";

    public static final String COLUMN_Purpose = "Purpose";

    public static final String COLUMN_BelongId = "BelongId";

    public static final String COLUMN_Title = "Title";

    public static final String COLUMN_Content = "Content";

    public static final String COLUMN_Url = "Url";

    public static final String COLUMN_RedirectUrl = "RedirectUrl";

    public static final String COLUMN_Sort = "Sort";

    public static final String COLUMN_Version = "Version";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "图片编号")
    private String Id;

    @ApiModelProperty(value = "用户编号")
    private String UseId;

    @ApiModelProperty(value = "图片类型")
    private Integer TypeId;

    @ApiModelProperty(value = "加密编号")
    private String Identity;

    @ApiModelProperty(value = "用途 ")
    private Integer Purpose;

    @ApiModelProperty(value = "所属")
    private String BelongId;

    @ApiModelProperty(value = "图片标题")
    private String Title;

    @ApiModelProperty(value = "图片说明")
    private String Content;

    @ApiModelProperty(value = "图片链接")
    private String Url;

    @ApiModelProperty(value = "跳转链接")
    private String RedirectUrl;

    @ApiModelProperty(value = "排序")
    private Integer Sort;

    @ApiModelProperty(value = "修改次数")
    private Integer Version;

    @ApiModelProperty(value = "状态信息")
    private Integer Status;

    @ApiModelProperty(value = "是否删除")
    private Integer Delete;

    @ApiModelProperty(value = "创建时间")
    private Date CreateTime;

    @ApiModelProperty(value = "修改时间")
    private Date ModifyTime;

	@ApiModelProperty(value = "['Id','图片编号']['UseId','用户编号']['TypeId','图片类型']['Identity','加密编号']['Purpose','用途 ']['BelongId','所属']['Title','图片标题']['Content','图片说明']['Url','图片链接']['RedirectUrl','跳转链接']['Sort','排序']['Version','修改次数']['Status','状态信息']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String PictureField;
}

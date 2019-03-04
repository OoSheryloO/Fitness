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
@ApiModel(value = "Fitdata")
public class Fitdata implements Serializable {

    public static final String COLUMN_Id = "Id";

    public static final String COLUMN_UseId = "UseId";

    public static final String COLUMN_Date = "Date";

    public static final String COLUMN_Height = "Height";

    public static final String COLUMN_Weight = "Weight";

    public static final String COLUMN_BMI = "BMI";

    public static final String COLUMN_BodyFat = "BodyFat";

    public static final String COLUMN_FatRate = "FatRate";

    public static final String COLUMN_MetabolicRate = "MetabolicRate";

    public static final String COLUMN_WHR = "WHR";

    public static final String COLUMN_Humidity = "Humidity";

    public static final String COLUMN_MuscleMass = "MuscleMass";

    public static final String COLUMN_UpperArm = "UpperArm";

    public static final String COLUMN_UnderArm = "UnderArm";

    public static final String COLUMN_Bust = "Bust";

    public static final String COLUMN_Waist = "Waist";

    public static final String COLUMN_Hipline = "Hipline";

    public static final String COLUMN_LeftThigh = "LeftThigh";

    public static final String COLUMN_RightThigh = "RightThigh";

    public static final String COLUMN_LeftShank = "LeftShank";

    public static final String COLUMN_RightShank = "RightShank";

    public static final String COLUMN_Memo = "Memo";

    public static final String COLUMN_Status = "Status";

    public static final String COLUMN_Delete = "Delete";

    public static final String COLUMN_CreateTime = "CreateTime";

    public static final String COLUMN_ModifyTime = "ModifyTime";

    @ApiModelProperty(value = "健康数据")
    private String Id;

    @ApiModelProperty(value = "用户编号")
    private String UseId;

    @ApiModelProperty(value = "体测时间")
    private Date Date;

    @ApiModelProperty(value = "身高/cm")
    private String Height;

    @ApiModelProperty(value = "体重/kg")
    private String Weight;

    @ApiModelProperty(value = "BMI")
    private String BMI;

    @ApiModelProperty(value = "体脂")
    private String BodyFat;

    @ApiModelProperty(value = "体脂率")
    private String FatRate;

    @ApiModelProperty(value = "代谢率")
    private String MetabolicRate;

    @ApiModelProperty(value = "腰臀比")
    private String WHR;

    @ApiModelProperty(value = "水分含量")
    private String Humidity;

    @ApiModelProperty(value = "肌肉含量")
    private String MuscleMass;

    @ApiModelProperty(value = "上臂围/cm")
    private String UpperArm;

    @ApiModelProperty(value = "下臂围/cm")
    private String UnderArm;

    @ApiModelProperty(value = "胸围/cm")
    private String Bust;

    @ApiModelProperty(value = "腰围/cm")
    private String Waist;

    @ApiModelProperty(value = "臀围/cm")
    private String Hipline;

    @ApiModelProperty(value = "左大腿围")
    private String LeftThigh;

    @ApiModelProperty(value = "右大腿围")
    private String RightThigh;

    @ApiModelProperty(value = "左小腿围")
    private String LeftShank;

    @ApiModelProperty(value = "右小腿围")
    private String RightShank;

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

	@ApiModelProperty(value = "['Id','健康数据']['UseId','用户编号']['Date','体测时间']['Height','身高/cm']['Weight','体重/kg']['BMI','BMI']['BodyFat','体脂']['FatRate','体脂率']['MetabolicRate','代谢率']['WHR','腰臀比']['Humidity','水分含量']['MuscleMass','肌肉含量']['UpperArm','上臂围/cm']['UnderArm','下臂围/cm']['Bust','胸围/cm']['Waist','腰围/cm']['Hipline','臀围/cm']['LeftThigh','左大腿围']['RightThigh','右大腿围']['LeftShank','左小腿围']['RightShank','右小腿围']['Memo','备注']['Status','状态信息']['Delete','是否删除']['CreateTime','创建时间']['ModifyTime','修改时间']")
	@JSONField(serialize = false)
	public String FitdataField;
}

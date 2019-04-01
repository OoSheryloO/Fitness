package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kjyl.pojo.Fitdata;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Fitdata", description="")
@RestController
@RequestMapping("/Fitdata")
public class FitdataController extends BaseController {

//    @GetMapping("/searchFitdataPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchFitdataPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchFitdataPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Fitdata.COLUMN_Status, status);
        }
        PageInfo<Fitdata> page = this.FitdataService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setFitdataStatus")
    @RequestMapping(value="/setFitdataStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setFitdataStatus(@RequestBody String data){
        Fitdata temp = JSON.parseObject(data, Fitdata.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                FitdataService.RecoverBySpecial(Id);
            }else{
                FitdataService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchFitdata/{id}")
    @RequestMapping(value="/searchFitdata/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchFitdata(@PathVariable("id") String Id){
        Fitdata temp = FitdataService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyFitdata")
    @RequestMapping(value="/modifyFitdata", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyFitdata(@RequestBody String data, HttpServletRequest request) {
        Fitdata temp = JSON.parseObject(data, Fitdata.class);
        Fitdata obj = new Fitdata();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = FitdataService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setDate(temp.getDate());
        obj.setHeight(temp.getHeight());
        obj.setWeight(temp.getWeight());
        obj.setBMI(temp.getBMI());
        obj.setBodyFat(temp.getBodyFat());
        obj.setFatRate(temp.getFatRate());
        obj.setMetabolicRate(temp.getMetabolicRate());
        obj.setWHR(temp.getWHR());
        obj.setHumidity(temp.getHumidity());
        obj.setMuscleMass(temp.getMuscleMass());
        obj.setUpperArm(temp.getUpperArm());
        obj.setUnderArm(temp.getUnderArm());
        obj.setBust(temp.getBust());
        obj.setWaist(temp.getWaist());
        obj.setHipline(temp.getHipline());
        obj.setLeftThigh(temp.getLeftThigh());
        obj.setRightThigh(temp.getRightThigh());
        obj.setLeftShank(temp.getLeftShank());
        obj.setRightShank(temp.getRightShank());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Fitdata tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = FitdataService.Insert(obj);
        }else{
            tempObj = FitdataService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}

package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kjyl.pojo.Fitdata;
import com.kjyl.service.FitdataService;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Fitdata")
@RestController
@RequestMapping("/Fitdata")
public class FitdataController extends BaseController {

    @GetMapping("/searchFitdataPage")
    @ApiOperation(value = "获取列表")
    public Map<String, Object> searchFitdataPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Fitdata> page = this.FitdataService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return mapResult;
    }

    @PostMapping("/setFitdataStatus")
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setFitdataStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Fitdata temp = JSON.parseObject(data, Fitdata.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                FitdataService.RecoverBySpecial(Id);
            }else{
                FitdataService.RemoveBySpecial(Id);
            }
        }
        mapResult.put(CodeInfo.sRowKey, 0);
        mapResult.put(CodeInfo.sMessageKey, "操作成功");
        return mapResult;
    }

    @GetMapping("/searchFitdata/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchFitdata(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Fitdata temp = FitdataService.SearchBySpecial(Id);
        if(temp != null){
        	mapResult.put(CodeInfo.sRowKey, 0);
        	mapResult.put(CodeInfo.sDataKey, temp);
        	mapResult.put(CodeInfo.sMessageKey, "获取成功");
    	}else{
    		mapResult.put(CodeInfo.sRowKey, -1);
    		mapResult.put(CodeInfo.sDataKey, temp);
    		mapResult.put(CodeInfo.sMessageKey, "获取失败");
		}
        return mapResult;
    }

    @PostMapping("/modifyFitdata")
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyFitdata(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Fitdata temp = JSON.parseObject(data, Fitdata.class);
        Fitdata obj = new Fitdata();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = FitdataService.SearchBySpecial(temp.getId());
            if(obj==null){
                isNew=true;
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

        Fitdata tempObj=null;
        if(isNew){
            obj.setId(String.valueOf(IdWorker.CreateNewId()));
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj=FitdataService.Insert(obj);
        }else{
            tempObj=FitdataService.Modify(obj);
        }
        mapResult.put(CodeInfo.sRowKey, tempObj != null ? 0 : -1);
        mapResult.put(CodeInfo.sMessageKey, tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}

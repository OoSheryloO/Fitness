package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.code.domain.Picture;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.code.until.CommonStatus;
import com.code.until.CommonUntil;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Picture")
@RestController
@RequestMapping("/Picture")
public class PictureController extends BaseController {

    @GetMapping("/searchPicturePage")
    @ApiOperation(value = "获取列表")
    public Map searchPicturePage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Picture> page = this.ReadPictureService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put("rows", page.getList());
        mapResult.put("total", page.getTotal());
        return mapResult;
    }

    @PostMapping("/setPictureStatus")
    @ApiOperation(value = "设置状态")
    public Map setPictureStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Picture temp = JSON.parseObject(data,Picture.class);
        String[] ids = temp.getId().split(",");
        for (String id : ids){
            if(temp.getStatus() == Integer.parseInt(CommonStatus.Status.Ectivity.getId())){
                PictureService.RecoverBySpecial(Id);
            }else{
                PictureService.RemoveBySpecial(Id);
            }
            mapSearch.clear();
        }
        mapResult.put("code", 0);
        mapResult.put("message", "操作成功");
        return mapResult;
    }

    @GetMapping("/searchPicture/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map searchPicture(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Picture temp = ReadPictureService.SearchBySpecial(Id);
        if(temp != null){
        	mapResult.put("code", 0);
        	mapResult.put("data", temp);
        	mapResult.put("message", "获取成功");
    	}else{
    		mapResult.put("code", -1);
    		mapResult.put("data", temp);
    		mapResult.put("message", "获取失败");
		}
        return mapResult;
    }


    @PostMapping("/modifyPicture")
    @ApiOperation(value = "修改")
    public Map modifyPicture(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Picture temp = JSON.parseObject(data, Picture.class);
        Picture  obj = new Picture();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = ReadPictureService.SearchBySpecial(String.valueOf(temp.getId()));
            if(obj==null){
                isNew=true;
            }
        }

        obj.setUseId(temp.getUseId());
        obj.setTypeId(temp.getTypeId());
        obj.setIdentity(temp.getIdentity());
        obj.setPurpose(temp.getPurpose());
        obj.setBelongId(temp.getBelongId());
        obj.setTitle(temp.getTitle());
        obj.setContent(temp.getContent());
        obj.setUrl(temp.getUrl());
        obj.setRedirectUrl(temp.getRedirectUrl());
        obj.setSort(temp.getSort());
        obj.setVersion(temp.getVersion());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Picture tempObj=null;
        if(isNew){
            obj.setID(CommonUntil.getInstance().CreateNewId());
            obj.setStatus(Integer.parseInt(CommonStatus.Status.Ectivity.getId()));
            tempObj=PictureService.Insert(obj);
        }else{
            tempObj=PictureService.Modify(obj);
        }
        mapResult.put("code", tempObj != null ? 0 : -1);
        mapResult.put("message", tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}

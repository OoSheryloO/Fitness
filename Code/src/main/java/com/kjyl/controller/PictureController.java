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

import com.kjyl.pojo.Picture;
import com.kjyl.service.PictureService;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Picture")
@RestController
@RequestMapping("/Picture")
public class PictureController extends BaseController {

//    @GetMapping("/searchPicturePage")
    @ApiOperation(value = "获取列表")
    public Map<String, Object> searchPicturePage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Picture> page = this.PictureService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return mapResult;
    }

//    @PostMapping("/setPictureStatus")
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setPictureStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Picture temp = JSON.parseObject(data, Picture.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                PictureService.RecoverBySpecial(Id);
            }else{
                PictureService.RemoveBySpecial(Id);
            }
        }
        mapResult.put(CodeInfo.sRowKey, 0);
        mapResult.put(CodeInfo.sMessageKey, "操作成功");
        return mapResult;
    }

//    @GetMapping("/searchPicture/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchPicture(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Picture temp = PictureService.SearchBySpecial(Id);
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

//    @PostMapping("/modifyPicture")
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyPicture(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Picture temp = JSON.parseObject(data, Picture.class);
        Picture obj = new Picture();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = PictureService.SearchBySpecial(temp.getId());
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
            obj.setId(String.valueOf(IdWorker.CreateNewId()));
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj=PictureService.Insert(obj);
        }else{
            tempObj=PictureService.Modify(obj);
        }
        mapResult.put(CodeInfo.sRowKey, tempObj != null ? 0 : -1);
        mapResult.put(CodeInfo.sMessageKey, tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}

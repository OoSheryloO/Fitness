package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.code.domain.Goods;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.code.until.CommonStatus;
import com.code.until.CommonUntil;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Goods")
@RestController
@RequestMapping("/Goods")
public class GoodsController extends BaseController {

    @GetMapping("/searchGoodsPage")
    @ApiOperation(value = "获取列表")
    public Map searchGoodsPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Goods> page = this.ReadGoodsService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put("rows", page.getList());
        mapResult.put("total", page.getTotal());
        return mapResult;
    }

    @PostMapping("/setGoodsStatus")
    @ApiOperation(value = "设置状态")
    public Map setGoodsStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Goods temp = JSON.parseObject(data,Goods.class);
        String[] ids = temp.getId().split(",");
        for (String id : ids){
            if(temp.getStatus() == Integer.parseInt(CommonStatus.Status.Ectivity.getId())){
                GoodsService.RecoverBySpecial(Id);
            }else{
                GoodsService.RemoveBySpecial(Id);
            }
            mapSearch.clear();
        }
        mapResult.put("code", 0);
        mapResult.put("message", "操作成功");
        return mapResult;
    }

    @GetMapping("/searchGoods/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map searchGoods(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Goods temp = ReadGoodsService.SearchBySpecial(Id);
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


    @PostMapping("/modifyGoods")
    @ApiOperation(value = "修改")
    public Map modifyGoods(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Goods temp = JSON.parseObject(data, Goods.class);
        Goods  obj = new Goods();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = ReadGoodsService.SearchBySpecial(String.valueOf(temp.getId()));
            if(obj==null){
                isNew=true;
            }
        }

        obj.setUseId(temp.getUseId());
        obj.setTitle(temp.getTitle());
        obj.setIntro(temp.getIntro());
        obj.setPrice(temp.getPrice());
        obj.setHeadIcon(temp.getHeadIcon());
        obj.setType(temp.getType());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Goods tempObj=null;
        if(isNew){
            obj.setID(CommonUntil.getInstance().CreateNewId());
            obj.setStatus(Integer.parseInt(CommonStatus.Status.Ectivity.getId()));
            tempObj=GoodsService.Insert(obj);
        }else{
            tempObj=GoodsService.Modify(obj);
        }
        mapResult.put("code", tempObj != null ? 0 : -1);
        mapResult.put("message", tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}

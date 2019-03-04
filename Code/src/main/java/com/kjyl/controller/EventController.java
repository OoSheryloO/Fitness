package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.code.domain.Event;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.code.until.CommonStatus;
import com.code.until.CommonUntil;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Event")
@RestController
@RequestMapping("/Event")
public class EventController extends BaseController {

    @GetMapping("/searchEventPage")
    @ApiOperation(value = "获取列表")
    public Map searchEventPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Event> page = this.ReadEventService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put("rows", page.getList());
        mapResult.put("total", page.getTotal());
        return mapResult;
    }

    @PostMapping("/setEventStatus")
    @ApiOperation(value = "设置状态")
    public Map setEventStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Event temp = JSON.parseObject(data,Event.class);
        String[] ids = temp.getId().split(",");
        for (String id : ids){
            if(temp.getStatus() == Integer.parseInt(CommonStatus.Status.Ectivity.getId())){
                EventService.RecoverBySpecial(Id);
            }else{
                EventService.RemoveBySpecial(Id);
            }
            mapSearch.clear();
        }
        mapResult.put("code", 0);
        mapResult.put("message", "操作成功");
        return mapResult;
    }

    @GetMapping("/searchEvent/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map searchEvent(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Event temp = ReadEventService.SearchBySpecial(Id);
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


    @PostMapping("/modifyEvent")
    @ApiOperation(value = "修改")
    public Map modifyEvent(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Event temp = JSON.parseObject(data, Event.class);
        Event  obj = new Event();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = ReadEventService.SearchBySpecial(String.valueOf(temp.getId()));
            if(obj==null){
                isNew=true;
            }
        }

        obj.setUseId(temp.getUseId());
        obj.setTouchId(temp.getTouchId());
        obj.setTitle(temp.getTitle());
        obj.setIntro(temp.getIntro());
        obj.setSite(temp.getSite());
        obj.setStartTime(temp.getStartTime());
        obj.setEndTime(temp.getEndTime());
        obj.setStartApply(temp.getStartApply());
        obj.setEndApply(temp.getEndApply());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Event tempObj=null;
        if(isNew){
            obj.setID(CommonUntil.getInstance().CreateNewId());
            obj.setStatus(Integer.parseInt(CommonStatus.Status.Ectivity.getId()));
            tempObj=EventService.Insert(obj);
        }else{
            tempObj=EventService.Modify(obj);
        }
        mapResult.put("code", tempObj != null ? 0 : -1);
        mapResult.put("message", tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}

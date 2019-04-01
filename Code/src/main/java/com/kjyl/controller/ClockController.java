package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kjyl.bean.ClockRankBean;
import com.kjyl.pojo.Clock;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Clock", description="")
@RestController
@RequestMapping("/Clock")
public class ClockController extends BaseController {

//    @GetMapping("/searchClockPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchClockPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
	 public Map<String, Object> searchClockPage(Integer status, String id, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Clock.COLUMN_UseId, id);
        if(status != null && status != -1){
        	mapSearch.put(Clock.COLUMN_Status, status);
        }
        PageInfo<Clock> page = this.ClockService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setClockStatus")
    @RequestMapping(value="/setClockStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setClockStatus(@RequestBody String data){
        Clock temp = JSON.parseObject(data, Clock.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                ClockService.RecoverBySpecial(Id);
            }else{
                ClockService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchClock/{id}")
    @RequestMapping(value="/searchClock/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchClock(@PathVariable("id") String Id){
        Clock temp = ClockService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyClock")
    @RequestMapping(value="/modifyClock", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyClock(@RequestBody String data, HttpServletRequest request) {
        Clock temp = JSON.parseObject(data, Clock.class);
        Clock obj = new Clock();
        boolean isNew = false;
        if("0".equals(temp.getId()) || temp.getId() == null){
            isNew = true;
        }else{
        	Map<String, Object> mapSearch = new HashMap<String, Object>();
        	mapSearch.put(Clock.COLUMN_UseId, temp.getUseId());
        	mapSearch.put(Clock.COLUMN_CreateTime, new Date());
        	mapSearch.put(Clock.COLUMN_Delete, DBParam.RecordStatus.Delete.getClass());
        	List<Clock> lisC = this.ClockService.SearchByCondition(mapSearch);
        	if (lisC == null || lisC.size() == 0) {
        		isNew = true;
			}
            /*obj = ClockService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
            }*/
        }
        obj.setUseId(temp.getUseId());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Clock tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = ClockService.Insert(obj);
        }else{
            tempObj = ClockService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }
    
    @RequestMapping(value="/clockrank", method=RequestMethod.GET)
    @ApiOperation(value = "打卡排行")
    public Map<String, Object> clockRank(Integer type, int pageNumber, int pageSize){
    	Map<String, Object> mapResult = new HashMap<String, Object>();
    	PageInfo<ClockRankBean> lstRank = ClockService.SearchRankByCondition(null, pageNumber, pageSize);
    	mapResult.put(CodeInfo.sRowKey, lstRank.getList());
    	mapResult.put(CodeInfo.sTotalKey, lstRank.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

}

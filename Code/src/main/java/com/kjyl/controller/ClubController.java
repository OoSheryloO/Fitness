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

import com.kjyl.pojo.Club;
import com.kjyl.service.ClubService;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.ResultUtil;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Club", description="")
@RestController
@RequestMapping("/Club")
public class ClubController extends BaseController {

//    @GetMapping("/searchClubPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchClubPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchClubPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Club.COLUMN_Status, status);
        }
        PageInfo<Club> page = this.ClubService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return ResultUtil.sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setClubStatus")
    @RequestMapping(value="/setClubStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setClubStatus(String data){
        Club temp = JSON.parseObject(data, Club.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                ClubService.RecoverBySpecial(Id);
            }else{
                ClubService.RemoveBySpecial(Id);
            }
        }
        return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchClub/{id}")
    @RequestMapping(value="/searchClub/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchClub(@PathVariable("id") String Id){
        Club temp = ClubService.SearchBySpecial(Id);
        if(temp != null){
        	return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return ResultUtil.sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyClub")
    @RequestMapping(value="/modifyClub", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyClub(String data, HttpServletRequest request) {
        Club temp = JSON.parseObject(data, Club.class);
        Club obj = new Club();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = ClubService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setTitle(temp.getTitle());
        obj.setTheme(temp.getTheme());
        obj.setIntro(temp.getIntro());
        obj.setSite(temp.getSite());
        obj.setLocation(temp.getLocation());
        obj.setTel(temp.getTel());
        obj.setOpenTime(temp.getOpenTime());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Club tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = ClubService.Insert(obj);
        }else{
            tempObj = ClubService.Modify(obj);
        }
        if (tempObj != null) {
			return ResultUtil.sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return ResultUtil.sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}

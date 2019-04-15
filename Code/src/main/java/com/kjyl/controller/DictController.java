package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import com.kjyl.pojo.Dict;

import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.ResultUtil;
import com.kjyl.util.GenerateKey.IdWorker;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Dict", description="")
@RestController
@RequestMapping("/Dict")
public class DictController  extends BaseController {

//    @GetMapping("/searchDictPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchDictPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
	public Map<String, Object> searchDictPage(Integer level, String value, Integer status, Integer logicId, String belongId, Integer type, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Dict.COLUMN_LogicId, logicId);
        mapSearch.put(Dict.COLUMN_Info, value);
        mapSearch.put(Dict.COLUMN_Type, type);
        mapSearch.put(Dict.COLUMN_Status, status);
        mapSearch.put(Dict.COLUMN_Level, level);
        mapSearch.put(Dict.COLUMN_BelongId, belongId);
        PageInfo<Dict> page = this.DictService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return ResultUtil.sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }
	
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchClassificationPage", method=RequestMethod.GET)
	public Map<String, Object> searchClassificationPage(Integer level, String value, Integer status, Integer logicId, Integer type, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
//        mapSearch.put(Dict.COLUMN_LogicId, logicId);
//        mapSearch.put(Dict.COLUMN_Info, value);
        mapSearch.put(Dict.COLUMN_Type, type);
//        mapSearch.put(Dict.COLUMN_Status, status);
//        mapSearch.put(Dict.COLUMN_Level, level);
        PageInfo<Dict> page = this.DictService.SearchClassificationByConditionPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return ResultUtil.sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setDictStatus")
    @RequestMapping(value="/setDictStatus", method=RequestMethod.POST)
    @ApiOperation(value = "删除状态")
    public Map<String, Object> setDictStatus(@RequestBody String data){
        Dict temp = JSON.parseObject(data, Dict.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                DictService.RecoverBySpecial(Id);
            }else{
                DictService.RemoveBySpecial(Id);
            }
        }
        return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchDict/{id}")
    @RequestMapping(value="/searchDict/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchDict(@PathVariable("id") String Id){
        Dict temp = DictService.SearchBySpecial(Id);
        if(temp != null){
        	return ResultUtil.sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return ResultUtil.sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyDict")
    @RequestMapping(value="/modifyDict", method=RequestMethod.POST)
    @ApiOperation(value = "修改/新增")
    public Map<String, Object> modifyDict(@RequestBody String data, HttpServletRequest request) {
        Dict temp = JSON.parseObject(data, Dict.class);
        Dict obj = new Dict();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = DictService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Dict();
            }
        }
        
        obj.setValue(temp.getValue());
        obj.setInfo(temp.getInfo());
        obj.setType(temp.getType());
        obj.setLogicId(temp.getLogicId());
        obj.setBelongId(temp.getBelongId());
        obj.setSort(temp.getSort());
        obj.setLevel(temp.getLevel());

        Dict tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = DictService.Insert(obj);
        }else{
            tempObj = DictService.Modify(obj);
        }
        if (tempObj != null) {
			return ResultUtil.sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return ResultUtil.sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}

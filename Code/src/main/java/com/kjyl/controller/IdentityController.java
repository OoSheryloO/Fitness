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
import com.kjyl.pojo.Identity;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Identity", description="")
@RestController
@RequestMapping("/Identity")
public class IdentityController extends BaseController {

//    @GetMapping("/searchIdentityPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchIdentityPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchIdentityPage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Identity.COLUMN_Status, status);
        }
        PageInfo<Identity> page = this.IdentityService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setIdentityStatus")
    @RequestMapping(value="/setIdentityStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setIdentityStatus(@RequestBody String data){
        Identity temp = JSON.parseObject(data, Identity.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                IdentityService.RecoverBySpecial(Id);
            }else{
                IdentityService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchIdentity/{id}")
    @RequestMapping(value="/searchIdentity/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchIdentity(@PathVariable("id") String Id){
        Identity temp = IdentityService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyIdentity")
    @RequestMapping(value="/modifyIdentity", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyIdentity(@RequestBody String data, HttpServletRequest request) {
        Identity temp = JSON.parseObject(data, Identity.class);
        Identity obj = new Identity();
        boolean isNew = false;
        if("0".equals(temp.getId()) || temp.getId() == null){
            isNew = true;
        }else{
            obj = IdentityService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Identity();
            }
        }
        obj.setName(temp.getName());
        obj.setCardFront(temp.getCardFront());
        obj.setCardVerso(temp.getCardVerso());
        obj.setCardNumber(temp.getCardNumber());
        obj.setStar(temp.getStar());
        obj.setLevel(temp.getLevel());
        obj.setStrong(temp.getStrong());
        obj.setInfo(temp.getInfo());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Identity tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = IdentityService.Insert(obj);
        }else{
            tempObj = IdentityService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}

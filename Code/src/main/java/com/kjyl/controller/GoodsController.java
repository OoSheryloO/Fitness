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
import com.kjyl.pojo.Goods;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Goods", description="")
@RestController
@RequestMapping("/Goods")
public class GoodsController extends BaseController {

//    @GetMapping("/searchGoodsPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchGoodsPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchGoodsPage(Integer status, Integer type, String logicId, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Goods.COLUMN_Type, type);
        mapSearch.put(Goods.COLUMN_Status, status);
        mapSearch.put(Goods.COLUMN_LogicId, logicId);
        PageInfo<Goods> page = this.GoodsService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setGoodsStatus")
    @RequestMapping(value="/setGoodsStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setGoodsStatus(@RequestBody String data){
        Goods temp = JSON.parseObject(data, Goods.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                GoodsService.RecoverBySpecial(Id);
            }else{
                GoodsService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchGoods/{id}")
    @RequestMapping(value="/searchGoods/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchGoods(@PathVariable("id") String Id){
        Goods temp = GoodsService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyGoods")
    @RequestMapping(value="/modifyGoods", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyGoods(@RequestBody String data, HttpServletRequest request) {
        Goods temp = JSON.parseObject(data, Goods.class);
        Goods obj = new Goods();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = GoodsService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Goods();
            }
        }
        obj.setLogicId(temp.getLogicId());
        obj.setHeadIcon(temp.getHeadIcon());
        obj.setTitle(temp.getTitle());
        obj.setIntro(temp.getIntro());
        obj.setPrice(temp.getPrice());
        obj.setFreeShip(temp.getFreeShip());
        obj.setFreeFirst(temp.getFreeFirst());
        obj.setRefundDay(temp.getRefundDay());
        obj.setDelivery(temp.getDelivery());
        obj.setType(temp.getType());
        obj.setSize(temp.getSize());
        obj.setSort(temp.getSort());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Goods tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = GoodsService.Insert(obj);
        }else{
            tempObj = GoodsService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}

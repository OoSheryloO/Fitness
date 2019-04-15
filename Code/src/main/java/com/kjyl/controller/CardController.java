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
import com.kjyl.pojo.Card;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Card", description="")
@RestController
@RequestMapping("/Card")
public class CardController extends BaseController {

//    @GetMapping("/searchCardPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchCardPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchCardPage(Integer status, int type, String logicId, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Card.COLUMN_Type, type);
        mapSearch.put(Card.COLUMN_Status, status);
        mapSearch.put(Card.COLUMN_LogicId, logicId);
        PageInfo<Card> page = this.CardService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setCardStatus")
    @RequestMapping(value="/setCardStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setCardStatus(@RequestBody String data){
        Card temp = JSON.parseObject(data, Card.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                CardService.RecoverBySpecial(Id);
            }else{
                CardService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchCard/{id}")
    @RequestMapping(value="/searchCard/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchCard(@PathVariable("id") String Id){
        Card temp = CardService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyCard")
    @RequestMapping(value="/modifyCard", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyCard(@RequestBody String data, HttpServletRequest request) {
        Card temp = JSON.parseObject(data, Card.class);
        Card obj = new Card();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = CardService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Card();
            }
        }
        obj.setLogicId(temp.getLogicId());
        obj.setHeadIcon(temp.getHeadIcon());
        obj.setTitle(temp.getTitle());
        obj.setIntro(temp.getIntro());
        obj.setPrice(temp.getPrice());
        obj.setLevel(temp.getLevel());
        obj.setValidTime(temp.getValidTime());
        obj.setDiscount(temp.getDiscount());
        obj.setType(temp.getType());
        obj.setSort(temp.getSort());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Card tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = CardService.Insert(obj);
        }else{
            tempObj = CardService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }

}

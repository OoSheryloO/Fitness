package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kjyl.pojo.Card;
import com.kjyl.pojo.Cart;
import com.kjyl.pojo.Course;
import com.kjyl.pojo.Goods;
import com.kjyl.pojo.Order;
import com.kjyl.pojo.Syllabus;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.AliPay.WeChatPaySign;
import com.kjyl.util.AliPay.aliPaySign;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Order", description="")
@RestController
@RequestMapping("/Order")
public class OrderController extends BaseController {

//    @GetMapping("/searchOrderPage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchOrderPage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchOrderPage(Integer status, String id, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put(Order.COLUMN_UseId, id);
        if(status != null && status != -1){
        	mapSearch.put(Order.COLUMN_Status, status);
        }
        PageInfo<Order> page = this.OrderService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setOrderStatus")
    @RequestMapping(value="/setOrderStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setOrderStatus(@RequestBody String data){
        Order temp = JSON.parseObject(data, Order.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                OrderService.RecoverBySpecial(Id);
            }else{
                OrderService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchOrder/{id}")
    @RequestMapping(value="/searchOrder/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchOrder(@PathVariable("id") String Id){
        Order temp = OrderService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyOrder")
    @RequestMapping(value="/modifyOrder", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyOrder(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        Order temp = JSON.parseObject(data, Order.class);
        Order obj = new Order();
        boolean isNew = false;
        boolean Residue = false;
        if("0".equals(temp.getId()) || temp.getId() == null){
            isNew = true;
        }else{
            obj = OrderService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Order();
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setLogicId(temp.getLogicId());
        obj.setGoods(temp.getGoods());
        obj.setPrize(temp.getPrize());
        obj.setSite(temp.getSite());
        obj.setType(temp.getType());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Order tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            
            //判断type 1 卡 2 课程 3 商品 支付回调 失败仓库+1
            switch (obj.getType()) {
			case 1:
				Card pjCard = CardService.SearchBySpecial(obj.getLogicId());
				if (pjCard != null && pjCard.getResidue() > 0) {
					pjCard.setResidue(pjCard.getResidue() - 1);
					this.CardService.Modify(pjCard);
				}else {
					Residue = true;
				}
				
				break;
			case 2://写入课程表
				Course pjCourse = CourseService.SearchBySpecial(obj.getLogicId());
				if (pjCourse != null && pjCourse.getApply() > 0) {
					pjCourse.setApply(pjCourse.getApply() - 1);
					this.CourseService.Modify(pjCourse);
				}else {
					Residue = true;
				}
				
				Syllabus pjSy = new Syllabus();
				pjSy.setId(IdWorker.CreateStringNewId());
				pjSy.setUseId(obj.getUseId());
				pjSy.setLogicId(obj.getLogicId());
				this.SyllabusService.Insert(pjSy);
				
				break;
			case 3:
				String[] goodids = obj.getGoods().split(",");
				for (String goodid : goodids) {
//					Cart pjCart = CartService.SearchBySpecial(goodid);
//					if (pjCart != null) {
//						pjCart.setDelete(DBParam.RecordStatus.Delete.getCode());
//						CartService.Modify(pjCart);
//					}
//					Goods pjGood = GoodsService.SearchBySpecial(pjCart.getLogicId());
//					if (pjGood != null && pjGood.getResidue() >= pjCart.getAmount()) {
//						pjGood.setResidue(pjGood.getResidue() - pjCart.getAmount());
//						this.GoodsService.Modify(pjGood);
//					}else {
//						Residue = true;
//					}
					Goods pjGood = GoodsService.SearchBySpecial(goodid);
					if (pjGood != null && pjGood.getResidue() >= 0) {
						pjGood.setResidue(pjGood.getResidue() - 1);
						this.GoodsService.Modify(pjGood);
					}else {
						Residue = true;
					}
				}
			break;
			}
        }else{
            tempObj = OrderService.Modify(obj);
        }
        String sign = "";
        if (Residue) {
        	return sharedInstance().FalseData("库存不足!请刷新", CodeInfo.IntegrationType.Residue.getCode());
		}else {
			tempObj = OrderService.Insert(obj);
			if (temp.getPayType() != null && temp.getPayType() == 1) { 
				sign = WeChatPaySign.weChatSignPrams(tempObj, UserService, request, response);
			} else {
				sign = aliPaySign.aliPaySandBoxSignPrams(tempObj);//支付宝沙箱环境
			}
//			sign = aliPaySign.aliPaySignPrams(tempObj);//支付宝沙箱环境
		}
        if (tempObj != null) {
        	Map<String, Object> result = new HashMap<String, Object>();
        	result.put(CodeInfo.sDataKey, tempObj);
        	result.put(CodeInfo.sSignKey, sign);
			return sharedInstance().TrueData(result, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }
    
    public static void main(String[] args) {
		Order pojo = new Order();
		pojo.setId(IdWorker.CreateStringNewId());
		pojo.setUseId("123456");
		pojo.setPrize("100");
		System.out.println(aliPaySign.aliPaySandBoxSignPrams(pojo));//支付宝沙箱环境
	}

}

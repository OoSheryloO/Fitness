package com.kjyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.code.domain.Cart;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.code.until.CommonStatus;
import com.code.until.CommonUntil;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api("Cart")
@RestController
@RequestMapping("/Cart")
public class CartController extends BaseController {

    @GetMapping("/searchCartPage")
    @ApiOperation(value = "获取列表")
    public Map searchCartPage(int status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status!=-1){
        	mapSearch.put("Status", status);
        }
        PageInfo<Cart> page = this.ReadCartService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put("rows", page.getList());
        mapResult.put("total", page.getTotal());
        return mapResult;
    }

    @PostMapping("/setCartStatus")
    @ApiOperation(value = "设置状态")
    public Map setCartStatus(String data){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Cart temp = JSON.parseObject(data,Cart.class);
        String[] ids = temp.getId().split(",");
        for (String id : ids){
            if(temp.getStatus() == Integer.parseInt(CommonStatus.Status.Ectivity.getId())){
                CartService.RecoverBySpecial(Id);
            }else{
                CartService.RemoveBySpecial(Id);
            }
            mapSearch.clear();
        }
        mapResult.put("code", 0);
        mapResult.put("message", "操作成功");
        return mapResult;
    }

    @GetMapping("/searchCart/{id}")
    @ApiOperation(value = "根据编号查询内容")
    public Map searchCart(@PathVariable("id") String Id){
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        Cart temp = ReadCartService.SearchBySpecial(Id);
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


    @PostMapping("/modifyCart")
    @ApiOperation(value = "修改")
    public Map modifyCart(String data, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Cart temp = JSON.parseObject(data, Cart.class);
        Cart  obj = new Cart();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew=true;
        }else{
            obj = ReadCartService.SearchBySpecial(String.valueOf(temp.getId()));
            if(obj==null){
                isNew=true;
            }
        }

        obj.setUseId(temp.getUseId());
        obj.setTouchId(temp.getTouchId());
        obj.setMemo(temp.getMemo());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Cart tempObj=null;
        if(isNew){
            obj.setID(CommonUntil.getInstance().CreateNewId());
            obj.setStatus(Integer.parseInt(CommonStatus.Status.Ectivity.getId()));
            tempObj=CartService.Insert(obj);
        }else{
            tempObj=CartService.Modify(obj);
        }
        mapResult.put("code", tempObj != null ? 0 : -1);
        mapResult.put("message", tempObj != null ? "修改成功" : "修改失败");
        return mapResult;
    }

}

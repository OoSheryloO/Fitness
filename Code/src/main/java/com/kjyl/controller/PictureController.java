package com.kjyl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kjyl.pojo.Picture;
import com.kjyl.util.CodeInfo;
import com.kjyl.util.DBParam;
import com.kjyl.util.File.Base64ToFile;
import com.kjyl.util.File.UploadAliYunFile;
import com.kjyl.util.GenerateKey.IdWorker;

import static com.kjyl.util.ResultUtil.sharedInstance;

/**
 * <p> 控制器 Class</p>
 * @author sheryl 自动构建脚本
 */
@Api(value="Picture", description="")
@RestController
@RequestMapping("/Picture")
public class PictureController extends BaseController {

//    @GetMapping("/searchPicturePage")
	@ApiOperation(value = "获取列表", notes= "", httpMethod = "GET")
	@RequestMapping(value="/searchPicturePage", method=RequestMethod.GET)
//  @ApiImplicitParam(name = "data", value = "data描述", required = true, dataType = "UserInfo", paramType = "query")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name="name",value="用户名",dataType="string", paramType = "query",example="xingguo"),
//	  @ApiImplicitParam(name="id",value="用户id",dataType="long", paramType = "query")
//  })
    public Map<String, Object> searchPicturePage(Integer status, String search, int pageNumber, int pageSize, HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Map<String, Object> mapSearch = new HashMap<String, Object>();
        mapSearch.put("search", search);
        if(status != null && status != -1){
        	mapSearch.put(Picture.COLUMN_Status, status);
        }
        PageInfo<Picture> page = this.PictureService.SearchPage(mapSearch, pageNumber, pageSize);
        mapResult.put(CodeInfo.sRowKey, page.getList());
        mapResult.put(CodeInfo.sTotalKey, page.getTotal());
        return sharedInstance().TrueData(mapResult, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @PostMapping("/setPictureStatus")
    @RequestMapping(value="/setPictureStatus", method=RequestMethod.POST)
    @ApiOperation(value = "设置状态")
    public Map<String, Object> setPictureStatus(@RequestBody String data){
        Picture temp = JSON.parseObject(data, Picture.class);
        String[] ids = temp.getId().split(",");
        for (String Id : ids){
            if(temp.getStatus() == DBParam.RecordStatus.Delete.getCode()){
                PictureService.RecoverBySpecial(Id);
            }else{
                PictureService.RemoveBySpecial(Id);
            }
        }
        return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    }

//    @GetMapping("/searchPicture/{id}")
    @RequestMapping(value="/searchPicture/{id}", method=RequestMethod.GET)
    @ApiOperation(value = "根据编号查询内容")
    public Map<String, Object> searchPicture(@PathVariable("id") String Id){
        Picture temp = PictureService.SearchBySpecial(Id);
        if(temp != null){
        	return sharedInstance().TrueData(temp, "请求成功!", CodeInfo.Code.OK.getCode());
    	}else{
    		return sharedInstance().FalseData("获取失败!", CodeInfo.Code.NO.getCode());
		}
    }

//    @PostMapping("/modifyPicture")
    @RequestMapping(value="/modifyPicture", method=RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Map<String, Object> modifyPicture(@RequestBody String data, HttpServletRequest request) {
        Picture temp = JSON.parseObject(data, Picture.class);
        Picture obj = new Picture();
        boolean isNew = false;
        if("0".equals(temp.getId())){
            isNew = true;
        }else{
            obj = PictureService.SearchBySpecial(temp.getId());
            if(obj == null){
                isNew = true;
                obj = new Picture();
            }
        }
        obj.setUseId(temp.getUseId());
        obj.setType(temp.getType());
        obj.setIdentity(temp.getIdentity());
        obj.setPurpose(temp.getPurpose());
        obj.setLogicId(temp.getLogicId());
        obj.setTitle(temp.getTitle());
        obj.setContent(temp.getContent());
        obj.setUrl(temp.getUrl());
        obj.setRedirectUrl(temp.getRedirectUrl());
        obj.setSort(temp.getSort());
        obj.setVersion(temp.getVersion());
        obj.setDelete(temp.getDelete());
        obj.setModifyTime(temp.getModifyTime());

        Picture tempObj = null;
        if(isNew){
            obj.setId(IdWorker.CreateStringNewId());
            obj.setStatus(DBParam.RecordStatus.Default.getCode());
            tempObj = PictureService.Insert(obj);
        }else{
            tempObj = PictureService.Modify(obj);
        }
        if (tempObj != null) {
			return sharedInstance().TrueData(tempObj, "修改成功!", CodeInfo.Code.OK.getCode());
		} else {
			return sharedInstance().FalseData("修改失败!", CodeInfo.Code.NO.getCode());
		}
    }
    
    
  @RequestMapping(value="/upload", method=RequestMethod.POST)
  @ApiOperation(value = "上传")
  public Map<String, Object> upload(@RequestBody String data) throws FileNotFoundException, IOException{
	  String file = JSON.parseObject(data).get("file").toString();
	  int type = Integer.parseInt(JSON.parseObject(data).get("type").toString());
	  String id = JSON.parseObject(data).get("id").toString();
	  String sFileType = "Picture";
	  switch (type) {
	  case 1:
		  sFileType = "UserHeadIcon";//用户头像
		  break;
	  case 2:
		  sFileType = "CardIcon";//卡片
		  break;
	  case 3:
		  sFileType = "ClubIcon";//俱乐部
		  break;
	  case 4:
		  sFileType = "CourseIcon";//课程
		  break;
	  case 5:
		  sFileType = "PostIcon";//帖子
		  break;
	  case 6:
		  sFileType = "EventIcon";//活动
		  break;
	  case 7:
		  sFileType = "GoodsIcon";//商品
		  break;
	  case 8:
		  sFileType = "InfoIcon";//资讯
		  break;
	  case 10:
		  sFileType = "IdentityIcon";//身份证
		  break;
		default:
			break;
	  }
	  String sAliyunUrl = Base64ToFile.Base64ToFileToAliCloud(file, id, sFileType);
	  Picture pjPic = new Picture();
	  pjPic.setId(IdWorker.CreateStringNewId());
	  pjPic.setUseId(id);
	  pjPic.setUrl(sAliyunUrl);
	  pjPic.setType(type);
	  
	  pjPic = PictureService.Insert(pjPic);
      if(pjPic != null){
      		return sharedInstance().TrueData(pjPic, "请求成功!", CodeInfo.Code.OK.getCode());
  		}else{
  			return sharedInstance().FalseData("请求失败!", CodeInfo.Code.NO.getCode());
		}
  }
}

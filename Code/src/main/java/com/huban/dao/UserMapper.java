package com.huban.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.huban.construct.RankUserModel;
import com.huban.construct.TotalWealthShowModel;
import com.huban.construct.UserInfoModel;
import com.huban.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);
    
    int wechatinsert(User user);

    int insertSelective(User record);

    User selectUser(User record);
    
    int findUser(User record);
   
    List<User> queryList(User record);
    
    List<User> queryAll(Map<String,Object> map);
    
    List<User> queryRead(Map<String,Object> map);
    
    int changeUser(User record);

    User selectUserByUserId(Long userId);
    
    List<User> toprankings(Map<String,Object> map);
    
    List<User> ranklist(Map<String, Object> map);
    
    User seletePassword(String phone);
    
    int modifyPassword(Map<String, Object> map);
    
    int addMoney(Map<String, Object> map);
    
    int delMoney(Map<String, Object> map);
    
    long selectMoney(Long userId);
    
    int addIntegral(Map<String, Object> map);
    
    String selectPayPass(Long userId);
    
    int phonecount(String phone);
    
    int phonewechatcount(String phone);
    
    String seletePaypassExist(String phone);
    
    int integral(Long userId);
    
    int updatelevel(Map<String, Object> map);
    
    String seletePaypasswechat(String wechatId);
    
    User querystatus(Long userId);
    
    int changestatus(Long userId);
    
    User seletename(Long userId);
    
    User PhoneYesWeChatNo(String phone);
    
    int UpdateWeChatOpenId(User user);
    
    User QueryOpenIdByUserId(Long userId);
    
    int DeleteWeChatId(Long userId);
    
    String selecticon(Long userId);
    
    int conversion(Map<String, Object> map);
    
    /*V2*/
    int ExaminePseudonym(Map<String, Object> map);
    
    User verifyPseudonymAndPwd(Map<String, Object> map);
    
    int QueryExistIdentity(Map<String, Object> map);
    
    List<RankUserModel> QueryRankList(Map<String, Object> map);
    
    List<UserInfoModel> QueryUserInfo(Map<String, Object> map);
    
    List<UserInfoModel> QueryTeacherInfo(Map<String, Object> map);
    
    String BaseQueryUserPseudonym(Map<String, Object> map);
    
    String BaseQueryDeptPresident(Map<String, Object> map);
    
    Map<String, Object> QuerySomeMessage(Map<String, Object> map);
    
    TotalWealthShowModel ShowTotalWealth(Map<String, Object> map);
    
}
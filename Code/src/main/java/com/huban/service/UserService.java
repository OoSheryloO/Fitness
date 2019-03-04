package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.construct.RankUserModel;
import com.huban.construct.TotalWealthShowModel;
import com.huban.construct.UserInfoModel;
import com.huban.pojo.User;

public interface UserService {
    
	public User selectUser(User user);
	
	public int findUser(User user);
	
	public List<User> queryList(User user);
	
	public List<User> queryAll(Map<String, Object> map);
	
	public List<User> queryRead(Map<String, Object> map);
	
	public int addUser(User user);
	
	public int wechatinsert(User user);
	
	public int changeUser(User user);
	
	public User selectUserByUserId(Long userId);
	
	public List<User> toprankings(Map<String, Object> map);
	
	public List<User>ranklist(Map<String, Object> map);
	
	public User seletePassword(String phone);
	
    public int modifyPassword(Map<String, Object> map);
    
    public int addMoney(Map<String, Object> map);
    
    public int delMoney(Map<String, Object> map);
    
    public long selectMoney(Long userId);
    
    public int addIntegral(Map<String, Object> map);
    
    public String selectPayPass(Long userId);
    
    public int phonecount(String phone);
    
    public int phonewechatcount(String phone);
    
    public String seletePaypassExist(String phone);
    
    public int integral(Long userId);
    
    public int updatelevel(Map<String, Object> map);
    
    public String seletePaypasswechat(String wechatId);
    
    public User querystatus(Long userId);
    
    public int changestatus(Long userId);
    
    public User seletename(Long userId);
    
    public User PhoneYesWeChatNo(String phone);
    
    public int UpdateWeChatOpenId(User user);
    
    public User QueryOpenIdByUserId(Long userId);
    
    public int DeleteWeChatId(Long userId);
    
    public String selecticon(Long userId);
    
    public int conversion(Map<String, Object> map);
    
    /*V2*/
    public int examinePseudonym(Map<String, Object> map);
    
    public User verifyPseudonymAndPwd(Map<String, Object> map);
    
    public int QueryExistIdentity(Map<String, Object> map);
    
    public List<RankUserModel> QueryRankList(Map<String, Object> map);
    
    public List<UserInfoModel> QueryUserInfo(Map<String, Object> map);
    
    public List<UserInfoModel> QueryTeacherInfo(Map<String, Object> map);
    
    public Map<String, Object> QuerySomeMessage(Map<String, Object> map);
    
    public TotalWealthShowModel ShowTotalWealth(Map<String, Object> map);

}

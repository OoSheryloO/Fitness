package com.huban.service.imp;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.construct.RankUserModel;
import com.huban.construct.TotalWealthShowModel;
import com.huban.construct.UserInfoModel;
import com.huban.dao.UserMapper;
import com.huban.pojo.User;
import com.huban.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper mapper;
    
	@Override
	public User selectUser(User user) {
		// TODO Auto-generated method stub
		return mapper.selectUser(user);
	}

	@Override
	public int findUser(User user) {
		// TODO Auto-generated method stub
		return mapper.findUser(user);
	}

	@Override
	public List<User> queryList(User user) {
		// TODO Auto-generated method stub
		return mapper.queryList(user);
	}
	
	@Override
	public List<User> queryAll(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryAll(map);
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return mapper.insert(user);
	}

	public int wechatinsert(User user){
		// TODO Auto-generated method stub
		return mapper.wechatinsert(user);
	}
	/* (non-Javadoc)
	 * @see com.huban.service.UserService#changeUser(com.huban.pojo.User)
	 */
	@Override
	public int changeUser(User user) {
		// TODO Auto-generated method stub
		return mapper.changeUser(user);
	}

	/* (non-Javadoc)
	 * @see com.huban.service.UserService#queryRead(java.util.Map)
	 */
	@Override
	public List<User> queryRead(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryRead(map);
	}

	@Override
	public User selectUserByUserId(Long userId) {
		// TODO Auto-generated method stub
		return mapper.selectUserByUserId(userId);
	}

	@Override
	public List<User> toprankings(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.toprankings(map);
	}

	@Override
	public List<User> ranklist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.ranklist(map);
	}

	@Override
	public User seletePassword(String phone) {
		// TODO Auto-generated method stub
		return mapper.seletePassword(phone);
	}

	@Override
	public int modifyPassword(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.modifyPassword(map);
	}

	@Override
	public int addMoney(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.addMoney(map);
	}

	@Override
	public int delMoney(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.delMoney(map);
	}

	@Override
	public long selectMoney(Long userId) {
		// TODO Auto-generated method stub
		return mapper.selectMoney(userId);
	}

	@Override
	public int addIntegral(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.addIntegral(map);
	}

	@Override
	public String selectPayPass(Long userId) {
		// TODO Auto-generated method stub
		return mapper.selectPayPass(userId);
	}

	@Override
	public int phonecount(String phone) {
		// TODO Auto-generated method stub
		return mapper.phonecount(phone);
	}

	@Override
	public int phonewechatcount(String phone) {
		// TODO Auto-generated method stub
		return mapper.phonewechatcount(phone);
	}

	@Override
	public String seletePaypassExist(String phone) {
		// TODO Auto-generated method stub
		return mapper.seletePaypassExist(phone);
	}

	@Override
	public int integral(Long userId) {
		// TODO Auto-generated method stub
		return mapper.integral(userId);
	}

	@Override
	public int updatelevel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.updatelevel(map);
	}

	@Override
	public String seletePaypasswechat(String wechatId) {
		// TODO Auto-generated method stub
		return mapper.seletePaypasswechat(wechatId);
	}

	@Override
	public User querystatus(Long userId) {
		// TODO Auto-generated method stub
		return mapper.querystatus(userId);
	}

	@Override
	public int changestatus(Long userId) {
		// TODO Auto-generated method stub
		return mapper.changestatus(userId);
	}

	@Override
	public User seletename(Long userId) {
		// TODO Auto-generated method stub
		return mapper.seletename(userId);
	}

	@Override
	public User PhoneYesWeChatNo(String phone) {
		// TODO Auto-generated method stub
		return mapper.PhoneYesWeChatNo(phone);
	}

	@Override
	public int UpdateWeChatOpenId(User user) {
		// TODO Auto-generated method stub
		return mapper.UpdateWeChatOpenId(user);
	}

	@Override
	public User QueryOpenIdByUserId(Long userId) {
		// TODO Auto-generated method stub
		return mapper.QueryOpenIdByUserId(userId);
	}

	@Override
	public int DeleteWeChatId(Long userId) {
		// TODO Auto-generated method stub
		return mapper.DeleteWeChatId(userId);
	}

	@Override
	public String selecticon(Long userId) {
		// TODO Auto-generated method stub
		return mapper.selecticon(userId);
	}

	@Override
	public int conversion(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.conversion(map);
	}

	@Override
	public int examinePseudonym(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.ExaminePseudonym(map);
	}
	
	@Override
	public User verifyPseudonymAndPwd(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.verifyPseudonymAndPwd(map);
	}

	@Override
	public int QueryExistIdentity(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryExistIdentity(map);
	}

	@Override
	public List<RankUserModel> QueryRankList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryRankList(map);
	}

	@Override
	public List<UserInfoModel> QueryUserInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryUserInfo(map);
	}
	
	@Override
	public List<UserInfoModel> QueryTeacherInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QueryTeacherInfo(map);
	}

	@Override
	public Map<String, Object> QuerySomeMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.QuerySomeMessage(map);
	}

	@Override
	public TotalWealthShowModel ShowTotalWealth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.ShowTotalWealth(map);
	}


}

/**
 * 
 */
package com.huban.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.MessageMapper;
import com.huban.pojo.Message;
import com.huban.service.MessageService;

/**
 * @author GeJiangbo
 * @date 2017年5月17日
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Resource
	private MessageMapper mapper;
	@Override
	public int addMessage(Message message) {
		// TODO Auto-generated method stub
		return mapper.insert(message);
	}

}

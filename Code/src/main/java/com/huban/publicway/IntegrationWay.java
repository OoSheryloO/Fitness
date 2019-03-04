package com.huban.publicway;

import java.math.BigDecimal;

import com.huban.Utils.ConstantUtils;
import com.huban.pojo.Integration;
import com.huban.service.IntegrationService;
import com.huban.service.imp.IntegrationServiceImpl;
import com.huban.util.IdWorker;

public class IntegrationWay {
	public static void addIntegrationMessage(Long userId,int daynum,String reason ,int type){
		Integration integration = new Integration();
		integration.setIntegrationId(IdWorker.CreateNewID());
		integration.setIntegrationStatus(0);
		integration.setIntegrationNum(new BigDecimal(ConstantUtils.daymultiple*daynum));
		integration.setIntegrationStudentid(userId);
		integration.setIntegrationType(type);
		integration.setIntegrationReason(reason);
		IntegrationService integrationService = new IntegrationServiceImpl();
		integrationService.addmessage(integration);
	}

}

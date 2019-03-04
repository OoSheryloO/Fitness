/**
 * 
 */
package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Certificate;

public interface CertificateService {

		//增
	   public int addCertificate(Certificate Certificate);
		
		//查
	   public List<Certificate> getCertificateList(Map<String, Object> map);
		
	   public int getCertificateCount(Map<String, Object> map);
		
		//删
	   public int deleteCertificate(Map<String, Object> map);
		
}

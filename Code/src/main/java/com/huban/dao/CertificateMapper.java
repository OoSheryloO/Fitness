package com.huban.dao;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Certificate;

public interface CertificateMapper {
	//增
	int addCertificate(Certificate Certificate);
	
	//查
	List<Certificate> getCertificateList(Map<String, Object> map);
	
	int getCertificateCount(Map<String, Object> map);
	
	//删
	int deleteCertificate(Map<String, Object> map);
}
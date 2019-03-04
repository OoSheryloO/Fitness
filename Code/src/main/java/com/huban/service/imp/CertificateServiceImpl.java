/**
 * 
 */
package com.huban.service.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huban.dao.CertificateMapper;
import com.huban.pojo.Certificate;
import com.huban.service.CertificateService;

@Service("certificateService")
public class CertificateServiceImpl implements CertificateService{
     @Resource
     private CertificateMapper mapper;

	@Override
	public int addCertificate(Certificate Certificate) {
		return mapper.addCertificate(Certificate);
	}
	@Override
	public List<Certificate> getCertificateList(Map<String, Object> map) {
		return mapper.getCertificateList(map);
	}

	@Override
	public int getCertificateCount(Map<String, Object> map) {
		return mapper.getCertificateCount(map);
	}

	@Override
	public int deleteCertificate(Map<String, Object> map) {
		return mapper.deleteCertificate(map);
	}
     
	
}

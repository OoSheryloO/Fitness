package com.huban.service;

import java.util.List;
import java.util.Map;

import com.huban.pojo.Regions;

public interface RegionsService {

    public List<Regions> firstLevel(Map<String, Object> map);
    
    public List<Regions> secondLevel(Long regionId);
    
    public List<Regions> thirdLevel(Long regionId);
}

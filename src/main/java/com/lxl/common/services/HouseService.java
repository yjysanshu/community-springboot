package com.lxl.common.services;

import com.lxl.admin.models.request.HouseRequest;
import com.lxl.admin.models.response.HouseResponse;
import com.lxl.common.mapper.HouseMapper;
import com.lxl.common.models.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseMapper houseMapper;

    public List<HouseResponse> getList(HouseRequest request) {
        House houseSearch = formatModelDetail(request);
        List<House> listHouse = houseMapper.selectAll(houseSearch);
        List<HouseResponse> list = new ArrayList<>();
        for (com.lxl.common.models.House house : listHouse) {
            HouseResponse response = formatInfoDetail(house);
            list.add(response);
        }
        return list;
    }

    public Integer getTotal(HouseRequest request) {
        com.lxl.common.models.House houseSearch = formatModelDetail(request);
        return houseMapper.getTotal(houseSearch);
    }

    public Integer save(HouseRequest request) {
        House house;
        if (request.getId() != null) {
            house = houseMapper.selectByPrimaryKey(request.getId());
        } else {
            house = new com.lxl.common.models.House();
            house.setHouseCreateAt(new Date());
        }
        house.setHouseCode(request.getCode());
        house.setHouseName(request.getName());
        house.setHouseDescription(request.getDescription());
        house.setHouseStages(request.getStages());
        if (request.getId() != null) {
            return houseMapper.updateByPrimaryKeySelective(house);
        } else {
            return houseMapper.insertSelective(house);
        }
    }

    public String getHouseName(Integer houseId) {
        House house = houseMapper.selectByPrimaryKey(houseId);
        return house.getHouseName();
    }

    private HouseResponse formatInfoDetail(House house) {
        HouseResponse response = new HouseResponse();
        response.setId(house.getHouseId());
        response.setCode(house.getHouseCode());
        response.setName(house.getHouseName());
        response.setStages(house.getHouseStages());
        response.setDescription(house.getHouseDescription());
        return response;
    }

    private House formatModelDetail(HouseRequest request) {
        House house = new House();
        house.setHouseId(request.getId());
        house.setHouseCode(request.getCode());
        house.setHouseName(request.getName());
        house.setHouseStages(request.getStages());
        house.setHouseDescription(request.getDescription());
        house.setHouseCreateAt(request.getCreateAt());
        house.setHouseUpdateAt(request.getUpdateAt());
        return house;
    }
}

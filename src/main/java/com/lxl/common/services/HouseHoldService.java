package com.lxl.common.services;

import com.lxl.admin.models.request.HouseHoldRequest;
import com.lxl.admin.models.response.HouseHoldResponse;
import com.lxl.common.mapper.HouseHoldMapper;
import com.lxl.common.models.HouseHold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HouseHoldService {

    @Autowired
    private HouseHoldMapper houseHoldMapper;

    @Autowired
    private RoomService roomService;

    @Autowired
    private OauthUserService userService;

    /**
     * 获取显示的列表
     * @param request -
     * @return -
     */
    public List<HouseHoldResponse> getList(HouseHoldRequest request) {
        Map<String, Object> map = new HashMap<>();
        HouseHold houseHoldSearch = formatModelDetail(request);
        map.put("houseHold", houseHoldSearch);
        map.put("page", request.getCurrentPage());
        map.put("size", request.getLimit());
        List<HouseHold> listHouseHold = houseHoldMapper.findByParamsAndPage(map);
        List<HouseHoldResponse> list = new ArrayList<>();
        for (HouseHold houseHold : listHouseHold) {
            HouseHoldResponse houseHoldResponse = formatResponseDetail(houseHold);
            houseHoldResponse.setRoomName(roomService.getRoomName(houseHoldResponse.getRoomId()));
            list.add(houseHoldResponse);
        }
        return list;
    }

    public Integer getTotal(HouseHoldRequest request) {
        HouseHold houseHoldSearch = formatModelDetail(request);
        return houseHoldMapper.findTotalByParams(houseHoldSearch);
    }

    /**
     * 保存住户信息
     * @param request 请求的信息
     * @return 受影响的行数
     */
    @Transactional
    public Integer save(HouseHoldRequest request) {
        HouseHold houseHold;
        if (request.getId() != null) {
            houseHold = houseHoldMapper.findOneById(request.getId());
        } else {
            houseHold = new HouseHold();
            houseHold.setHouseHoldCreateAt(new Date());
        }
        houseHold.setHouseHoldRoomId(request.getRoomId());
        houseHold.setHouseHoldName(request.getName());
        houseHold.setHouseHoldPhone(request.getPhone());
        houseHold.setHouseHoldSex(request.getSex());
        houseHold.setHouseHoldIndividual(request.getIndividual());
        houseHold.setHouseHoldType(request.getType());
        houseHold.setHouseHoldDescription(request.getDescription());
        if (request.getId() != null) {
            houseHoldMapper.updateByIdAndParams(houseHold);
        } else {
            houseHoldMapper.insertByParams(houseHold);
        }
        return userService.addUser(houseHold.getHouseHoldId(), houseHold.getHouseHoldPhone(), houseHold.getHouseHoldName());
    }

    /**
     * 删除一条住户记录
     * @param id 住户ID
     * @return 影响的行数
     */
    public Integer delete(Integer id) {
        return houseHoldMapper.deleteOneById(id);
    }

    private HouseHoldResponse formatResponseDetail(HouseHold houseHold) {
        HouseHoldResponse response = new HouseHoldResponse();
        response.setId(houseHold.getHouseHoldId());
        response.setRoomId(houseHold.getHouseHoldRoomId());
        response.setName(houseHold.getHouseHoldName());
        response.setPhone(houseHold.getHouseHoldPhone());
        response.setSex(houseHold.getHouseHoldSex());
        response.setIndividual(houseHold.getHouseHoldIndividual());
        response.setType(houseHold.getHouseHoldType());
        response.setDescription(houseHold.getHouseHoldDescription());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(houseHold.getHouseHoldCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(houseHold.getHouseHoldUpdateAt()));
        return response;
    }

    private HouseHold formatModelDetail(HouseHoldRequest request) {
        HouseHold houseHold = new HouseHold();
        houseHold.setHouseHoldId(request.getId());
        houseHold.setHouseHoldRoomId(request.getRoomId());
        houseHold.setHouseHoldName(request.getName());
        houseHold.setHouseHoldPhone(request.getPhone());
        houseHold.setHouseHoldSex(request.getSex());
        houseHold.setHouseHoldIndividual(request.getIndividual());
        houseHold.setHouseHoldType(request.getType());
        houseHold.setHouseHoldDescription(request.getDescription());
        houseHold.setHouseHoldCreateAt(request.getCreateAt());
        houseHold.setHouseHoldUpdateAt(request.getUpdateAt());
        return houseHold;
    }
}

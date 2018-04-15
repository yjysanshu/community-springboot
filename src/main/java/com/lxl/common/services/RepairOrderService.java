package com.lxl.common.services;

import com.lxl.admin.models.request.RepairOrderRequest;
import com.lxl.admin.models.response.RepairOrderResponse;
import com.lxl.api.models.request.RepairRequest;
import com.lxl.common.mapper.RepairOrderMapper;
import com.lxl.common.models.OauthUser;
import com.lxl.common.models.RepairOrder;
import com.lxl.common.util.CodeUtil;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RepairOrderService extends BaseService {

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Autowired
    private OauthUserService userService;

    public List<RepairOrderResponse> getListByUser() {
        OauthUser oauthUser = this.getUser();
        if (oauthUser == null) {
            return null;
        }
        ConsoleUtil.formatPrint(oauthUser);
        List<RepairOrderResponse> list = new ArrayList<>();
        List<RepairOrder> listRepairOrder = repairOrderMapper.findByUserId(oauthUser.getOauthUserId());
        for (RepairOrder repairOrder : listRepairOrder) {
            RepairOrderResponse repairOrderResponse = formatResponseDetail(repairOrder);
            list.add(repairOrderResponse);
        }
        ConsoleUtil.formatPrint(list);
        return list;
    }

    public Integer userReport(RepairRequest request) {
        OauthUser oauthUser = this.getUser();
        if (oauthUser == null) {
            return 0;
        }
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRepairOrderCreateAt(new Date());
        repairOrder.setRepairOrderCode(CodeUtil.createRepairOrderCode());
        repairOrder.setRepairOrderUserId(oauthUser.getOauthUserId());
        repairOrder.setRepairOrderRepairRangeId(request.getRangeId());
        repairOrder.setRepairOrderPhone(request.getPhone());
        repairOrder.setRepairOrderStatus(0);
        repairOrder.setRepairOrderDescription(request.getDescription());
        return repairOrderMapper.insertByParams(repairOrder);
    }

    public List<RepairOrderResponse> getList(RepairOrderRequest request) {
        Map<String, Object> map = new HashMap<>();
        RepairOrder repairOrderSearch = formatModelDetail(request);
        map.put("repairOrder", repairOrderSearch);
        map.put("page", request.getCurrentPage());
        map.put("size", request.getLimit());
        List<RepairOrder> listRepairOrder = repairOrderMapper.findByParamsAndPage(map);
        List<RepairOrderResponse> list = new ArrayList<>();
        for (RepairOrder repairOrder : listRepairOrder) {
            RepairOrderResponse repairOrderResponse = formatResponseDetail(repairOrder);
            list.add(repairOrderResponse);
        }
        return list;
    }

    public Integer getTotal(RepairOrderRequest request) {
        RepairOrder repairOrderSearch = formatModelDetail(request);
        return repairOrderMapper.findTotalByParams(repairOrderSearch);
    }

    /**
     * 保存报修工单
     * @param request -
     * @return -
     */
    public Integer save(RepairOrderRequest request) {
        RepairOrder repairOrder;
        if (request.getId() != null) {
            repairOrder = repairOrderMapper.findOneById(request.getId());
            repairOrder.setRepairOrderAdminUserId(this.getCurrentUser().getAdminUserId());
        } else {
            repairOrder = new RepairOrder();
            repairOrder.setRepairOrderCreateAt(new Date());
            repairOrder.setRepairOrderCode(CodeUtil.createRepairOrderCode());
        }
        repairOrder.setRepairOrderUserId(request.getUserId());
        repairOrder.setRepairOrderRepairRangeId(request.getRepairRangeId());
        repairOrder.setRepairOrderPhone(request.getPhone());
        repairOrder.setRepairOrderStatus(request.getStatus());
        repairOrder.setRepairOrderDescription(request.getDescription());
        if (request.getId() != null) {
            return repairOrderMapper.updateByIdAndParams(repairOrder);
        } else {
            return repairOrderMapper.insertByParams(repairOrder);
        }
    }

    public Integer delete(Integer id) {
        return repairOrderMapper.deleteOneById(id);
    }

    private RepairOrderResponse formatResponseDetail(RepairOrder repairOrder) {
        RepairOrderResponse response = new RepairOrderResponse();
        response.setId(repairOrder.getRepairOrderId());
        response.setCode(repairOrder.getRepairOrderCode());
        response.setUserId(repairOrder.getRepairOrderUserId());
        response.setUserName(userService.getUserNameById(repairOrder.getRepairOrderUserId()));
        response.setRepairRangeId(repairOrder.getRepairOrderRepairRangeId());
        response.setPhone(repairOrder.getRepairOrderPhone());
        response.setStatus(repairOrder.getRepairOrderStatus());
        response.setAdminUserId(repairOrder.getRepairOrderAdminUserId());
        response.setDescription(repairOrder.getRepairOrderDescription());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(repairOrder.getRepairOrderCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(repairOrder.getRepairOrderUpdateAt()));
        return response;
    }

    private RepairOrder formatModelDetail(RepairOrderRequest request) {
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRepairOrderId(request.getId());
        repairOrder.setRepairOrderCode(request.getCode());
        repairOrder.setRepairOrderUserId(request.getUserId());
        repairOrder.setRepairOrderRepairRangeId(request.getRepairRangeId());
        repairOrder.setRepairOrderPhone(request.getPhone());
        repairOrder.setRepairOrderStatus(request.getStatus());
        repairOrder.setRepairOrderAdminUserId(request.getAdminUserId());
        repairOrder.setRepairOrderDescription(request.getDescription());
        repairOrder.setRepairOrderCreateAt(request.getCreateAt());
        repairOrder.setRepairOrderUpdateAt(request.getUpdateAt());
        return repairOrder;
    }
}
package com.lxl.common.services;

import com.lxl.admin.models.request.RepairOrderRequest;
import com.lxl.admin.models.response.RepairOrderResponse;
import com.lxl.common.mapper.RepairOrderMapper;
import com.lxl.common.models.RepairOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RepairOrderService {

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    public List<RepairOrderResponse> getList(RepairOrderRequest request) {
        RepairOrder repairOrderSearch = formatModelDetail(request);
        List<RepairOrder> listRepairOrder = repairOrderMapper.findByParams(repairOrderSearch);
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

    public Integer save(RepairOrderRequest request) {
        RepairOrder repairOrder;
        if (request.getId() != null) {
            repairOrder = repairOrderMapper.findOneById(request.getId());
        } else {
            repairOrder = new RepairOrder();
            repairOrder.setRepairOrderCreateAt(new Date());
        }
        repairOrder.setRepairOrderCreateAt(request.getCreateAt());
        if (request.getId() != null) {
            return repairOrderMapper.updateByIdAndParams(repairOrder);
        } else {
            return repairOrderMapper.insertByParams(repairOrder);
        }
    }

    private RepairOrderResponse formatResponseDetail(RepairOrder repairOrder) {
        RepairOrderResponse response = new RepairOrderResponse();
        response.setId(repairOrder.getRepairOrderId());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(repairOrder.getRepairOrderCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(repairOrder.getRepairOrderUpdateAt()));
        return response;
    }

    private RepairOrder formatModelDetail(RepairOrderRequest request) {
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRepairOrderId(request.getId());
        repairOrder.setRepairOrderCreateAt(request.getCreateAt());
        repairOrder.setRepairOrderUpdateAt(request.getUpdateAt());
        return repairOrder;
    }
}

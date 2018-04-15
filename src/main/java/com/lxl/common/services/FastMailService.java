package com.lxl.common.services;

import com.lxl.admin.models.request.FastMailRequest;
import com.lxl.admin.models.response.FastMailResponse;
import com.lxl.common.mapper.FastMailMapper;
import com.lxl.common.models.FastMail;
import com.lxl.common.models.OauthUser;
import com.lxl.common.util.ConsoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FastMailService extends BaseService {

    @Autowired
    private FastMailMapper fastMailMapper;

    public List<FastMailResponse> getListByUser() {
        OauthUser oauthUser = this.getUser();
        if (oauthUser == null) {
            return null;
        }
        ConsoleUtil.formatPrint(oauthUser);
        List<FastMailResponse> list = new ArrayList<>();
        List<FastMail> listFastMail = fastMailMapper.findByPhone(oauthUser.getOauthUserPhone());
        for (FastMail fastMail : listFastMail) {
            FastMailResponse fastMailResponse = formatResponseDetail(fastMail);
            list.add(fastMailResponse);
        }
        ConsoleUtil.formatPrint(list);
        return list;
    }

    public List<FastMailResponse> getList(FastMailRequest request) {
        Map<String, Object> map = new HashMap<>();
        FastMail fastMailSearch = formatModelDetail(request);
        map.put("fastMail", fastMailSearch);
        map.put("page", request.getCurrentPage());
        map.put("size", request.getLimit());
        List<FastMail> listFastMail = fastMailMapper.findByParamsAndPage(map);
        List<FastMailResponse> list = new ArrayList<>();
        for (FastMail fastMail : listFastMail) {
            FastMailResponse fastMailResponse = formatResponseDetail(fastMail);
            list.add(fastMailResponse);
        }
        return list;
    }

    public Integer getTotal(FastMailRequest request) {
        FastMail fastMailSearch = formatModelDetail(request);
        return fastMailMapper.findTotalByParams(fastMailSearch);
    }

    public Integer save(FastMailRequest request) {
        FastMail fastMail;
        if (request.getId() != null) {
            fastMail = fastMailMapper.findOneById(request.getId());
        } else {
            fastMail = new FastMail();
            fastMail.setFastMailCreateAt(new Date());
        }
        fastMail.setFastMailOrderNo(request.getOrderNo());
        fastMail.setFastMailCustomNo(request.getCustomNo());
        fastMail.setFastMailBusiness(request.getBusiness());
        fastMail.setFastMailReceiveRoomId(request.getReceiveRoomId());
        fastMail.setFastMailReceiveName(request.getReceiveName());
        fastMail.setFastMailReceivePhone(request.getReceivePhone());
        fastMail.setFastMailReceiveAddress(request.getReceiveAddress());
        fastMail.setFastMailStatus(request.getStatus());
        fastMail.setFastMailMemo(request.getMemo());
        if (request.getId() != null) {
            return fastMailMapper.updateByIdAndParams(fastMail);
        } else {
            return fastMailMapper.insertByParams(fastMail);
        }
    }

    /**
     * 快递收货
     * @param id Integer
     * @return Integer
     */
    public Integer receive(Integer id) {
        return fastMailMapper.receiveById(id);
    }

    /**
     * 删除一条快递记录
     * @param id Integer
     * @return Integer
     */
    public Integer delete(Integer id) {
        return fastMailMapper.deleteOneById(id);
    }

    private FastMailResponse formatResponseDetail(FastMail fastMail) {
        FastMailResponse response = new FastMailResponse();
        response.setId(fastMail.getFastMailId());
        response.setOrderNo(fastMail.getFastMailOrderNo());
        response.setCustomNo(fastMail.getFastMailCustomNo());
        response.setBusiness(fastMail.getFastMailBusiness());
        response.setReceiveRoomId(fastMail.getFastMailReceiveRoomId());
        response.setReceiveName(fastMail.getFastMailReceiveName());
        response.setReceivePhone(fastMail.getFastMailReceivePhone());
        response.setReceiveAddress(fastMail.getFastMailReceiveAddress());
        response.setStatus(fastMail.getFastMailStatus());
        response.setMemo(fastMail.getFastMailMemo());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(fastMail.getFastMailCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(fastMail.getFastMailUpdateAt()));
        return response;
    }

    private FastMail formatModelDetail(FastMailRequest request) {
        FastMail fastMail = new FastMail();
        fastMail.setFastMailId(request.getId());
        fastMail.setFastMailOrderNo(request.getOrderNo());
        fastMail.setFastMailCustomNo(request.getCustomNo());
        fastMail.setFastMailBusiness(request.getBusiness());
        fastMail.setFastMailReceiveRoomId(request.getReceiveRoomId());
        fastMail.setFastMailReceiveName(request.getReceiveName());
        fastMail.setFastMailReceivePhone(request.getReceivePhone());
        fastMail.setFastMailReceiveAddress(request.getReceiveAddress());
        fastMail.setFastMailStatus(request.getStatus());
        fastMail.setFastMailMemo(request.getMemo());
        fastMail.setFastMailCreateAt(request.getCreateAt());
        fastMail.setFastMailUpdateAt(request.getUpdateAt());
        return fastMail;
    }
}
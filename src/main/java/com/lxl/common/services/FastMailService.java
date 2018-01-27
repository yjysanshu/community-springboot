package com.lxl.common.services;

import com.lxl.admin.models.request.FastMailRequest;
import com.lxl.admin.models.response.FastMailResponse;
import com.lxl.common.mapper.FastMailMapper;
import com.lxl.common.mapper.HouseMapper;
import com.lxl.common.models.FastMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FastMailService {

    @Autowired
    private FastMailMapper fastMailMapper;

    public List<FastMailResponse> getList(FastMailRequest request) {
        FastMail fastMailSearch = formatModelDetail(request);
        List<FastMail> listFastMail = fastMailMapper.selectAll(fastMailSearch);
        List<FastMailResponse> list = new ArrayList<>();
        for (FastMail fastMail : listFastMail) {
            FastMailResponse fastMailResponse = formatResponseDetail(fastMail);
            list.add(fastMailResponse);
        }
        return list;
    }

    public Integer getTotal(FastMailRequest request) {
        FastMail fastMailSearch = formatModelDetail(request);
        return fastMailMapper.getTotal(fastMailSearch);
    }

    public Integer save(FastMailRequest request) {
        FastMail fastMail;
        if (request.getId() != null) {
            fastMail = fastMailMapper.selectByPrimaryKey(request.getId());
        } else {
            fastMail = new FastMail();
            fastMail.setFastMailCreateAt(new Date());
        }
        fastMail.setFastMailCreateAt(request.getCreateAt());
        if (request.getId() != null) {
            return fastMailMapper.updateByPrimaryKeySelective(fastMail);
        } else {
            return fastMailMapper.insertSelective(fastMail);
        }
    }

    private FastMailResponse formatResponseDetail(FastMail fastMail) {
        FastMailResponse response = new FastMailResponse();
        response.setId(fastMail.getFastMailId());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(fastMail.getFastMailCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(fastMail.getFastMailUpdateAt()));
        return response;
    }

    private FastMail formatModelDetail(FastMailRequest request) {
        FastMail fastMail = new FastMail();
        fastMail.setFastMailId(request.getId());
        fastMail.setFastMailCreateAt(request.getCreateAt());
        fastMail.setFastMailUpdateAt(request.getUpdateAt());
        return fastMail;
    }
}

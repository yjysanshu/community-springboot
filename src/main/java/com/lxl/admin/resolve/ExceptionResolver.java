package com.lxl.admin.resolve;

import com.lxl.common.consts.ErrorConst;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ExceptionResolver extends Throwable implements HandlerExceptionResolver {

    private Integer code;
    private String message;

    public ExceptionResolver(Integer code) {
        this.code = code;
        this.message = ErrorConst.messageMap.get(code);
    }

    public ExceptionResolver(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        if (e instanceof DataIntegrityViolationException) {
            map.put("message", message);
        } else {
            map.put("message", message);
        }
        mv.addAllObjects(map);
        return mv;
    }
}

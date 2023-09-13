package com.lq.oauth.handler;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author: liuqianqian
 * @CreateTime: 2023-09-11  14:49
 * @Description: token认证失败异常
 * @Version: 1.0
 */
@Slf4j
public class OpenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException{
      log.error("认证异常:{},{}",httpServletRequest.getRequestURI(),e.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        httpServletResponse.setContentType(ContentType.build(ContentType.JSON.getValue(), Charset.defaultCharset()));
        httpServletResponse.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        JSONObject json=new JSONObject();
        json.set("code",401);
        json.set("msg",e.getMessage());
        httpServletResponse.getWriter().write(mapper.writeValueAsString(json));
    }
}

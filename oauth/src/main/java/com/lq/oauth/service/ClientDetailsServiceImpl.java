package com.lq.oauth.service;

import com.lq.oauth.domain.OpenAuthority;
import com.lq.oauth.utils.DateUtils;
import com.lq.oauth.utils.SecurityUtils;
import com.lq.oauth.domain.OpenClientDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author: liuqianqian
 * @date: 2018/11/12 16:26
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ClientDetailsServiceImpl implements ClientDetailsService {

//    @Autowired
//    private BaseAppServiceClient baseAppServiceClient;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//        ClientDetails details = baseAppServiceClient.getAppClientInfo(clientId).getData();
        OpenClientDetails details=new OpenClientDetails();
        details.setClientId("11111");
        details.setClientSecret(SecurityUtils.encryptPassword("11111"));
        details.setAuthorizedGrantTypes(Arrays.asList("client_credentials", "refresh_token"));
        details.setResourceIds(Arrays.asList("11111"));
//        details.setAccessTokenValiditySeconds(7200);
        Map<String ,Object> map = new HashMap<>();
        map.put("status",1);
        details.setAdditionalInformation(map);
        details.setScope(Arrays.asList("all"));

        /**
         * 接口权限
         */
        OpenAuthority authority=new OpenAuthority("test");
        authority.setExpireTime(DateUtils.getDateFromLocalDateTime(LocalDateTime.now().plusHours(-2)));
        List<GrantedAuthority> lists=new ArrayList<>();
        lists.add(authority);

        details.setAuthorities(lists);
        if (details != null && details.getClientId()!=null && details.getAdditionalInformation() != null) {
            String status = details.getAdditionalInformation().getOrDefault("status", "0").toString();
            if(!"1".equals(status)){
                throw new ClientRegistrationException("客户端已被禁用");
            }
        }
        return details;
    }
}

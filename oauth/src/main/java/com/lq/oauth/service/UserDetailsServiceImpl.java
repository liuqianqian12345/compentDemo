package com.lq.oauth.service;

import com.lq.oauth.domain.OpenUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security用户信息获取实现类
 *
 * @author liuqianqian
 */
@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private BaseDeveloperServiceClient baseDeveloperServiceClient;
//    @Autowired
//    private OpenOAuth2ClientProperties clientProperties;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        OpenUserDetails userDetails = new OpenUserDetails();
        userDetails.setAccountId(1000L);
        userDetails.setUserId(1000L);
        userDetails.setUsername("1q");
        userDetails.setPassword("1111");
        userDetails.setNickName("nickName");
        userDetails.setEnabled(true);
        return userDetails;
    }
}

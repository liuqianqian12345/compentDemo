package com.lq.oauth.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Collection;
import java.util.Date;

public class AutoRenewalRedisTokenStore extends RedisTokenStore {

    private ClientDetailsService clientDetailsService;

    public AutoRenewalRedisTokenStore(RedisConnectionFactory connectionFactory, ClientDetailsService clientDetailsService) {

        super(connectionFactory);

        this.clientDetailsService = clientDetailsService;

    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {

        OAuth2Authentication result = readAuthentication(token.getValue());

        if (result != null) {

            DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) token;

            String clientId=result.getOAuth2Request().getClientId();
            Collection<OAuth2AccessToken> tokens=findTokensByClientId(clientId);
            OAuth2AccessToken o= (OAuth2AccessToken) tokens.toArray()[0];
            if (o.getExpiresIn()>0){
                oAuth2AccessToken.setExpiration(new Date(System.currentTimeMillis() + 7200 * 1000L));
            }

            storeAccessToken(oAuth2AccessToken, result);

        }

        return result;

    }

    protected int getAccessTokenValiditySeconds(OAuth2Request clientAuth) {

        if (clientDetailsService != null) {

            ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());

            Integer validity = client.getAccessTokenValiditySeconds();

            if (validity != null) {

                return validity;

            }

        }
        int accessTokenValiditySeconds = 7200;

        return accessTokenValiditySeconds;
    }

}

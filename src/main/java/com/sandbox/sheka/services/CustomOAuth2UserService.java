package com.sandbox.sheka.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import static com.sandbox.sheka.config.utils.Constants.NAME;
import static com.sandbox.sheka.config.utils.Constants.SCOPE;


public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
{

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException
    {

        Map<String, Object> userAttributes = new HashMap<>();
        userAttributes.put(SCOPE, userRequest.getAccessToken().getScopes());

        Map<String, Object> additionalParameters = userRequest.getAdditionalParameters();
        if(additionalParameters.containsKey(NAME)){
            userAttributes.put(NAME, additionalParameters.get(NAME));
        }

        return new DefaultOAuth2User(null, userAttributes, NAME);
    }
}

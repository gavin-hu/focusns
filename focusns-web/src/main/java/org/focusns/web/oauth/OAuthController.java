package org.focusns.web.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.SinaWeiboApi20;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.Properties;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

    private static final Token EMPTY_TOKEN = null;

    @Autowired
    @Qualifier("applicationProperties")
    private Properties applicationProperties;

    @RequestMapping("/authorize")
    public String authorize(@RequestParam String provider, WebRequest webRequest) throws Exception {
        OAuthService oAuthService = getOAuthService(provider);
        //
        webRequest.setAttribute("oAuthService", oAuthService, WebRequest.SCOPE_SESSION);
        //
        return "redirect:" + oAuthService.getAuthorizationUrl(EMPTY_TOKEN);
    }

    @RequestMapping("/callback")
    public String callback(@RequestParam String code, WebRequest webRequest) {
        OAuthService oAuthService = (OAuthService) webRequest.getAttribute("oAuthService", WebRequest.SCOPE_SESSION);
        //
        Verifier verifier = new Verifier(code);
        Token accessToken = oAuthService.getAccessToken(EMPTY_TOKEN, verifier);
        webRequest.setAttribute("accessToken", accessToken, WebRequest.SCOPE_SESSION);
        //
        return "redirect:/register";
    }

    private OAuthService getOAuthService(String provider) throws Exception {
        String apiClass = applicationProperties.getProperty(String.format("oauth.%s.apiClass", provider));
        String apiKey = applicationProperties.getProperty(String.format("oauth.%s.apiKey", provider));
        String apiSecret = applicationProperties.getProperty(String.format("oauth.%s.apiSecret", provider));
        String callback = applicationProperties.getProperty(String.format("oauth.%s.callback", provider));
        //
        Class<? extends Api> apiClazz = (Class<? extends Api>) ClassUtils.forName(apiClass);
        //
        return new ServiceBuilder().provider(apiClazz).apiKey(apiKey).apiSecret(apiSecret).callback(callback).build();
    }

}

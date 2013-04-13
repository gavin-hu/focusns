package org.focusns.web.oauth;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.Properties;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
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

@Controller
@RequestMapping("/oauth")
public class OAuthController {

    private static final Token EMPTY_TOKEN = null;

    @Autowired
    @Qualifier("application")
    private Properties application;

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

    @SuppressWarnings("unchecked")
    private OAuthService getOAuthService(String provider) throws Exception {
        String apiClass = application.getProperty(String.format("oauth.%s.apiClass", provider));
        String apiKey = application.getProperty(String.format("oauth.%s.apiKey", provider));
        String apiSecret = application.getProperty(String.format("oauth.%s.apiSecret", provider));
        String callback = application.getProperty(String.format("oauth.%s.callback", provider));
        //
        Class<? extends Api> apiClazz = (Class<? extends Api>) ClassUtils.forName(apiClass, getClass().getClassLoader());
        //
        return new ServiceBuilder().provider(apiClazz).apiKey(apiKey).apiSecret(apiSecret).callback(callback).build();
    }

}

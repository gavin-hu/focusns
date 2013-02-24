package org.focusns.social.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;

public class SinaApi extends DefaultApi20 {

    private static final String ACCESS_TOKEN_END_POINT = "https://api.weibo.com/oauth2/access_token?grant_type=authorization_code";
    private static final String AUTHORIZATION_URL = "https://api.weibo.com/oauth2/authorize?client_id=%s&response_type=code&redirect_uri=%s";

    @Override
    public String getAccessTokenEndpoint() {
        return String.format(ACCESS_TOKEN_END_POINT, "", "", "");
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        return String.format(AUTHORIZATION_URL, config.getApiKey(), config.getCallback());
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

}

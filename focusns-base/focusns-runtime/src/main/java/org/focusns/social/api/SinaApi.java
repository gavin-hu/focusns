/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package org.focusns.social.api;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

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

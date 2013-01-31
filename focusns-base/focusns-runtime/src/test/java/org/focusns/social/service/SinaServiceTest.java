

package org.focusns.social.service;

/*
 * #%L
 * FocusSNS Runtime
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


import org.focusns.social.api.SinaApi;
import org.junit.Before;
import org.junit.Test;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

public class SinaServiceTest {

    private Token EMPTY_TOKEN = null;
    private OAuthService oAuthService;

    @Before
    public void setup() {
        oAuthService = new ServiceBuilder()
                .provider(SinaApi.class)
                .apiKey("1530323438")
                .apiSecret("5578be7bd2ff290413ab1046cea7e99c")
                .callback("http://www.opensourceforce.org")
                .build();
    }

    @Test
    public void testOAuth() {
        //Scanner scanner = new Scanner(System.in);

        String authorizationUrl = oAuthService.getAuthorizationUrl(EMPTY_TOKEN);
        System.out.println(authorizationUrl);

        //
        Verifier verifier = new Verifier("22b0abf449005695a5ee0b0343ffd263");
        Token accessToken = oAuthService.getAccessToken(EMPTY_TOKEN, verifier);
        System.out.println("Got access token : " + accessToken);
    }

}

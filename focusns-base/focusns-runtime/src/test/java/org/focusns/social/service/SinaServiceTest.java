

package org.focusns.social.service;

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

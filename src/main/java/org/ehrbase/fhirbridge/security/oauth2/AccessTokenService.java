/*
 * Copyright 2020-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.fhirbridge.security.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * @author Renaud Subiger
 * @since 1.2
 */
@SuppressWarnings("java:S6212")
public class AccessTokenService {

    private final HttpClient httpClient = HttpClients.createDefault();

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String tokenUrl;

    private final String clientId;

    private final String clientSecret;

    private String token;

    private Instant expirationTime;

    public AccessTokenService(String tokenUrl, String clientId, String clientSecret) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getToken() {
        if (token == null || isTokenExpired()) {
            token = requestAccessToken();
        }
        return token;
    }

    private String requestAccessToken() {
        var requestTime = Instant.now();

        var post = new HttpPost(tokenUrl);
        var parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
        parameters.add(new BasicNameValuePair("client_id", clientId));
        parameters.add(new BasicNameValuePair("client_secret", clientSecret));

        try {
            post.setEntity(new UrlEncodedFormEntity(parameters));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        AccessTokenResponse accessTokenResponse;
        try {
            var response = httpClient.execute(post);
            var content = response.getEntity().getContent();
            accessTokenResponse = objectMapper.readValue(content, AccessTokenResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set token expiration time
        expirationTime = requestTime.plus(accessTokenResponse.getExpiresIn(), ChronoUnit.SECONDS);

        return accessTokenResponse.getAccessToken();
    }

    private boolean isTokenExpired() {
        return Instant.now().isAfter(expirationTime);
    }
}

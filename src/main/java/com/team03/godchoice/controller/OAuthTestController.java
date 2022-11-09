package com.team03.godchoice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/login/oauth/code")
public class OAuthTestController {

    @GetMapping("/naver")
    @ResponseBody
    public String naverOAuthRedirect(@RequestParam String code, @RequestParam String state, Model model) {
        // 인증 토큰으로 Access Token으로 교환하기
        // RestTemplate 인스턴스 생성
        RestTemplate rt = new RestTemplate();

        HttpHeaders accessTokenHeaders = new HttpHeaders();
        accessTokenHeaders.add("Content-type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> accessTokenParams = new LinkedMultiValueMap<>();
        accessTokenParams.add("grant_type", "authorization_code");
        accessTokenParams.add("client_id", "BeFF2BLXGHQ26lS7FEd0");
        accessTokenParams.add("client_secret", "zk9ebNHQld");
        accessTokenParams.add("code" , code);	// 응답으로 받은 코드
        accessTokenParams.add("state" , state); // 응답으로 받은 상태

        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(accessTokenParams, accessTokenHeaders);
        System.out.println(accessTokenRequest);
        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                accessTokenRequest,
                String.class
        );
        System.out.println("code = " + code + ", state = " + state + ", model = " + model);
        System.out.println("accessTokenResponse = " + accessTokenResponse);
        return "accessToken: " + accessTokenResponse.getBody();

        // Access Token을 이용해 프로필 API 호출하기
    }
}
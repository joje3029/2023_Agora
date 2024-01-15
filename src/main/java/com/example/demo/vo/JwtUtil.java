package com.example.demo.vo;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import reactor.netty.internal.util.MapUtils;

@Component
public class JwtUtil {

	public Claims extractClaims(String idToken) throws Exception {

	    String token = idToken;
	    Base64.Decoder decoder = Base64.getDecoder();
	    String payload = new String(decoder.decode(token));

	    ObjectMapper mapper = new ObjectMapper();
	    Map<String, Object> returnMap = mapper.readValue(payload, Map.class);
		
	    System.out.println("오냐?");
	    
	    System.out.println("decoder : "+decoder);
	    
	    return null;
	}
}

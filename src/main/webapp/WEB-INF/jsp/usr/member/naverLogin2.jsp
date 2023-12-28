<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="네이버 로그인" />

<%@ include file="../common/head2.jsp"%>

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>

<html>
  <body>
  <div>뱁새씨?</div>
  
  <%
    String clientId = "sre3apwylaef28oEMZxP";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "rwYLDjLKwK";//애플리케이션 클라이언트 시크릿값";
    String code = request.getParameter("code");
    String state = request.getParameter("state");
    String redirectURI = URLEncoder.encode("http://localhost:8081/usr/member/naverLogin", "UTF-8");
    String apiURL;
    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
    apiURL += "client_id=" + clientId;
    apiURL += "&client_secret=" + clientSecret;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&code=" + code;
    apiURL += "&state=" + state;
    String access_token = "";
    String refresh_token = "";
    System.out.println("apiURL="+apiURL); // https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id=sre3apwylaef28oEMZxP&client_secret=rwYLDjLKwK&redirect_uri=YOUR_CALLBACK_URL&code=uLwOp5cHvFxS05Chki&state=1294423827402488524614539180491164678895
    try {
      URL url = new URL(apiURL);
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();
      BufferedReader br;
      System.out.print("responseCode="+responseCode); //https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id=sre3apwylaef28oEMZxP&client_secret=rwYLDjLKwK&redirect_uri=YOUR_CALLBACK_URL&code=uLwOp5cHvFxS05Chki&state=1294423827402488524614539180491164678895
      if(responseCode==200) { // 정상 호출 // 일단 200이 떴거든. 
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      } else {  // 에러 발생
        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
      }
      String inputLine;
      StringBuffer res = new StringBuffer();
      while ((inputLine = br.readLine()) != null) {
        res.append(inputLine);
      }
      br.close();
      if(responseCode==200) {
        out.println(res.toString());
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  %> 
  </body>
</html>

<!--  인가코드까지는 함 -->
<!-- 이제부터 토큰 받을꺼임 -->
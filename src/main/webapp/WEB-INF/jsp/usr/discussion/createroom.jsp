<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<c:set var="pageTitle" value="${board.name }" />

<%@ include file="../common/head2.jsp"%>

		<section class="login border">
	        <h1 class="text-4xl">토론방 만들기</h1>
	        <form action="">
	            <table class="border">
	                <tr class="border">
	                    <th class="border">토론방 이름</th>
	                    <td><input class="input input-bordered w-full max-w-xs input-sm" type="text" placeholder="토론방 이름을 입력해주세요"></td>
	                </tr>
	                <tr class="border">
	                    <th colspan="2">
	                        <label>
	                            <input type="radio" name="type" value="채팅" class="radio radio-success radio-xs" checked/>
	                            채팅 토론
	                        </label>
	                        &nbsp;
	                        <label>
	                            <input type="radio" name="type" value="화상" class="radio radio-success radio-xs ">
	                            화상 토론
	                        </label>
	                    </th>
	                </tr>
	                <tr class="border">
	                    <th colspan="2"><button class="btn border mt-1">만들기</button></th>
	                </tr>
	            </table>
	        </form>
	    </section>
	</body>
</html>    

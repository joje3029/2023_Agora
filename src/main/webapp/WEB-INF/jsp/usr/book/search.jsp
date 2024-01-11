<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>

<%@ include file="../common/floatingBanner.jsp"%>

<link rel="stylesheet" href="/resource/main.css" />

<script>
    $(document).ready(function () {
        $(".searchBook").click(function () {
            const bookTitle = $("input[name='bookTitle']").val();
            $.ajax({
                url: "doBookSearch", // ajax 요청 날림.
                method: "get",
                data: {
                    "bookTitle": bookTitle,
                },
                dataType: "json", // 답이 text로옴
                success: function (data) {
                    data.items.forEach(function (item) {
                        // 표로 해서 들어가야겠네. 
                    });
                    
                    
                    // 여기에 검색 결과를 처리하는 코드 추// 표 시작 태그
                     let tableHtml = "<table class='table'>";
                     
                     // 표 헤더 추가
                     tableHtml += "<tr><th>책 이미지</th><th>책 제목</th><th>작가</th><th>책 소개내용</th><th>가격</th><th>출판사</th><th>출판일</th></tr>";

                     // 각 아이템을 표 행으로 추가
                     data.items.forEach(function (item) {
                         tableHtml += "<tr class=text-xs>";
                         tableHtml += "<td><a href ='" + item.link + "'><img src='" + item.image + "' alt='Book Image'width='1500'><a></td>";
                         tableHtml += "<td>" + item.title + "</td>";
                         tableHtml += "<td width='6%'>" + item.author + "</td>";
                         tableHtml += "<td  width='15%'>" + item.description + "</td>";
                         tableHtml += "<td>" + item.discount + "</td>";
                         tableHtml += "<td width='7%'>" + item.publisher + "</td>";
                         tableHtml += "<td>" + item.pubdate + "</td>";
                         tableHtml += "</tr>";
                     });

                     // 표 종료 태그
                     tableHtml += "</table>";

                     // 결과를 HTML에 삽입
                     $(".bookSearchResult").html(tableHtml);
                 },
                 error: function (xhr, status, error) {
                     console.error("ERROR : " + status + " - " + error);
                 }
            });
        });
    });
</script>

		<section class="center">
		    <div>
		        <!-- 검색기능 폼 -->
		        <div class="navbar bg-base-100 border">
		            <input name="bookTitle" placeholder="검색할 책 제목을 써주세요."
		                class="w-full h-10" />
		            <div>
		                <button type="button" class="searchBook">
		                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5"
		                        fill="none" viewBox="0 0 24 24" stroke="currentColor">
		                        <path stroke-linecap="round" stroke-linejoin="round"
		                            stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
		                </button>
		            </div>
		        </div>
		    </div>
		    <div class="bookSearchResult"></div>
		</section>
	</body>
</html>
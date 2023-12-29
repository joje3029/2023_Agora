<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<%@ include file="../common/head.jsp"%>

<%@ include file="../common/floatingBanner.jsp"%>

<script src="/resource/common.js" defer="defer"></script>

<section class="listBody">
	 <div>
            <div class="text-sm breadcrumbs">
                <ul>
                  <li><a href="/usr/home/main">Home<i class="fa-solid fa-house"></i></a></li> 
                  <li><a href="/usr/article/list">칼럼</a></li> 
                  <li>${typeName}</li>
                </ul>
            </div>
            <!--메뉴 : 네비게이션 일자로 만들어요!-->
            <div class="menuBar">
			<ul
				class="menu menu-vertical lg:menu-horizontal bg-base-200 rounded-box my-2 ">
				<!-- <li><select class="select select-bordered w-32 max-w-xs">
				  <option disabled selected>전체 칼럼</option>
				  <option>종교</option>
				  <option>사회과학</option>
				  <option>자연과학</option>
				  <option>기술과학</option>
				  <option>예술</option>
				  <option>언어</option>
				  <option>문학</option>
				  <option>역사</option> 
				</select></li>-->
				<li class="text-lg"><a href="?type=1">전체 칼럼</a></li>
				<c:if test="${rq.getLoginedMemberId() != 0 }">
					<li class="text-lg"><a href="?type=2">구독한 작가 칼럼</a></li>
					<li class="text-lg"><a href="?type=3">좋아요 한 칼럼</a></li>
				</c:if>
			</ul>
		</div>
            <div class="container mx-auto px-3">
        
                <!-- 여다가 검색기능 만들꺼임. -->
                <form>
                	<input type="hidden" name="type" value="${type }">
                    <div class="navbar bg-base-100 border">
                        <select name="searchKeywordType"  data-value="${searchKeywordType }" class="select select-bordered select-sm mr-1" >
                        	<option value="all">전체</option>
							<option value="title">제목</option>
							<option value="body">내용</option>
							<option value="write">작성자</option>
                        </select>
                        <input type="text" name="searchKeyword" placeholder="검색어를 써주세요." class="btn btn-ghost text-xl w-10/12"/>
                        <div>
                            <button class="btn btn-ghost btn-circle">
                              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
                            </button>
                            <div class="btn btn-ghost btn-circle">
                              </div>
                            </div>
                          </div>
                    </div>
                </form>
        </div>
        <!--칼럼작성버튼 로그인시에만 나옴-->
        <c:if test="${rq.getLoginedMemberId() != 0 }">
            <div class="flex justify-end">
                <div class="mr-16 btn mt-1">
                    <a href="write">칼럼 작성</a>
                </div>
            </div>
        </c:if>

		<div class="list_outline">
            <div>
                <c:forEach var="article" items="${articles }">
                    <div class="border list_outline m-0.5">
                        <div>
                            <a href="detail?id=${article.id }"> 
                                <p class="room-name p-3 bg-green-100">${article.title }</p>
                                <div class="empty"></div>
                                <div class="flex">
                                    <i class="fa-solid fa-user"></i>
                                    &nbsp;
                                    <p class="moderator">${article.nickname }</p>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <p class="headcount">${article.writngTime }</p>
                                </div>
                            </a>
                        </div>
                      </div>
                      <hr />
                </c:forEach>
            </div>
        </div>
        <!-- 여기서부터 페이지네이션 부분 -->
        <div class="mt-2 flex justify-center">
				<div class="join">
					<c:set var="pageMenuLen" value="5" />
					<c:set var="startPage" value="${page - pageMenuLen >= 1 ? page - pageMenuLen : 1 }" />
					<c:set var="endPage" value="${page + pageMenuLen <= pagesCnt ? page + pageMenuLen : pagesCnt }" />
					
					<c:set var="baseUri" value="type=${type }&session=${session }&searchKeywordType=${searchKeywordType }&searchKeyword=${searchKeyword }" />
					
					<c:if test="${page == 1 }">
						<a class="join-item btn btn-sm btn-disabled">«</a>
					</c:if>
					<c:if test="${page > 1 }">
						<a class="join-item btn btn-sm" href="?page=1&${baseUri }">«</a>
					</c:if>
					<c:forEach begin="${startPage }" end="${endPage }" var="i">
						<a class="join-item btn btn-sm ${page == i ? 'btn-active' : '' }" href="?page=${i }&${baseUri }">${i }</a>
					</c:forEach>
					<c:if test="${page < pagesCnt }">
						<a class="join-item btn btn-sm" href="?page=${pagesCnt }&${baseUri }">»</a>
					</c:if>
					<c:if test="${page == pagesCnt }">
						<a class="join-item btn btn-sm btn-disabled" href="?page=${pagesCnt }&${baseUri }">»</a>
					</c:if>
				</div>
			</div>
			
	</div>
</section>

<%@ include file="../common/foot.jsp"%>
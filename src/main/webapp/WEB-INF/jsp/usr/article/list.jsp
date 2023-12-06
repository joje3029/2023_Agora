<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<%@ include file="../common/head.jsp"%>

<section class="listBody">
	 <div>
            <!--Breadcrumbs : 왼쪽이 더 이쁨!-->
            <div class="text-sm breadcrumbs">
            <!-- 여기 변수로 변경해야함. 변해야해. -->
                <ul>
                  <li><a>Home</a></li> 
                  <li><a>Documents</a></li> 
                  <li>Add Document</li>
                </ul>
            </div>
            <!--메뉴 : 네비게이션 일자로 만들어요!-->
            <div class="menuBar">
                <ul class="menu menu-vertical lg:menu-horizontal bg-base-200 rounded-box border">
                    <li><a>전체 칼럼</a></li>
                    <li><a>구독한 작가 칼럼</a></li>
                    <li><a>좋아요 한 칼럼</a></li>
                  </ul>
            </div>
            <div class="container mx-auto px-3">
        
                <!-- 여다가 검색기능 만들꺼임. -->
                <form> <!-- form은 Action이 없으면 자기한테로 감. method도 현 상태에다가 붙음 그래서 표시 안해도 됨. -->
                    <div class="navbar bg-base-100 border">
                        <select name="searchKeywordType" >
                            <option value="title">제목</option>
                            <option value="body">내용</option>
                            <option value="title,body">제목+내용</option>
                        </select>
                        <input type="text" name="searchKeyword" placeholder="검색어를 써주세요." class="btn btn-ghost text-xl"/>
                        <div>
                            <button class="btn btn-ghost btn-circle">
                              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
                            </button>
                            <div class="btn btn-ghost btn-circle">
                              <div class="indicator">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" /></svg>
                                <span class="badge badge-xs badge-primary indicator-item"></span>
                              </div>
                            </div>
                          </div>
                    </div>
                </form>
        </div>
        <!--칼럼작성버튼 로그인시에만 나옴-->
        <c:if test="${rq.getLoginedMemberId() != 0 }">
            <div class="flex justify-end">
                <div class="mr-16 btn border mt-1">
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

					<c:if test="${page == 1 }">
						<a class="join-item btn btn-sm btn-disabled">«</a>
					</c:if>
					<c:if test="${page > 1 }">
						<a class="join-item btn btn-sm" href="?boardId=${board.id }&page=1">«</a>
					</c:if>
					<c:forEach begin="${startPage }" end="${endPage }" var="i">
						<a class="join-item btn btn-sm ${page == i ? 'btn-active' : '' }" href="?page=${i } ">${i }</a>
					</c:forEach>
					<c:if test="${page < pagesCnt }">
						<a class="join-item btn btn-sm" href="?page=${pagesCnt }">»</a>
					</c:if>
					<c:if test="${page == pagesCnt }">
						<a class="join-item btn btn-sm btn-disabled">»</a>
					</c:if>
				</div>
			</div>
			
	</div>
</section>

<%@ include file="../common/foot.jsp"%>
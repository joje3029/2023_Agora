<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이 페이지에서 쓸꺼니까 c. head로 넘기면 안되는 이유. head 아래의 선언보다 아래에서 연결됨. -->

<%@ include file="../common/head.jsp"%>

<%@ include file="../common/floatingBanner.jsp"%>

<script src="/resource/common.js" defer="defer"></script>

<script>
	// ajax 함수 2개 만들기 -  (1)cnaJoin : 토론방 참가하는 거 (2)canCreate : 로톤방 만드는거 
	// 근데 canCreate는 지금 만들필요 없을것 같아 나는 병호님과는 다르게 1유저가 1개만 만드는게 아니니까.			
			
</script>

<section class="listBody">
	 <div>
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
                <ul class="menu menu-vertical lg:menu-horizontal bg-base-200 rounded-box my-2">
                    <li><a>전체 토론방</a></li>
                    <li><a>추천 토론방</a></li>
                    <li><a>내 토론방</a></li>
                  </ul>
            </div>
            <div class="container mx-auto px-3">
        
                <!-- 여다가 검색기능 만들꺼임.  : 토론쪽 검색은 아티클 컨트롤러 보고 참고하면서 같이수정하기-->
                <form>
                    <div class="navbar bg-base-100 border">
                        <select name="searchKeywordType"  data-value="${searchKeywordType }" class="select select-bordered select-sm mr-1" >
                        	<option value="all">전체</option>
							<option value="roomName">토론방 이름</option>
							<option value="managerName">방장 이름</option>
                        </select>
                        <input type="text" name="searchKeyword" placeholder="검색어를 써주세요." class="btn btn-ghost text-xl w-10/12"/>
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
                <div class="mr-16 btn mt-1">
                    <a href="createroom" >토론방 만들기</a>
                </div>
            </div>
        </c:if>

		<div class="list_outline">
            <div>
                <c:forEach var="disussionRoom" items="${disussionRooms }">
                    <div class="border list_outline m-0.5">
                        <div>
                            <a href="detail?id=${article.id }" onclick="if (canJoin(${disussionRoom.id}) == false) {return false;}"> 
                                <p class="room-name p-3 bg-green-100">${disussionRoom.dscsnRoomNm }</p>
                                <div class="empty"></div>
                                <div class="flex"> 
                                    <i class="fa-solid fa-user"></i>
                                    &nbsp;
                                    <p class="moderator">${disussionRoom.crtrId }</p><!-- 이거 가꼬올때 inner join 해야겠다. -->
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <p class="headcount">방 인원</p>
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
					
					<c:set var="baseUri" value="&searchKeywordType=${searchKeywordType }&searchKeyword=${searchKeyword }" />
					
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
						<a class="join-item btn btn-sm" href="?page=${pagesCnt }">»</a>
					</c:if>
					<c:if test="${page == pagesCnt }">
						<a class="join-item btn btn-sm btn-disabled" href="?page=${pagesCnt }&${baseUri }">»</a>
					</c:if>
				</div>
			</div>
			
	</div>
</section>

<%@ include file="../common/foot.jsp"%>
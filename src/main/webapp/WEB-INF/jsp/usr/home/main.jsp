<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/head.jsp"%>
<%@ include file="../common/floatingBanner.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />
<%@ include file="mainScript.jsp"%>

		<section>
			<div class="recomm_outline">
				<input type="text" value="0" hidden>
				<div class="recomm" id="recomm_coumn">추천 칼럼</div>
				<div class="carousel-wrapper">
					<i class="fa-solid fa-arrow-right" id="column_left"></i>
					<i class="fa-solid fa-arrow-left" id="column_right"></i>
					<i class="bi bi-chevron-left" id="column_left"></i> 
					<i class="bi bi-chevron-right" id="column_right"></i>
					<div class="slide_bar" id="column_slide_bar">
						<div class="cut_div">
							<div class="bar_item">
								<!-- 칼럼 제목이 들어갈 디자인 예시 -->
								<div class="column_title">경차가 뭐 어때서</div>
								<div class="column_writer">책마음</div>
							</div>
							<div class="bar_item">칼럼 이름2</div>
							<div class="bar_item">칼럼 이름3</div>
							<div class="bar_item">칼럼 이름4</div>
							<div class="bar_item">칼럼 이름5</div>
						</div>
						<div class="cut_div">
							<div class="bar_item">칼럼 이름6</div>
							<div class="bar_item">칼럼 이름7</div>
							<div class="bar_item">칼럼 이름8</div>
							<div class="bar_item">칼럼 이름9</div>
							<div class="bar_item">칼럼 이름10</div>
						</div>
						<div class="cut_div">
							<div class="bar_item">칼럼 이름11</div>
							<div class="bar_item">칼럼 이름12</div>
							<div class="bar_item">칼럼 이름13</div>
							<div class="bar_item">칼럼 이름14</div>
							<div class="bar_item">칼럼 이름15</div>
						</div>
					</div>
		
					<div class="slide-indicators">
						<!-- 이전, 다음 버튼과 슬라이드 지표 클릭 시에 라디오 체크 제대로 되는지 확인하시려면 hidden 제거하시면 됩니다! -->
						<label for="column_first_cut_div" class="indicators-label">
							<input type="radio" id="column_first_cut_div"
							class="indicators-radio" name="column_indicators" hidden checked>
						</label> <label for="column_second_cut_div" class="indicators-label">
							<input type="radio" id="column_second_cut_div"
							class="indicators-radio" name="column_indicators" hidden>
						</label> <label for="column_third_cut_div" class="indicators-label">
							<input type="radio" id="column_third_cut_div"
							class="indicators-radio" name="column_indicators" hidden>
						</label>
					</div>
				</div>
			</div>
		
			<hr>
		
			<div class="recomm_outline">
				<input type="text" value="1" hidden>
				<div class="recomm" id="recomm_book">추천 책</div>
				<div class="carousel-wrapper">
					<i class="bi bi-chevron-left" id="book_left"></i> <i
						class="bi bi-chevron-right" id="book_right"></i>
					<div class="slide_bar" id="book_slide_bar">
						<div class="cut_div">
							<div class="cut_div">
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/32474243888?NaPm=ct%3Dlnpp7x2o%7Cci%3D5c68ab12bf290861cc2442a0d5dde7f8ef06271d%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3D6a926b19ea60e3c6db876a3dccd5752571f34129"><img
										src="https://shopping-phinf.pstatic.net/main_3247424/32474243888.20230117165328.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/32482025744?NaPm=ct%3Dlnppbcj4%7Cci%3Dfcc0d75786e3bcc368147092606d151921e7e373%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3D4e79db9ca6b2b3a7e199200e5b7897516cacdaac"><img
										src="https://shopping-phinf.pstatic.net/main_3248202/32482025744.20230829090515.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/32438186724?NaPm=ct%3Dlnppd97c%7Cci%3Dafc51d9fa7236d48a03e8e88af65e1239bc422f2%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3D488e5ecf4b2b4c6f8cfc77aa2d9d558d88e14385"><img
										src="https://shopping-phinf.pstatic.net/main_3243818/32438186724.20230313185126.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/42072985647?NaPm=ct%3Dlnppn120%7Cci%3De1de47f3af6dbb901ccebdf04f6ffc6e225447e9%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3D3daec4ed097c6c644e689770803e700a61b34ca6"><img
										src="https://shopping-phinf.pstatic.net/main_4207298/42072985647.20230905100943.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/32490724936?NaPm=ct%3Dlnppnb34%7Cci%3Dd40aba88b76cc5dc5ed744d1a9019aeb36f24fa5%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Df0f5585691f23337ef343e8ce094b43338b5f102"><img
										src="https://shopping-phinf.pstatic.net/main_3249072/32490724936.20230801121655.jpg?type=w300"
										alt=""></a>
								</div>
							</div>
							<div class="cut_div">
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/38641662627?NaPm=ct%3Dlnppo3mw%7Cci%3D7c45c1b3230aa8fa1fe6adc02b1185b62dc98852%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3D145515d8e337235b9bc86c0f188919bcac3c1842"><img
										src="https://shopping-phinf.pstatic.net/main_3864166/38641662627.20231004072405.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/42074839626?NaPm=ct%3Dlnppotvc%7Cci%3Deaba6d574acc30737c722bfe88dc705661c8832d%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3D9fe23198aa017a750ae2787c8c4b4cc899d1f10e"><img
										src="https://shopping-phinf.pstatic.net/main_4207483/42074839626.20230919130929.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/37128011625?NaPm=ct%3Dlnppptd4%7Cci%3D6fb38cb286be72cb3f7d66114c2fc0e3a28291d4%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Dec187c9dccf34bb9a7dbcabb6a03f8ff32944c80"><img
										src="https://shopping-phinf.pstatic.net/main_3712801/37128011625.20230926085041.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/43068993629?NaPm=ct%3Dlnppqeyw%7Cci%3Dea67f5ae748121db112e88223ea4af845944675e%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Da4937deaab22aaa9612c774fe2266b1672cbae5c"><img
										src="https://shopping-phinf.pstatic.net/main_4306899/43068993629.20231007091723.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/40570668621?NaPm=ct%3Dlnppqrbc%7Cci%3D90ec555b2f3ad7ff98c021f8700d9f6751bda84d%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Dcc392fc5fb42041f731798f6edfd9d8945d252c7"><img
										src="https://shopping-phinf.pstatic.net/main_4306899/43068993629.20231007091723.jpg?type=w300"
										alt=""></a>
								</div>
							</div>
		
							<div class="cut_div">
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/32457904996?NaPm=ct%3Dlnpprf8g%7Cci%3D4231be12e11bcd4ac0c9faf1406606099f044ae1%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Da64a6e6190fc5062652b5b5974698d44800f6918"><img
										src="https://shopping-phinf.pstatic.net/main_3245790/32457904996.20230913071329.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/39317870619?NaPm=ct%3Dlnpprvfs%7Cci%3Da46a065469b849628e731a63864ec33762c8a3b7%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Da4b51ce8d0e69b769624ea484ce5778353d5144a"><img
										src="https://shopping-phinf.pstatic.net/main_3931787/39317870619.20230829085410.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/32444990070?NaPm=ct%3Dlnppsorc%7Cci%3D358bca7747a87df64c34cb1c4b202cb04b06396c%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Dc41b5f0ffa62dc0eab5c55754abc9a9c4f4e79bd"><img
										src="https://shopping-phinf.pstatic.net/main_3244499/32444990070.20230927071131.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/42733162622?NaPm=ct%3Dlnppt7a0%7Cci%3D9f60243f370244a3505238357069aeb141c9aff6%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Daceee640fb6a49be9799187950e5d96570348005"><img
										src="https://shopping-phinf.pstatic.net/main_4273316/42733162622.20231003084637.jpg?type=w300"
										alt=""></a>
								</div>
								<div class="bar_item">
									<a
										href="https://search.shopping.naver.com/book/catalog/42621657622?NaPm=ct%3Dlnppts40%7Cci%3D7fc784ebb48b39191d754cc86b90444af0cc01a4%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3Deeb22df41b0e5633f9104dff40d5418752f03071"><img
										src="https://shopping-phinf.pstatic.net/main_4262165/42621657622.20230926085017.jpg?type=w300"
										alt=""></a>
								</div>
							</div>
						</div>
						<div class="slide-indicators">
							<label for="book_first_cut_div" class="indicators-label"> <input
								type="radio" id="book_first_cut_div" class="indicators-radio"
								name="book_indicators" hidden checked>
							</label> <label for="book_second_cut_div" class="indicators-label">
								<input type="radio" id="book_second_cut_div"
								class="indicators-radio" name="book_indicators" hidden>
							</label> <label for="book_third_cut_div" class="indicators-label">
								<input type="radio" id="book_third_cut_div"
								class="indicators-radio" name="book_indicators" hidden>
							</label>
						</div>
					</div>
				</div>
		
				<hr>
				<div class="recomm" id="recomm_class">분야별 칼럼 분류</div>
				<div class="class_frame">
					<div class="recomm_bar">
						<a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">국내 소설</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">해외 소설</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">시/에세이</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">인문</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">가정/육아</div>
						</a>
					</div>
					<div class="recomm_bar">
						<a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">요리</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">건강</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">취미/실용</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">경제/경영</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">자기계발</div>
						</a>
					</div>
					<div class="recomm_bar">
						<a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">정치/사회</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">역사/문화</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">종교</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">예술/대중문화</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">기술 공학</div>
						</a>
					</div>
					<div class="recomm_bar">
						<a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">외국어</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">여행</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">컴퓨터/IT</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">잡지</div>
						</a> <a
							href="/project/views/user/column/maincolumn/classificationColumn.html">
							<div class="class_item">만화</div>
						</a>
					</div>
				</div>
		
		
				<hr>
		
		
				<input type="text" value="2" hidden>
				<div class="recomm" id="recomm_discussRoom">추천 토론방</div>
				<div class="carousel-wrapper">
					<i class="bi bi-chevron-left" id="discussRoom_left"></i> <i
						class="bi bi-chevron-right" id="discussRoom_right"></i>
					<div class="slide_bar" id="discussRoom_slide_bar">
						<div class="cut_div">
							<div class="bar_item">월요 경제 토론방</div>
							<div class="bar_item">토론방 이름2</div>
							<div class="bar_item">토론방 이름3</div>
							<div class="bar_item">토론방 이름4</div>
							<div class="bar_item">토론방 이름5</div>
						</div>
						<div class="cut_div">
							<div class="bar_item">토론방 이름6</div>
							<div class="bar_item">토론방 이름7</div>
							<div class="bar_item">토론방 이름8</div>
							<div class="bar_item">토론방 이름9</div>
							<div class="bar_item">토론방 이름10</div>
						</div>
						<div class="cut_div">
							<div class="bar_item">토론방 이름11</div>
							<div class="bar_item">토론방 이름12</div>
							<div class="bar_item">토론방 이름13</div>
							<div class="bar_item">토론방 이름14</div>
							<div class="bar_item">토론방 이름15</div>
						</div>
					</div>
		
					<div class="slide-indicators">
						<label for="discuss_first_cut_div" class="indicators-label">
							<input type="radio" id="discuss_first_cut_div"
							class="indicators-radio" name="discuss_indicators" hidden checked>
						</label> <label for="discuss_second_cut_div" class="indicators-label">
							<input type="radio" id="discuss_second_cut_div"
							class="indicators-radio" name="discuss_indicators" hidden>
						</label> <label for="discuss_third_cut_div" class="indicators-label">
							<input type="radio" id="discuss_third_cut_div"
							class="indicators-radio" name="discuss_indicators" hidden>
						</label>
					</div>
				</div>
		</section>

<%@ include file="../common/foot.jsp"%>
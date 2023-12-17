<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="head.jsp"%>
<link rel="stylesheet" href="/resource/main.css" />

<!-- <script src="/resource/tostUiChart.js"></script> -->

<!-- 토스트UI 차트 사용하기 위한 cdn -->
<link rel="stylesheet" href="https://uicdn.toast.com/chart/latest/toastui-chart.min.css" />
<script src="https://uicdn.toast.com/chart/latest/toastui-chart.min.js"></script>


		<section class="Admain">
			  <section class="listBody">
		<div class="flex justify-between">
			<h1 class="bg-green-100 inline-block text-3xl p-3">사이트 통계</h1>
		</div>
		<section class="mt-8 text-xl">
			<div class="container mx-auto px-3 border border-green-600 mb-4 w-full ">
				<ul class="flex justify-around p-4">
					<li class="btn">신규 가입자 수</li>
					<li class="btn">일일 방문횟수</li>
					<li class="btn">탈퇴 회원 수와 이유</li>
				</ul>
			</div>
			<div>
				<!--h-screen : height: 100vh; 임시 임시 대충 모양 볼려구-->
				<div class="chart-section w-full flex justify-center">
					<div id="chart" class="border border-green-600"></div> 
				</div>
			</div>

		</section>
	</section>
		</section>
		
		<script>
		// 이건 생이니까 이제 이걸 DB에서 자료를 보내고 그걸로 뿌리게 해야겠네. 
		// 이건 일단 기준을 탈퇴회원과 탈퇴 이유로 함.
		/* namespace */
		const Chart = toastui.Chart; // 이건 toastui.Chart를 Chart 변수에 담음 
		const el = document.getElementById('chart'); // html의 id가 chart인 부분 가져오는거.
		// 이게 실질적으로 그래프를 그리는 data님
		// 여기가 controller에서 데이터를 요청하고 받아야하는 부분.
		const data = {
		  categories: ['Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], //옆의 날짜
		  series: [
		    {
		      name: 'Budget', // 퍼렁이 위에서부터 아래로 길이
		      data: [5000, 3000, 5000, 7000, 6000, 4000, 1000],
		    },
		    {
		      name: 'Income', // 노랑이 위에서부터 아래로 길이
		      data: [8000, 4000, 7000, 2000, 6000, 3000, 5000],
		    },
		  ],
		};
		const options = {
				chart: { width: 700, height: 400 }, // 차트의 가로 세로 높이 : 토스트 ui 버전이 상향되면서 상대값을 못함. 썩을...
		  series: {
			    stack: true
			  }
		};
		

		const chart = Chart.barChart({ el, data, options }); // 차트 그림. 위의 변수로 설정한 Chart에 넣어서 
		// const chart = new BarChart({ el, data, options }); // Second way


		</script>
		
	</body>
</html>
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
			<div class="container mx-auto px-3 border border-green-600 mb-4">
				<ul class="flex justify-around p-4">
					<li class="btn">신규 가입자 수</li>
					<li class="btn">일일 방문횟수</li>
					<li class="btn">탈퇴 회원 수</li>
				</ul>
			</div>
			<div class=" border2">
				<!--h-screen : height: 100vh; 임시 임시 대충 모양 볼려구-->
					토스트 ui 차트 들어올 자리
				<div class="chart-section border">
					<div id="chart"></div> 
				</div>
			</div>

		</section>
	</section>
		</section>
		
		<script>
		/* namespace */
		const Chart = toastui.Chart;
		const el = document.getElementById('chart');
		const data = {
		  categories: ['Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
		  series: [
		    {
		      name: 'Budget',
		      data: [5000, 3000, 5000, 7000, 6000, 4000, 1000],
		    },
		    {
		      name: 'Income',
		      data: [8000, 4000, 7000, 2000, 6000, 3000, 5000],
		    },
		  ],
		};
		const options = {
		  chart: { width: 700, height: 400 },
		};

		const chart = Chart.barChart({ el, data, options });
		// const chart = new BarChart({ el, data, options }); // Second way


		</script>
		
	</body>
</html>
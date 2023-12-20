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
					<li class="btn"><a href="marketingNew">신규 가입자 수</a></li>
					<li class="btn"><a href="marketingwithDrow">탈퇴 회원 수와 이유</a></li>
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
	var monthList = ${dataMap['monthList']};
    var countList = ${dataMap['countList']};
    
    console.log("monthList : "+monthList);
    console.log("countList : "+countList);
	
		const Chart = toastui.Chart;
		const el = document.getElementById('chart');
		
		const data = {
				  categories: monthList,
				  series: [
				    {
				      name: '신규회원',
				      data: countList,
				    },
				  ],
				}
		  
		const options = {
		  chart: { width: 700, height: 400 },
		  series: {
			eventDetectType: 'grouped'
			}
		};
		const chart = Chart.lineChart({ el, data, options });
		</script>
					
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
//플로팅 배너 위아래로 움직이는 스크립트
 $(document).ready(function () {
    var arrowUp = $('.fa-arrow-up');
    var arrowDown = $('.fa-arrow-down');

    arrowUp.click(function () {
      scrollSmoothly(0);
    });

    arrowDown.click(function () {
      var windowHeight = $(window).height();
      var documentHeight = $(document).height();

      scrollSmoothly(documentHeight - windowHeight);
    });

    function scrollSmoothly(targetPosition) {
      $('html, body').animate({ scrollTop: targetPosition }, 1000); // Adjust the duration as needed
    }
  });
</script>

<div class="banner_div">
	<ul class="menu bg-base-200 rounded-box">
	  <li>
	    <a>
		    <i class="fa-solid fa-arrow-up"></i>
	    </a>
	  </li>
	  <li>
	    <a>
	      <i class="fa-solid fa-arrow-down"></i>
	    </a>
	  </li>
	  <li>
	    <a href="/usr/customer/chatBot">
	      <i class="fa-sharp fa-solid fa-robot"></i>
	    </a>
	  </li>
	</ul>
</div>
// jQuery document.ready
$(document).ready(function() {
  // 여기에 실행하고자 하는 jQuery 코드 작성
  alert('Document is ready!'); // 잘 연결되었군!
  
  // li 요소에 대한 click 이벤트를 jQuery를 사용하여 바인딩
  $('.question-item').on('click', function() {
    var questionId = $(this).data('data-question-id');
    // 여기에서 questionId에 따른 동작 수행
    alert('Question ID clicked:', questionId);
  });
});
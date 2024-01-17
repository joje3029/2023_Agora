// jQuery document.ready
$(document).ready(function() {
  
  // 여기서 봇이 환영 문구를 하고 옆에서 고르라고 안내를
  let welcomeElement = `
  <div  class="chat chat-start">
    <div class="chat-image avatar">
      <div class="w-10 rounded-full">
        <i class="fa-solid fa-robot"></i>
      </div>
    </div>
    <div class="chat-bubble">안녕하세요. Agora 챗봇이에요! 옆에서 질문할 내용을 클릭해주세요.</div></div>
  `;
  $('#chatbot-bubble').append(welcomeElement);
  
  
  // li 요소에 대한 click 이벤트를 jQuery를 사용하여 바인딩
  $('.question-item').on('click', function() {
    var questionId = $(this).data('question-id');
    // 여기에서 questionId에 따른 동작 수행
    console.log('Question ID clicked:', questionId) //Question ID clicked: 1
    
    let userchoice;
    let robotAnswer;
    // 일단 클릭한 questionId 값에 따라 나눠야겠지
    if(questionId ===1){
		userchoice = '계정관리';
		
	}else if(questionId ===2){
		userchoice = '칼럼';
	}else if(questionId ===3){
		userchoice = '토론방';
	}else if(questionId ===4){
		userchoice = '구독';
	}else if(questionId ===5){
		userchoice = '탈퇴';				
    }
    
    // 우선 유저가 선택한거가 뭔지 뜨고
    var userElement = `
	  <div class="chat chat-end">
	    <div class="chat-image avatar">
	      <div class="w-10 rounded-full">
	        <i class="fa-solid fa-user"></i>
	      </div>
	    </div>
	    <div class="chat-bubble">`+userchoice+`</div></div>
	  `;
     $('#chatbot-bubble').append(userElement);
     
     // 유저가 선택한거에 대한 답이 append로 들어감
     let robotAnswerElement = `
	  <div  class="chat chat-start">
	    <div class="chat-image avatar">
	      <div class="w-10 rounded-full">
	        <i class="fa-solid fa-robot"></i>
	      </div>
	    </div>
	    <div class="chat-bubble">`+userchoice+`을/를 선택하셨군요. 상세보기를 클릭해주세요</div>
	   
	   </div>
	  `;
	  $('#chatbot-bubble').append(robotAnswerElement);
     
     
  });
});

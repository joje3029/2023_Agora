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
  $('.chat-contents').on('click', 'li', function() {
    var questionId = $(this).data('question-id');
    // 여기에서 questionId에 따른 동작 수행
    console.log('Question ID clicked:', questionId) //Question ID clicked: 1
    
    let userchoice;
    let robotAnswer;
    // 일단 클릭한 questionId 값에 따라 나눠야겠지
    if(questionId ===1){
		userchoice = '계정관리';
		robotAnswer=`<ul>
		  <li data-question-id="6"><i class="fa-solid fa-caret-right"></i>회원정보 변경 하는 법</li>
		  <li data-question-id="7"><i class="fa-solid fa-caret-right"></i>인증번호 이메일이 오지 않아요</li>
		  <li data-question-id="8"><i class="fa-solid fa-caret-right"></i>등록한 인증번호 이메일을 더 이상 쓰지 않아요</li>
		  <li data-question-id="9"><i class="fa-solid fa-caret-right"></i>아이디를 까먹었어요</li>
		  <li data-question-id="10"><i class="fa-solid fa-caret-right"></i>비밀번호를 까먹었어요</li>
		</ul>`;
		
	}else if(questionId ===2){
		userchoice = '칼럼';
		robotAnswer=`<ul>
		  <li data-question-id="12"><i class="fa-solid fa-caret-right"></i>칼럼 작성하려면 어떻게 해야하나요?</li>
		  <li data-question-id="13"><i class="fa-solid fa-caret-right"></i>칼럼 검색을 하고 싶어요.</li>
		  <li data-question-id="14"><i class="fa-solid fa-caret-right"></i>더 많은 칼럼을 보려면 어떻게 해야하나요?</li>
		</ul>`;
	}else if(questionId ===3){
		userchoice = '토론방';
		robotAnswer=`<ul>
		  <li data-question-id="15"><i class="fa-solid fa-caret-right"></i>토론방은 어떻게 참가할수 있나요?</li>
		  <li data-question-id="16"><i class="fa-solid fa-caret-right"></i>하나의 토론방만 참가할수 있나요?</li>
		  <li data-question-id="17"><i class="fa-solid fa-caret-right"></i>토론방을 나가려면 어떻게 해야하나요?</li>
		</ul>`;
	}else if(questionId ===4){
		userchoice = '구독';
		robotAnswer=`<ul>
		  <li data-question-id="18"><i class="fa-solid fa-caret-right"></i>구독기능이 뭔가요?</li>
		  <li data-question-id="19"><i class="fa-solid fa-caret-right"></i>구독은 어떻게 하나요?</li>
		  <li data-question-id="20"><i class="fa-solid fa-caret-right"></i>구독 알림은 어떻게 받나요?</li>
		</ul>`;
	}else if(questionId ===5){
		userchoice = '탈퇴';
		robotAnswer=`<ul>
		  <li data-question-id="21"><i class="fa-solid fa-caret-right"></i>탈퇴하고 싶어요. 어떻게 해야 탈퇴할수 있나요?</li>
		  <li data-question-id="22"><i class="fa-solid fa-caret-right"></i>탈퇴하고 나서 개인정보는 어떻게 되냐요?</li>
		</ul>`;				
    }else if(questionId ===6){
		userchoice = '탈퇴';
		robotAnswer=`<ul>
		  <li data-question-id="21"><i class="fa-solid fa-caret-right"></i>탈퇴하고 싶어요. 어떻게 해야 탈퇴할수 있나요?</li>
		  <li data-question-id="22"><i class="fa-solid fa-caret-right"></i>탈퇴하고 나서 개인정보는 어떻게 되냐요?</li>
		</ul>`;				
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
	   	<div>
	   	</div>
	   </div>
	   `+robotAnswer+`
	  `;
	  $('#chatbot-bubble').append(robotAnswerElement);
     
     
     
  });
});

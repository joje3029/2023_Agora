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
		userchoice = '회원정보 변경을 하는 법';
		robotAnswer=`로그인 후 마이페이지 → 회원정보 수정을 클릭합니다. 회원정보를 수정후 저장을 누르시면 회원정보가 변경됩니다.`;				
    }else if(questionId ===7){
		userchoice = '인증번호 이메일이 오지않아요';
		robotAnswer=`스팸메일로 분류된건 아닌지 확인해주세요. 스팸메일로도 오지 않았다면 1:1 고객상담센터로 문의 바랍니다.`;				
    }else if(questionId ===8){
		userchoice = '등록한 인증번호 이메일을 더이상쓰지 않아요.';
		robotAnswer=`회원정보 변경에서 이메일을 변경해주세요.`;				
    }else if(questionId ===9){
		userchoice = '아이디를 까먹었어요';
		robotAnswer=`시작하기 → 로그인 → 아이디찾기 클릭을 하시면 아이디를 찾을 수 있습니다.`;				
    }else if(questionId ===10){
		userchoice = '비밀번호를 까먹었어요.';
		robotAnswer=`시작하기 → 로그인 → 비밀번호 찾기를 클릭하시면 인증후 비밀번호 변경할수 있습니다. 비밀번호를 변경후 로그인 해주세요.`;				
    }else if(questionId ===12){
		userchoice = '칼럼 작성하려면 어떻게 해야하나요?';
		robotAnswer=`칼럼작성은 회원만 가능합니다. 회원가입 이후 로그인해서 이용해주세요.`;				
    }else if(questionId ===13){
		userchoice = '칼럼 검색을 하고 싶어요.';
		robotAnswer=`메뉴의 칼럼을 클릭후 검색창에서 검색하시거나 메뉴바의 돋보기를 클릭하셔서 찾을수 있습니다.`;				
    }else if(questionId ===14){
		userchoice = '더 많은 칼럼을 보려면 어떻게 해야하나요?';
		robotAnswer=`메뉴의 칼럼을 누르시면 더 많은 칼럼을 볼수 있습니다.`;				
    }else if(questionId ===15){
		userchoice = '토론방은 어떻게 참가할수 있나요?';
		robotAnswer=`토론방은 가입한 회원만 참가할수 있습니다. 회원가입 이후 로그인해서 이용해주세요.`;				
    }else if(questionId ===16){
		userchoice = '하나의 토론방만 참가할수 있나요?';
		robotAnswer=`아닙니다. 다수의 토론방에 참가 가능합니다. 제한은 없습니다. 활발한 토론 부탁드려요!`;				
    }else if(questionId ===17){
		userchoice = '토론방을 나가려면 어떻게 해야하나요?';
		robotAnswer=`해당 토론방의 나가기 버튼을 누르면 나가집니다.`;				
    }else if(questionId ===18){
		userchoice = '구독기능이 뭔가요?';
		robotAnswer=`관심가는 작성자의 글을 구독해서 받을수 있는 서비스입니다.`;				
    }else if(questionId ===19){
		userchoice = '구독은 어떻게 하나요?';
		robotAnswer=`글 옆의 하트를 누르시면 구독할 수 있습니다. 구독한 작성자의 글은 구독한 칼럼에서 볼수 있습니다.`;				
    }else if(questionId ===20){
		userchoice = '구독 알림은 어떻게 받나요?';
		robotAnswer=`회원정보의 알림을 on으로 하시면 로그인 정보 옆의 종에 표시가 됩니다.`;				
    }else if(questionId ===21){
		userchoice = '탈퇴하고 싶어요. 어떻게 해야 탈퇴할수 있나요?';
		robotAnswer=`마이페이지→회원탈퇴 클릭 후 비밀번호 인증을 해주시면 탈퇴처리가 됩니다. 이용해주셔서 감사합니다.`;				
    }else if(questionId ===22){
		userchoice = '탈퇴하고 나서 개인정보는 어떻게 되냐요?';
		robotAnswer=`글 옆의 하트를 누르시면 구독할 수 있습니다. 구독한 작성자의 글은 구독한 칼럼에서 볼수 있습니다.`;				
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
     
     if(questionId===1|| questionId===2 || questionId===3 || questionId===4 || questionId===5 || questionId===6){
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
	 }else{
		let robotAnswerElement = `
	  <div  class="chat chat-start">
	    <div class="chat-image avatar">
	      <div class="w-10 rounded-full">
	        <i class="fa-solid fa-robot"></i>
	      </div>
	    </div>
	    <div class="chat-bubble">`+robotAnswer+`입니다.</div>
	   	<div>
	   	</div>
	   </div>
	  `;
	  $('#chatbot-bubble').append(robotAnswerElement); 
	 }
     
     
     
  });
});

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>인덱스를 만들어 보자</title>

    <link href="${pageContext.request.contextPath }/resources/css/bootstrap.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!--개인 디자인 추가-->
    <link href="${pageContext.request.contextPath }/resources/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/resources/js/bootstrap.js"></script>
	

</head>
<body>
	
	<%@ include file="../include/header.jsp" %>
	<!--게시판-->
    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>상세보기</p>
                        </div>
                        
                        <form action="freeModify" method="post" name="regForm">
                            <div>
                                <label>DATE</label>
                                <p><fmt:formatDate value="${boardVO.regdate}" pattern="yyyy년MM월dd일 hh시mm분"/></p>
                            </div>   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" value="${boardVO.bno}" name='bno' readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" value="${boardVO.writer}" name='writer' readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" value="${boardVO.title}" name='title' readonly>
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content' readonly>${boardVO.content}</textarea>
                            </div>

                            <button type="button" class="btn btn-primary" id="freeModify">변경</button>
                            <button type="button" class="btn btn-dark" id="freeList">목록</button>
                    </form>
                </div>
            </div>
        </div>
        </section>
        
        <section style="margin-top: 80px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-9 write-wrap">
                        <form class="reply-wrap">
                            <div class="reply-image">
                                <img src="../resources/img/profile.png">
                            </div>
                            <!--form-control은 부트스트랩의 클래스입니다-->
	                    <div class="reply-content">
	                        <textarea class="form-control" rows="3" id="reply" ></textarea>
	                        <div class="reply-group">
	                              <div class="reply-input">
	                              <input type="text" class="form-control" id="replyId" placeholder="이름">
	                              <input type="password" class="form-control" id="replyPw" placeholder="비밀번호">
	                              </div>
	                              
	                              <button type="button" class="right btn btn-info" id="replyRegist">등록하기</button>
	                        </div>
	
	                    </div>
                        </form>

                        <!--여기에접근 반복-->
                        <div id="replyList">
                        <!-- <div class='reply-wrap'>
                            <div class='reply-image'>
                                <img src='../resources/img/profile.png'>
                            </div>
                            <div class='reply-content'>
                                <div class='reply-group'>
                                    <strong class='left'>honggildong</strong> 
                                    <small class='left'>2019/12/10</small>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                </div>
                                <p class='clearfix'>여기는 댓글영역</p>
                            </div>
                        </div> -->
                        
                        
                        
                        </div>
                        <button type="button" class="btn btn-default btn-block" id="moreList">더보기</button>
                    </div>
                </div>
            </div>
        </section>
        
        
	    <!-- 모달 -->
		<div class="modal fade" id="replyModal" role="dialog">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
						<h4 class="modal-title">댓글수정</h4>
					</div>
					<div class="modal-body">
						<!-- 수정폼 id값을 확인하세요-->
						<div class="reply-content">
						<textarea class="form-control" rows="4" id="modalReply" placeholder="내용입력"></textarea>
						<div class="reply-group">
							<div class="reply-input">
							    <input type="hidden" id="modalRno">
								<input type="password" class="form-control" placeholder="비밀번호" id="modalPw">
							</div>
							<button class="right btn btn-info" id="modalModBtn">수정하기</button>
							<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
						</div>
						</div>
						<!-- 수정폼끝 -->
					</div>
				</div>
			</div>
		</div>
        
        
        <%@ include file="../include/footer.jsp" %>
        
        <script>
        	var freeList = document.getElementById("freeList");
        	freeList.onclick = function() {
				location.href="freeList";
			}
        	
        	var freeModify = document.getElementById("freeModify");
        	freeModify.onclick = function() {
				
        		document.regForm.submit(); //폼에서 접근해서 폼으로 전송
        		
			}
        </script>
        
        
        <!-- 댓글 처리 영역 -->
        <script>
			
        	$(document).ready(function(){
        		
        		$("#replyRegist").click(function() {
					add();
				})
        		
        		//댓글 등록
        		function add() {
					//1.필요한 값을 취득
					//2.조건검사
					//3.ajax
					var bno = '${boardVO.bno}';
					var reply = $("#reply").val();
					var replyId = $("#replyId").val();
					var replyPw = $("#replyPw").val();
					
					if (reply =='' || replyId =='' ||replyPw =='' ) {
						alert("댓글, 아이디, 비밀번호를 입력하세요");
						return false; //함수강제 종료
					}
					//ajax 요청
					$.ajax({
						type:"POST", //전송 형식
						url:"../reply/new",//전송 url
						data:JSON.stringify({"bno":bno, "reply":reply, "replyId":replyId, "replyPw":replyPw}),//전송 데이터
						contentType:"application/json; charset=utf-8",//전송할 타입
						success: function(result) {//전송 성공시 돌려받을 콜백 함수 익명함수
							alert(result);
							//게시글 등록후 공백처리
							$("#reply").val("");
							$("#replyId").val("");
							$("#replyPw").val("");
							getList(1,true);
							
						},
						error: function(status) {//응답 실패시 실행되는 익명함수
							alert("댓글등록에 실패"+status);
						}   
					})

				}
        		
        		//더보기 처리
        		$("#moreList").click(function(){
        			getList(++pageNum,false); //str을 리셋하지 않기 위해 false전달
        			
        		})
        		
        		
        		//댓글 목록
        		getList(1,true);
        		var str = ""; //화면에 뿌려줄 ㅐ그를 문자열의 형태로 저장하는(전역변수)
        		var pageNum = 1; //페이지 번호를 전역변수로 변경
        		
        		function getList(page, reset) {
        			var bno = "${boardVO.bno}";
        			pageNum = page; //전달받은 페이지번호를 멤버변수에 다시저장 
					$.getJSON(
						"../reply/getReply/"+bno+"/"+pageNum, //요청보낼 주소
						function(data) {//성공시 전달받을 익명함수
							
							
							var list = data.list;// 컨트롤러에서 넘어온 댓글 목록
							var count = data.replyCount;//컨트롤러에서 넘어온 토탈카운트
		        			
							if (reset == true) { //reset은 str의 초기화 여부 (초기화를 안해주면 댓글 등록하면 이미등록된것들도나온다.)
								str = "";
							}
		        			
		        			if(pageNum *20 >= count){// 전체 게시글 수보다 pageNum에 따른 게시글수가 큰경우에는 더보기 삭제 
		        				$("#moreList").css("display","none");
		        			}else {
		        				$("#moreList").css("display","inline");
							}
		        			
						
							for (var i = 0; i < list.length; i++) {
								//console.log(list[i]);	
								
								str += "<div class='reply-wrap'>";
								str += "<div class='reply-image'>";
								str += "<img src='../resources/img/profile.png'>";
								str += "</div>";
								str += "<div class='reply-content'>";
								str += "<div class='reply-group'>";
								str += "<strong class='left'>"+list[i].replyId+"</strong> ";
								str += "<small class='left'>"+timeStamp(list[i].replydate)+"</small>";//시간처리함수 호출
								str += "<a href='"+list[i].rno+"' id='replyModify' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>";
								str += "<a href='"+list[i].rno+"' id='replyDelete' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>";
								str += "</div>";
								str += "<p class='clearfix'>"+list[i].reply+"</p>";
								str += "</div>";
								str += "</div>";
		
							}
							$("#replyList").html(str); //replyList아래에 문자열을 통째로 추가
						
						}
						
					)
        			
        			
				}//목록처리 끝
				
				
				//삭제, 수정 클리시 모달창에 띄우는 이벤트 처리
				//실제로 에이젝스의 실행이 더늦게 완료가 되므로, 이벤트 선언이 먼저 일어난다
				//그렇다면 화면에 띄우는 댓글과 관련된 이벤트는 아무것도 등록되지않는 형태이므로, 일반적인 클릭 이벤트는 동작하지 않습니다
				// 이때 이미 존재하는 요소 $("#replyList")(부모요소)에 이벤트를 등록하고, 해당 태그의 내부요소를 클릭하여 동작하는 이벤트 위임방식을 사용
				
				$("#replyList").on("click","a",function(event){
					event.preventDefault();//a태그의 이동을 막는다
					
					//수정클릭시, modal-title에 접근해서 "댓글수정 "변경
					//textarea태그를 보여지도록 처리
					//수정버튼 보여지도록 처리
					//삭제버튼 가리도록
					//
					//삭제클릭시, modal-title에 접근해서 "댓글삭제 "변경
					//textarea태그를 숨겨지도록 처리
					//삭제버튼 보여지도록 처리
					//수정버튼 가리도록
					//$("#replyModal").modal("show");//모달창 띄우기
					
					console.log(event.target);
					console.log($(this));
					
					var num = $(this).attr("id");
					
					if (num == 'replyModify') {
						
						var rno = $(this).attr("href");//클릭요소의 href값을 가져온다
						$("#modalRno").val(rno);//modalRno에 값을 세팅
						
						$(".modal-title").html("댓글수정");
						$("#modalReply").css("display","inline"); // textarea창 보이게한다
						$("#modalModBtn").css("display", "inline");
						$("#modalDelBtn").css("display", "none");
						$("#replyModal").modal("show");
					}
					else if(num == 'replyDelete'){
						
						var rno = $(this).attr("href");//클릭요소의 href값을 가져온다
						$("#modalRno").val(rno);//modalRno에 값을 세팅
						
						$(".modal-title").html("댓글삭제");
						$("#modalReply").css("display","none"); // textarea창 안보이게한다
						$("#modalDelBtn").css("display", "inline");
						$("#modalModBtn").css("display", "none");
						$("#replyModal").modal("show");
					}

				})//삭제, 수정 클리시 모달창에 띄우는 이벤트 처리 끝
				
				
				//삭제처리
				$("#modalDelBtn").click(function(){
					//1.modalRno값을 얻고, modalPw를 얻는다
					//ajax를 통해서 reply/delete로 json형식으로 요청처리
					//pwCheck()메서드를 통해서 비밀번호 맞는지 확인
					//비밀번호가 맞으면 삭제를 실행
					//콜백함수로 삭제 성공이 돌아오면 , 비밀번호 창을 비우고 modal("hidden")로 처리

					var rno = $("#modalRno").val();
					var replyPw = $("#modalPw").val();
					
					console.log(rno);
					console.log(replyPw);
					
					//ajax 요청
					$.ajax({
						type:"DELETE", //전송 형식
						url:"../reply/delete",//전송 url
						data:JSON.stringify({"rno" : rno, "replyPw" : replyPw}),//전송 데이터
						contentType:"application/json; charset=utf-8",//전송할 타입
						success: function(result) {//전송 성공시 돌려받을 콜백 함수 익명함수
							if (result == 1) {//삭제성공
								alert("댓글이 삭제되었습니다");
								$("#modalPw").val("");
								$("#replyModal").modal("hide");
								getList(1,true);
							}
							else{//비밀번호가 틀린 경우
								alert("비밀번호가 틀렷습니다");
								$("#modalPw").val("");
							}

						},
						error: function(status) {//응답 실패시 실행되는 익명함수
							
						}   
					})
				})//삭제끝
				
				//수정
				$("#modalModBtn").click(function(){
					
					var rno = $("#modalRno").val();
					var reply = $("#modalReply").val();
					var replyPw = $("#modalPw").val();
					
					//ajax 요청
					$.ajax({
						type:"put", //전송 형식
						url:"../reply/update",//전송 url
						data:JSON.stringify({"rno" : rno, "reply" : reply ,"replyPw" : replyPw}),//전송 데이터
						contentType:"application/json; charset=utf-8",//전송할 타입
						success: function(result) {//전송 성공시 돌려받을 콜백 함수 익명함수
							if (result == 1) {//수정성공
								alert("댓글이 수정되었습니다");
								$("#modalPw").val("");
								$("#modalReply").val("");
								$("#replyModal").modal("hide");
								getList(1,true);
							}
							else{//비밀번호가 틀린 경우
								alert("댓글수정 실패했습니다.");
								$("#modalPw").val("");
								$("#modalReply").val("");
							}

						},
						error: function(status) {//응답 실패시 실행되는 익명함수
							
						}   
					})
					
				})//수정끝
				
				
				//날짜 처리
				console.log(timeStamp(1575514837000));
				function timeStamp(millis) {
					//var millis = 1575519837000;
					
					var date = new Date(); //오늘 날짜.
					console.log("현재 시간에대한 밀리초"+date.getTime());//오늘 날짜의 밀리초
					console.log("하루에대한 밀리초"+1000*60*60*24);//하루에대한  밀리초
					var gap = date.getTime() - millis ;
					
					var time;//리턴할 문자열
					if (gap < 1000 * 60 * 60 * 24 ) {//등록일 기준 하루 미만
						if (gap < 1000 * 60 * 60) { //1시간 미만일 경우
							time = "방금전";
						}else{//1시간 이상인경우
							time = parseInt(gap /( 1000 * 60 * 60))+"시간전";
						}
					}else{//등록일 기준 하루 이상
						var today = new Date(millis);
						
						var year = today.getFullYear();
						var month = today.getMonth();
						var day = today.getDate();
						var hour = today.getHours();
						var minutes = today.getMinutes();
						var second = today.getSeconds();
						
						time = year+"년 "+month+"월 "+ day+"일 "+ hour+": "+ minutes+":"+ second
						
					}
					
					return time;
					
				}
				
				
					
					
				
				
				
				
        		
        	})//ready끝
        
        </script>
        
        
        
        
        
        
	
</body>

</html>
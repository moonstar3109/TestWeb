<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<%@include file = "../include/header.jsp" %>
    
<section>
        <div class="container">
            <div class="row join-wrap">
                <!--join-form을 적용한다 float해제 margin:0 auto-->
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    <form action = "updateSet.user" method = "post" name = "regForm">
                    <p>*표시는 필수 입력 표시입니다</p>
                    
                    <table class="table">
                        <tbody class="m-control">
                            <tr>
                                <td class="m-title">ID</td>
                                <td><input class="form-control input-sm" id = "id" name = "id" value = "${user.id }" readonly ></td>
                            </tr>
                            <tr>
                                <td class="m-title">*이름</td>
                                <td><input class="form-control input-sm" id = "name" name = "name"  value = "${user.name }"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호</td>
                                <td><input class="form-control input-sm" id = "password" name = "pw" value = "${user.pw }"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호확인</td>
                                <td><input class="form-control input-sm" id = "password-confrim" name = "pw_ch"></td>
                            </tr>
                            <tr>
                                <td class="m-title" id = "email">*E-mail</td>
                                <td>
                                    <input class="form-control input-sm" name = "email1" value = "${user.email1 }">@
                                    <select class="form-control input-sm sel" name = "email2" value = "${user.email2 }">
                                        <option>naver.com</option>
                                        <option>gmail.com</option>
                                        <option>daum.net</option>
                                    </select>
                                    <button class="btn btn-info" onclick = "distnict()">중복확인</button>
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*휴대폰</td>
                                <td>
                                    <input class="form-control input-sm sel" name = "num1" value = "${user.num1 }"> -
                                    <input class="form-control input-sm sel" name = "num2" value = "${user.num2 }"> -
                                    <input class="form-control input-sm sel" name = "num3" value = "${user.num3 }">
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*주소</td>	
                                <td><input class="form-control input-sm add" name = "add1" value = "${user.add1 }"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*상세주소</td>
                                <td><input class="form-control input-sm add" name = "add2" value = "${user.add2 }"></td>
                            </tr>
                        </tbody>
                    </table>
                   
                    <div class="titlefoot">
                        <button class="btn" onclick = "check()">수정</button>
                        <button class="btn" onclick = "location.href= ''">목록</button>
                    </div>
                    </form>
                </div>


            </div>

        </div>

   <script>
  	function check() {
  		//form태그는 유일하게 document.form이름.이름...이 가능합니다
  		
  		 if (document.regForm.password.value.length < 8 ){
  			alert("비밀번호 (영 대/소문자, 숫자, 특수문자 3종류 이상 조합 8자 이상");
  			return;
	  	} else if (document.regForm.pw.value != document.regForm.pw_ch.value){
	  		alert("비밀번호 확인란을 확인하세요");
	  		return;
	  	} else if (document.regForm.name.value == ''){
	  		alert("이름은 필수 입니다");
	  		return;
	  	} else{
	  		//submit()은 자바스크립트의 서브밋기능
	  		document.regForm.submit();
	  	}
  	
  </script>
    
   <%@include file = "../include/footer.jsp" %>
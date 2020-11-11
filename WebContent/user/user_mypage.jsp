<%@page import="truc.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
    <html lang="en">
     <style type="text/css">

    </style>
<%@include  file="../include/header.jsp" %>

<%

UserVO vo = (UserVO)session.getAttribute("user");
String id = vo.getId();

%>
    
<section>
        <div class="container">
            <div class="row join-wrap">
                
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER                   
                    </div>
                    <div>
                        <p><%=id %>님 회원정보</p>
                    </div>
                    <div>
                        <button type="button" class="btn btn-primary" onclick = "location.href = 'updateForm.user'">회원정보수정</button>
                        <button type="button" class="btn btn-primary" id="delCheck" >회원 탈퇴</button>
                        
                    </div>
                    <div class="delete-hidden">
                        <form action="userdelete.user" method = "post" name = "regForm">
                        <input type="password" class="form-control" name = "pw" placeholder="비밀번호를 입력하세요">
                        <button type="button" class="btn btn-primary" onclick = "check()">확인</button>
                        </form>
                    </div>
                    
                    <br>
                    <div>
                        <p><%=id %>님의 작성 게시물</p>
                        <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="text-align: center;">번호</th>
                            <th style="text-align: center;">제목</th>
                            <th style="text-align: center;">작성자</th>
                            <th style="text-align: center;">작성일</th>
                        </tr>
                    </thead>
                     <c:forEach var="vo" items = "${list }">
						<tbody>
						<tr>
							<td>${vo.bno }</td>
							<td>${vo.name }</td>
							<td>
							<a href="../board/content.board?bno=${vo.bno }">${vo.title }</a>
							</td>
					
					
							<td><fmt:formatDate value="${vo.regdate }" pattern="yyyy년MM월dd일"/></td>
					
						</tr>
						</tbody>
				</c:forEach>
                    <!-- <tbody>
                        <tr>
                            <td>1</td>
                            <td><a>Test</a></td>
                            <td>Min</td>
                            <td>2019-09-14 08:05</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td><a>Lorem Ipsum is simply dummyg industry.</a></td>
                            <td>MBW</td>
                            <td>2019-09-15 13:05</td>
                        </tr>

                    </tbody> -->
                </table>
                    </div>
                    
                    
                </div>


            </div>

        </div>

    </section>
    <script>
	function check() {

  		document.regForm.submit();
  	}
    </script>
    <script>
        //탈퇴버튼 디스플레이 처리
        var delCheck = document.getElementById("delCheck");
        delCheck.onclick = function() {
            var del  = document.querySelector(".delete-hidden");
            if(del.style.display == "none" || del.style.display == "") {
                del.style.display = "inline";
            } else {
                del.style.display = "none";
            }
        }
    </script>
  <%@include file="../include/footer.jsp"%>
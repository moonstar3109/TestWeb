<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <!--게시판만 적용되는 css-->            
        <style>

            .table-striped > tbody > tr {
                background-color: rgba(255, 255, 255)
            }
            .row h2 {
                color: aliceblue;
                
            }
            .pagination-sm {
                margin: 0;
            }
            
        </style>
<%@include file = "../include/header.jsp" %>
        
    <section>
        
        <div class="container">
            <div class="row">
                
                <h2>게시판 목록</h2>
                <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="background-color: #9DCAFF; text-align: center;">번호</th>
                            <th style="background-color: #9DCAFF; text-align: center;">제목</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성자</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성일</th>
                        </tr>
                    </thead>
					<tbody>
                    <c:forEach var="vo" items = "${list }">
					<tr>
						<td>${vo.bno}</td>
						<td><a href = "content.board?bno=${vo.bno} ">${vo.title }</a></td>
						<td>${vo.name }</td>
					    <td><fmt:formatDate value="${vo.regdate}" pattern="yyyy년MM월dd일"/></td>
					</tr>
					</c:forEach>
					</tbody>
                    <!-- <tbody>
                        <tr>
                            <td>1</td>
                            <td><a>Test</a></td>
                            <td>Min</td>
                            <td>2019-09-14 08:05</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td><a>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</a></td>
                            <td>MBW</td>
                            <td>2019-09-15 13:05</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td><a>Test Text</a></td>
                            <td>박인욱</td>
                            <td>2019-09-15 19:05</td>
                        </tr>
                    </tbody> -->
                </table>

                <div class="text-center">
                    <ul class="pagination pagination-sm">
                		<c:if test="${pageVO.prev }">
                        <li><a href="list.board?pageNum=${pageVO.startPage-1 }&amount=${pageVO.amount}">이전</a></li>
                        </c:if>
	            			
	            			
	                        <c:forEach var="num" begin = "${pageVO.startPage }" end="${pageVO.endPage}">
                        
    		                    <li class="${num == pageVO.pageNum ? 'avtive' : '' }">
    		                    <a href="list.board?pageNum=${num }&amount=${pageVO.amount}">${num}</a></li>
            	
            	            </c:forEach>
                      <c:if test = "${pageVO.next }">
                       <li><a href="list.board?pageNum=${pageVO.endPage+1 }&amount=${ pageVO.amount}">다음</a></li>
                      </c:if>  
                    </ul>
                    <button class="btn btn-info pull-right" onclick = "location.href='write.board'">글쓰기</button>
                </div>
                
            </div>
        </div>
    </section>
        
    <%@include file = "../include/footer.jsp" %>
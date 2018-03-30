<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="url" required="true" %>

<c:set var="block" value="${empty param.p ? 1 : param.p}" />
<c:set var="block1" value="${block <= 1 ? 2 : block}" />
<c:set var="block2" value="${block >= page.totalPages ? page.totalPages - 1 : block}" />

<div class="pagination">
	
	<a href="${url}?p=${block1-1}">&lt;&lt;</a>

	<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
		<c:choose>
			<c:when test="${page.number != pageNumber - 1}">
				<a href="${url}?p=${pageNumber}"><c:out value="${pageNumber}" /></a> 
			</c:when>
			<c:otherwise>
				<strong><c:out value="${pageNumber}" /></strong>
			</c:otherwise>
		</c:choose>
		<c:if test="${pageNumber != page.totalPages}"> | </c:if>				 
	</c:forEach>
	
	<a href="${url}?p=${block2+1}">&gt;&gt;</a>
</div>	
		

<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="url" required="true" %>
<%@ attribute name="size" required="false" %>

<%-- <c:set var="block" value="${empty param.p ? 1 : param.p}" />
<c:set var="block1" value="${block <= 1 ? 2 : block}" />
<c:set var="block2" value="${block >= page.totalPages ? page.totalPages - 1 : block}" /> --%>

<c:set var="size" value="${empty size ? 4 : size}" />
<c:set var="block" value="${empty param.b ? 0 : param.b}" />
<%-- <p>Block: ${block}</p>
<p>Size: ${size}</p> --%>

<c:set var="startPage" value="${block * size + 1}" />
<c:set var="endPage" value="${(block + 1) * size}" />
<c:set var="endPage" value="${endPage > page.totalPages ? page.totalPages : endPage}" />

<%-- <p>Start Page: ${startPage}</p>
<p>End Page: ${endPage}</p> --%>

<div class="pagination">
	
	<%-- <a href="${url}?p=${block1-1}">&lt;&lt;</a> --%>
	<c:if test="${block != 0}">
		<a href="${url}?b=${block-1}&p=${(block-1)*size + 1}">&lt;&lt;</a>
	</c:if>

	<c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">
		<c:choose>
			<c:when test="${page.number != pageNumber - 1}">
				<a href="${url}?p=${pageNumber}&b=${block}"><c:out value="${pageNumber}" /></a> 
			</c:when>
			<c:otherwise>
				<strong><c:out value="${pageNumber}" /></strong>
			</c:otherwise>
		</c:choose>
		<c:if test="${pageNumber != endPage}"> | </c:if>				 
	</c:forEach>
	
	<%-- <a href="${url}?p=${block2+1}">&gt;&gt;</a> --%>
	<c:if test="${endPage != page.totalPages}">
		<a href="${url}?b=${block+1}&p=${(block+1)*size + 1}">&gt;&gt;</a>
	</c:if>
</div>	
		

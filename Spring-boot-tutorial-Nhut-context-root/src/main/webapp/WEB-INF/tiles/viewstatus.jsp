<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp" %>

<c:url var="url" value="/viewstatus" />

<div class="row">
	<div class="col-md-8 col-md-offset-2">
		<jwp:pagination url="${url}" page="${page}" size="4" />
		<c:forEach var="statusUpdate" items="${page.content}">
			<c:url var="editLink" value="/editstatus?id=${statusUpdate.id}" />
			<c:url var="deleteLink" value="/deletestatus?id=${statusUpdate.id}" />
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						Status update added on
						<fmt:formatDate pattern="EEEE d MMMM y HH:mm:ss"
							value="${statusUpdate.added}" />
					</div>
				</div>
				<div class="panel-body">
					<div>${statusUpdate.text}</div>
					<div class="edit-links pull-right">												
						<button class="btn btn-large btn-primary" onclick="window.location.href='${editLink}'" >Edit</button>
						 
 						<button class="btn btn-large btn-primary" data-toggle="confirmation"
						        data-btn-ok-label="Yes" data-btn-ok-icon="glyphicon glyphicon-share-alt"
						        data-btn-ok-class="btn-success"
						        data-btn-cancel-label="Cancel" data-btn-cancel-icon="glyphicon glyphicon-ban-circle"
						        data-btn-cancel-class="btn-danger"
						        data-title="Are you sure ?" data-content="You will delete this status update"
						        href="${deleteLink}"
						        >
						  Delete
						</button>	 	
						<%-- <a href="${editLink}">edit</a> |									
						<a onclick="return confirm('Really delete this status update ?')" href="${deleteLink}">delete</a> --%> 
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

Page number: ${param.p}

<script src="${contextRoot}/js/jquery.js"></script>
<script src="${contextRoot}/js/bootstrap.js"></script> 
<script src="${contextRoot}/js/bootstrap-confirmation.js"></script>
<script> 
$(document).ready(function() {/* add this to make dropdown menu works */
    $(".dropdown-toggle").dropdown();
});
$('[data-toggle=confirmation]').confirmation({
	  rootSelector: '[data-toggle=confirmation]'
	});
</script>
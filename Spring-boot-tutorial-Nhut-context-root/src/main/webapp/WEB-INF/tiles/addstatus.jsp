<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
   
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
<%-- 			Request statusUpdate attribute: <%= request.getAttribute("statusUpdate") %> <br/>
			JSP object: <%= this %> <br/>
			JSP object: <%= this.getClass() %> <br/> --%>
<%-- 			<c:out value="${statusUpdate}" />	 --%>	
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Add a Status Update</div>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="statusUpdate">
						<div class="errors">
							<form:errors path="text"/>
						</div>
						<div class="form-group"> 
							<form:textarea id="tinyMCEtextarea" path="text" name="text" rows="id" cols="50"></form:textarea>
						</div>
						<input type="submit" name="submit" value="Add status" />
					</form:form>
				</div>
			</div>
		
			<div class="panel panel-default">
				<div class="panel-heading">
					<%-- <div class="panel-title">Status update added on <c:out value="${latestStatusUpdate.added}" /></div> --%>
					<div class="panel-title">Status update added on 
						<fmt:formatDate pattern="EEEE d MMMM y HH:mm:ss" value="${latestStatusUpdate.added}" />
					</div>
				</div>
				<div class="panel-body">
					${latestStatusUpdate.text}
				</div>
			</div>
		</div>
	</div>

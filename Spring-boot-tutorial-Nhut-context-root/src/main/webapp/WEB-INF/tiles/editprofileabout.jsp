<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
   
	<div class="row">
		<div class="col-md-8 col-md-offset-2">		
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Edit Your 'About' Text</div>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="profile" >
						<div class="errors">
							<form:errors path="profile.*"/>
						</div>
						<div class="form-group"> 
							<form:textarea id="nicEdit-textarea" path="about" name="about" rows="id" cols="50"></form:textarea>
						</div>
						<input type="submit" name="submit" value="Save About" />
						<input type="button" onclick="history.go(-1);" value="Cancel" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
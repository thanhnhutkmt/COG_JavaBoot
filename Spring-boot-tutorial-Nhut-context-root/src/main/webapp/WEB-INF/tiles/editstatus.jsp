<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
   
	<div class="row">
		<div class="col-md-8 col-md-offset-2">		
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Edit Status Update</div>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="statusUpdate" >
						<form:input type="hidden" path="id" />
						<form:input type="hidden" path="added" />
						<div class="errors">
							<form:errors path="text"/>
						</div>
						<div class="form-group"> 
							<form:textarea id="tinyMCEtextarea" path="text" name="text" rows="id" cols="50"></form:textarea>
						</div>
						<input type="submit" name="submit" value="Save status" />
						<input type="button" onclick="history.go(-1);" value="Cancel" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
	
	<textarea id="froala-editor"></textarea></p></p>
	<textarea id="quilleditor"></textarea>	</p></p>
	<textarea id="nicEdit-textarea"></textarea></p></p>
<!--     Refer default.jsp for declare editors  -->

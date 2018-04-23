<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- <c:url var="img" value="/img" /> --%>
<c:url var="editProfileAbout" value="/edit-profile-about" />
<c:url var="profilephoto" value="/profilephoto" />

<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<div class="profile-about">
			<div class="profile-image">
				<%-- <img src="${img}/avatar.png" /> --%>
				<img src="${profilephoto}" />
			</div>
			<div class="profile-text">
				<c:choose>
					<c:when test="${profile.about == null}">
						Click 'edit' to add information about yourself to your profile
					</c:when>
					<c:otherwise>
						${profile.about}
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="profile-about-edit">
			<%-- <a href="${editProfileAbout}">edit</a> --%>
			<button class="btn btn-large btn-primary"
				onclick="window.location.href='${editProfileAbout}'">Edit</button>
		</div>
		
		<p>&nbsp;</p>
		<div class="upload-form">				
			<c:url value="/upload-profile-photo" var="uploadPhotoLink"></c:url>
			<form method="post" action="${uploadPhotoLink}"	enctype="multipart/form-data">
				Select photo : 
				<input type="file" name="file" accept="image/*" /> 
				<input type="submit" value="Upload" /> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>				
	</div>	
</div>

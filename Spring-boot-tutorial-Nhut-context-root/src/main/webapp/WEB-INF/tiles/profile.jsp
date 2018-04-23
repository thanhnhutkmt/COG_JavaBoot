<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- <c:url var="img" value="/img" /> --%>
<c:url var="editProfileAbout" value="/edit-profile-about" />
<c:url var="profilephoto" value="/profilephoto/${userId}" />

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	
	<div id="profile-photo-status"></div>
	
		<div class="profile-about">
			<div class="profile-image">
				<%-- <img src="${img}/avatar.png" /> --%>
				<div>
					<img id="profilePhotoImage" src="${profilephoto}" />
				</div>	
				<div class="text-center">
					<a href="#" id="uploadLink">Upload photo</a>
				</div>
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
		
		<div class="upload-form">				
			<c:url value="/upload-profile-photo" var="uploadPhotoLink"></c:url>
			<form method="post" action="${uploadPhotoLink}"	enctype="multipart/form-data"
				id="photoUploadForm" >
				Select photo : 
				<input type="file" name="file" accept="image/*" id="photoFileInput"/> 
				<input type="submit" value="Upload" /> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>				
	</div>	
</div>

<script>
function setUploadStatusText(text) {
	$("#profile-photo-status").text(text);
	window.setTimeout(function() {
		$("#profile-photo-status").text("");
	}, 2000);	
}

function uploadSuccess(data) {
	$("#profilePhotoImage").attr("src", "${profilephoto};t=" + new Date());
 	$("#profilePhotoImage").val(""); 
 	setUploadStatusText(data.message);
}

function uploadPhoto(event) {
	$.ajax({
		url: $(this).attr("action"),
		type:'POST',
		data: new FormData(this), 
		processData: false,
		contentType: false,
		success: uploadSuccess, /* function(data) {alert(data.message); console.log("upload ok");}, */
		error: function() {setUploadStatusText("Server unreachable");}
	});
	console.log("Form being submitted");

	event.preventDefault();	
}

$(document).ready(function() {
	console.log("Hello! Document loaded!");
	$("#uploadLink").click(function(event) {
		event.preventDefault();
		$("#photoFileInput").trigger('click');
	});
	$("#photoFileInput").change(function() {
		$("#photoUploadForm").submit();
	});
	$("#photoUploadForm").on("submit", uploadPhoto);
});

</script>
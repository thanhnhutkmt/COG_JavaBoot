<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- <c:url var="img" value="/img" /> --%>
<c:url var="editProfileAbout" value="/edit-profile-about" />
<c:url var="profilephoto" value="/profilephoto/${userId}" />
<c:url var="saveInterest" value="/save-interest" />
<c:url var="deleteInterest" value="/delete-interest" />

<div class="row">
	<div class="col-md-10 col-md-offset-1">
	
	<div id="interestDiv">
		<ul id="interestList">
			<c:choose>
				<c:when test="${empty profile.interests}">
					<li>Add your interests here</li>
				</c:when>
				<c:otherwise>
					<c:forEach var="interest" items="${profile.interests}">
						<li>${interest}</li>
					</c:forEach>
				</c:otherwise>				
			</c:choose>
		</ul>	
	</div>
	
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
			<c:if test="${ownProfile == true}">
				<%-- <a href="${editProfileAbout}">edit</a> --%>
				<button class="btn btn-large btn-primary"
					onclick="window.location.href='${editProfileAbout}'">Edit</button>
			</c:if>
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

function saveInterest(text) {
	editInterest(text, "${saveInterest}");	
}

function deleteInterest(text) {
	editInterest(text, "${deleteInterest}");	
}

function editInterest(text, actionUrl) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	/* alert(token + ": " + header); */
	$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
		jqXHR.setRequestHeader(header, token);
	});
	
	$.ajax({
		'url': actionUrl,
		data: {
			'name': text
		},
		type: 'POST',
		success: function() {
			alert("Ok");
		},
		error: function() {
			alert("error");
		}
	});
}

$(document).ready(function() {
	console.log("Hello! Document loaded!");
	
	$('#interestList').tagit({		
		afterTagRemoved: function(event, ui) {			
			deleteInterest(ui.tagLabel);
		},
		afterTagAdded: function(event, ui) {
			if(ui.duringInitialization != true) {
				saveInterest(ui.tagLabel);
			}			
		},		
		caseSensitive: false,
		allowSpace: true,
		tagLimit: 10,
		readOnly: ${!ownProfile}
	});
	
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

<!-- jquery min for tagit lib -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
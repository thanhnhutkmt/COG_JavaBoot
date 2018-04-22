<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title><tiles:insertAttribute name="title" /></title>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${contextRoot}/css/main.css" rel="stylesheet" />

</head>
<body>
	<!-- Static navbar -->
	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextRoot}/">Spring boot tutorial Nhut</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${contextRoot}/">Home</a></li>
				<%-- <li><a href="${contextRoot}/viewstatus">View Status</a></li> --%>
				<li><a href="${contextRoot}/about">About</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${contextRoot}/login">Login</a></li>				
					<li><a href="${contextRoot}/register">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><a href="${contextRoot}/profile">Profile</a></li>
					<li><a href="javascript:$('#logoutForm').submit();">Logout</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="dropdown">
		                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		                	Status <span class="caret"></span>
		                </a>
		                <ul class="dropdown-menu">
		                  <li><a href="${contextRoot}/addstatus">Add Status</a></li>
		                  <li><a href="${contextRoot}/viewstatus">View Status Updates</a></li>
		                </ul>
	              	</li>
	            </sec:authorize>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>
	
	<c:url var="logoutLink" value="/logout" />
	<form id="logoutForm" method="post" action="${logoutLink}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
	<div class="container">
		<tiles:insertAttribute name="content" />
	</div>	
	<script src="${contextRoot}/js/jquery.min.js"></script>
 	<script src="${contextRoot}/js/bootstrap.min.js"></script>
 	
<!-- TINYMCE editor with trial apikey --> 
  	<script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=wzaoq2ifu65zy8gk3xsnsvt3u0noskebsm763krln9eluvqn"></script>
	<script>
	tinymce.init({
		  selector: '#tinyMCEtextarea',
		  plugins: 'print preview fullpage powerpaste searchreplace autolink directionality advcode visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount tinymcespellchecker a11ychecker imagetools mediaembed  linkchecker contextmenu colorpicker textpattern help',
		  toolbar: 'formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat'
		 });
	</script>
<!-- TINYMCE editor --> 
	
<!-- froala editor --> 	
	<link rel="stylesheet" href="${contextRoot}/css/froala_editor.pkgd.min.css" />	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />	  	
  	<script type="text/javascript" src="${contextRoot}/js/froala_editor.pkgd.min.js"></script> 	   	
	<script>
	   $(function() {
	    $('textarea#froala-editor').froalaEditor({
	      toolbarButtons: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 
	    	  'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'paragraphStyle', 
	    	  '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', 
	    	  '-', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', '|', 'emoticons', 
	    	  'specialCharacters', 'insertHR', 'selectAll', 'clearFormatting', '|', 'print', 'help', 
	    	  'html', '|', 'undo', 'redo'] 	  
	    })
	  });   
	</script>
<!-- froala editor --> 

<!-- quill editor --> 
	<link href="https://cdn.quilljs.com/1.1.6/quill.snow.css" rel="stylesheet">	
	<script src="https://cdn.quilljs.com/1.1.6/quill.js"></script>
	<script> 
		var toolbarOptions = [
		  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
		  ['blockquote', 'code-block'],
	
		  [{ 'header': 1 }, { 'header': 2 }],               // custom button values
		  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
		  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
		  [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
		  [{ 'direction': 'rtl' }],                         // text direction
	
		  [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
		  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
	
		  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
		  [{ 'font': [] }],
		  [{ 'align': [] }],	  
		];
	
 		var quill = new Quill('#quilleditor', {
		  modules: {
		    toolbar: toolbarOptions
		  },
		  theme: 'snow'
		}); 
	</script>
<!-- quill editor --> 

<!-- nicEditor editor --> 
	<script type="text/javascript" src="http://js.nicedit.com/nicEdit-latest.js"></script> 
	<script type="text/javascript">
	   bkLib.onDomLoaded(function() {new nicEditor({fullPanel : true}).panelInstance('nicEdit-textarea');});
	</script>
<!-- nicEditor editor --> 
</body>
</html>
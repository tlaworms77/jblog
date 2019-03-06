<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>
<style type="text/css">
html
body {
  font-family: 'Roboto', sans-serif;
}

.container {
  max-width: 720px;
  margin-top: 20px;
}

.button-group {
  margin-bottom: 20px;
}

.counter {
  display: inline;
  margin-top: 0;
  margin-bottom: 0;
  margin-right: 10px;
}

.posts {
  clear: both;
  list-style: none;
  padding-left: 0;
}

.posts li {
  background-color: #fff;
  border: 1px solid #d8d8d8;
  padding-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 10px;
  margin-bottom: 10px;
  word-wrap: break-word;
  min-height: 42px;
  border-radius: 4px;
}

li.selected {
  background-color: red;
}
</style>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${postVo.content }
					<p>
				</div>
				<ul class="blog-list">
					<c:if test="${!empty postlist }">
			            <c:forEach items="${postlist }" var="post"><!-- 2015/05/02 -->
			               <li><a href="${pageContext.servletContext.contextPath}/${id}/${post.categoryNo}/${post.no}">${post.title }</a> <span>${post.regDate }</span>   </li>
			            </c:forEach>
		            </c:if>
				</ul>
				<div class="container">
					<form>
						<div class="form-group">
							<textarea class="form-control status-box" rows="2"
								placeholder="What's on your mind?"></textarea>
						</div>
					</form>
					<div class="button-group pull-right">
						<p class="counter">140</p>
						<a href="#" class="btn btn-danger">Delete</a> <a href="#"
							class="btn btn-primary">Post</a>
					</div>
				
					<ul class="posts">
					</ul>
				</div>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}">
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main" />
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
	var main = function() {
		$('.btn-primary').click(function() {
			var post = $('.status-box').val();
			$('<li>').text(post).prependTo('.posts');
			$('.status-box').val('');
			$('.counter').text('140');
			$('.btn-primary').addClass('disabled');
		});

		$('.posts li').on('click', function() {
			$(this).addClass('selected');
		});

		$('.btn-danger').click(function() {
			$('selected').remove();
		});

		$('.status-box').keyup(function() {
			var postLength = $(this).val().length;
			var charactersLeft = 140 - postLength;
			$('.counter').text(charactersLeft);

			if (charactersLeft < 0) {
				$('.btn').addClass('disabled');
			} else if (charactersLeft == 140) {
				$('.btn').addClass('disabled');
			} else {
				$('.btn').removeClass('disabled');
			}
		});
		$('.btn').addClass('disabled');
	};

	$(document).ready(main);
</script>
</body>
</html>
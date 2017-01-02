<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Recover</title>

<!-- Global stylesheets -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900"
	rel="stylesheet" type="text/css">
<link href="./resources/assets/css/icons/icomoon/styles.css"
	rel="stylesheet" type="text/css">
<link href="./resources/assets/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="./resources/assets/css/core.css" rel="stylesheet"
	type="text/css">
<link href="./resources/assets/css/components.css" rel="stylesheet"
	type="text/css">
<link href="./resources/assets/css/colors.css" rel="stylesheet"
	type="text/css">
<!-- /global stylesheets -->

<!-- Core JS files -->
<script type="text/javascript"
	src="./resources/assets/js/plugins/loaders/pace.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/core/libraries/jquery.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/core/libraries/bootstrap.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/plugins/loaders/blockui.min.js"></script>
<!-- /core JS files -->

<!-- Theme JS files -->
<script type="text/javascript"
	src="./resources/assets/js/plugins/forms/styling/uniform.min.js"></script>

<script type="text/javascript" src="./resources/assets/js/core/app.js"></script>

<!-- /theme JS files -->

</head>

<body class="login-container">


	<!-- Navbar -->
	<%@ include file="./includes/login-navbar.jsp"%>
	<!-- /Navbar -->

	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">

			<!-- Main content -->
			<div class="content-wrapper">

				<!-- Content area -->
				<div class="content">

					<div class="col-md-3"></div>
					<!-- Advanced login -->
					<div class="col-md-6">
						<c:if test="${param.error == false}">
							<div
								class="alert alert-success alert-styled-right alert-arrow-right alert-bordered">
								<button type="button" class="close" data-dismiss="alert">
									<span>×</span><span class="sr-only">Close</span>
								</button>
								<span class="text-semibold">Well done!</span>An email was send
								to recover your password.
							</div>
						</c:if>

						<c:if test="${param.error == true}">
							<div class="alert bg-danger alert-styled-left">
								<button type="button" class="close" data-dismiss="alert">
									<span>×</span><span class="sr-only">Close</span>
								</button>
								<span class="text-semibold">Oh snap!</span> An error appear when
								we try to process your request.
							</div>
						</c:if>
						<form id="recover_form" method="POST" action="./recover">
							<div class="panel panel-body login-form">
								<div class="text-center">
									<div class="icon-object border-success text-success">
										<i class="icon-reset"></i>
									</div>
									<h5 class="content-group">Recover your account</h5>
								</div>

								<div class="form-group">
									<input name="username" type="text" class="form-control"
										placeholder="Insert your email or your username(CNP) to recover your password" />
								</div>

								<div class="form-group">
									<button type="submit" class="btn bg-teal btn-block btn-lg">
										Recover <i class="icon-circle-right2 position-right"></i>
									</button>
								</div>

								<div class="content-divider text-muted form-group">
									<span>You have an account?</span>
								</div>
								<a href="<c:url value='/login'/>"
									class="btn btn-default btn-block content-group">Back to
									login</a>
							</div>
						</form>
						<!-- /advanced login -->
					</div>
					<div class="col-md-3"></div>

					<!-- Footer -->
					<%@ include file="./includes/page-footer.jsp"%>
					<!-- /footer -->

				</div>
				<!-- /content area -->

			</div>
			<!-- /main content -->

		</div>
		<!-- /page content -->

	</div>
	<!-- /page container -->

</body>

</html>

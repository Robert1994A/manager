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
<title>Register</title>

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

<script type="text/javascript"
	src='<c:url value="./resources/assets/js/validation/jquery.form-validator.min.js"/>'></script>

<script type="text/javascript"
	src="<c:url value="./resources/assets/js/validation/validate.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="./resources/assets/js/validation/register_validation.js"/>"></script>

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
						<c:if test="${param.registered == true}">
							<div
								class="alert alert-success alert-styled-right alert-arrow-right alert-bordered">
								<button type="button" class="close" data-dismiss="alert">
									<span>×</span><span class="sr-only">Close</span>
								</button>
								<span class="text-semibold">Well done!</span> You successfully <a
									href="#" class="alert-link">registered.</a>You will receive a
								email to validate your account!
							</div>
						</c:if>
						<form:form modelAttribute="user" id="register_form">
							<div class="panel panel-body login-form">
								<div class="text-center">
									<div class="icon-object border-success text-success">
										<i class="icon-plus3"></i>
									</div>
									<h5 class="content-group">
										Create account <small class="display-block">All fields
											are required</small>
									</h5>
								</div>

								<div class="content-divider text-muted form-group">
									<span>Your credentials</span>
								</div>

								<div class="form-group">
									<form:input path="username" cssClass="form-control"
										placeholder="Choose your username" />
									<span><form:errors path="username"
											cssClass="help-block text-danger" /></span>
								</div>

								<div class="form-group">
									<form:password path="password" cssClass="form-control"
										placeholder="Create password" />
									<span><form:errors path="password"
											cssClass="help-block text-danger" /></span>
								</div>

								<div class="form-group">
									<input type="password" name="repeatPassword"
										class="form-control" placeholder="Repeat password" />
								</div>

								<div class="content-divider text-muted form-group">
									<span>Your privacy</span>
								</div>

								<div class="form-group">
									<form:input path="email" cssClass="form-control"
										placeholder="Your email" />
									<span><form:errors path="email"
											cssClass="help-block text-danger" /></span>
								</div>

								<div class="form-group">
									<input type="text" name="repeatEmail" class="form-control"
										placeholder="Repeat email">
								</div>

								<div class="form-group">
									<button type="submit" class="btn bg-teal btn-block btn-lg">
										Register <i class="icon-circle-right2 position-right"></i>
									</button>
								</div>

								<div class="content-divider text-muted form-group">
									<span>You have an account?</span>
								</div>
								<a href="<c:url value='/login'/>"
									class="btn btn-default btn-block content-group">Back to
									login</a>
							</div>
						</form:form>
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

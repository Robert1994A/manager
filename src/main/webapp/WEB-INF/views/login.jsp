<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

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
	src="./resources/assets/js/plugins/forms/validation/validate.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/plugins/forms/styling/uniform.min.js"></script>

<script type="text/javascript" src="./resources/assets/js/core/app.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/validation/login_validation.js"></script>
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
				<div class="content pb-20">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<c:if test="${param.error != null}">
							<div class="alert bg-danger alert-styled-left">
								<button type="button" class="close" data-dismiss="alert">
									<span>×</span><span class="sr-only">Close</span>
								</button>
								<span class="text-semibold">Error: <c:out
										value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></span>
							</div>
						</c:if>


						<c:if test="${param.validatedAccount == true}">
							<div
								class="alert alert-success alert-styled-right alert-arrow-right alert-bordered">
								<button type="button" class="close" data-dismiss="alert">
									<span>×</span><span class="sr-only">Close</span>
								</button>
								<span class="text-semibold">Well done!</span>Your account was
								succesfully <a href="#" class="alert-link">validated.</a>Now you
								can login in application!
							</div>
						</c:if>
						
						<c:if test="${param.recoverPassword == true}">
							<div
								class="alert alert-success alert-styled-right alert-arrow-right alert-bordered">
								<button type="button" class="close" data-dismiss="alert">
									<span>×</span><span class="sr-only">Close</span>
								</button>
								<span class="text-semibold">Well done!</span>You changed the password succesfully.
							</div>
						</c:if>

						<c:if test="${param.validatedAccount == false}">
							<div class="alert bg-danger alert-styled-left">
								<button type="button" class="close" data-dismiss="alert">
									<span>×</span><span class="sr-only">Close</span>
								</button>
								<span class="text-semibold">Oh snap!</span> Change a few things
								up and <a href="#" class="alert-link">try submitting again</a>.
							</div>
						</c:if>

						<!-- Form with validation -->
						<form action="./login_spring_security" method="POST"
							class="form-validate">
							<div class="panel panel-body login-form">
								<div class="text-center">
									<div class="icon-object border-slate-300 text-slate-300">
										<i class="icon-reading"></i>
									</div>
									<h5 class="content-group">
										Login to your account <small class="display-block">Your
											credentials</small>
									</h5>
								</div>

								<div class="form-group has-feedback has-feedback-left">
									<input type="text" class="form-control" placeholder="Username"
										name="username" required="required">
									<div class="form-control-feedback">
										<i class="icon-user text-muted"></i>
									</div>
								</div>

								<div class="form-group has-feedback has-feedback-left">
									<input type="password" class="form-control"
										placeholder="Password" name="password" required="required">
									<div class="form-control-feedback">
										<i class="icon-lock2 text-muted"></i>
									</div>
								</div>

								<div class="form-group login-options">
									<div class="row">
										<div class="col-sm-6">
											<label class="checkbox-inline"> <input
												type="checkbox" name="remember_me" class="styled" checked="checked">
												Remember me
											</label>
										</div>

										<div class="col-sm-6 text-right">
											<a href='<c:url value="/recover"/>'>Forgot password?</a>
										</div>
									</div>
								</div>

								<div class="form-group">
									<button type="submit" class="btn bg-blue btn-block">
										Login <i class="icon-arrow-right14 position-right"></i>
									</button>
								</div>

								<div class="content-divider text-muted form-group">
									<span>Don't have an account?</span>
								</div>
								<a href="<c:url value='/register'/>"
									class="btn btn-default btn-block content-group">Sign up</a> <span
									class="help-block text-center no-margin">By continuing,
									you're confirming that you've read our <a href="#">Terms
										&amp; Conditions</a> and <a href="#">Cookie Policy</a>
								</span>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />.
						</form>
						<!-- /form with validation -->
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

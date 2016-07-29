<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" ng-app="HomeApp">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="shortcut icon" href='./resources/img/favicon_1.ico'>

<title>Feedback</title>

<!-- Bootstrap core CSS -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/bootstrap-reset.css" rel="stylesheet">


<!--Animation css-->
<link href="./resources/css/animate.css" rel="stylesheet">

<!--Icon-fonts css-->
<link href="./resources/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link href="./resources/assets/ionicon/css/ionicons.min.css"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="./resources/css/style.css" rel="stylesheet">
<link href="./resources/css/helper.css" rel="stylesheet">

<!--Date picker css-->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"
	rel="stylesheet">

<script src="./resources/js/angular.js"></script>
<script src="./resources/js/angular-route.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
<!--[if lt IE 9]>
    <script src="./resources/js/html5shiv.js"></script>
    <script src="./resources/js/respond.min.js"></script>
    <![endif]-->


</head>


<body>
sdasdasdas
	<!-- Aside -->
	<jsp:include page="includes/navbar.jsp"></jsp:include>
	<!-- Aside Ends -->

	<!-- Main Content Start -->
	<section class="content">

		<jsp:include page="includes/header.jsp"></jsp:include>

		<!-- Page Content Start -->
		<div class="wraper container-fluid">
			<ng-view></ng-view>
		</div>
		<!-- Page Content Ends -->

		<!-- Footer Start -->
		<jsp:include page="includes/footer.jsp"></jsp:include>
		<!-- Footer Ends -->


	</section>
	<!-- Main Content Ends -->

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="./resources/js/jquery.js"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
	<script src="./resources/js/pace.min.js"></script>
	<script src="./resources/js/modernizr.min.js"></script>
	<script src="./resources/js/wow.min.js"></script>
	<script src="./resources/js/jquery.nicescroll.js"
		type="text/javascript"></script>
	<script src="./resources/js/jquery.app.js"></script>

	<!-- Sweet Alert -->
	<script src="./resources/assets/sweet-alert/sweet-alert.min.js"></script>
	<script src="./resources/assets/sweet-alert/sweet-alert.init.js"></script>
	<script src="./resources/myscripts/routing.js"></script>
	<script src="./resources/myscripts/services/UserService.js"></script>
	<script src="./resources/myscripts/controllers/UserController.js"></script>
	<script src="./resources/myscripts/services/FormService.js"></script>
	<script src="./resources/myscripts/controllers/FormController.js"></script>
	<script src="./resources/myscripts/controllers/HomeController.js"></script>


	<!--form validation-->
	<script type="text/javascript"
		src="./resources/assets/jquery.validate/jquery.validate.min.js"></script>
	<!--form validation init-->
	<script
		src="./resources/assets/jquery.validate/form-validation-init.js"></script>

	<script src="./resources/assets/timepicker/bootstrap-timepicker.min.js"></script>
	<script src="./resources/assets/timepicker/bootstrap-datepicker.js"></script>


</body>

</html>

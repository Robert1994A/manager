<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Admitere</title>

<script type="text/javascript"
	src="./resources/assets/js/core/libraries/jquery.min.js"></script>
<script src="./resources/myscripts/angular/angular.js"></script>

<script src="./resources/myscripts/angular/angular_module.js"></script>
<script src="./resources/myscripts/angular/ng_infinite_scroll.js"></script>
<script src="./resources/myscripts/angular/angular-route.js"></script>

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
	src="./resources/assets/js/plugins/visualization/d3/d3.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/plugins/visualization/d3/d3_tooltip.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/plugins/forms/styling/uniform.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/plugins/forms/selects/bootstrap_multiselect.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/plugins/ui/moment/moment.min.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/plugins/pickers/daterangepicker.js"></script>
<script type="text/javascript"
	src="./resources/assets/js/plugins/ui/nicescroll.min.js"></script>
<!-- /theme JS files -->

<script type="text/javascript" src="./resources/ckeditor/ckeditor.js"></script>


<!-- Theme JS files -->
<script type="text/javascript" src="./resources/assets/js/core/app.js"></script>
<!-- /theme JS files -->

</head>

<body class="navbar-top" ng-app="HomeApp">
	<!-- Main navbar -->
	<%@ include file="./includes/page-navbar.jsp"%>
	<!-- /main navbar -->


	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">

			<!-- Main sidebar -->
			<%@ include file="./includes/page-sidebar.jsp"%>
			<!-- /main sidebar -->


			<!-- Main content -->
			<div class="content-wrapper">

				<!-- Page header -->
				<!--  <%@ include file="./includes/page-header.jsp"%> -->
				<!-- /page header -->


				<!-- Content area -->
				<div class="content">

					<!-- Content -->
					<ng-view></ng-view>
					<!-- /content -->

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
	<script src="./resources/myscripts/angular/ui-bootstrap-tpls-2.0.1.js"></script>
	<script src="./resources/myscripts/angular/angular-animate.js"></script>
	<script src="./resources/myscripts/routing.js"></script>
	<script src="./resources/myscripts/services/UserService.js"></script>
	<script src="./resources/myscripts/controllers/UserController.js"></script>
	<script src="./resources/myscripts/services/FormService.js"></script>
	<script src="./resources/myscripts/controllers/FormController.js"></script>
	<script src="./resources/myscripts/controllers/HomeController.js"></script>
	<script src="./resources/myscripts/services/ContractPageService.js"></script>
	<script
		src="./resources/myscripts/controllers/ContractPageController.js"></script>
	<script src="./resources/myscripts/services/ProfileService.js"></script>
	<script src="./resources/myscripts/controllers/ProfileController.js"></script>
	<!-- 
	<script type="text/javascript">
		$("#navbar li").click(function(e) {
			$("li").removeClass("active");
			$(this).addClass("active");
		});
		$(".navbar-brand").click(function() {
			$("#navbar li").removeClass("active");
			$("#homePage").addClass("active");
		});
	</script>
 -->
</body>
</html>

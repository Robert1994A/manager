<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Admitere</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="./resources/assets-minified/images/icons/apple-touch-icon-144-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="./resources/assets-minified/images/icons/apple-touch-icon-114-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="./resources/assets-minified/images/icons/apple-touch-icon-72-precomposed.png" />
<link rel="apple-touch-icon-precomposed"
	href="./resources/assets-minified/images/icons/apple-touch-icon-57-precomposed.png" />
<link rel="shortcut icon"
	href="./resources/assets-minified/images/icons/favicon.png" />

<script type="text/javascript"
	src="./resources/assets-minified/js-core.js" /></script>


<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function() {
			$('#loading').fadeOut(400, "linear");
		}, 300);
	});
</script>

<style>
#loading {
	position: fixed;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
	display: block;
	background: #fff;
	z-index: 10000;
}

#loading img {
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -23px 0 0 -23px;
}
</style>

<link rel="stylesheet" type="text/css"
	href="./resources/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/demo-widgets.css">

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/helpers/helpers-all.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/elements/elements-all.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/icons/fontawesome/fontawesome.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/icons/linecons/linecons.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/snippets/snippets-all.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/applications/mailbox.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/themes/supina/layout.css" />

<link id="layout-color" rel="stylesheet" type="text/css"
	href="./resources/assets-minified/themes/supina/default/layout-color.css" />

<link id="framework-color" rel="stylesheet" type="text/css"
	href="./resources/assets-minified/themes/supina/default/framework-color.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/themes/supina/border-radius.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/assets-minified/helpers/colors.css" />

<link rel="stylesheet" type="text/css"
	href="./resources/bootstrap/css/bootstrap.css" />

<script src="./resources/myscripts/angular/angular.js"></script>

<script src="./resources/myscripts/angular/angular-route.js"></script>

</head>
<body ng-app="HomeApp">
	<div id="loading">
		<img src="./resources/assets-minified/images/spinner/loader-dark.gif"
			alt="Loading...">
	</div>
	<div id="sb-site">
		<div id="page-wrapper">

			<%@ include file="./includes/page-header.jsp"%>

			<%@ include file="./includes/page-sidebar.jsp"%>


			<div id="page-content-wrapper" class="rm-transition">
				<div id="page-content">
					<ng-view></ng-view>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="./resources/assets-minified/demo-widgets.js"></script>

	<script src="./resources/myscripts/routing.js"></script>
	<script src="./resources/myscripts/services/UserService.js"></script>
	<script src="./resources/myscripts/controllers/UserController.js"></script>
	<script src="./resources/myscripts/services/FormService.js"></script>
	<script src="./resources/myscripts/controllers/FormController.js"></script>
	<script src="./resources/myscripts/controllers/HomeController.js"></script>

</body>
</html>
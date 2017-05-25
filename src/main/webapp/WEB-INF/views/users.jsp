<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><spring:message code="title" /></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">


<%@ include file="include/script_files.jsp"%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">

		<!--START HEADER =============================================== -->
		<%@ include file="include/header.jsp"%>
		<!--END HEADER =============================================== -->

		<!--START SIDEBAR =============================================== -->
		<%@ include file="include/sidebar.jsp"%>
		<!--END SIDEBAR =============================================== -->


		<!--START CONTENT =============================================== -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Users</h1>
				<ol class="breadcrumb">
					<li><i class="fa fa-dashboard"></i> Acasa</li>
					<li class="active"><a href="#">Users</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Default box -->
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Users</h3>
						<div class="box-tools">
							<form method="get"
								action="${pageContext.request.contextPath}/users/search">
								<div class="input-group input-group-sm" style="width: 250px;">
									<input type="text" name="query" value="${param.query}"
										class="form-control pull-right"
										placeholder="Cauta utilizatori dupa email sau username">
									<div class="input-group-btn">
										<button type="submit" class="btn btn-default">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="box-body">
						<c:if test="${param.query != null && not empty param.query}">
							<div class="info-box">
								<span class="info-box-icon bg-aqua"><i
									class="fa fa-search"></i></span>
								<div class="info-box-content">
									<h3 class="widget-user-username">
										Utilizatorii gasiti dupa: <span class="text-aqua">${param.query}</span>
									</h3>
								</div>
								<!-- /.info-box-content -->
							</div>
						</c:if>
						<c:choose>
							<c:when test="${exist == false}">
								<div class="alert alert-warning alert-dismissible">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">×</button>
									<h4>
										<i class="icon fa fa-warning"></i> Warning!
									</h4>
									Ne pare rau dar nu am gasit nici-un utilizator definit. Pentru
									a adauga un utilizator va rugam apasati butonul de adaugare
									utilizatori: <a class="btn btn-primary"
										href="${pageContext.request.contextPath}/users/add_user">Adauga
										utilizatori</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="table-responsive no-padding">
									<table class="table table-hover">
										<tbody>
											<tr>
												<th>#</th>
												<th>Email</th>
												<th>Username</th>
											</tr>
											<c:forEach items="${users.content}" var="user">
												<tr>
													<td><c:out value="${user.id}" /></td>
													<td><c:out value="${user.email}" /></td>
													<td><c:out value="${user.username}" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->

			</section>
			<!-- /.content -->
		</div>
		<!--END CONTENT=============================================== -->

		<!--START FOOTER=============================================== -->
		<%@ include file="include/footer.jsp"%>
		<!--END FOOTER=============================================== -->
	</div>
	<!-- ./wrapper -->
</body>
</html>

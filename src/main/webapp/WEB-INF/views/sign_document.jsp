<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title><spring:message code="title" /> Sign document</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<style type="text/css">
p .help-block {
	color: red;
}
</style>

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
				<h1>Upload certificate</h1>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i> <a
						href="${pageContext.request.contextPath}/">Home</a></li>
					<li class="active"><a href="#">Sign document</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Default box -->
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Sign document</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse" data-toggle="tooltip" title="Collapse">
								<i class="fa fa-minus"></i>
							</button>
							<button type="button" class="btn btn-box-tool"
								data-widget="remove" data-toggle="tooltip" title="Remove">
								<i class="fa fa-times"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<c:if test="${not empty error}">
							<div class="alert alert-warning alert-dismissible">
								<button type="button" class="close" data-dismiss="alert"
									aria-hidden="true">�</button>
								<h4>
									<i class="icon fa fa-ban"></i> Warning!
								</h4>
								${error}
							</div>
						</c:if>
						<form:form id="sign_document"
							action="${pageContext.request.contextPath}/sign_document?${_csrf.parameterName}=${_csrf.token}"
							enctype="multipart/form-data">
							<div class="box-body">
								<div class="form-group">
									<label>Document to sign: </label> <input type="file"
										name="documentToSign" accept="application/pdf" class="form-control" />
								</div>
								<div class="form-group">
									<label>Private key: </label> <input type="file"
										name="privateKey" class="form-control" />
								</div>
							</div>
							<!-- /.box-body -->

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

							<div class="box-footer">
								<button type="submit" class="btn btn-primary" id="import">Sign
									document</button>
							</div>
						</form:form>
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

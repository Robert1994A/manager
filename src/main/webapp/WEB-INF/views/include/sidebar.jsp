<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="app" value="${pageContext.request.contextPath}" />
<!-- Left side column. contains the sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${app}/resources/admin/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal.username" />
					</security:authorize>
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<!-- search form -->
		<form action="${pageContext.request.contextPath}/certificates" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="query" class="form-control"
					placeholder="Search..." value="${ param.query}"> <span class="input-group-btn">
					<button type="submit" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu" id="main-menu">
			<li class="header">Navigation</li>
			<li id="home-page"><a href="${pageContext.request.contextPath}/">
					<i class="fa fa-home"></i> <span>Home</span>
			</a></li>

			<li class="treeview"><a href="#"><i class="fa  fa-file-text"></i>
					<span>Certificates</span> <span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<li><a href="${app}/certificates"><i class="fa fa-list-ul"></i>
							Certificates</a></li>
					<li><a href="${app}/generate_certificate"><i
							class="fa fa-plus-square"></i> Generate certificate</a></li>
					<li><a href="${app}/import_certificate"><i
							class="fa fa-plus-square"></i> Import certificate</a></li>
					<li><a href="${app}/upload_certificate"><i
							class="fa fa-upload"></i> Upload certificate</a></li>
				</ul></li>

			<li class="treeview"><a href="#"><i class="fa fa-edit"></i>
					<span>Tools</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span> </a>
				<ul class="treeview-menu">
					<!--li><a href="${app}/sign_document"><i
							class="fa fa-pencil-square-o"></i> Sign documents</a></li-->
					<li><a href="${app}/retrieve_certificates"><i
							class="fa fa-upload"></i> Certificate retriever</a></li>
				</ul></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
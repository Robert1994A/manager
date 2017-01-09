<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-group border-top-lg border-top-primary">
	<div class="page-header page-header-default"
		style="border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<div class="page-header-content">
			<div class="page-title">
				<h5>
					<span class="text-semibold">Personal files</span>
				</h5>
			</div>
			<div class="heading-elements">
				<ul class="breadcrumb heading-text">
					<li><a href="#/home"><i class="icon-home2 position-left"></i>
							Home</a></li>
					<li><a href="#/profile">Profile</a></li>
					<li class="active">Personal files</li>
				</ul>
			</div>
			<a class="heading-elements-toggle"><i class="icon-more"></i></a>
		</div>
	</div>
</div>
<div class="panel panel-flat">
	<div class="panel-body">
		<div class="col-md-12">
			<form method="POST" action="./profile/personalFilesUpload"
				enctype="multipart/form-data" id="addPersonalFiles_form">
				<fieldset>
					<legend class="text-bold">Add personal files</legend>
					<div class="form-group">
						<div class="col-md-12">
							<input type="file" class="file-input" name="file"
								multiple="multiple"> <span class="help-block">You
								can select multiple files to upload</span>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<c:if test="${existFiles == true}">
			<div ng-if="checkedFile">
				<div class="col-md-12">
					<div class="panel panel-body text-center">
						<div class="btn-group">
							<button type="button" class="btn btn-danger">Delete</button>
							<button type="button" class="btn btn-primary">Add</button>
							<button type="button" class="btn btn-success">Download</button>
						</div>
					</div>

				</div>
			</div>
			<div class="col-md-12">
				<fieldset>
					<legend class="text-bold">Personal files</legend>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr class="border-double">
									<th><div class="checkbox">
											<label> <input type="checkbox"
												class="control-primary" ng-model="all">
											</label>
										</div></th>
									<th>Filename</th>
									<th>Uploaded date</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${files}" var="file" varStatus="myIndex">
									<tr>
										<td><div class="checkbox">
												<label> <input type="checkbox"
													class="control-primary" name="checked[]" value="${file.id}"
													ng-checked="all" ng-model="checkedFile[${myIndex.index}]">
												</label>
											</div></td>
										<td>${file.name}</td>
										<td>${file.createdDate}</td>
										<td><div class="btn-group">
												<button type="button"
													class="btn btn-primary dropdown-toggle"
													data-toggle="dropdown" aria-expanded="false">
													Actions <span class="caret"></span>
												</button>
												<ul class="dropdown-menu dropdown-menu-right">
													<li><a ng-click="deleteFile('${file.id}')"><i
															class="icon-menu7"></i> Delete</a></li>
													<li><a ng-click="previewFile('${file.id}')"><i
															class="icon-screen-full"></i> Preview</a></li>
												</ul>
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</fieldset>
			</div>
		</c:if>
		<c:if test="${existFiles == false}">
			<div class="col-md-12">
				<div class="alert bg-danger alert-styled-left">
					<button type="button" class="close" data-dismiss="alert">
						<span>×</span><span class="sr-only">Close</span>
					</button>
					<span class="text-semibold">You don't have any files
						uploaded</span>
				</div>
			</div>
		</c:if>
	</div>
</div>



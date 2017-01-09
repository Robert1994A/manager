<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-group border-top-lg border-top-primary">
	<div class="page-header page-header-default"
		style="border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<div class="page-header-content">
			<div class="page-title">
				<h5>
					<span class="text-semibold">Previous Faculty</span>
				</h5>
			</div>
			<div class="heading-elements">
				<ul class="breadcrumb heading-text">
					<li><a href="#/home"><i class="icon-home2 position-left"></i>
							Home</a></li>
					<li><a href="#/profile">Profile</a></li>
					<li class="active">Previous Faculty</li>
				</ul>
			</div>
			<a class="heading-elements-toggle"><i class="icon-more"></i></a>
		</div>
	</div>
</div>

<form:form method="POST" id="addPreviousFaculty_form"
	commandName="previousFaculty">
	<div class="panel panel-flat">
		<div class="panel-body">
			<fieldset>
				<div class="form-group">
					<label>Enter institution name:</label>
					<form:input cssClass="form-control" path="institutionName"></form:input>
				</div>

				<div class="form-group">
					<label>Enter faculty name:</label>
					<form:input cssClass="form-control" path="facultyName"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your graduated profile:</label>
					<form:input cssClass="form-control" path="profil"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your specialization:</label>
					<form:input cssClass="form-control" path="specialization"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your obtaining title:</label>
					<form:input cssClass="form-control" path="obtainingTitle"></form:input>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label>Enter your form of education:</label>
						<form:select path="formOfEducation" cssClass="form-control">
							<form:options items="${formOfEducation}" />
						</form:select>
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label>Enter your form of financing:</label>
						<form:select path="formOfFinancing" cssClass="form-control">
							<form:options items="${formOfFinancing}" />
						</form:select>
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label>Enter your length of studies:</label>
						<form:input type="number" cssClass="form-control"
							path="lengthOfStudies"></form:input>
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label>Enter your graduation year:</label>
						<form:input type="number" cssClass="form-control"
							path="graduationYear"></form:input>
					</div>
				</div>

			</fieldset>
			<div class="text-right">
				<button type="button" class="btn btn-primary"
					ng-click="savePreviousFaculty()">
					Save data <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>
		</div>
	</div>
</form:form>
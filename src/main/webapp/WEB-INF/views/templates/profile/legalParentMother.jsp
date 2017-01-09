<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-group border-top-lg border-top-primary">
	<div class="page-header page-header-default"
		style="border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<div class="page-header-content">
			<div class="page-title">
				<h5>
					<span class="text-semibold">Legal Parent Mother</span>
				</h5>
			</div>
			<div class="heading-elements">
				<ul class="breadcrumb heading-text">
					<li><a href="#/home"><i class="icon-home2 position-left"></i>
							Home</a></li>
					<li><a href="#/profile">Profile</a></li>
					<li class="active">Legal Parent Mother</li>
				</ul>
			</div>
			<a class="heading-elements-toggle"><i class="icon-more"></i></a>
		</div>
	</div>
</div>
<form:form method="POST" id="addLegalParentMother_form"
	commandName="legalParentMother">
	<div class="panel panel-flat">
		<div class="panel-body">
			<fieldset>
				<div class="form-group">
					<label>Enter your mother first name:</label>
					<form:input cssClass="form-control" path="firstName"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your mother last name:</label>
					<form:input cssClass="form-control" path="lastName" />
				</div>

				<div class="form-group">
					<label>Enter your mother education level:</label>
					<form:select path="educationLevel" cssClass="form-control">
						<form:options items="${educationLevel}" />
					</form:select>
				</div>

			</fieldset>
			<div class="text-right">
				<button type="button" class="btn btn-primary"
					ng-click="savelegalParentFather()">
					Save data <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>
		</div>
	</div>
</form:form>
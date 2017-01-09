<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-group border-top-lg border-top-primary">
	<div class="page-header page-header-default"
		style="border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<div class="page-header-content">
			<div class="page-title">
				<h5>
					<span class="text-semibold">Personal address</span>
				</h5>
			</div>
			<div class="heading-elements">
				<ul class="breadcrumb heading-text">
					<li><a href="#/home"><i class="icon-home2 position-left"></i>
							Home</a></li>
					<li><a href="#/profile">Profile</a></li>
					<li class="active">Personal address</li>
				</ul>
			</div>
			<a class="heading-elements-toggle"><i class="icon-more"></i></a>
		</div>
	</div>
</div>
<form method="POST" id="addAddress_form">
	<div class="panel panel-flat">
		<div class="panel-body">
			<fieldset>
				<div class="form-group move-down">
					<label>Type your address:</label> <input type="text"
						id="Autocomplete2" ng-model="autocomplete" class="form-control"
						ng-autocomplete options="options" details="details" />
				</div>
			</fieldset>
			<div class="text-right">
				<button type="button" class="btn btn-primary"
					ng-click="savePersonalData()">
					Save personal data <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>
		</div>
	</div>
</form>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-group border-top-lg border-top-primary">
	<div class="page-header page-header-default"
		style="border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<div class="page-header-content">
			<div class="page-title">
				<h5>
					<span class="text-semibold">Personal identity card</span>
				</h5>
			</div>
			<div class="heading-elements">
				<ul class="breadcrumb heading-text">
					<li><a href="#/home"><i class="icon-home2 position-left"></i>
							Home</a></li>
					<li><a href="#/profile">Profile</a></li>
					<li class="active">Personal identity card</li>
				</ul>
			</div>
			<a class="heading-elements-toggle"><i class="icon-more"></i></a>
		</div>
	</div>
</div>
<form:form method="POST" id="addLegalParentFather_form"
	commandName="identityCard">
	<div class="panel panel-flat">
		<div class="panel-body">
			<fieldset>
				<div class="form-group">
					<label>Enter your series:</label>
					<form:input cssClass="form-control" path="series"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your number:</label>
					<form:input type="number" cssClass="form-control" path="number" />
				</div>

				<div class="form-group">
					<label>Enter your issued by:</label>
					<form:input cssClass="form-control" path="issuedBy" />
				</div>

				<div class="col-md-6">
					<label>Enter your identity card release date:</label>
					<div class="input-group">
						<span class="input-group-addon"><i class="icon-calendar3"></i></span>
						<form:input cssClass="form-control" path="releaseDate"
							value="Wednesday, 4th of June, 2014" readonly="readonly" />
					</div>
				</div>

				<div class="col-md-6">
					<label>Enter your identity card expiry date:</label>
					<div class="input-group">
						<span class="input-group-addon"><i class="icon-calendar3"></i></span>
						<form:input cssClass="form-control" path="expiryDate"
							value="Wednesday, 4th of June, 2014" readonly="readonly" />
					</div>
				</div>
			</fieldset>

			<div class="text-right">
				<button type="button" class="btn btn-primary"
					ng-click="saveIdentityCard()">
					Save data <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>
		</div>
	</div>
</form:form>
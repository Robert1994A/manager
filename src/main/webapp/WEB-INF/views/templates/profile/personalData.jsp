<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-group border-top-lg border-top-primary">
	<div class="page-header page-header-default"
		style="border-left: 1px solid #ddd; border-right: 1px solid #ddd;">
		<div class="page-header-content">
			<div class="page-title">
				<h5>
					<span class="text-semibold">Add personal data</span>
				</h5>
			</div>

			<div class="heading-elements">
				<ul class="breadcrumb heading-text">
					<li><a href="#/home"><i class="icon-home2 position-left"></i>
							Home</a></li>
					<li><a href="#/profile">Profile</a></li>
					<li class="active">Add personal data</li>
				</ul>
			</div>
			<a class="heading-elements-toggle"><i class="icon-more"></i></a>
		</div>
	</div>
</div>

<form:form method="POST" id="addPersonalData_form"
	commandName="userPersonalData">
	<div class="panel panel-flat">
		<div class="panel-body">
			<fieldset>

				<div class="form-group">
					<label>Enter your family name from certificate:</label>
					<form:input cssClass="form-control"
						path="familyNameBirtCertificate"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your actual family name:</label>
					<form:input cssClass="form-control" path="familyNameActual" />
				</div>


				<div class="form-group">
					<label>Enter your forename:</label>
					<form:input cssClass="form-control" path="forename" />
				</div>



				<div class="form-group">
					<label>Enter your initial mother forename:</label>
					<form:input cssClass="form-control" path="initialMotherForename"></form:input>
				</div>



				<div class="form-group">
					<label>Enter your initial father forename:</label>
					<form:input cssClass="form-control" path="initialFatherForename"></form:input>
				</div>


				<div class="form-group">
					<label>Enter your CNP:</label>
					<form:input type="number" cssClass="form-control" path="cnp"></form:input>
				</div>



				<div class="form-group">
					<label class="display-block">Enter your birthday:</label>
					<div class="row">
						<div class="col-xs-4">
							<label>Day</label>
							<form:input type="number" cssClass="form-control" path="birthDay"></form:input>
						</div>

						<div class="col-xs-4">
							<label>Month</label>
							<form:input type="number" cssClass="form-control"
								path="birthMonth"></form:input>
						</div>

						<div class="col-xs-4">
							<label>Year</label>
							<form:input type="number" cssClass="form-control"
								path="birthYear"></form:input>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label>Enter your gender:</label>
					<form:select path="gender" cssClass="form-control">
						<form:options items="${gender}" />
					</form:select>
				</div>

				<div class="form-group">
					<label>Enter your marital status:</label>
					<form:select path="maritalStatus" cssClass="form-control">
						<form:options items="${maritalStatus}" />
					</form:select>
				</div>

				<div class="form-group">
					<label>Enter your social status:</label>
					<form:select path="socialStatus" cssClass="form-control">
						<form:options items="${socialStatus}" />
					</form:select>
				</div>

				<div class="form-group">
					<label>Enter your citizenship:</label>
					<form:input cssClass="form-control" path="citizenship"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your ethnicity:</label>
					<form:input cssClass="form-control" path="ethnicity"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your religion:</label>
					<form:input cssClass="form-control" path="religion"></form:input>
				</div>

				<div class="form-group">
					<label>Enter your phone number:</label>
					<form:input cssClass="form-control" path="phoneNumber"></form:input>
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
</form:form>


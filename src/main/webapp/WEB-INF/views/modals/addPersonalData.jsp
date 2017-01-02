<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
<div class="modal-header">
	<h3 class="modal-title">Add personal data</h3>
</div>
<div class="modal-body">
	<form:form method="POST" id="addPersonalData"
		commandName="userPersonalData">
		<div class="panel panel-flat">
			<div class="panel-body">
				<fieldset>
					<div class="form-group">
						<label>Enter your family name from certificate:</label>
						<form:input cssClass="form-control"
							path="familyNameBirtCertificate"
							ng-model="userPersonalData.familyNameBirtCertificate"></form:input>
						<span><form:errors path="familyNameBirtCertificate"
								cssClass="error" /></span>
					</div>

					<div class="form-group">
						<label>Enter your actual family name:</label>
						<form:input cssClass="form-control" path="familyNameActual"
							ng-model="userPersonalData.familyNameActual" />
					</div>

					<div class="form-group">
						<label>Enter your forename:</label>
						<form:input cssClass="form-control" path="forename"
							ng-model="userPersonalData.forename" />
					</div>

					<div class="form-group">
						<label>Enter your initial mother forename:</label>
						<form:input cssClass="form-control" path="initialMotherForename"
							ng-model="userPersonalData.initialMotherForename"></form:input>
					</div>

					<div class="form-group">
						<label>Enter your initial father forename:</label>
						<form:input cssClass="form-control" path="initialFatherForename"
							ng-model="userPersonalData.initialFatherForename"></form:input>
					</div>

					<div class="form-group">
						<label>Enter your CNP:</label>
						<form:input cssClass="form-control" path="cnp"
							ng-model="userPersonalData.cnp"></form:input>
					</div>

					<div class="form-group">
						<label>Enter your birthday:</label>
						<fieldset>
							<div class="row">
								<div class="col-xs-4">
									<label>Day</label>
									<form:input cssClass="form-control" path="birthDay"
										ng-model="userPersonalData.birthDay"></form:input>
								</div>

								<div class="col-xs-4">
									<label>Month</label>
									<form:input cssClass="form-control" path="birthMonth"
										ng-model="userPersonalData.birthMonth"></form:input>
								</div>

								<div class="col-xs-4">
									<label>Year</label>
									<form:input cssClass="form-control" path="birthYear"
										ng-model="userPersonalData.birthYear"></form:input>
								</div>
							</div>
						</fieldset>
					</div>

					<div class="form-group">
						<label>Select your sex type:</label>
						<fieldset>
							<div class="row">
								<div class="col-xs-6">
									<label>Male:</label>
									<form:radiobutton cssClass="form-control" path="sexType"
										value="Male" ng-model="userPersonalData.sexType"></form:radiobutton>
								</div>
								<div class="col-xs-6">
									<label>Female:</label>
									<form:radiobutton cssClass="form-control" path="sexType"
										value="Female" ng-model="userPersonalData.sexType"></form:radiobutton>
								</div>
							</div>
						</fieldset>
					</div>

					<div class="form-group">
						<label>Enter your marital status:</label>
						<form:input cssClass="form-control" path="maritalStatus"
							ng-model="userPersonalData.maritalStatus"></form:input>
					</div>

					<div class="form-group">
						<label>Enter your social status:</label>
						<form:input cssClass="form-control" path="socialStatus"
							ng-model="userPersonalData.socialStatus"></form:input>
					</div>

					<div class="form-group">
						<label>Enter your citizenship:</label>
						<form:input cssClass="form-control" path="citizenship"
							ng-model="userPersonalData.citizenship"></form:input>
					</div>

					<div class="form-group">
						<label>Enter your ethnicity:</label>
						<form:input cssClass="form-control" path="ethnicity"
							ng-model="userPersonalData.ethnicity"></form:input>
					</div>

					<div class="form-group">
						<label>Enter your religion:</label>
						<form:input cssClass="form-control" path="religion"
							ng-model="userPersonalData.religion"></form:input>
					</div>
					
					<div class="form-group">
						<label>Enter your phone number:</label>
						<form:input cssClass="form-control" path="phoneNumber"
							ng-model="userPersonalData.phoneNumber"></form:input>
					</div>
				</fieldset>
			</div>

		</div>
	</form:form>
</div>
<div class="modal-footer">
	<button class="btn btn-primary" type="button"
		ng-click="savePersonalData()">
		Save <i class="icon-arrow-right14 position-right"></i>
	</button>
	<button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
</div>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div id="contractEditor">
	<form>
		<div class="form-group">
			<label>Name of contract:</label> <input type="text"
				class="form-control" ng-model="contract.name" ng-init="contract.name=''">
		</div>

		<div class="form-group">
			<label>Contract content:</label>
			<textarea name="editor" ck-editor id="editor" class="form-control"
				ng-model="contract.content" ng-init="contract.content=''"></textarea>
		</div>

		<div class="form-group">
			<label>Contract description:</label>
			<textarea class="form-control" ng-model="contract.description" ng-init="contract.description=''"></textarea>
		</div>

		<div class="form-group">
			<label class="checkbox-inline checkbox-right"> <span><input
					type="checkbox" ng-model="contract.published" ng-init="contract.published='false'"></span> Publish this
				contract
			</label>
		</div>

		<div class="form-group">
			<input type="button" id="saveContractButton"
				ng-click="saveContract()" class="btn btn-primary btn-lg"
				value="Save contract" />
		</div>
	</form>
</div>
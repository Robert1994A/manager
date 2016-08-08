
<!-- Form validation -->
<div class="panel panel-flat">

	<div class="panel-body">
		<form class="form-horizontal form-validate-jquery" action="#">
			<fieldset class="content-group" id="addContract">
				<legend class="text-bold">Add contract</legend>

				<!-- Basic text input -->
				<div class="form-group">
					<label class="control-label col-lg-3">Basic text input <span
						class="text-danger">*</span></label>
					<div class="col-lg-8">
						<input type="text" name="basic" class="form-control"
							placeholder="Text input validation">
					</div>
					<div class="col-lg-1">
						<input type="button" class="form-control btn-danger" value="X">
					</div>
				</div>
				<!-- /basic text input -->
				
			</fieldset>

			<fieldset class="content-group" id="addContract">
				<legend class="text-bold">Add new parameters</legend>
				<!-- Basic text input -->
				<div class="form-group">
					<label class="control-label col-lg-3">Parameter name:</label>
					<div class="col-lg-9">
						<input type="text" ng-model="parameterName" name="parameterName"
							id="parameterName" class="form-control"
							placeholder="Insert parameter name">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-5"></label>
					<div class="col-lg-2">
						<button type="button" ng-click="addParameter()" class="btn btn-primary">Add parameter</button>
					</div>
					<label class="control-label col-lg-5"></label>
				</div>
				<legend></legend>
				<!-- /basic text input -->

			</fieldset>

			<div class="text-right">
				<button type="reset" class="btn btn-default" id="reset">
					Reset <i class="icon-reload-alt position-right"></i>
				</button>
				<button type="submit" class="btn btn-primary" id="submit">
					Submit <i class="icon-arrow-right14 position-right"></i>
				</button>
			</div>
		</form>
	</div>
</div>


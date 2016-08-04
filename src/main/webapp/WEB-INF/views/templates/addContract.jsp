<script src="./resources/ckeditor/ckeditor.js" type="text/javascript"></script>
<div class="example-box-wrapper">

	<div class="alert alert-success" ng-if="response">
		<div class="bg-green alert-icon">
			<i class="glyph-icon icon-check"></i>
		</div>
		<div class="alert-content">
			<h4 class="alert-title">Success message title</h4>
			<p>
				Information message box using the
				<code>.alert-success</code>
				color scheme. <a href="#" title="Link">Link</a>
			</p>
		</div>
	</div>

	<div class="alert alert-danger" ng-if="!validInput">
		<div class="bg-red alert-icon">
			<i class="glyph-icon icon-times"></i>
		</div>
		<div class="alert-content">
			<h4 class="alert-title">Danger message title</h4>
			<p>
				Information message box using the
				<code>.alert-danger</code>
				color scheme. <a href="#" title="Link">Link</a>
			</p>
		</div>
	</div>

	<textarea name="contractPage" id="contractPage" rows="10" cols="160"></textarea>

	<div class="button-pane">
		<button class="btn btn-lg btn-post float-right btn-primary"
			id="saveContractPage">Save</button>
	</div>

</div>
<script>
	CKEDITOR.replace('contractPage');
</script>
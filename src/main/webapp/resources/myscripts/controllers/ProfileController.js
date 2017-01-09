app.controller("profileController", function($scope, ProfileService, $timeout,
		$uibModal, $log) {

	$scope.animationsEnabled = true;

	$scope.addPersonalData = function() {
		var modalInstance = $uibModal.open({
			animation : $scope.animationsEnabled,
			templateUrl : './modals/addPersonalData.html',
			controller : 'profileModal'
		});
	};

	$scope.toggleAnimation = function() {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};

});

app.controller("personalDataController", function($scope, ProfileService,
		$timeout, $uibModal, $log) {

	$("#addPersonalData_form").validate(
			{
				rules : {
					familyNameBirtCertificate : {
						required : true,
						minlength : 2
					},
					familyNameActual : {
						required : true,
						minlength : 2
					},
					forename : {
						required : true,
						minlength : 2
					},
					initialMotherForename : {
						required : true,
						minlength : 1,
						maxlength : 1

					},
					initialFatherForename : {
						required : true,
						minlength : 1,
						maxlength : 1
					},
					cnp : {
						required : true,
						minlength : 13,
						maxlength : 13,
						digits : true
					},
					birthDay : {
						required : true,
						digits : true,
						maxlength : 2,
					},
					birthMonth : {
						required : true,
						digits : true,
						maxlength : 2,
					},
					birthYear : {
						required : true,
						maxlength : 4,
						minlength : 4,
						digits : true,
					},
					citizenship : {
						required : true,
						minlength : 2
					},
					religion : {
						required : true,
						minlength : 2
					},
					ethnicity : {
						required : true,
						minlength : 2
					},
					phoneNumber : {
						required : true,
						digits : true,
						maxlength : 10,
						minlength : 10,
					},
					gender : {
						required : true
					}
				},
				highlight : function(element) {
					$(element).closest('.form-group')
							.removeClass('has-success').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error')
							.addClass('has-success');
				},
			});

	$scope.savePersonalData = function() {
		if ($("#addPersonalData_form").valid()) {
			var formData = $("#addPersonalData_form").serializeObject();
			ProfileService.saveUserPersonalData(formData);
		}
	}
});

app.controller("profileIdentityCardController", function($scope,
		ProfileService, $timeout, $uibModal, $log) {

	$("#releaseDate").AnyTime_picker({
		format : "%W, %D of %M, %Z"
	});

	$("#expiryDate").AnyTime_picker({
		format : "%W, %D of %M, %Z"
	});

});

app.controller("profileAddressController", function($scope, ProfileService,
		$timeout, $uibModal, $log) {

});

app.controller("profilePreviousFacultyController", function($scope,
		ProfileService, $timeout, $uibModal, $log) {
});

app.controller("profilePreviousHighSchoolController", function($scope,
		ProfileService, $timeout, $uibModal, $log) {
});

app.controller("profileLegalParentMotherController", function($scope,
		ProfileService, $timeout, $uibModal, $log) {
});

app.controller("profileLegalParentFatherController", function($scope,
		ProfileService, $timeout, $uibModal, $log) {

	$scope.saveLegalParentFather = function() {
		if ($("#addLegalParentFather_form").valid()) {
			var formData = $("#addLegalParentFather_form").serializeObject();
			ProfileService.saveLegalParentFather(formData);
		}
	}

});

app
		.controller(
				"profilePersonalFilesController",
				function($scope, ProfileService, ResponseUtilService) {

					$('.file-input')
							.fileinput(
									{
										browseLabel : 'Browse',
										browseIcon : '<i class="icon-file-plus"></i>',
										uploadIcon : '<i class="icon-file-upload2"></i>',
										removeIcon : '<i class="icon-cross3"></i>',
										layoutTemplates : {
											icon : '<i class="icon-file-check"></i>'
										},
										initialCaption : "No file selected",
										maxFilesNum : 10,
										allowedFileExtensions : [ "jpg", "gif",
												"png", "txt", "pdf", "png",
												"pptx" ]
									});

					$('#addPersonalFiles_form')
							.on(
									'submit',
									function(e) {
										e.preventDefault();
										var formData = new FormData($(this)[0]);
										var msg_error = 'An error has occured. Please try again later.';
										var msg_timeout = 'The server is not responding';
										var message = '';
										var form = $('#addPersonalFiles_form');
										$
												.ajax({
													data : formData,
													async : false,
													cache : false,
													processData : false,
													contentType : false,
													url : form.attr('action'),
													type : form.attr('method'),
													error : function(xhr,
															status, error) {
														if (status === "timeout") {
															alert(msg_timeout);
														} else {
															alert(msg_error);
														}
													},
													success : function(response) {
														angular
																.injector(
																		[ 'ng',
																				'HomeApp' ])
																.invoke(
																		function(
																				ResponseUtilService) {
																			ResponseUtilService
																					.handleResponse();
																		});
														$(
																".fileinput-remove-button")
																.trigger(
																		"click");
													},
													error : function(response) {
														angular
																.injector(
																		[ 'ng',
																				'HomeApp' ])
																.invoke(
																		function(
																				ResponseUtilService) {
																			ResponseUtilService
																					.handleResponse();
																		});
													},
													timeout : 7000
												});
									});

				});
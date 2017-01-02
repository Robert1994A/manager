app.controller("addContract", function($scope, ContractPageService) {

	// CKEDITOR.replace('editor');

	$scope.saveContract = function() {
		ContractPageService.saveContractPage($scope.contract).success(
				function(response) {
					console.log(response);
				});
	}

});

app.controller("ContractUniquePageController", function($scope, $routeParams,
		ContractPageService) {
	var id = $routeParams.id;
	ContractPageService.getContractPage(id).success(function(response) {
		$scope.contract = response;
	});
});
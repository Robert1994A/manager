app.controller("ContractPageController", function($scope, ContractPageService) {

	$scope.contractPage = {
		page : ""
	};
	
	getAllContractPage();

	$scope.validInput = true;

	$("#saveContractPage").click(
			function() {
				var pageValue = CKEDITOR.instances['contractPage'].getData()
				if (pageValue != "") {
					$scope.contractPage.page = pageValue;
					ContractPageService.saveContractPage($scope.contractPage)
							.success(function(response) {
								$scope.response = response;
							});
				} else {
					$scope.validInput = false;
				}

			});

	function getAllContractPage() {
		ContractPageService.getAllContractPage(function(contracts) {
			$scope.contractPage = contracts;
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
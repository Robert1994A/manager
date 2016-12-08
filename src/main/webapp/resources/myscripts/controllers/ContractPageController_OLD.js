app.controller("ContractPageController", function($scope, ContractPageService) {
	
	$scope.addParameter = function() {
		var parameterName =  $scope.parameterName;
		if(parameterName !== undefined && parameterName !== ""){
			if($("input[name="+parameterName+"]").length === 0){
				$("#addContract").append(appendInput(parameterName));
				$scope.parameterName = "";
			}
		}
    };
    
    $scope.deleteParameter = function (parameterName) {
    	alert("delete");
		$("#row_"+parameterName+"").remove();
    };
    
    function appendInput(parameterName){
    	return "<div class=\"form-group\" id=\"row_"+parameterName+"\"><label class=\"control-label col-lg-3\">"+parameterName+"<spanclass=\"text-danger\">*</span></label><div class=\"col-lg-8\"><input type=\"text\" name="+parameterName+" class=\"form-control\" placeholder=\"Insert our "+parameterName+"\"></div><div class=\"col-lg-1\"><button type=\"button\" ng-click='deleteParameter(\""+parameterName+"\")' class=\"form-control btn-danger\">X</button></div>";
    }
	
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
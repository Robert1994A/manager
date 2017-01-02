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

app.controller('profileModal', function($scope, ProfileService ,$uibModalInstance) {
	$scope.userPersonalData = {};
	$scope.savePersonalData = function() {
		console.log($scope.userPersonalData);
		ProfileService.savePersonalData($scope.userPersonalData).success(function(response) {
			console.log(response);
		});
		//$uibModalInstance.close();
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});
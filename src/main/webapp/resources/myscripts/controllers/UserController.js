app.controller("usersController", function($scope, UserService, Reddit,
		$timeout, $uibModal, $log, ResponseUtilService) {

	$scope.perPage = 10;

	$scope.sortBy = "id";

	$scope.sortDirection = "ASC";

	$scope.reddit = new Reddit($scope.perPage, $scope.sortBy,
			$scope.sortDirection, "");

	$scope.lastPage = $scope.reddit.lastPage;

	$scope.searchUser = function() {
		$scope.reddit = new Reddit($scope.perPage, $scope.sortBy,
				$scope.sortDirection, $scope.searchUserValue);

		$scope.reddit.nextPage();

		$scope.lastPage = $scope.reddit.lastPage;
	}

	$scope.animationsEnabled = true;

	$scope.viewUserDetails = function(id) {
		$uibModal.open({
			animation : $scope.animationsEnabled,
			templateUrl : './modals/singleUserModal.html/' + id + "?v=" + ResponseUtilService.generateRandomString(),
			controller : 'singleUserModal'
		});
	};

	$scope.toggleAnimation = function() {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};

});

app.controller('singleUserModal', function($scope, $uibModalInstance,
		UserService, ResponseUtilService) {

	$scope.deleteAccount = function(id) {
		UserService.deleteAccount(id);
	};

	$scope.disableAccount = function(id) {
		UserService.disableAccount(id);
	};

	$scope.enableAccount = function(id) {
		UserService.enableAccount(id);
	};

	$scope.cancel = function() {
		$uibModalInstance.close();
	};
});

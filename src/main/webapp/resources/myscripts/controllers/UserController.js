app.controller("usersController", function($scope, UserService, Reddit,
		$timeout, $uibModal, $log) {

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
		var modalInstance = $uibModal.open({
			animation : $scope.animationsEnabled,
			templateUrl : './modals/singleUserModal.html/' + id,
			controller : 'singleUserModal'
		});
	};

	$scope.toggleAnimation = function() {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};

});

app.controller('singleUserModal', function($scope, $uibModalInstance) {
	$scope.ok = function() {
		$uibModalInstance.close();
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});

app.controller("userDetailsController", function($scope, $routeParams,
		UserService) {
	var id = $routeParams.id;
	var promise = UserService.getUser(id);
	promise.then(function(data) {
		$scope.user = data.data;
	});

});

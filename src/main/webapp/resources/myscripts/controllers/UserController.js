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

app.controller('singleUserModal', function($scope, $uibModalInstance,
		UserService) {
	$scope.deleteAccount = function(id) {
		var promise = UserService.deleteAccount(id);
		promise.then(function(data) {
			if (data.data.status == "OK") {
				$scope.validResponse = data.data.message;
			}
			if (data.data.status == "BAD_REQUEST") {
				$scope.invalidResponse = data.data.message;
			}

		});
	};

	$scope.disableAccount = function(id) {
		var promise = UserService.disableAccount(id);
		promise.then(function(data) {
			if (data.data.status == "OK") {
				$scope.validResponse = data.data.message;
			}
			if (data.data.status == "BAD_REQUEST") {
				$scope.invalidResponse = data.data.message;
			}
		});
	};
	
	$scope.enableAccount = function(id) {
		var promise = UserService.enableAccount(id);
		promise.then(function(data) {
			if (data.data.status == "OK") {
				$scope.validResponse = data.data.message;
			}
			if (data.data.status == "BAD_REQUEST") {
				$scope.invalidResponse = data.data.message;
			}
		});
	};

	$scope.cancel = function() {
		$uibModalInstance.close();
	};
});

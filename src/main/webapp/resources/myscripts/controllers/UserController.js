app.controller("usersController", function($scope, UserService, Reddit,
		$timeout) {

	$scope.reddit;
	
	UserService.getUsersCount().success(
			function(data, status, headers, config) {
				if (status === 200) {
					$scope.usersCount = data.countUsers;
					if ($scope.usersCount % 20 != 0) {
						$scope.usersCount += 1;
					}

					$scope.reddit = new Reddit($scope.usersCount / 20);

					$scope.lastPage = $scope.reddit.lastPage;
				}
			});

});

app.controller("userDetailsController", function($scope, $routeParams,
		UserService) {
	var id = $routeParams.id;
	UserService.getUser(id).success(function(response) {
		$scope.user = response;
	});
});
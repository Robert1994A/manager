app.controller("usersController", function($scope, UserService, Reddit) {

	$scope.reddit = new Reddit();

	$scope.lastPage = $scope.reddit.lastPage;

});

app.controller("userDetailsController", function($scope, $routeParams,
		UserService) {
	var id = $routeParams.id;
	UserService.getUser(id).success(function(response) {
		$scope.user = response;
	});
});
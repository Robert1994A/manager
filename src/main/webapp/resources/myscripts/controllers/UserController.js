app.controller("usersController", function ($scope, UserService) {
    UserService.getAllUsers(function (users) {
        $scope.users = users;
    });
    $scope.rowLimit = 10;
    $scope.perPages = [25, 50, 100];
    $scope.sortColumn = 'username';


    $scope.showUsername = true;
    $scope.showEmail = true;
    $scope.showRoles = true;
    $scope.showAction = true;

});

app.controller("userDetailsController", function ($scope, $routeParams,
                                                  UserService) {
    var id = $routeParams.id;
    UserService.getUser(id).success(function (response) {
        $scope.user = response;
    });
});
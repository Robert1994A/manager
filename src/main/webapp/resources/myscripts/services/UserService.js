app.factory('UserService', function ($http) {
    return {
        getAllUsers: function (callback) {
            $http({
                url: "/admitere/json/users.json",
                method: "get"
            }).success(callback);
        },
        getUser: function (id) {
            return $http({
                url: "/admitere/json/users/" + id + ".json",
                method: "get"
            });
        }
    };
});
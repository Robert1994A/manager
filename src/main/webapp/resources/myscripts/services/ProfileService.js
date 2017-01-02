app.factory('ProfileService', function ($http) {
    return {
        savePersonalData: function (data) {
            return $http({
                url: "/admitere/modals/addPersonalData.html",
                data: data,
                method: "POST"
            });
        }
    };
});
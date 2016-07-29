app.factory('FormService', function ($http) {
    return {
        getAllForms: function (callback) {
            $http({
                url: "/feedback/json/forms.json",
                method: "get"
            }).success(callback);
        },
        getForm: function (id) {
            return $http({
                url: "/feedback/json/forms/" + id + ".json",
                method: "get"
            });
        }
    };
});
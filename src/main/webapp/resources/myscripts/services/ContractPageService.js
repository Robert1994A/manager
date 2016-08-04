app.factory('ContractPageService', function ($http) {
    return {
        getAllContractPage: function (callback) {
            $http({
                url: "/admitere/json/contractPage/contractsPage.json",
                method: "GET"
            }).success(callback);
        },
        getContractPage: function (id) {
            return $http({
                url: "/admitere/json/contractsPage/" + id + ".json",
                method: "GET"
            });
        },
        saveContractPage: function (data) {
            return $http({
                url: "/admitere/json/contractPage/create",
                data: data,
                method: "POST"
            });
        }
    };
});
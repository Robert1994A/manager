var app = angular.module("HomeApp", ["ngRoute"]);

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider.when("/home", {
        templateUrl: "templates/home.html",
        controller: "homeController",
    }).when("/users", {
        templateUrl: "templates/users.html",
        controller: "usersController",
    }).when("/users/:id", {
        templateUrl: "templates/userDetails.html",
        controller: "userDetailsController"
    }).when("/forms", {
        templateUrl: "templates/forms.html",
        controller: "formsController"
    }).when("/add_form", {
        templateUrl: "templates/add_form.html",
        controller: "formsController"
    }).otherwise({
        redirectTo: "/home"
    })
});


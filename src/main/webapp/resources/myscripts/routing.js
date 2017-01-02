var app = angular.module("HomeApp", [ "ngRoute", "infinite-scroll", "ngAnimate", "ui.bootstrap", "ck" ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when("/home", {
		templateUrl : "templates/home.html",
		controller : "homeController",
	}).when("/users", {
		templateUrl : "templates/users.html",
		controller : "usersController",
	}).when("/users/:id", {
		templateUrl : "templates/userDetails.html",
		controller : "userDetailsController"
	}).when("/forms", {
		templateUrl : "templates/forms.html",
		controller : "formsController"
	}).when("/add_form", {
		templateUrl : "templates/add_form.html",
		controller : "formsController"
	}).when("/addContract", {
		templateUrl : "templates/addContract.html",
		controller : "addContract"
	}).when("/contracts", {
		templateUrl : "templates/contracts.html",
	}).when("/contract", {
		templateUrl : "templates/contract.html",
	}).when("/profile", {
		templateUrl : "templates/profile.html",
		controller : "profileController"
	}).otherwise({
		redirectTo : "/home"
	})
});
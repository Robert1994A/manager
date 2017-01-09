var app = angular.module("HomeApp", [ "ngRoute", "infinite-scroll","ngAutocomplete",
		"ngAnimate", "ui.bootstrap", "ck" ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when("/home", {
		templateUrl : "templates/home.html?v=" + $.now(),
		controller : "homeController",
	}).when("/users", {
		templateUrl : "templates/users.html?v=" + $.now(),
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
		templateUrl : "templates/profile.html?v=" + $.now(),
		controller : "profileController",
	}).when("/profile/personalData", {
		templateUrl : "templates/profile/personalData.html",
		controller : "personalDataController"
	}).when("/profile/address", {
		templateUrl : "templates/profile/address.html",
		controller : "profileAddressController"
	}).when("/profile/identityCard", {
		templateUrl : "templates/profile/identityCard.html",
		controller : "profileIdentityCardController"
	}).when("/profile/previousFaculty", {
		templateUrl : "templates/profile/previousFaculty.html",
		controller : "profilePreviousFacultyController"
	}).when("/profile/previousHighSchool", {
		templateUrl : "templates/profile/previousHighSchool.html",
		controller : "profilePreviousHighSchoolController"
	}).when("/profile/legalParentMother", {
		templateUrl : "templates/profile/legalParentMother.html",
		controller : "profileLegalParentMotherController"
	}).when("/profile/legalParentFather", {
		templateUrl : "templates/profile/legalParentFather.html",
		controller : "profileLegalParentFatherController"
	}).when("/profile/personalFiles", {
		templateUrl : "templates/profile/personalFiles.html",
		controller : "profilePersonalFilesController"
	}).otherwise({
		redirectTo : "/home"
	})
});
var applicationName = "/admitere/";

app.service('UserService', function($http, $q, ResponseUtilService) {
	var deferred = $q.defer();

	this.deleteAccount = function(id) {
		$http.post(applicationName + "users/deleteAccount/" + id).then(
				function(response) {
					if (typeof response.data === 'object') {
						ResponseUtilService.handleReponse(response.data);
						return response.data;
					} else {
						// invalid response
						ResponseUtilService.handleReponse(response.data);
						return $q.reject(response.data);
					}

				}, function(response) {
					// something went wrong
					ResponseUtilService.handleReponse(response.data);
					return $q.reject(response.data);
				});
	}

	this.disableAccount = function(id) {
		$http.post(applicationName + "users/disableAccount/" + id).then(
				function(response) {
					if (typeof response.data === 'object') {
						ResponseUtilService.handleReponse(response.data);
						return response.data;
					} else {
						// invalid response
						ResponseUtilService.handleReponse(response.data);
						return $q.reject(response.data);
					}

				}, function(response) {
					// something went wrong
					ResponseUtilService.handleReponse(response.data);
					return $q.reject(response.data);
				});
	}

	this.enableAccount = function(id) {
		$http.post(applicationName + "users/enableAccount/" + id).then(
				function(response) {
					if (typeof response.data === 'object') {
						ResponseUtilService.handleReponse(response.data);
						return response.data;
					} else {
						// invalid response
						ResponseUtilService.handleReponse(response.data);
						return $q.reject(response.data);
					}

				}, function(response) {
					// something went wrong
					ResponseUtilService.handleReponse(response.data);
					return $q.reject(response.data);
				});
	}
});

app.factory('Reddit', function($http) {
	var Reddit = function(perPage, sortBy, sortDirection, searchUser) {
		this.items = [];
		this.totalPages = undefined;
		this.busy = false;
		this.after = 0;
		this.lastPage = false;
		this.perPage = perPage;
		this.sortBy = sortBy;
		this.searchUser = searchUser;
		this.sortDirection = sortDirection;
	};

	Reddit.prototype.nextPage = function() {
		if (this.busy) {
			return;
		}
		this.busy = true;
		if (this.totalPages !== undefined && this.totalPages === this.after) {
			this.lastPage = true;
			return;
		}
		if (this.searchUser == undefined) {
			this.searchUser = "";
		}
		var url = applicationName + "users/paginate?pageNumber=" + this.after
				+ "&pageSize=" + this.perPage + "&sortBy=" + this.sortBy
				+ "&searchUser=" + this.searchUser + "&direction="
				+ this.sortDirection;
		$http.get(url).success(function(data) {
			var items = data.content;
			this.totalPages = data.totalPages;
			for (var i = 0; i < items.length; i++) {
				this.items.push(data.content[i]);
			}
			this.after = this.after + 1;
			this.busy = false;
		}.bind(this));
	};
	return Reddit;
});

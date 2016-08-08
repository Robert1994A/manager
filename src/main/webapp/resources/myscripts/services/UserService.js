var applicationName = "/admitere/";

app.factory('UserService', function($http) {
	return {
		getAllUsers : function(callback) {
			$http({
				url : applicationName + "json/users.json",
				method : "get"
			}).success(callback);
		},
		getUser : function(id) {
			return $http({
				url : applicationName + "json/users/" + id + ".json",
				method : "get"
			});
		}
	};
});

app.factory('Reddit', function($http) {
	var Reddit = function() {
		this.items = [];
		this.busy = false;
		this.after = 0;
		this.lastPage = false;
	};

	Reddit.prototype.nextPage = function() {
		if (this.busy)
			return;
		this.busy = true;

		if (this.after == 3) {
			this.lastPage = true;
		} else {
			var url = applicationName + "json/users/paginate?pageNumber="
					+ this.after + "&pageSize=20&sortBy=id";
			$http.get(url).success(function(data) {

				var items = data;
				for (var i = 0; i < items.length; i++) {
					this.items.push(data[i]);
				}
				this.after = this.after + 1;
				this.busy = false;
			}.bind(this));
		}
	};

	return Reddit;
});

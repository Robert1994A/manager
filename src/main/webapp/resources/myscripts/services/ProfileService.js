app.service('ProfileService', function($http, $q, ResponseUtilService) {

	this.saveUserPersonalData = function(data) {
		var req = {
			method : 'POST',
			url : "./profile/savePersonalData",
			data : data
		}
		$http(req).then(function(response) {
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
	
	this.saveLegalParentFather = function(data) {
		var req = {
			method : 'POST',
			url : "./profile/savePersonalData",
			data : data
		}
		$http(req).then(function(response) {
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

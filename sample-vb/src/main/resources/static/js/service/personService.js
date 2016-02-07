'use strict';

app.factory('PersonService', [ '$http', '$q', function($http, $q) {

	var url = '/api/people';

	return {

		findFirstNameOrLastNameContains : function(name) {

			var pageParam = '?firstName=' + name + '&lastName=' + name;

			return $http.get(url + '/search/firstNameOrLastNameContains' + pageParam);
		}
	};
} ]);

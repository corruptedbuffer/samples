'use strict';

app.factory('CompanyService', ['$http', '$q', function($http, $q){
	
	var url = '/api/companies';
	
	function thenInternal(){
		return {
			function(response){
				return response.data;
			}, 
			function(errResponse){
				return $q.reject(errResponse);
			}			
		}
	}

	return {
		
			findAll: function(page) {
					var pageParam = '?';
					
					pageParam += (page && page.page ? 'page=' + page.page : 'page=0');
					pageParam += (page && page.size ? '&size=' + page.size : '&size=10');
					pageParam += (page && page.sort ? '&sort=' + page.sort : '');
					
					if (page && page.sort && page.sort.indexOf('id') == -1){
						pageParam += '&sort=id,desc';	
					}
				
					return $http.get(url + pageParam).then(thenInternal());
			},
		    
		    create: function(user){
					return $http.post(url, user).then(thenInternal());
		    },
		    
		    update: function(company){
					return $http.put(url +'/'+ company.id, company).then(thenInternal());
			},
		    
			delete: function(id){
					return $http.delete(url +'/'+ id).then(thenInternal());
			}		
	};
}]);

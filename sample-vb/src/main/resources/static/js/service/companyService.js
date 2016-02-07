'use strict';

app.factory('CompanyService', ['$http', '$q', function($http, $q){
	
	var url = '/api/companies';

	return {
		
			findAll: function(page) {
					var pageParam = '?';
					
					pageParam += (page && page.page ? 'page=' + page.page : 'page=0');
					pageParam += (page && page.size ? '&size=' + page.size : '&size=10');
					pageParam += (page && page.sort ? '&sort=' + page.sort : '');
					
					if (page && page.sort && page.sort.indexOf('id') == -1){
						pageParam += '&sort=id,desc';	
					}
				
					return $http.get(url + pageParam);
			},
							    
		    create: function(user){
					return $http.post(url, user);
		    },
		    
		    update: function(company){
					return $http.put(url +'/'+ company.id, company);
			},
		    
			delete: function(id){
					return $http.delete(url +'/'+ id);
			},
			
			findBeneficialOwners: function(id) {			
				return $http.get(url + '/' + id +'/beneficialOwners');
			},				
			
			addBeneficialOwner: function(id, urlBeneficialOwner){
				return $http({
				    url: url +'/'+ id + '/beneficialOwners',
				    method: 'POST',
				    data: urlBeneficialOwner,
				    headers: {
				        "Content-Type": "text/uri-list"
				    }					
				});
			},
			
			deleteBeneficialOwner: function(id, beneficialOwner){
				return $http.delete(url +'/'+ id + '/beneficialOwners/' + beneficialOwner.id);
			}						
	};
}]);

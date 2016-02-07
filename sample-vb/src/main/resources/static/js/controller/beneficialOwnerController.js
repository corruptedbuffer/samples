'use strict';

app.controller('BeneficialOwnerController', [ '$scope', 'CompanyService', 'PersonService', function($scope, companyService, personService) {
	var that = this;

	var companyId = $scope.$parent.companyId;

	that.error = null;
	that.firstSearch = false;
	
	that.beneficialOwners = [];

	that.person = {};
	that.person.name = null;

	that.people = [];

	that.isAlreadyBeneficialOwner = function(person) {
		return that.beneficialOwners.some(function(bo) {
			return bo.id == person.id;
		})
	}

	that.findBeneficialOwners = function() {

		that.error = false;
		companyService.findBeneficialOwners(companyId).then(function(success) {

			that.beneficialOwners = success.data._embedded.people;

		}, function(errResponse) {
			that.error = true;
		});

	}

	that.searchPeople = function() {

		that.error = false;
		that.firstSearch = true;		
		personService.findFirstNameOrLastNameContains(that.person.name).then(function(success) {

			that.people = success.data._embedded.people;

		}, function(errResponse) {
			that.error = true;
		});
	}

	that.addBeneficialOwner = function(person) {

		that.error = false;
		companyService.addBeneficialOwner(companyId, person._links.self.href).then(function(success) {

			that.findBeneficialOwners();

		}, function(errResponse) {
			that.error = true;
		});
	}

	that.deleteBeneficialOwner = function(person) {

		that.error = false;
		companyService.deleteBeneficialOwner(companyId, person).then(function(success) {

			that.findBeneficialOwners();

		}, function(errResponse) {
			that.error = true;
		});
	}

	// 
	// init

	that.findBeneficialOwners();

} ]);

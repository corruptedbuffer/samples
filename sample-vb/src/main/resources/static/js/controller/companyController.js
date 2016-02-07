'use strict';

app.controller('CompanyController', [ '$scope', '$compile', 'notifications', 'ngDialog', 'DTColumnBuilder', 'DTOptionsBuilder', 'CompanyService', // 
                                      function($scope, $compile, notifications, ngDialog, DTColumnBuilder, DTOptionsBuilder, companyService) {
	var that = this;

	that.companies = [];
	
	//
	// services
	
	that.reset = function (){
				
		that.company = {
				name : null,
				address : null,
				city : null,
				country : null,
				email : null,
				phoneNumber : null,	
		};
		
		if($scope.companyForm){
			$scope.companyForm.$setPristine();
		}
		
	}
	
	that.reset();
		
	that.create = function(company) {
						
		companyService.create(company).then(function (success){
			
            notifications.showSuccess('Company created');

			// reload table and pagination/ sorting
			var order = that.vm.dtOptions.order;
	        that.vm.dtInstance.dataTable.fnSort(order);
	        
		}, function(errResponse) {
			notifications.showError('Error creating company. Please try again...');
		});
	};

	that.update = function(companyToUpdate) {
		companyService.update(companyToUpdate).then(function (success){
			
			notifications.showSuccess('Company updated');

			that.vm.dtInstance.dataTable.fnStandingRedraw();
			
		}, function(errResponse) {
			notifications.showError('Error updating company. Please try again...');
		});
	};

	that.delete = function(id) {
		
		companyService.delete(id).then(function (success){
			
            notifications.showSuccess('Company deleted');
			
			// if editing the company we just removed
			if (that.company.id == id){
				// reset the form
				that.reset()	
			}
						
			// all of this is to send a refresh request for the same page we are
			// I have not found any easier way to send a refresh request and
			// maintain
			// the page/ sort criteria
			
			var currentPage = that.currentPage.number;
			
			// if in first page or there are not more elements in the table
			if (that.companies.length > 1){
				that.vm.dtInstance.dataTable.fnStandingRedraw();
				return;
			}
			
			if (currentPage == 1){
				that.vm.dtInstance.dataTable.fnDraw();
			} else {
				that.vm.dtInstance.dataTable.fnPageChange(currentPage -1, currentPage -1);	
			}

			}, function(errResponse) {
			notifications.showError('Error deleting company. Please try again...');
		});
	};

	that.save = function() {
		if (that.company.id) {
			that.update(that.company);
		} else {
			that.create(that.company);
		}
	};

	that.load = function(id) {
		
		that.company = that.companies.filter(function (company){
			return company.id == id;
		}).shift();		
	};
		
	that.loadBeneficialOwners = function (id){
		
		$scope.companyId = id;
		
		ngDialog.open({ 
			template: 'beneficialOwner.html',
			scope: $scope
		});
	}
	
		
	//
	// datatables
	
	that.vm = {};
	that.vm.dtInstance = {};
	that.vm.dtColumns = [
	    DTColumnBuilder.newColumn('id', 'Id'),	                     
        DTColumnBuilder.newColumn('name', 'Name'),
        DTColumnBuilder.newColumn('address', 'Address'),
        DTColumnBuilder.newColumn('city', 'City'),
        DTColumnBuilder.newColumn('country', 'Country'),
        DTColumnBuilder.newColumn('email', 'Email'),
        DTColumnBuilder.newColumn('phoneNumber', 'Phone Number'),
        DTColumnBuilder.newColumn(null).withTitle('Actions').notSortable().renderWith( function (value, display, data) {
        	
        	var companyId = data.id;
        	
            return '<a title="View" class="btn btn-sm btn-default" ng-click="$event.preventDefault(); ctrl.load('+ companyId +')"><i class="fa fa-eye"></i></a> ' +
            '<a title="Manage beneficial owners" class="btn btn-sm btn-default" ng-click="$event.preventDefault(); ctrl.loadBeneficialOwners('+ companyId +')"><i class="fa fa-user"></i></a> ' +
            '<a title="Delete" class="btn btn-sm btn-danger" ng-click="$event.preventDefault(); ctrl.delete('+ companyId +')"><i class="fa fa-trash-o"></i></a>';
        })
    ];
	
	that.vm.dtColumns[0].visible = false;
	
	that.currentPage = null;

	that.vm.dtOptions = DTOptionsBuilder
        .newOptions()
        .withFnServerData(serverData)
        .withBootstrap()
        .withOption('bFilter', false)
        .withOption('processing', true)
        .withOption('serverSide', true)
        .withOption('order', [0, 'desc'])
        .withOption('paging', true)
        .withOption('createdRow', function (row, data, dataIndex) {
            $compile(angular.element(row).contents())($scope);
        })
        .withDisplayLength(10);

	function serverData (sSource, aoData, fnCallback, oSettings) {

        var draw = aoData[0].value;
        var columns = aoData[1].value;
        var order = aoData[2].value;
        var start = aoData[3].value;
        var length = aoData[4].value;

        if (order && order.length > 0) {
        	var sortColumnIndex = order[0].column,
        	    sortColumnName = columns[sortColumnIndex].data,
        	    sortDirection = order[0].dir,
        	    sort = sortColumnName + ',' + sortDirection;
        }

		var pageRequest = {
			page: start / length,
     		size: length,
            sort: sort
		};
		
		companyService.findAll(pageRequest).then(function(response) {
			that.currentPage = response.data.page;
			that.companies = response.data._embedded.companies;
			
			var records = {
	                draw: draw,
	                recordsTotal: that.currentPage.totalElements,
	                recordsFiltered: that.currentPage.totalElements,
	                data: that.companies
	            };
	            fnCallback(records);
			
		}, function(errResponse) {
		});		
	};	

} ]);

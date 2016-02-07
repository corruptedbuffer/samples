'use strict';

var app = angular.module('app', [ 'datatables', 'datatables.bootstrap', 'ngNotificationsBar', 'ngSanitize', 'ngDialog' ]);

app.config([ 'notificationsConfigProvider', function(notificationsConfigProvider) {

	notificationsConfigProvider.setAutoHide(true);
	notificationsConfigProvider.setHideDelay(1000);
	notificationsConfigProvider.setAcceptHTML(true);
} ]);
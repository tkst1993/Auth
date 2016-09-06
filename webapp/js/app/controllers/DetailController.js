'use strict';
app.controller('DetailCtrl', ['$scope','$http','$location','$timeout','dataService','$stateParams',function($scope,$http,$location,$timeout, dataService, $stateParams) {
	console.log('DetailCtrl start');
	App.init();
	Login.init();
	$("#detailPage").css("background-color","#3D3D3D");
	
	
	
	
}]);



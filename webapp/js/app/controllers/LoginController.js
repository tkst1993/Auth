'use strict';
app.controller('loginCtrl', ['$scope','$http','$location','$timeout','dataService','$stateParams',function($scope,$http,$location,$timeout, dataService, $stateParams) {
	console.log('loginControl start');
	App.init();
	Login.init();
	
	$scope.loginSubmit = function(){
		var username = $scope.username;
		var pwd = $scope.pwd;
		if(!$scope.username || !$scope.pwd ){ return; }
		
		
		//$location.path('detail.html');
		
		//进行ajax请求
		/*$http.post('#/app/detail').success(function(data, status, headers, config) {
			//return $scope.results = data;
			//console.log('ajax test!');
			$location.path('/app/detail');
		});*/
		
		$http.post('/login').success(function(data, status, headers, config) {
			
		});
	}
	
	
	
}]);



'use strict';

/* Controllers */
MetronicApp.controller('dataList', ['$scope', '$http','$location', function($scope, $http,$location) {
	  
	  $scope.queryData = function(num){
		  $scope.reply=null;
		  $scope.path=$location.protocol()+'://'+$location.host()+':'+$location.port()+'/query/queryData/';
		  if(isNaN(num)){
			  num=1;
		  }
		  var sql = $scope.searchSQL;
		  if(sql==undefined){
			  sql=null;
		  }
		  var savePath = $scope.searchSavePath;
		  if(savePath==undefined){
			  savePath="";
		  }
		  var startDate = $scope.startDate;
		  if(startDate==undefined){
			  startDate="";
		  }
		  var endDate = $scope.endDate;
		  if(endDate==undefined){
			  endDate="";
		  }
		  var url = 'query/queryData.do',
          data = {
				  'pageSize': num,
				  'savePath': savePath,
				  'startDate': startDate,
				  'endDate': endDate,
				  'sql': sql
          },
          transFn = function(data) {
              return $.param(data);
          },
          postCfg = {
              headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
              transformRequest: transFn
          };

		  
		  $http.post(url, data, postCfg).success(function(data){
			  $scope.items=data.recordList;
			  $scope.columns=data.columns;
			  $scope.currentPage=data.currentPage;
			  $scope.recordCount=data.recordCount;
			  $scope.pageCount=data.pageCount;
			  $scope.beginPageIndex=data.beginPageIndex; 
			  $scope.endPageIndex=data.endPageIndex;
			  $scope.reply=data.reply;
			  $scope.sequence=(data.currentPage-1)*data.pageSize+1
			  var size=1;
			  if(data.endPageIndex -data.beginPageIndex>=10 ){
				  size=10;
			  }else{
				  size=data.endPageIndex -data.beginPageIndex+1;
				  
			  }
			  
			  var colum=new Array(size);
//			  $index=data.beginPageIndex;
			  $scope.colums = colum;
		  });
	  };
	
	  
	
	  
}]);


MetronicApp.controller('dataList2',function ($scope,$http,$location) {
	  
	  $scope.queryData = function(num){
		  $scope.reply=null;
		  $scope.path=$location.protocol()+'://'+$location.host()+':'+$location.port()+'/query/queryDataByPath/';
		  if(isNaN(num)){
			  num=1;
		  }
		  var searchPath = $scope.searchPath;
		  if(searchPath==undefined){
			  searchPath=null;
		  }
		  var savePath = $scope.searchSavePath;
		  if(savePath==undefined){
			  savePath="";
		  }
		  var url = 'query/queryDataByPath.do',
		  data = {
				  'pageSize': num,
				  'savePath': savePath,
				  'searchPath': searchPath
		  },
		  transFn = function(data) {
			  return $.param(data);
		  },
		  postCfg = {
				  headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
				  transformRequest: transFn
		  };
		  
		  
		  $http.post(url, data, postCfg).success(function(data){
			  $scope.items=data.recordList;
			  $scope.columns=data.columns;
			  $scope.currentPage=data.currentPage;
			  $scope.recordCount=data.recordCount;
			  $scope.pageCount=data.pageCount;
			  $scope.beginPageIndex=data.beginPageIndex; 
			  $scope.endPageIndex=data.endPageIndex;
			  $scope.reply=data.reply;
			  $scope.sequence=(data.currentPage-1)*data.pageSize+1
			  var size=1;
			  if(data.endPageIndex -data.beginPageIndex>=10 ){
				  size=10;
			  }else{
				  size=data.endPageIndex -data.beginPageIndex+1;
				  
			  }
			  
			  var colum=new Array(size);
//			  $index=data.beginPageIndex;
			  $scope.colums = colum;
		  });
	  };
	  
	  
	  
	  
  });

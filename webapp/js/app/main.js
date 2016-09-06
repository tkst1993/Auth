'use strict';
angular.module('app').controller('AppCtrl',['$scope','$window',
    function($scope,$translate,$localStorage,$window){
        $scope.app = {
            name:"权限管理系统",
            settings: {
                //navbarHeaderColor: 'bg-black',
                //navbarCollapseColor: 'bg-white-only',
                //asideColor: 'bg-black',
                headerFixed: true,
                asideFixed: true,
                asideFolded: false,
                asideDock: false,
                container: false
            }
        };
        $scope.ipt="cxb";
    }]);
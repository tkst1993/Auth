'use strict';
angular.module('app').run([
    '$rootScope','$state','$stateParams',
    function ($rootScope,$state,$stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
        $rootScope.$on("$routeChangeStart",function(){
            console.log(arguments);
            console.log("$routeChangeStart");
        });
        $rootScope.$on("$routeChangeSuccess",function(){
            console.log(arguments);
            console.log("$routeChangeSuccess");
        });
        $rootScope.$on("$routeChangeError",function(){
            console.log(arguments);
            console.log("$routeChangeError");
        });
    }
]).config(['$stateProvider','$urlRouterProvider',
    function ($stateProvider,$urlRouterProvider){
        $urlRouterProvider.otherwise('/app/login');
        $stateProvider.state('app', {
            abstract: true,
            url: '/app',
            templateUrl: 'pages/app.html'
        }).state("app.login",{
            url:"/login",
            templateUrl:"login.html",
            //params:{"jobid":null},
            resolve: {
                deps: ['uiLoad',
                    function(uiLoad){
                        var d = new Date();
                        return uiLoad.load([
                        	'media/css/login.css',
                            'media/css/bootstrap.min.css',
                            'media/css/bootstrap-responsive.min.css',                            
                            'media/css/font-awesome.min.css',
                            'media/css/style-metro.css',
                            'media/css/style.css',               
                            'media/css/style-responsive.css',
                            'media/css/default.css',
                            'media/css/uniform.default.css',
                            'media/js/jquery-1.10.1.min.js',
                            'media/js/jquery-migrate-1.2.1.min.js',
                            'media/js/jquery-ui-1.10.1.custom.min.js',
                            'media/js/jquery.slimscroll.min.js',
                            'media/js/jquery.blockui.min.js',
                            'media/js/jquery.cookie.min.js',
                            'media/js/jquery.uniform.min.js',
                            'media/js/jquery.validate.min.js',
                            'media/js/bootstrap.min.js',                            
                            'media/js/app.js',
                            'media/js/login.js',
                            'js/app/controllers/LoginController.js' + "?version=" + d.getTime()]);
                    }]
            }
        }).state("app.detail",{
            url:"/detail",
            templateUrl:"detail_t.html",
            //params:{"jobid":null},
            resolve: {
                deps: ['uiLoad',
                    function(uiLoad){
                        var d = new Date();
                        return uiLoad.load([
                        	
                            /*'media/css/bootstrap.min.css',
                            'media/css/bootstrap-responsive.min.css',                            
                            'media/css/font-awesome.min.css',
                            'media/css/style-metro.css',
                            'media/css/style.css',               
                            'media/css/style-responsive.css',
                            'media/css/default.css',
                            'media/css/uniform.default.css',
                            
                            'media/css/daterangepicker.css',
                            'media/css/fullcalendar.css',
                            'media/css/jqvmap.css',
                            'media/css/jquery.easy-pie-chart.css',
                            
                            'media/js/jquery-1.10.1.min.js',
                            'media/js/jquery-migrate-1.2.1.min.js',
                            'media/js/jquery-ui-1.10.1.custom.min.js',
                            'media/js/jquery.slimscroll.min.js',
                            'media/js/jquery.blockui.min.js',
                            'media/js/jquery.cookie.min.js',
                            'media/js/jquery.uniform.min.js',
                            
                            
                            'media/js/jquery.vmap.js',
                            'media/js/jquery.vmap.russia.js',
                            'media/js/jquery.vmap.world.js',
                            'media/js/jquery.vmap.europe.js',
                            'media/js/jquery.vmap.germany.js',
                            'media/js/jquery.vmap.usa.js',
                            'media/js/jquery.vmap.sampledata.js',
                            'media/js/jquery.flot.js',
                            'media/js/jquery.flot.resize.js',
                            'media/js/jquery.pulsate.min.js',
                            'media/js/date.js',
                            'media/js/daterangepicker.js',
                            'media/js/jquery.gritter.js',
                            'media/js/fullcalendar.min.js',
                            'media/js/jquery.easy-pie-chart.js',
                            'media/js/jquery.sparkline.min.js',
                            
                            'media/js/bootstrap.min.js', 
                            
                            
                            'media/js/app.js',
                            'media/js/index.js',
                            'media/css/default.css',*/
                            'js/app/controllers/DetailController.js' + "?version=" + d.getTime()
                            ]);
                    }]
            }
        });
    }
]);
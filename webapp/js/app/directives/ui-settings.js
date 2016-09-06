angular.module('ui.settings',["data.service"])
    .directive('uiSettings', ['$timeout','dataService','$window', '$location','$state',function($timeout,dataService,$window,$location,$state) {
        return {
            restrict:"A",
            link:function(scope, element, attr) {
                var indArr = [
                    '<span class="badge badge-empty">',
                    '<span class="badge bg-info">',
                    '<span class="badge bg-success">',
                    '<span class="badge bg-dark">',
                    '<span class="badge bg-warning">',
                    '<span class="badge bg-danger">'
                ];
                var jobList=null;
            	$.ajax({
            		type:"post",
            		url:"",
            		dataType: 'json',
            		success:function(data){
            			console.log(data)
            			if (!data){
            				if (data.length > 0){
            	                var indArrLen = data.length;
            	                jobList = data;
            	                var arr = new Array();
            	                var num = 0; 
            	                for (var i = 0;i < jobList.length;i++){
            	                    if (num == 6){
            	                        num = 0;
            	                    }
            	                    var job = jobList[i];
            	                    arr.push('<a ui-sref="app.settings" ind=' + i + ' class="list-group-item">');
            	                    arr.push(indArr[num]);
            	                    num++;
            	                    arr.push(i + 1);
            	                    arr.push('</span>');
            	                    arr.push(job.jobnm + ",");
            	                    arr.push(job.datastartday + "至" + job.dataendday + ",");
            	                    var tNum = job.resultcnt;
            	                    arr.push("新增" + tNum + "条数据 </a>");
            	                }
            	                element.html(arr.join(""));
            				}
            			}
            		},
            		error:function(data){
            		}
            	})  
                element.on("click","a",function(evt){
                    var arrInd = parseInt(angular.element(this).attr("ind"));
                    dataService.setCacheData("jobParams",jobList[arrInd]);
                    console.log(jobList[arrInd]);
                    //console.log(angular.element(this).attr("ui-sref"));
                    //$window.location.hash = angular.element(this).attr("ui-sref") + "/" + jobList[arrInd].id;
                    $state.go(angular.element(this).attr("ui-sref"),{jobid:jobList[arrInd].jobid});
                    //alert(jobList[arrInd].id);
                });
                angular.element("#settingsBtn").on("click",function(evt){
                    var indArr = [
                                  '<span class="badge badge-empty">',
                                  '<span class="badge bg-info">',
                                  '<span class="badge bg-success">',
                                  '<span class="badge bg-dark">',
                                  '<span class="badge bg-warning">',
                                  '<span class="badge bg-danger">'
                              ];                	
                	$.ajax({
                		type:"post",
                		url:"",
                		dataType: 'json',
                		success:function(data){
                			console.log(data)
                			if (!data){
                				if (data.length > 0){
                	                var indArrLen = data.length;
                	                var jobList = data;
                	                var arr = new Array();
                	                var num = 0; 
                	                for (var i = 0;i < jobList.length;i++){
                	                    if (num == 6){
                	                        num = 0;
                	                    }
                	                    var job = jobList[i];
                	                    arr.push('<a ui-sref="app.settings" ind=' + i + ' class="list-group-item">');
                	                    arr.push(indArr[num]);
                	                    num++;
                	                    arr.push(i + 1);
                	                    arr.push('</span>');
                	                    arr.push(job.jobnm + ",");
                	                    arr.push(job.datastartday + "至" + job.dataendday + ",");
                	                    var tNum = job.resultcnt;
                	                    arr.push("新增" + tNum + "条数据 </a>");
                	                }
                	                element.html(arr.join(""));
                				}
                			}
                		},
                		error:function(data){
                		}
                	})               
                });
            }
        }
    }]);
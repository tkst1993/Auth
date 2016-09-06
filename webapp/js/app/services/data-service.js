angular.module("data.service", [])
	.factory("dataService", function($http ,$window) {
		var modelData = {
				"aaData": [{
					"id":"1",
					"modelName": "模型一",
					"creator": "张三",
					"createTime": "2015-07-09 09:10:30",
					"count": "1",
					"remark": "X",
					"status":"0",
					"gs":"(组件1∩组件2)∪(组件2∩组件3)"
				}, {
					"id":"2",
					"modelName": "模型二",
					"creator": "李四 ",
					"createTime": "2015-09-09 09:09:09",
					"count": "1",
					"remark": "C",
					"status":"1",
					"gs":"(组件1∩组件3)∪(组件1∩组件2)"
				}, {
					"id":"3",
					"modelName": "模型三",
					"creator": "王五 ",
					"createTime": "2015-09-09 09:09:09",
					"count": "1",
					"remark": "A",
					"status":"1",
					"gs":"(组件1∩sss)∪(sss∩组件3)"
				}]
			},
			componentData = {
				"aaData": [{
					"id":"1",
					"componentName": "组件1",
					"creator": "张三",
					"createTime": "2015-09-09 09:09:09",
					"count": "3",
					"desc": "X",
					"base":"(年龄1∩年龄2)∪(男∩湖北)",
					"action":"(紧接电话∩紧拨电话)"
				}, {
					"id":"2",
					"componentName": "组件2",
					"creator": "张三 ",
					"createTime": "2015-09-09 09:09:09",
					"count": "2",
					"desc": "C",
					"base":"(年龄1∩年龄2)∪(女∩湖南)",
					"action":"(上网专卡∩紧拨电话)"
				}, {
					"id":"3",
					"componentName": "组件3",
					"creator": "张三 ",
					"createTime": "2015-09-09 09:09:09",
					"count": "3",
					"desc": "A",
					"base":"(有∩年龄2)∪(女∩湖南)",
					"action":"(上网专卡∩频繁开关机)"
				}]
			},
			jobData = {
                "aaData":[{
                    "id":"1001",
                    "jobNm":"任务一",
                    "modelNm":"模型一",
                    "modelId":"1",
                    "startTm":"2016-04-01 12:12:12",
                    "endTm":"2016-04-05 12:12:12",
                    "status":"0",
                    "period":"4天",
                    "result":"2条"
                },{
                    "id":"1002",
                    "jobNm":"任务二",
                    "modelNm":"模型二",
                    "modelId":"2",
                    "startTm":"2016-04-01 12:12:12",
                    "endTm":"2016-04-05 12:12:12",
                    "status":"1",
                    "period":"4天",
                    "result":"3条"
                },{
                    "id":"1003",
                    "jobNm":"任务三",
                    "modelNm":"模型三",
                    "modelId":"3",
                    "startTm":"2016-04-01 12:12:12",
                    "endTm":"2016-04-05 12:12:12",
                    "status":"2",
                    "period":"4天",
                    "result":"1条"
                }]
            };
        var cacheData = {};
        var phoneData = {"aaData":[
                {
                    "id":"101",
                    "phonenum":"13511111111",
                    "IMSI":"4601011210312",
                    "IMEI":"73287493218",
                    "address":"广东河池",
                    "name":"张三",
                    "idcard":"420102198303302117",
                    "idcardarea":"乌鲁木齐",
                    "sex":"男"
                },
                {
                    "id":"102",
                    "phonenum":"13611111111",
                    "IMSI":"4601011210312",
                    "IMEI":"73287493218",
                    "address":"湖北武汉",
                    "name":"李四",
                    "idcard":"420102198303302117",
                    "idcardarea":"湖北武汉",
                    "sex":"男"
                },
                {
                    "id":"103",
                    "phonenum":"13911111111",
                    "IMSI":"4601011210312",
                    "IMEI":"73287493218",
                    "address":"湖北武汉",
                    "name":"小小",
                    "idcard":"420102198303302117",
                    "idcardarea":"湖北武汉",
                    "sex":"女"
                }
        ]};
		$window.localStorage.removeItem("modelData");
		$window.localStorage.removeItem("componentData");
		$window.localStorage.removeItem("jobData");
		$window.localStorage.removeItem("phoneData");
		$window.localStorage.removeItem("str");
		if (!$window.localStorage.getItem("modelData")){
            $window.localStorage.setItem("modelData",JSON.stringify(modelData));
        }else{
            modelData = JSON.parse($window.localStorage.getItem("modelData"));
        }
        if (!$window.localStorage.getItem("componentData")){
            //alert("1")
            $window.localStorage.setItem("componentData",JSON.stringify(componentData));
        }else{
            //alert("2")
            componentData = JSON.parse($window.localStorage.getItem("componentData"));
        }
        if (!$window.localStorage.getItem("jobData")){
            $window.localStorage.setItem("jobData",JSON.stringify(jobData));
        }else{
            jobData = JSON.parse($window.localStorage.getItem("jobData"));
        }
        if (!$window.localStorage.getItem("phoneData")){
            $window.localStorage.setItem("phoneData",JSON.stringify(phoneData));
        }else{
            phoneData = JSON.parse($window.localStorage.getItem("phoneData"));
        }
		return {
			setPhoneData:function(phone){
                phoneData.aaData.push(phone);
                $window.localStorage.setItem("phoneData",JSON.stringify(phoneData));
            },
            getPhoneData:function(){
                return phoneData;
            },
            setCacheData:function(key,data){
                cacheData[key] = data;
            },
            getCacheData:function(key){
                return cacheData[key];
            },
			getModelData: function() {
				return modelData;
			},
			setModelData: function(model) {
				modelData.aaData.push(model);
				$window.localStorage.setItem("modelData",JSON.stringify(modelData));
			},
			setComponentData: function(component) {
				componentData.aaData.push(component);
				$window.localStorage.setItem("componentData",JSON.stringify(componentData));
                console.log(JSON.parse($window.localStorage.getItem("componentData")));
			},
			getComponentData: function() {
				return componentData;
			},
            setJobData:function(job){
                jobData.aaData.push(job);
                $window.localStorage.setItem("jobData",JSON.stringify(jobData));
            },
            getJobData:function(){
                return jobData;
            },
            updateJobData:function(job){
              for (var i = 0;i < jobData.aaData.length;i++){
                  var tmpJob = jobData.aaData[i];
                  if (tmpJob.id == job.id){
                      jobData.aaData[i] = job;
                      break;
                  }
              }
              $window.localStorage.setItem("jobData",JSON.stringify(jobData));
            },
            deleteJobData:function(idArr){
                var newJobData = new Array();
                for (var i = 0;i < jobData.aaData.length;i++){
                    var job = jobData.aaData[i],flag = true;
                    for (var j = 0;j < idArr.length;j++){
                        if (job.id == idArr[j]){
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        newJobData.push(job);
                    }else{
                        flag = true;
                    }
                }
                jobData.aaData = newJobData;
                $window.localStorage.setItem("jobData",JSON.stringify(jobData));
            },			
			updateModel:function(model){
				for (var i = 0; i < modelData.aaData.length; i++) {
					var data = modelData.aaData[i];
					if (data.id == model.id) {
						data.modelName=model.modelName;
						data.creator=model.creator;
						data.createTime=model.createTime;
						data.count=model.count;
						data.desc=model.desc;
						data.gs=model.gs;
						break;
					}
				}
				$window.localStorage.setItem("modelData",JSON.stringify(modelData));
			},
			updateComponent:function(component){
				for (var i = 0; i < componentData.aaData.length; i++) {
					var data = componentData.aaData[i];
					if (data.id == component.id) {
						data.componentName=component.componentName;
						data.creator=component.creator;
						data.createTime=component.createTime;
						data.count=component.count;
						data.desc=component.desc;
						data.base=component.base;
						data.action=component.action;
						break;
					}
				}
				$window.localStorage.setItem("componentData",JSON.stringify(componentData));
			},
			deleteModel: function(engineArr) {
				var newModelData = new Array();
				for (var i = 0; i < modelData.aaData.length; i++) {
					var model = modelData.aaData[i],
						flag = true;
					for (var j = 0; j < engineArr.length; j++) {
						if (model.id == engineArr[j]) {
							flag = false;
							break;
						}
					}
					if (flag) {
						newModelData.push(model);
					} else {
						flag = true;
					}
				}
				modelData.aaData = newModelData;
				$window.localStorage.setItem("modelData",JSON.stringify(modelData));
			},
			deleteComponent: function(engineArr) {
				var newComponentData = new Array();
				for (var i = 0; i < componentData.aaData.length; i++) {
					var component = componentData.aaData[i],
						flag = true;
					for (var j = 0; j < engineArr.length; j++) {
						if (component.id == engineArr[j]) {
							flag = false;
							break;
						}
					}
					if (flag) {
						newComponentData.push(component);
					} else {
						flag = true;
					}
				}
				componentData.aaData = newComponentData;
				$window.localStorage.setItem("componentData",JSON.stringify(componentData));
			},
			getUniqueKey:function(len){
    			len = len || 8;
    			var $char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrst";
    			var maxPos = $char.length; 
    			var pwd = "";
    			for (var i = 0;i < len;i++){
        			pwd += $char.charAt(Math.floor(Math.random() * maxPos));
    			}
    			return pwd + "-" + new Date().getTime();
			}

			

		}
	});
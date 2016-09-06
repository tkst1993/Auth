angular.module('app')
    .directive('uiDatatable', function($timeout, $parse,$location) {
        return {
            compile: function(scope, element, attr) {
                console.log(element);
                console.log(attr);
                element.dataTable({
                    sAjaxSource: 'js/jquery/datatables/managetable.json',
                    aoColumns: [
                        { mData: function(){
                            return '<label class="i-checks m-b-none"><input type="checkbox"><i></i></label>'
                        }},
                        {mData: function(row){
                            var num = arguments[3].row + 1;
                            return num;
                        }},
                        { mData: 'Name'},
                        { mData: 'modelName' },
                        { mData: 'startTime' },
                        { mData: 'endTime'},
                        { mData: 'status' },
                        {mData:function(row){
                            var arrBtn = new Array();
                            arrBtn.push('<span class="label bg-primary label-btn" name="start" data="' +  row.Name + '">执行</span>');
                            arrBtn.push('<span class="label bg-success label-btn" name="end" data="' +  row.Name + '">终止</span>');
                            arrBtn.push('<span class="label bg-danger label-btn" name="del" data="' +  row.Name + '">删除</span>');
                            return arrBtn.join("");
                        }},
                        { mData: function(row){
                            return '<a name="linkResult" ng-click="ftn()" class="table-link-a table-linka-red" data="' +  row.result + '">' + row.result + '</a>';
                        }}
                    ],
                    "sPaginationType": "bootstrap",
                    "bFilter":true,
                    "bSort": false,
                    "bGoBtn":true,
                    "oLanguage": {
                        "sLengthMenu": "每页 _MENU_ 条记录",
                        "sSearch":"快速查找：",
                        "sZeroRecords":"没有检索到数据",
                        "sProcessing":"正在加载数据中...",
                        "sInfoEmpty":"没有数据",
                        "sInfoFiltered":"{筛选自 _MAX_ 条数据}",
                        "sInfo":"显示 _START_ 至  _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条&nbsp;&nbsp;总共_PAGES_页",
                        "oPaginate":{
                            "sFirst":"首页",
                            "sPrevious":"前一页",
                            "sNext":"后一页",
                            "sLast":"末页"
                        }
                    }
                });
                function ftn(){
                    alert(111);
                    console.log($location.url("/app/dataTable-v4"));
                }
                /*element.on("click","a[name='linkResult']",function(evt){
                    //console.log($location.path("/app/dataTable-v4").replace());
                    //$location.state("app.dataTable-v4");
                    console.log($location.url("/app/dataTable-v4"));
                    //console.log($location);
                    //$window.location.hash = "/app/dataTable-v4";
                });*/
            }
        };
    });

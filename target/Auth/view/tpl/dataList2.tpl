 <!-- BEGIN PAGE TITLE-->
<h3 class="page-title">
    Spark <small>tools</small>
</h3>
<!-- END PAGE TITLE-->
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="icon-home"></i><a href="#/dataList">Home</a><i class="fa fa-angle-right"></i></li>
        <li><span>根据路径获取</span></li>
    </ul>
</div>
<!-- END PAGE BAR -->
<div ng-controller="dataList2" ></div>
<div ng-show="reply!=null"id="myAlert" class="alert alert-success">
   <a href="#" class="close" data-dismiss="alert">&times;</a>
   <strong>{{reply}}</strong>。
</div>


<div class="portlet-body form">
<form class="well form-search form-horizontal form-bordered" user="searchform">
<div class=form-body>
    <div class="form-group">
        <label class="control-label col-md-2">目标在HDFS的存储路径：</label>
        <div class="col-md-3">
          <textarea ng-model="searchPath" class="form-control" placeholder="目标在HDFS的存储路径,支持同协议以逗号分隔,例如/structdata/bcpdata3_/HDFS003/RJZ_RESOURCE_0002/20160715/RJZ_RESOURCE_0002-7-1468546555539.parquet,/structdata/bcpdata3_/HDFS003/RJZ_RESOURCE_0002/20160712/RJZ_RESOURCE_0002-7-1468304312988.parquet " style="width:250%;" rows="5"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-2">存储路径</label>
        <div class="col-md-3">
            <input type="text" ng-model="searchSavePath" class="form-control" style="width:200%;" placeholder="结果在HDFS的存储路径,例如/xx001/RWA_SOURCE_0002">
        </div>
    </div>
    <div class="pagination">
      <button type="submit" class="btn red btn-outline" ng-click="queryData(1)"><i class="fa fa-check"></i>搜索</button>
    </div>
</div>
</form>
</div>


<table class="table table-hover table-bordered" style="background:#F7F7F7;">
   <caption><h4>查询结果</h4></caption>
   <thead>
      <tr >
         <th>序号</th>
         <th ng-repeat="cs in columns">{{cs}}</th>
      </tr>
   </thead>
   <tbody>
      <tr  ng-repeat="item in items">
         <td>{{sequence+$index}}</td>
         <td ng-repeat="it in item track by $index">{{it}}</td>
      </tr>
     
   </tbody>
</table>

<div >
<nav style="text-align: center">
  </nav>
   
</div>

<script type="text/javascript">
</script>



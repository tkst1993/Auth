 <!-- BEGIN PAGE TITLE-->
<h3 class="page-title">
    Spark <small>tools</small>
</h3>
<!-- END PAGE TITLE-->
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li><i class="icon-home"></i><a href="#/dataList">Home</a><i class="fa fa-angle-right"></i></li>
        <li><span>SparkSQL</span></li>
    </ul>
</div>
<!-- END PAGE BAR -->

<div ng-controller="dataList" ></div>
<div ng-show="reply!=null"id="myAlert" class="alert alert-success">
   <a href="#" class="close" data-dismiss="alert">&times;</a>
   <strong>{{reply}}</strong>。
</div>
<div class="portlet-body form">
<form class="well form-search form-horizontal form-bordered" user="searchform">
<div >
    <div class="form-group">
		<label class="control-label col-md-2">查询SQL</label>
		<div class="col-md-3">
		  <textarea ng-model="searchSQL" class="form-control" placeholder="仅支持单表查询,例如SELECT * FROM RWA_SOURCE_0002 WHERE 1=1 " style="width:250%;" rows="5"></textarea>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2">存储路径</label>
		<div class="col-md-3">
		    <input type="text" ng-model="searchSavePath" class="form-control" style="width:200%;" placeholder="结果在HDFS的存储路径,例如/xx001/RWA_SOURCE_0002">
		</div>
	</div>
	<div class="form-group">
        <label class="control-label col-md-2">查询日期</label>
        <div class="col-md-3">
            <div class="input-group input-large date-picker input-daterange" data-date="20160727" data-date-format="yyyymmdd">
                <input type="text" class="form-control" name="from" placeholder="查询开始日期" ng-model="startDate">
                <span class="input-group-addon"> to </span>
                <input type="text" class="form-control" name="to" placeholder="查询结束日期" ng-model="endDate"> </div>
            <!-- /input-group -->
            <span class="help-block"> 选择日期区间 </span>
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
ComponentsDateTimePickers.init(); // init todo page
</script>



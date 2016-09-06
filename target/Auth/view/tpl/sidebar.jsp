<div class="page-sidebar navbar-collapse collapse">
    <!-- BEGIN SIDEBAR MENU -->
    <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
    <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
    <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
    <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" ng-class="{'page-sidebar-menu-closed': settings.layout.pageSidebarClosed}">
        <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
        <li class="sidebar-search-wrapper">
            <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
            <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
            <!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
            <form class="sidebar-search sidebar-search-bordered" action="#" method="POST">
            </form>
            <!-- END RESPONSIVE QUICK SEARCH FORM -->
        </li>
        <li class="nav-item start active open">
            <a href="javascript:;" class="nav-link nav-toggle">
                <i class="icon-home"></i>
                <span class="title">导航</span>
                <span class="selected"></span>
                <span class="arrow open"></span>
            </a>
            
            
            <ul class="sub-menu">
                <li class="nav-item start ">
                    <a href="#/dataList" class="nav-link ">
                        <i class="icon-list"></i>
                        <span class="title">商品管理</span>
                        <span class="selected"></span>
                    </a>
                </li>
                <li class="nav-item start ">
                    <a href="#/dataList2" class="nav-link ">
                        <i class="icon-list"></i>
                        <span class="title">品牌管理</span>
                        <span class="badge badge-success"></span>
                    </a>
                </li>
                <li class="nav-item start ">
                    <a href="#/dataList3" class="nav-link ">
                        <i class="icon-list"></i>
                        <span class="title">分类管理</span>
                        <span class="badge badge-success"></span>
                    </a>
                </li>
                <li class="nav-item start ">
                    <a href="#/dataList4" class="nav-link ">
                        <i class="icon-list"></i>
                        <span class="title">商品维护</span>
                        <span class="badge badge-success"></span>
                    </a>
                </li>
            </ul>
        </li>
        
    </ul>
    <!-- END SIDEBAR MENU -->
</div>
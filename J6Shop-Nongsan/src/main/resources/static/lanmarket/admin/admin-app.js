app =angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider){
    $routeProvider
    .when("/product",{
        templateUrl:"/lanmarket/admin/product/table.html",
        controller: "product-ctrl"
    })
    .when("/report",{
        templateUrl:"/lanmarket/admin/report/report.html",
        controller: "report-ctrl"
    })
    .when("/order",{
        templateUrl:"/lanmarket/admin/order/ordertable.html",
        controller: "order-ctrl"
    })
    .when("/authorize",{
        templateUrl:"/admin/admin/authorize/table.html",
        controller: "authority-ctrl"
    })
    .when("/unauthorize",{
        templateUrl:"/admin/admin/unauthorized/table.html",
        controller: "authority-ctrl"
    })
    .when("/category",{
        templateUrl:"/lanmarket/admin/category/categorytable.html",
        controller: "category-ctrl"
    })
    .when("/account",{
        templateUrl:"/lanmarket/admin/account/accounttable.html",
        controller: "account-ctrl"
    })
    .otherwise({
        templateUrl:"/lanmarket/admin/report/report.html",
        // controller: "report-ctrl"
    });
    
})

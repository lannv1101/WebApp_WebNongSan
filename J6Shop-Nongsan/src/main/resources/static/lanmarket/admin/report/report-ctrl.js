app.controller("report-ctrl", function($scope,$http){

    
    $scope.items=[];
    $scope.items.length;
    $scope.sums;
    $scope.countorderdetails;
  

    $scope.initialize= function(){
        //load product
        $http.get('/lanmarket/api/orders/order0').then(resp =>{
            $scope.items = resp.data.length;

          
        });
        $http.get('/lanmarket/api/orders/sum').then(resp =>{
            $scope.sums = resp.data;

          
        });
        $http.get('/lanmarket/api/orderdetails/count').then(resp =>{
            $scope.countorderdetails = resp.data;

          
        });
      
      
        
      
      
    }


    $scope.initialize();

   
    
});
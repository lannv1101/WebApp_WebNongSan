app.controller("category-ctrl", function($scope,$http){

    $scope.items=[];
    $scope.form={};
    $scope.cates=[];
    $scope.initialize= function(){
        //load product
        $http.get("/lanmarket/api/categories").then(resp =>{
            $scope.items = resp.data;
           
        });
        // //load categories
        // $http.get("/lanmarket/api/categories").then(resp =>{
        //     $scope.cates = resp.data;
           
        // });
      
    }
    $scope.findbyname = function(){
        var item = $("#search").text();
        $http.get(`/lanmarket/api/categories/${item}`).then(resp =>{
            $scope.items = resp.data;
           
        });
    }
     //khoi dau
     $scope.initialize();
    
    $scope.edit =function(item){
        $scope.form = angular.copy(item);
        // $('html, body').animate({
        //     scrollTop: $("#card-form").offset().top
        // }, 500);

    }

    $scope.create =function(){
      var item =angular.copy($scope.form);
      $http.post('/lanmarket/api/categories', item).then(resp =>{
       
          $scope.items.push(resp.data);
          $scope.reset();
          $scope.initialize();

        
          alert("Add category successfully");
      }).catch(error =>{
        alert("Add category fail");
        console.error("Error",error);
      });
        
    }
    $scope.reset =function(){
        $scope.form={
           
        };
        
    }
    $scope.update =function(){
        var item =angular.copy($scope.form);
      $http.put(`/lanmarket/api/categories/${item.id}`, item).then(resp =>{
        var index =$scope.items.findIndex(p =>p.id ==item.id);
       
                $scope.items[index] = item;
          $scope.reset();
          $scope.initialize();
          alert("Update category successfully");
      }).catch(error =>{
        alert("Update category fail");
        console.error("Error",error);
      });
        
    }
    $scope.delete =function(item){
        $http.delete(`/lanmarket/api/categories/${item.id}`).then(resp =>{
          var index =$scope.items.findIndex(p =>p.id ==item.id);
         
                  $scope.items.splice(index,1) //delete tai index 1 phan tu
            $scope.reset();
            // $scope.initialize();
            alert("Delete category successfully");
        }).catch(error =>{
          alert("Delete category fail");
          console.error("Error",error);
        });
    }
 
    
    $scope.pager={
      
        page:0,
        size: $("#dataTable_length").find(":selected").val(), // $("#dataTable_length").val()
       
        get items(){
            var start = this.page*this.size;
            return $scope.items.slice(start,start+this.size);
           
        },
        get count(){
            return Math.ceil(1.0*$scope.items.length/this.size);
        },
        get countitems(){
            return Math.ceil(1.0*$scope.items.length);
        },
        first(){
            this.page=0;
        },
        prev(){
            this.page--;
            if(this.page<0){
                this.last();
            }

        },
        next(){
            this.page++;
            if(this.page >=this.count){
                this.first();
            }
        },
        last(){
            this.page = this.count-1
        },
        showitems(){
            this.size=$("#dataTable_length").find(":selected").val();
        },
        get countshowitems(){
            return Math.ceil(1.0*this.size);
        }

      
    }
    $scope.initialize();

    $scope.reset();
   
    
});
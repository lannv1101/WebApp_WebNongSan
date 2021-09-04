app.controller("product-ctrl", function($scope,$http){

    $scope.items=[];
    $scope.form={};
    $scope.cates=[];
    $scope.initialize= function(){
        //load product
        $http.get("/lanmarket/api/product").then(resp =>{
            $scope.items = resp.data;
            $scope.items.forEach(item =>{
                item.createDate = new Date(item.createDate)
            })
        });
        //load categories
        $http.get("/lanmarket/api/categories").then(resp =>{
            $scope.cates = resp.data;
           
        });
      
    }
    $scope.findbynamelike= function(){
        var name = $("#name").text();

        $http.get(`/lanmarket/api/product/${name}`).then(resp =>{
            $scope.items = resp.data;
           
        });
    }
     //khoi dau
     $scope.initialize();
    
    $scope.edit =function(item){
        $scope.form = angular.copy(item);
        $('html, body').animate({
            scrollTop: $("#card-form").offset().top
        }, 500);

    }

    $scope.create =function(){
      var item =angular.copy($scope.form);
      $http.post('/lanmarket/api/product', item).then(resp =>{
          resp.data.createDate = new Date(resp.data.createDate)
          $scope.items.push(resp.data);
          $scope.reset();
          $scope.initialize();
          alert("Add product successfully");
      }).catch(error =>{
        alert("Add product fail");
        console.error("Error",error);
      });
        
    }
    $scope.reset =function(){
        $scope.form={
            createDate:new Date(),
            image:'cloud-upload.jpg',
            available: true,
        };
        
    }
    $scope.update =function(){
        var item =angular.copy($scope.form);
      $http.put(`/lanmarket/api/product/${item.id}`, item).then(resp =>{
        var index =$scope.items.findIndex(p =>p.id ==item.id);
       
                $scope.items[index] = item;
          $scope.reset();
          $scope.initialize();
          alert("Update product successfully");
      }).catch(error =>{
        alert("Update product fail");
        console.error("Error",error);
      });
        
    }
    $scope.delete =function(item){
        $http.delete(`/lanmarket/api/product/${item.id}`).then(resp =>{
          var index =$scope.items.findIndex(p =>p.id ==item.id);
         
                  $scope.items.splice(index,1) //delete tai index 1 phan tu
            $scope.reset();
            // $scope.initialize();
            alert("Delete product successfully");
        }).catch(error =>{
          alert("Delete product fail");
          console.error("Error",error);
        });
    }
    $scope.imageChanged =function(files){
       var data = new FormData();
       data.append('file',files[0]);
       $http.post('/lanmarket/api/upload/images',data,{
        transformRequest: angular.identity,
        headers:{'Content-Type': undefined}
       }).then(resp =>{
           $scope.form.image = resp.data.name;
       }).catch(error =>{
           alert("Error Image");
           console.log("Error",error);
       })
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
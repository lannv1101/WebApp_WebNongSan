app.controller("account-ctrl", function($scope,$http){

    $scope.items=[];
    $scope.form={};
    $scope.cates=[];
    $scope.roles=[];
    $scope.admins=[];
    $scope.authorities=[];

    $scope.initialize= function(){
        //load product
        $http.get("/lanmarket/api/accounts").then(resp =>{
            $scope.items = resp.data;
            $scope.items.forEach(item =>{
                item.createDate = new Date(item.createDate)
            })
        });
        //load all roles
        $http.get("/lanmarket/api/roles").then(resp =>{
            $scope.roles = resp.data;
           
        });
        //load staff admin drirector
        $http.get("/lanmarket/api/accounts?admin=true").then(resp =>{
            $scope.admins = resp.data;
           
        });
            //load staff admin drirector
            $http.get("/lanmarket/api/authorities?admin=true").then(resp =>{
                $scope.authorities = resp.data;
               
            }).catch(error =>{
                $loccation.path("/unauthorized");
            })
        
      
      
    }
    $scope.authority_of =function(acc, role){
        if ($scope.authorities) {
            return $scope.authorities.find(ur =>ur.account.username ==acc.username && ur.role.id==role.id)
        }
    }
    $scope.authority_changed = function(acc,role){
        var authority = $scope.authority_of(acc,role);
        if (authority) { // da cap quyen => thu hoi quyen xoa
            $scope.revoke_authority(authority);
        }else{ //chua dc cap quyen => cap them quyen moi
            authority ={account: acc,role: role};
            $scope.grant_authority(authority);
        }
    }

    $scope.grant_authority =function(authority){
        $http.post(`/lanmarket/api/authorities`, authority).then(resp =>{
            $scope.authorities.push(resp.data);
            alert("Cap quyen su dung thanh cong")
        }).catch(error=>{
            alert("Cap quyen su dung that bai");
            console.log("Error", error);
        })
    }

    $scope.revoke_authority =function(authority){
        $http.delete(`/lanmarket/api/authorities/${authority.id}`, authority).then(resp =>{
         var index = $scope.authorities.findIndex(a =>a.id == authority.id);
         $scope.authorities.splice(index,1);
            alert("Thu hoi quyen su dung thanh cong")
        }).catch(error=>{
            alert("Thu hoi quyen su dung that bai");
            console.log("Error", error);
        })
    }
     //khoi dau
     $scope.initialize();
    
    $scope.edit =function(item){
        $scope.form = angular.copy(item);
      
    }

    $scope.create =function(){
      var item =angular.copy($scope.form);
      $http.post('/lanmarket/api/accounts', item).then(resp =>{
          resp.data.register_date = new Date(resp.data.register_date)
          $scope.items.push(resp.data);
          $scope.reset();
          $scope.initialize();
          alert("Add account successfully");
      }).catch(error =>{
        alert("Add account fail");
        console.error("Error",error);
      });
        
    }
    $scope.reset =function(){
        $scope.form={
            register_date:new Date(),
            image:'cloud-upload.jpg',
            available: true,
        };
        
    }
    $scope.update =function(){
        var item =angular.copy($scope.form);
      $http.put(`/lanmarket/api/accounts/${item.username}`, item).then(resp =>{
        var index =$scope.items.findIndex(p =>p.username ==item.username);
       
                $scope.items[index] = item;
          $scope.reset();
          $scope.initialize();
          alert("Update account successfully");
      }).catch(error =>{
        alert("Update account fail");
        console.error("Error",error);
      });
        
    }
    $scope.delete =function(item){
        $http.delete(`/lanmarket/api/accounts/${item.username}`).then(resp =>{
          var index =$scope.items.findIndex(p =>p.username ==item.username);
         
                  $scope.items.splice(index,1) //delete tai index 1 phan tu
            $scope.reset();
            // $scope.initialize();
            alert("Delete account successfully");
        }).catch(error =>{
          alert("Delete account fail");
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
           $scope.form.photo = resp.data.name;
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
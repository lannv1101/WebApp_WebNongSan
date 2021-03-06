paypal.Buttons.driver("angular", window.angular);
const app = angular.module("shopping-cart-app", ["paypal-buttons"]);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
   

    $scope.cart = {
        items: [],


        //them vao gio hang
        addtocart(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
                alert("Thêm sản phẩm mã " + id + " thành công ");
            } else {
                $http.get(`/lanmarket/api/product/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                    alert("Thêm sản phẩm mã " + id + " thành công ");
                })
            }

        },

        //xoa khoi gio hang
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();

        },

        //xoa sach gio hang
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },

        //tinh tien cua 1 san pham
        amt_of(item) {},

        //tinh tong so luong cac mat hang trong gio
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);

        },

        //tinh tong thanh tien cac mat hang trong gio
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        get amountdiscount() {
            return this.items
                .map(item => item.qty * (item.price - item.price * item.discount / 100))
                .reduce((total, qty) => total += qty, 0);
        },


        //luu go hang vao local storage
        saveToLocalStorage() {

            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        //doc gio hang tu local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }

    }
    $scope.cart.loadFromLocalStorage();
    $scope.wishlist = {
        itemswishlist: [],


        //them vao gio hang
        addtowishlist(id) {
            var item = this.itemswishlist.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveWishListToLocalStorage();
                alert("Thích sản phẩm mã " + id + " thành công ");
            } else {
                $http.get(`/lanmarket/api/product/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.itemswishlist.push(resp.data);
                    this.saveWishListToLocalStorage();
                    alert("Thích sản phẩm mã " + id + " thành công ");
                })
            }

        },

        //xoa khoi gio hang
        removewishlist(id) {
            var index = this.itemswishlist.findIndex(item => item.id == id);
            this.itemswishlist.splice(index, 1);
            this.saveWishListToLocalStorage();

        },

        //xoa sach gio hang
        clearwishlist() {
            this.itemswishlist = [];
            this.saveWishListToLocalStorage();
        },

        //tinh tien cua 1 san pham
        amt_of(item) {},

        //tinh tong so luong cac mat hang trong gio
        get countwishlist() {
            return this.itemswishlist
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);

        },

        //tinh tong thanh tien cac mat hang trong gio
        get amountwishlist() {
            return this.itemswishlist
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },


        //luu go hang vao local storage
        saveWishListToLocalStorage() {

            var json = JSON.stringify(angular.copy(this.itemswishlist));
            localStorage.setItem("wishlist", json);
        },
        //doc gio hang tu local storage
        loadWishListFromLocalStorage() {
            var json = localStorage.getItem("wishlist");
            this.itemswishlist = json ? JSON.parse(json) : [];
        }

    }
    $scope.wishlist.loadWishListFromLocalStorage();
    var  getusername = $("#username").text();
    $scope.order = {
      

        
        createDate: new Date(),
        first_name: $("#first_name").text(),
        last_name: $("#last_name").text(),
        address_line1: $("#address_line1").text(),
        address_line2: $("#address_line2").text(),
        state: $("#state").text(),
        city: $("#city").text(),
        postal_code: $("#postal_code").text(),
        phone_number: $("#phone_number").text(),
        account: {

            username:  getusername,
            //lay username


        },
        payment_method: "COD",
     

       
       
        // $("#username").text()
        //$("#username").text() document.getElementById("username").innerText 
        amount: $scope.cart.amountdiscount,
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {
                        id: item.id
                    },
                    price: item.price - item.price * item.discount / 100,
                    quantity: item.qty
                }
            });
        },
        purchase() {


            var orders = angular.copy(this);
            // alert("Đặt hàng thành công " + orders.account.username);
            //account la mot mang nen ko the post data len dc
            $http.post("/lanmarket/api/orders",orders).then(resp =>{
                location.href="/lanmarket/order/detail/"+resp.data.id;
                $scope.cart.clear();

            }).catch(error =>{
                alert("Đặt hàng lỗi"); 
                console.log(error)
            })

        }
    }
    $scope.orderpaypal = {
      

        
        createDate: new Date(),
        first_name: $("#first_name").text(),
        last_name: $("#last_name").text(),
        address_line1: $("#address_line1").text(),
        address_line2: $("#address_line2").text(),
        state: $("#state").text(),
        city: $("#city").text(),
        postal_code: $("#postal_code").text(),
        phone_number: $("#phone_number").text(),
        account: {

            username: getusername //lay username


        },
        payment_method: "Paypal",
     

       
       
        // $("#username").text()
        //$("#username").text() document.getElementById("username").innerText 
        amount: $scope.cart.amountdiscount,
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {
                        id: item.id
                    },
                    price: item.price - item.price * item.discount / 100,
                    quantity: item.qty
                }
            });
        },
        purchase() {


            var orders = angular.copy(this);
            // alert("Đặt hàng thành công " + orders.account.username);
            //account la mot mang nen ko the post data len dc
            $http.post("/lanmarket/api/orders",orders).then(resp =>{
                location.href="/lanmarket/order/detail/"+resp.data.id;
                $scope.cart.clear();

            }).catch(error =>{
                alert("Đặt hàng lỗi"); 
                console.log(error)
            })

        }
    }

    var amountToVnd = $scope.cart.amountdiscount / 22500;
    amountTotal = amountToVnd.toFixed(2);
   
    $scope.opts = {
        createOrder: function (data, actions) {
            return actions.order.create({
                intent: 'CAPTURE',

                payer: {
                    name: {
                        given_name: $("#first_name").text(),
                        surname: $("#last_name").text(),
                    },
                    address: {
                        address_line_1: $("#address_line1").text(),
                        address_line_2: $("#address_line2").text(),
                        admin_area_2: $("#city").text(),
                        admin_area_1: $("#state").text(),
                        postol_code: $("#postal_code").text(),
                        country_code: "US"


                    },
                    email_address: $("#email").text(),
                    phone: {
                        phone_type: "MOBILE",
                        phone_number: {
                            national_number: $("#phone_number").text(),
                        }
                    }
                },

                purchase_units: [{

                    amount: {
                        value: amountTotal,
                        currency_code: "USD"
                    }
                }]
            });
        },

        onApprove: function (data, actions) {
            
            return actions.order.capture().then(function(details){
                

                console.log(details);
                ///phai biet thanh toan bang paypal hay cod
                $scope.orderpaypal.purchase(
                    

                );
                alert("Cảm ơn bạn đã thanh toán");
                
               
             });
        },
        onCancel: function(data){
            alert("Thanh toán đã hủy");
        }, 
    };



});
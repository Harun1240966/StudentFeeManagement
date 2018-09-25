productApp.config(function ($routeProvider) {
    $routeProvider.
            when('/', {
                controller: 'prodCtrl',
                templateUrl: 'partials/StudentView.htm'
            })
            .otherwise({redirectTo: '/'});
});

productApp.controller('prodCtrl', function ($scope, $rootScope, $modal, productFactory, $interval, flash, localStorageService) {

    // Initializing variables

    $scope.prodList = {};
    $scope.alert = {};

    var callProds = function () {
        productFactory.getAllProducts(function successCallBack(data) {
            $scope.prodList = data;

            for (var poss = 0; poss < $scope.prodList.length; poss++) {
                if ($scope.prodList[poss].stock === 'SIN STOCK') {
                    $scope.prodList[poss].stock_color = 'red';
                } else {
                    $scope.prodList[poss].stock_color = 'green';
                }
            }

        }, function errorCallback(data, status) {
            $scope.errorAlert = true;
            $scope.alertErrorData = 'Problem when trying to retrieve Students from DB';
        });
    };

    callProds();

    $interval(function () {
        callProds();
    }, 5000);

    $scope.addNewProduct = function () {
        var studentName = $scope.Name;
        var email = $scope.Email;
        var sex = $scope.Sex;
        var course = $scope.Course;
        var fee = $scope.Fee;
        var paid = $scope.Paid;
        var due = $scope.Due;
        var address = $scope.Address;
        var contact = $scope.Contact;

        productFactory.addSimpleProduct(studentName, email, sex, course, fee, paid, due, address, contact, function callbackSuccess(data) {
            $scope.alert = {
                heading: "Success!"
            };
            flash.success = data.response;
            callProds();
        }, function errorCallback(data, status) {
            $scope.alert = {
                heading: "Error!"
            };
            flash.error = data.response + ' Reason: ' + status;
        });

    };

    $scope.removeProduct = function (rollno) {
        productFactory.removeProductById(rollno, function callbackSuccess(data) {
            $scope.alert = {
                heading: "Success!"
            };
            flash.success = data.response;
            callProds();
        }, function errorCallback(data, status) {
            $scope.alert = {
                heading: "Error!"
            };
            flash.error = data + ' with status ' + status;
        });
    };  

    /* Listening to modal broadcasting on update events*/
    $rootScope.$on('UPDATE_PRODUCT_RESPONSE_SUCCESS', function (event, data) {
        $scope.alert = {
            heading: "Success!"
        };
        flash.success = data.response;
    });

    $rootScope.$on('UPDATE_PRODUCT_RESPONSE_ERROR', function (event, data) {
        $scope.alert = {
            heading: "Error!"
        };
        flash.success = data.response;
    });
    /* End of broadcasting events */

    this.$inject = ['flash'];
});


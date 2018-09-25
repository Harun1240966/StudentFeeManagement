productApp.controller('ModalDemoCtrl', function ($scope, $modal) {

    $scope.open = function (rollno) {

        var modalInstance = $modal.open({
            templateUrl: 'modals/updateStudentModal.htm',
            controller: 'ModalInstanceCtrl',
            resolve: {
                prodId: function () {
                    return rollno;
                }
            }
        });
    };
});

productApp.controller('ModalInstanceCtrl', function ($scope, $route, $rootScope, $modalInstance, productFactory, prodId, localStorageService) {

    // search Student in the database
    productFactory.getProductById(prodId, function successCallback(data) {
        $scope.modal_studentName = data.name;
        $scope.modal_studentEmail = data.email;
        $scope.modal_studentSex = data.sex;
        $scope.modal_studentCourse = data.course;
        $scope.modal_studentFee = data.fee;
        $scope.modal_studentPaid = data.paid;
        $scope.modal_studentDue = data.due;
        $scope.modal_studentAddress = data.address;
        $scope.modal_studentContact = data.contact;
    }, function errorCallback(data, status) {
        alert("An error occurred retrieving Student. Please refresh the page & try again.");
    });

    $scope.ok = function () {

        this.$watchCollection('[modal_studentName, modal_studentEmail, modal_studentSex, modal_studentCourse, modal_studentFee, modal_studentPaid,modal_studentDue,modal_studentAddress,modal_studentContact]',
                function (newValues) {
                    var productObject = {
                        rollno: prodId,
                        name: newValues[0],
                        email: newValues[1],
                        sex: newValues[2],
                        course: newValues[3],
                        fee: newValues[4],
                        paid: newValues[5],
                        due: newValues[6],
                        address: newValues[7],
                        contact: newValues[8]
                    };

                    productFactory.updateProductById(productObject, function successCallback(data) {

                        $modalInstance.dismiss('cancel');

                        $rootScope.$broadcast('UPDATE_PRODUCT_RESPONSE_SUCCESS', data);
                        //re-render the whole page
//			  $route.reload();
                    }, function errorCallback(data, status) {
                        $rootScope.$broadcast('UPDATE_PRODUCT_RESPONSE_ERROR', data);
                    });

                });

    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});
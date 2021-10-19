angular.module('market-app').controller('adminController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1/';

    $scope.createProduct = function () {
        if ($scope.new_product == null) {
            alert('Форма не заполнена');
            return;
        }
        $http.post(contextPath + 'products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.new_product = null;
                alert('Продукт успешно создан');
                $location.path('/store');
            }, function failureCallback(response) {
                console.log(response);
                alert(response.data.messages);
            });
    }

    $scope.checkRole = function () {
        $http({
            url: contextPath + 'admin/check',
            method: 'GET'
        }).then(function successCallback(response) {
        }, function errorCallback(response) {
            $location.path('/');
        });
    }

    $scope.checkRole();
});
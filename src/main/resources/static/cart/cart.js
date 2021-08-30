angular.module('market-app').controller('cartController', function ($scope, $http) {
    console.log('cart');
    const contextPath = 'http://localhost:8189/market/api/v1/';
    let currentResponseLog = null;

    $scope.loadProducts = function () {
        $http.get(contextPath + 'cart/')
            .then(function (response) {
                console.log(response);
                $scope.cart = response.data;
                currentResponseLog = response;
            });
    };

    $scope.showInfo = function (productId) {
        alert(productId.categoryTitle);
    };


    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + 'cart/delete/' + productId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.loadProducts();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
        alert('product with id: ' + productId + ' - deleted successfully');
    }

    $scope.loadProducts();
});
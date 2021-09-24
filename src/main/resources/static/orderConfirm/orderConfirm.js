angular.module('market-app').controller('orderConfirmController', function ($scope, $http, $location) {
    console.log('cart');
    const contextPath = 'http://localhost:8189/market/api/v1/';
    let currentResponseLog = null;

    $scope.loadCart = function () {
        $http.get(contextPath + 'cart')
            .then(function (response) {
                console.log(response);
                $scope.cart = response.data;
                currentResponseLog = response;
            });
    };

    $scope.createNewOrder = function () {
        console.log( $scope.cart);
        $http.post(contextPath + 'orders', $scope.newOrder)
            .then(function successCallback(response) {
                console.log(response);
                alert('Oder done ')
                $location.path('/');
            });
    }

    $scope.loadCart();
});
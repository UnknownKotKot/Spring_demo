angular.module('market-app').controller('orderConfirmController', function ($scope, $http, $location, $localStorage) {
    console.log('cart');
    const contextPath = 'http://localhost:8189/market/api/v1/';
    let currentResponseLog = null;

    $scope.loadCart = function () {
        $http.get(contextPath + 'cart/' + $localStorage.webMarketGuestCartId)
            .then(function (response) {
                console.log(response);
                $scope.cart = response.data;
                currentResponseLog = response;
            });
    };

    $scope.createNewOrder = function () {
        console.log($scope.cart);
        $http({
            url: contextPath + 'orders',
            method: 'POST',
            data: $scope.newOrder
        }).then(function successCallback(response) {
                console.log(response);
                alert('Oder done ')
                $location.path('/');
            });
    };

    $scope.loadCart();
});
angular.module('market-app').controller('ordersController', function ($scope, $http) {
    console.log('cart');
    const contextPath = 'http://localhost:8189/market/api/v1/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'orders')
            .then(function (response) {
                console.log(response);
                $scope.orders = response.data;
            });
    };

    $scope.loadOrders();
});
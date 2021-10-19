angular.module('market-app').controller('cartController', function ($scope, $http, $location, $localStorage) {
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

    $scope.increaseCount = function (productId) {
        $http.get(contextPath + 'cart/' + $localStorage.webMarketGuestCartId +'/add/' + productId )
            .then(function successCallback(response) {
                console.log(response);
                $scope.loadCart();
                }, function failureCallback(response) {
                alert(response.data.message);
            });
    }

    $scope.reduceCount = function (productId) {
        $http.get(contextPath + 'cart/' + $localStorage.webMarketGuestCartId + '/reduce/' + productId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.loadCart();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    }

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + 'cart/' + $localStorage.webMarketGuestCartId + '/remove/' + productId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.loadCart();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
        alert('product with id: ' + productId + ' - deleted successfully');
    }

    $scope.checkOut = function () {
        $location.path("/orderConfirm");
    }
    $scope.disabledCheckOut = function () {
        alert("For order please log in");
    }

    $scope.loadCart();
});
angular.module('market-app').controller('productAddController', function ($scope, $http, $routeParams, $location) {
    console.log('update');
    const contextPath = 'http://localhost:8189/market-core/api/v1/';

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.new_product = null;
                alert('product add success' + $scope.new_product);
                $location.path('/catalogue');
            }, function failureCallback(response) {
                alert(response.data.message);
            });
        console.log($scope.new_product);
    }

});
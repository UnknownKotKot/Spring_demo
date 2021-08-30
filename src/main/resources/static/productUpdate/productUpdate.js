angular.module('market-app').controller('updateController', function ($scope, $http, $routeParams, $location) {
    console.log('update');
    const contextPath = 'http://localhost:8189/market/api/v1/';

    $scope.confirmUpdate = function () {
        $scope.getValues.id = $routeParams.productId;
        $http.put(contextPath + 'products', $scope.getValues)
            .then(function successCallback(response) {
                console.log(response);
                alert('Update success');
                $location.path('/catalogue');
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };
});
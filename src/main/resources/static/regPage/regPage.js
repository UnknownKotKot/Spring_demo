angular.module('market-app').controller('userRegController', function ($scope, $http, $routeParams, $location) {
    console.log('update');
    const contextPath = 'http://localhost:8189/market/api/v1/';

    $scope.createNewUser = function () {
        $http.post(contextPath + 'registration', $scope.new_user)
            .then(function successCallback(response) {
                $scope.new_user = null;
                alert('user reg success' + $scope.new_user);
                $location.path('/catalogue');
            }, function failureCallback(response) {
                alert(response.data.message);
            });
        console.log($scope.new_user);
    }

});
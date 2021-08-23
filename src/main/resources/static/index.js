angular.module('market-app', []).controller('indexController', function ($scope, $http) {
    console.log('web app started');

    $scope.name = 'Bob';

    const contextPath = 'http://localhost:8189/market/';

    $scope.loadProducts = function (pageIndex = 0, pageSize = 10) {
        $http.get(contextPath + 'products?p=' + pageIndex + '&s=' + pageSize)
            .then(function (response) {
                console.log(response)
                $scope.products = response.data.content;

            });
    };

    $scope.getStarted = function () {
      document.location.href = 'productCatalogue.html';
    };

    $scope.showInfo = function (productId) {
        alert(productId.price);
    };

    $scope.deleteProduct = function (productId) {
        const  id = productId;
        $http.get(contextPath + 'products/delete/' + productId)
                .then(function (response) {
                    console.log(response)
                    $scope.loadProducts();
                });
        alert('product with id: '+ id + ' - deleted successfully');
    }
});
angular.module('market-app').controller('catalogueController', function ($scope, $http, $location) {
    console.log('catalogue');
    const contextPath = 'http://localhost:8189/market/api/v1/';
    let currentResponseLog = null;

    $scope.loadProducts = function (pageIndex = 0, pageSize = 10) {
        $http.get(contextPath + 'products?p=' + pageIndex + '&s=' + pageSize)
            .then(function (response) {
                console.log(response);
                $scope.products = response.data.content;
                currentResponseLog = response;
                $scope.productsPage = response.data;
                $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
            });
    };

    $scope.prevPage = function () {
        const currentPage = currentResponseLog.data.pageable.pageNumber;
        if (currentPage >= 1) {
            $scope.loadProducts(currentPage - 1);
        } else {
            alert('this is the first page');
        }
    }
    $scope.nextPage = function () {
        const currentPage = currentResponseLog.data.pageable.pageNumber;
        if (currentResponseLog.data.last === false) {
            $scope.loadProducts(currentPage + 1);
        } else {
            alert('this is the last page');
        }
    }

    $scope.showInfo = function (productId) {
        alert(productId.categoryTitle);
    };

    $scope.updateProduct = function (productId) {
        console.log(productId);
        $location.path('/productUpdate/' + productId);
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + 'products/delete/' + productId)
            .then(function successCallback(response) {
                console.log(response);
                $scope.loadProducts();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
        alert('product with id: ' + productId + ' - deleted successfully');
    }

    $scope.addToCart = function (productId) {
        $http.get(contextPath + 'cart/add/' + productId)
            .then(function successCallback(response) {
                console.log(response);
            }, function failureCallback(response) {
                alert(response.data.message);
            });
        alert('product with id: ' + productId + ' - added successfully');
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadProducts();
});
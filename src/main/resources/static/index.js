angular.module('market-app', []).controller('indexController', function ($scope, $http) {
    console.log('web app started');

    $scope.name = 'Bob';
    let productId;

    const contextPath = 'http://localhost:8189/market/api/v1/';
    let currentResponseLog = null;

    $scope.loadProducts = function (pageIndex = 0, pageSize = 10) {
        $http.get(contextPath + 'products?p=' + pageIndex + '&s=' + pageSize)
            .then(function (response) {
                console.log(response)
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

    $scope.getStarted = function () {
        document.location.href = 'productCatalogue.html';
    };

    $scope.showInfo = function (productId) {
        alert(productId.categoryTitle);
    };

    $scope.updateProduct = function (productId) {
        sessionStorage.setItem(productId, productId);
        console.log(productId);
        document.location.href = 'productAdd.html?id=' + productId;
    };

    $scope.confirmUpdate = function () {
        $scope.getValues.id = productId;
        $http.put(contextPath + 'products', $scope.getValues)
            .then(function successCallback(response) {
                console.log(response);
                document.location.href = 'productCatalogue.html';
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.idGetter = function OnLoad() {
        productId = window.location.href.split('?')[1].split('=')[1];
        console.log(productId);
    }

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + 'products/delete/' + productId)
            .then(function successCallback(response) {
                console.log(response)
                $scope.loadProducts();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
        alert('product with id: ' + productId + ' - deleted successfully');
    }

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.loadProducts();
                $scope.new_product = null;
            }, function failureCallback(response) {
                alert(response.data.message);
            });
        console.log($scope.new_product);
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }
});
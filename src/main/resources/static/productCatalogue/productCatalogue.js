angular.module('market-app').controller('catalogueController', function ($scope, $http, $location, $localStorage) {
    console.log('catalogue');

    const contextPath = 'http://localhost:8189/market/api/v1/';
    let currentResponseLog = null;


    $scope.loadProducts = function (pageIndex = 0) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex,
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            currentResponseLog = response.data;
            console.log(response.data);
            console.log(currentResponseLog.pageable.pageNumber);

            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.prevPage = function () {
        const currentPage = currentResponseLog.pageable.pageNumber;
        if (currentPage >= 1) {
            $scope.loadProducts(currentPage - 1);
        } else {
            alert('this is the first page');
        }
    }
    $scope.nextPage = function () {
        const currentPage = currentResponseLog.pageable.pageNumber;
        if (currentResponseLog.last === false) {
            $scope.loadProducts(currentPage + 2);
        } else {
            alert('this is the last page');
        }
    }

    $scope.addToCart = function (productId) {
        $http.get(contextPath + 'cart/' + $localStorage.webMarketGuestCartId + '/add/' + productId)
            .then(function successCallback(response) {
                console.log(response);
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.navToEditProductPage = function (productId) {
        $location.path('/productUpdate/' + productId);
    }

    $scope.loadProducts();
});
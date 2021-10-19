(function () {
    angular
        .module('market-app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcomePage/welcomePage.html',
                controller: 'welcomeController'
            })
            .when('/catalogue', {
                templateUrl: 'productCatalogue/productCatalogue.html',
                controller: 'catalogueController'
            })
           // .when('/productUpdate/:productId', {
            //    templateUrl: 'productUpdate/productUpdate.html',
            //    controller: 'updateController'
           // })
           // .when('/productAdd', {
           //     templateUrl: 'productAdd/productAdd.html',
           //     controller: 'productAddController'
            //})
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
           // .when('/registration', {
           //     templateUrl: 'regPage/regPage.html',
            //    controller: 'userRegController'
          //  })
            .when('/orderConfirm', {
                templateUrl: 'orderConfirm/orderConfirm.html',
                controller: 'orderConfirmController'
            })
           // .when('/orders', {
            //    templateUrl: 'orders/orders.html',
           //     controller: 'ordersController'
           // })
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        const contextPath = 'http://localhost:8189/market';
        if ($localStorage.webMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
        if (!$localStorage.webMarketGuestCartId) {
            $http.get(contextPath + '/api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.webMarketGuestCartId = response.data.value;
                });
        }
    }
})();

angular.module('market-app').controller('indexController', function ($rootScope, $scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $http.get(contextPath + '/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/merge')
                        .then(function successCallback(response) {
                        });
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };
});
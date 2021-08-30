(function () {
    angular
        .module('market-app', ['ngRoute'])
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
            .when('/productUpdate/:productId', {
                templateUrl: 'productUpdate/productUpdate.html',
                controller: 'updateController'
            })
            .when('/productAdd', {
                templateUrl: 'productAdd/productAdd.html',
                controller: 'productAddController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http) {
    }
})();
angular.module('market-app').controller('indexController', function ($rootScope, $scope, $http) {
    console.log('index');
    const contextPath = 'http://localhost:8189/market/api/v1/';
});
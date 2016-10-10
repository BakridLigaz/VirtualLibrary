angular.module("App")
    .config(function ($routeProvider) {

        $routeProvider.when('/genres', {
            templateUrl: 'static/html/templates/genrelist.html',
            controller: 'GenreCtrl'
        });
        $routeProvider.when('/authors', {
            templateUrl: 'static/html/templates/authorlist.html',
            controller: 'AuthorCtrl'
        });
        $routeProvider.when('/books', {
            templateUrl: 'static/html/templates/booklist.html',
            controller: 'BookCtrl'
        });
        $routeProvider.otherwise({redirectTo: '/books'});
    });

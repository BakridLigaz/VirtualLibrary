angular.module("App")
    .config(function ($routeProvider) {

        $routeProvider.when('/genres', {
            templateUrl: 'static/html/templates/genres.html',
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
        $routeProvider.when('/presBook',{
           templateUrl: 'static/html/templates/present_book.html',
            controller: 'PresentCtrl'
        });
        $routeProvider.otherwise({redirectTo: '/books'});
    });

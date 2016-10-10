var controllers = angular.module("Controllers", ['Services', 'ui.bootstrap', 'ngFileUpload'])
controllers.controller("MainCtrl", function ($scope, $location) {
    $scope.changeView = function (view) {
        switch (view) {
            case "books":
                $location.path('/books');
                break;
            case "genres":
                $location.path('/genres');
                break;
            case "authors":
                $location.path('/authors');
                break;
            default:
                $location.path('/books');
        }
    };

});

controllers.controller("GenreCtrl", function ($scope, $uibModal, genreService) {
    $scope.fetchGenres = function () {
        genreService.fetchGenres()
            .then(
                function (list) {
                    $scope.genreList = list;
                }
            );
    };
    $scope.updateGenre = function (genre) {
        genreService.updateGenre(genre)
            .then(
                $scope.fetchGenres
            );
    }
    $scope.openModal = function (genre) {
        var instance = $uibModal.open({
            animation: true,
            templateUrl: "../../static/html/templates/edit_genre.html",
            controller: closeModalGenreCtrl,
        });
        instance.result.then(function () {
            $scope.fetchGenres();
        });
    };
    $scope.fetchGenres();
});

var closeModalGenreCtrl = function ($scope, $uibModalInstance, genreService) {
    $scope.isEdit = true;
    $scope.isResult = false;
    $scope.isLoad = false;
    $scope.save = function (genre) {
        $scope.isEdit = false;
        $scope.isResult = false;
        $scope.isLoad = true;
        $scope.promise = genreService.addGenre(genre);
        $scope.promise.then(function () {
            $scope.result = "Жанр успешно добавлен";
            $scope.isLoad = false;
            $scope.isResult = true;
        });
        $scope.promise.catch(function () {
            $scope.result = "Жанр не добавлен";
            $scope.isLoad = false;
            $scope.isResult = true;
        });
    };
    $scope.closeAndExit = function () {
        $uibModalInstance.close();
    };
};

controllers.controller("AuthorCtrl", function ($scope, authorService, $uibModal) {
    $scope.fetchAuthors = function () {
        authorService.fetchAuthors()
            .then(
                function (list) {
                    $scope.authorList = list;
                }
            );
    };
    $scope.updateAuthor = function (author) {
        authorService.updateAuthor(author)
            .then(
                $scope.fetchAuthors
            );
    };
    $scope.openModal = function (author) {
        var instance = $uibModal.open({
            animation: true,
            templateUrl: "../../static/html/templates/edit_author.html",
            controller: closeModalAuthorCtrl,
        });
        instance.result.then(function () {
            $scope.fetchAuthors();
        });
    };
    $scope.fetchAuthors();
});

var closeModalAuthorCtrl = function ($scope, $uibModalInstance, authorService) {
    $scope.isEdit = true;
    $scope.isResult = false;
    $scope.isLoad = false;
    $scope.save = function (author) {
        $scope.isEdit = false;
        $scope.isResult = false;
        $scope.isLoad = true;
        $scope.promise = authorService.addAuthor(author);
        $scope.promise.then(function () {
            $scope.result = "Автор успешно добавлен";
            $scope.isLoad = false;
            $scope.isResult = true;
        });
        $scope.promise.catch(function () {
            $scope.result = "Автор не добавлен";
            $scope.isLoad = false;
            $scope.isResult = true;
        });
    };
    $scope.closeAndExit = function () {
        $uibModalInstance.close();
    };
};

controllers.controller("BookCtrl", function ($scope, $uibModal, bookService) {
    $scope.fetchBooks = function () {
        bookService.fetchBooks()
            .then(
                function (list) {
                    $scope.bookList = list;
                }
            );
    };
    $scope.getBooksByAuthor = function (author) {
        bookService.getBooksByAuthor(author)
            .then(
                function (list) {
                    $scope.bookList = list;
                }
            );
    };
    $scope.getBooksByGenre = function (genre) {
        bookService.getBooksByGenre(genre)
            .then(
                function (list) {
                    $scope.bookList = list;
                }
            );
    };
    $scope.getBooksBySearch = function (search) {
        bookService.getBooksBySearch(search)
            .then(
                function (list) {
                    $scope.bookList = list;
                }
            );
    };
    $scope.updateBook = function (book) {
        bookService.updateBook(book)
            .then(
                function () {
                    $scope.fetchBooks
                }
            );
    };
    $scope.deleteBook = function (id) {
        bookService.deleteBook(id)
            .then(
                function () {
                    $scope.fetchBooks
                }
            );
    };
    $scope.openModal = function () {
        var instance = $uibModal.open({
            animation: true,
            templateUrl: "../../static/html/templates/edit_book.html",
            controller: closeModalBookCtrl,
        });
        instance.result.then(function () {
            $scope.fetchBooks();
        });
    };
    $scope.fetchBooks();
});

var closeModalBookCtrl = function ($scope, $uibModalInstance, authorService, genreService, bookService) {
    authorService.fetchAuthors().then(function (list) {
        $scope.currentAuthorList = list
    });
    genreService.fetchGenres().then(function (list) {
        $scope.currentGenreList = list
    });
    $scope.Exist = function (name) {
        var promise = bookService.getBookByName(name);
        //if(bookService.getBookByName(name)!=null){
        //    $scope.isExist = true;
        //}else { $scope.isExist = false}
    };
    $scope.isEdit = true;
    $scope.isResult = false;
    $scope.isLoad = false;
    $scope.save = function (image, content, book) {
        $scope.isEdit = false;
        $scope.isResult = false;
        $scope.isLoad = true;
        $scope.promise = bookService.addBook(book, image, content);
        $scope.promise.then(function () {
            $scope.allResults = "Книга успешно загружена";
            $scope.isLoad = false;
            $scope.isResult = true;
        });
        $scope.promise.catch(function () {
            $scope.allResults = "Книга не загружена";
            $scope.isLoad = false;
            $scope.isResult = true;
        });
    };
    $scope.closeAndExit = function () {
        $uibModalInstance.close();
    };
};



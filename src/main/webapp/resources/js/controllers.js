var controllers = angular.module("Controllers", ['Services', 'ui.bootstrap', 'ngFileUpload','ngMessages','imagesLoaded']);
controllers.controller("BookListCtrl", function ($scope, $location,bookService) {
    $scope.mode={user:"user"};
    $scope.genres = {genreList:[]};
    $scope.authors = {authorList:[]};
    $scope.books = {bookList:[],curBook:undefined};
    $scope.type = {listType:'all'};
    $scope.page ={curPage:1};
    $scope.limit = 3;
    $scope.random = {isRandom: true};
    $scope.changeView = function (view) {
        $location.path(view);
    };
    $scope.getBook = function(book){
      $scope.changeView("presBook");
        $scope.books.curBook = book;

    };
    $scope.getBooksByRandom = function (limit) {
        bookService.getBooksByRandom(limit)
            .then(
                function (list) {
                    $scope.changeView("/books");
                    $scope.random.isRandom = true;
                    $scope.books.bookList = list;
                    $scope.size = list.length;
                }
            );
    };

    $scope.getBooksByRandom($scope.limit);
    $scope.getBooksByAuthor = function (authorId) {
        $scope.random.isRandom = false;
        $scope.page.curPage=1;
        console.log($scope.page.curPage);
        bookService.checkBookListSizeByAuthor(authorId)
            .then(
                function(result){
                    $scope.currentAuthorId = authorId;
                    $scope.type.listType = 'byAuthor';
                    $scope.size = result.data;
                }
            );
        bookService.getBooksByAuthor($scope.page.curPage-1,$scope.limit,authorId)
            .then(
                function (list) {
                    $scope.changeView("/books");
                    $scope.books.bookList = list;
                }
            );
    };
    $scope.getBooksByGenre = function (genreId) {
        $scope.random.isRandom = false;
        $scope.page.curPage=1;
        bookService.checkBookListSizeByGenre(genreId)
            .then(
                function(result){
                    $scope.currentGenreId = genreId;
                    $scope.type.listType = 'byGenre';
                    $scope.size = result.data;
                }
            );
        bookService.getBooksByGenre($scope.page.curPage-1,$scope.limit,genreId)
            .then(
                function (list) {
                    $scope.changeView("/books");
                    $scope.books.bookList = list;
                }
            );
    };

    $scope.getBooksBySearch = function (search) {
        $scope.random.isRandom = false;
        $scope.page.curPage=1;
        bookService.checkBookListSizeBySearch(search)
            .then(
                function(result){
                    $scope.type.listType = 'bySearch';
                    $scope.size = result.data;
                }
            );
        bookService.getBooksBySearch($scope.page.curPage-1,$scope.limit,search)
            .then(
                function (list) {
                    $scope.changeView("/books");
                    $scope.books.bookList = list;
                }
            );
    };
    $scope.getAllBooks = function () {
        $scope.random.isRandom = false;
        $scope.page.curPage=1;
        bookService.checkBooklistSize()
            .then(
                function(result){
                    $scope.page.curPage = 1;
                    $scope.type.listType = 'All';
                    $scope.size = result.data;
                }
            );
        bookService.getAllBooks($scope.page.curPage-1,$scope.limit)
            .then(
                function (list) {
                    $scope.changeView("/books");
                    $scope.books.bookList = list;
                }
            );
    };
});

controllers.controller("GenreCtrl", function ($scope, $uibModal, genreService) {
    $scope.fetchGenres = function () {
        genreService.fetchGenres()
            .then(
                function (list) {
                    $scope.genres.genreList = list;
                }
            );
    };
    $scope.openModal = function (genre) {

        var instance = $uibModal.open({
            animation: true,
            templateUrl: "../../static/html/templates/edit_genre.html",
            controller: closeModalGenreCtrl,
            resolve:{
                genre:function(){
                    return genre;
                }
            }
        });
        instance.result.then(function () {
            $scope.fetchGenres();
        });
    };
    $scope.deleteGenre = function(genre){
      genreService.deleteGenre(genre)
          .then(
              function (result) {
                  var instance = $uibModal.open({
                      animation: true,
                      templateUrl: "../../static/html/templates/delete_template.html",
                      controller: closeDeleteCtrl,
                      resolve:{
                          message:function(){
                              return result.data;
                          },
                          isAuthor: function(){
                              return false;
                          },
                          isBook: function(){
                              return false;
                          },
                          isGenre: function(){
                              return true;
                          }
                      }
                  });
                  instance.result.then(function () {
                      $scope.fetchGenres();
                  });
              }
          );
    };
    $scope.fetchGenres();
});

controllers.controller("AuthorCtrl", function ($scope, authorService, $uibModal) {
    $scope.fetchAuthors = function () {
        authorService.fetchAuthors()
            .then(
                function (list) {
                    $scope.authors.authorList = list;
                }
            );
    };
    $scope.openModal = function (author) {
        var instance = $uibModal.open({
            animation: true,
            templateUrl: "../../static/html/templates/edit_author.html",
            controller: closeModalAuthorCtrl,
            resolve:{
                author:function(){
                    return author;
                }
            }
        });
        instance.result.then(function () {
            $scope.fetchAuthors();
        });
    };
    $scope.deleteAuthor = function(author){
      authorService.deleteAuthor(author)
          .then(
              function (result) {
                  var instance = $uibModal.open({
                      animation: true,
                      templateUrl: "../../static/html/templates/delete_template.html",
                      controller: closeDeleteCtrl,
                      resolve:{
                          message:function(){
                              return result.data;
                          },
                          isAuthor: function(){
                              return true;
                          },
                          isBook: function(){
                              return false;
                          },
                          isGenre: function(){
                              return false;
                          }
                      }
                  });
                  instance.result.then(function () {
                      $scope.fetchAuthors();
                  });
              }
          );
    };
    $scope.fetchAuthors();
});

controllers.controller("BookCtrl", function ($scope, $uibModal, bookService,authorService,genreService) {
    $scope.setPage = function(pageNo){
        $scope.page.curPage = pageNo;
    };
    $scope.pageChanged = function() {
        if($scope.type.listType =='byAuthor'){
            bookService.getBooksByAuthor($scope.page.curPage-1,$scope.limit,$scope.currentAuthorId)
                .then(
                    function (list) {
                        $scope.books.bookList = list;
                    }
                );
        }else if($scope.type.listType=='byGenre'){
            bookService.getBooksByGenre($scope.page.curPage-1,$scope.limit,$scope.currentGenreId)
                .then(
                    function (list) {
                        $scope.books.bookList = list;
                    }
                );
        }else if($scope.type.listType=='All'){
            bookService.getAllBooks($scope.page.curPage-1,$scope.limit)
                .then(
                    function (list) {
                        $scope.books.bookList = list;
                    }
                );
        }else if($scope.type.listType = 'BySearch'){
            bookService.getBooksBySearch($scope.page.curPage-1,$scope.limit,$scope.query)
                .then(
                    function (list) {
                        $scope.books.bookList = list;
                    }
                );
        }
        console.log($scope.page.curPage);
    };

    $scope.deleteBook = function (book) {
        bookService.deleteBook(book.id)
            .then(
                function (result) {
                    var instance = $uibModal.open({
                        animation: true,
                        templateUrl: "../../static/html/templates/delete_template.html",
                        controller: closeDeleteCtrl,
                        resolve:{
                            message:function(){
                                return result;
                            },
                            isBook: function(){
                                return true;
                            },
                            isGenre: function(){
                                return false;
                            },
                            isAuthor: function(){
                                return false;
                            }
                        }
                    });
                    instance.result.then(function () {
                        $scope.getAllBooks($scope.page.curPage,$scope.limit);
                    });
                }
            );
    };
    $scope.openModal = function (book) {
        var instance = $uibModal.open({
            animation: true,
            templateUrl: "../../static/html/templates/edit_book.html",
            controller: closeModalBookCtrl,
            resolve:{
                book:function(){
                    return book;
                }
            }
        });
        instance.result.then(function () {
            $scope.getAllBooks($scope.page.curPage,$scope.limit);
            authorService.fetchAuthors()
                .then(function(list){
                    $scope.authors.authorList = list;
                });
            genreService.fetchGenres()
                .then(function(list){
                    $scope.genres.genreList = list;
                });
        });
    };
});

controllers.controller('PresentCtrl', function($scope,$window){
   $scope.getContent = function(book){
        $window.open("/books/getContent/?bookName="+book.name,book.name);
   };
});

//Close Modal Controllers
var closeModalBookCtrl = function ($scope, $uibModalInstance, authorService, genreService, bookService,book,$log) {
    if(book!=undefined){
        $scope.book = book;
        $scope.isImageUpdate = true;
        $scope.isFileUpdate = true;
        $scope.isNew = false;
        $scope.isDirty = true;
    }else {$scope.isNew=true;
    $scope.isDirty = false;}
    $scope.changeMode=function(type){
        if(type=='image'){
            $scope.isImageUpdate = false;
        }else if(type=='file'){
            $scope.isFileUpdate = false;
        }
    };
    authorService.fetchAuthors().then(function (list) {
        $scope.currentAuthorList = list
    });
    genreService.fetchGenres().then(function (list) {
        $scope.currentGenreList = list
    });
    $scope.isEdit = true;
    $scope.isLoad = false;
    $scope.save = function (valid,image, content, book) {
        if(!valid){
            $scope.isDirty = true;
            return;
        }
        $scope.isEdit = false;
        $scope.isLoad = true;
        book.add_date=new Date();
        $scope.promise = bookService.addBook(book, image, content);
        $scope.progress = 0;
        $scope.promise.then(function (results) {
            if(results[0].data&&results[1].data){
                $log.debug("Обложка и контент к книге "+book.name+ " успешно добавлены");
                $scope.allResults = "Книга успешно загружена";
                $scope.isLoad = false;
            }
        });
        $scope.promise.catch(function () {
            $scope.allResults = "Книга не загружена";
            $scope.isLoad = false;
        });
    };
    $scope.update = function(valid,book){
        if(!valid){
            $scope.isDirty = true;
            return;
        }
        $scope.isEdit = false;
        $scope.isLoad = true;
          bookService.updateBook(book).then(
              function(res){
                  $scope.allResults = res;
                  $scope.isLoad = false;
              },
              function(error){
                  $scope.allResults = error;
                  $scope.isLoad = false;
              }
          );
    };
    $scope.closeAndExit = function () {
        $uibModalInstance.close();
    };
};

var closeModalAuthorCtrl = function ($scope, $uibModalInstance, authorService,author) {
    if(author==undefined){
        $scope.isNew = true;

    }else {
        $scope.isNew = false;
        $scope.author = author;
    }
    $scope.isDirty = false;
    $scope.isEdit = true;
    $scope.isLoad = false;
    $scope.update = function(valid,author){
        if(!valid){
            $scope.isDirty = true;
            return;
        }
        $scope.isEdit = false;
        $scope.isLoad = true;
        authorService.updateAuthor(author)
            .then(
                function (result) {
                    $scope.result = result.data;
                    $scope.isLoad = false;
                },
                function(error){
                    $scope.result = error.data;
                    $scope.isLoad = false;
                }
            );
    };
    $scope.save = function (valid,author) {
        if(!valid){
            $scope.isDirty = true;
            return;
        }
        $scope.isEdit = false;
        $scope.isLoad = true;
        authorService.addAuthor(author)
            .then(
                function (result) {
                    $scope.result = result.data;
                    $scope.isLoad = false;
                },
                function(error){
                    $scope.result = error.data;
                    $scope.isLoad = false;
                }
            );
    };
    $scope.closeAndExit = function () {
        $uibModalInstance.close();
    };
};

var closeModalGenreCtrl = function ($scope, $uibModalInstance, genreService,genre) {
    if(genre==undefined){
        $scope.isNew = true;
    }else {
        $scope.isNew = false;
        $scope.genre = genre;
    }
    $scope.isEdit = true;
    $scope.isLoad = false;
    $scope.isDirty = false;
    $scope.save = function (valid,genre) {
        if(!valid){
            $scope.isDirty = true;
            return;
        }
        $scope.isEdit = false;
        $scope.isLoad = true;
        genreService.addGenre(genre)
            .then(
                function (result) {
                    $scope.result = result.data;
                    $scope.isLoad = false;
                },
                function(error){
                    $scope.result = error.data;
                    $scope.isLoad = false;
                }
            );
    };
    $scope.update = function(valid,genre){
        if(!valid){
            $scope.isDirty = true;
            return;
        }
        $scope.isEdit = false;
        $scope.isLoad = true;
        genreService.updateGenre(genre)
            .then(
                function (result) {
                    $scope.result = result.data;
                    $scope.isLoad = false;
                },
                function(error){
                    $scope.result = error.data;
                    $scope.isLoad = false;
                }
            );
    };
    $scope.closeAndExit = function () {
        $uibModalInstance.close();
    };
};

var closeDeleteCtrl = function($scope,$uibModalInstance,message,isBook,isGenre,isAuthor){
    $scope.isGenre = isGenre;
    $scope.isAuthor = isAuthor;
    $scope.isBook = isBook;
    $scope.message = message;
    $scope.close = function(){
        $uibModalInstance.close();
    };
};



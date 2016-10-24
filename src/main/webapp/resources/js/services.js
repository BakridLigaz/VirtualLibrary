var services = angular.module("Services", ['ngFileUpload'])
services.service("bookService", function ($http, $log, $q, Upload,$rootScope) {
    return {
        addBook: function (book, image, content) {
            var loadBook = $http.post("/books", book)
                    .then(function(result){
                        if(result.data){
                            $log.debug("Книга "+book.name+" успешно загружена");
                            return $q.all([ image.upload = Upload.upload({
                                url: "/books/addImage",
                                file: image,
                                fields: {name: book.name}
                            }), content.upload = Upload.upload({
                                url: "/books/addContent",
                                file: content,
                                fields: {name: book.name}
                            })]);
                        }else return false;
                    },function(error){

                    });
            return loadBook;
        },
        checkBooklistSize: function(){
            return $http.get("/books/sizeListAll");
        },
        checkBookListSizeByAuthor: function(id){
            return $http.get("/books/sizeListByAuthor/"+id);
        },
        checkBookListSizeByGenre: function(id){
            return $http.get("/books/sizeListByGenre/"+id);
        },
        checkBookListSizeBySearch: function(query){
            return $http.get("/books/sizeListBySearch/?query="+query);
        },
        getAllBooks: function (offset,limit) {
            return $http.get("/books?offset="+offset+"&limit="+limit)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (error) {
                        $log.error("ошибка получения данных ");
                        return $q.reject(error);
                    }
                );
        },
        getBooksByRandom: function(limit){
          return $http.get("/books/randomBooks?limit="+limit)
              .then(
                  function (response) {
                      return response.data;
                  },
                  function (error) {
                      $log.error("ошибка получения данных ");
                      return $q.reject(error);
                  }
              );
        },
        getBooksByAuthor: function (offset,limit,authorId) {
            return $http.get("/books/getByAuthor?offset="+offset+"&limit="+limit+"&id="+authorId)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (error) {
                        $log.error("ошибка получения данных ");
                        return $q.reject(error);
                    }
                );
        },
        getBooksByGenre: function (offset,limit,genreId) {
            return $http.get("/books/getByGenre?offset="+offset+"&limit="+limit+"&id="+genreId)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (error) {
                        $log.error("ошибка получения данных ");
                        return $q.reject(error);
                    }
                );
        },
        getBooksBySearch: function (offset,limit,searchQuery) {
            return $http.get("/books/getBySearch/?offset="+offset+"&limit="+limit+"&query="+searchQuery).then(
                function (response) {
                    return response.data;
                },
                function (error) {
                    $log.error("ошибка получения данных ");
                    return $q.reject(error);
                }
            );
        },
        deleteBook: function (id) {
            return $http.delete("/books/"+id)
                .then(
                    function (response) {
                        $log.debug(response.data);
                        return response.data;
                    },
                    function (error) {
                        $log.error("ошибка удаления книги");
                        return $q.reject(error);
                    }
                );
        },
        updateBook: function (book) {
            return $http.put("/books", book)
                .then(
                    function (response) {
                        $log.debug(response.data)
                    },
                    function (error) {
                        $log.error("ошибка обновления ");
                        return $q.reject(error);
                    }
                )
        },
        checkUnique: function(name){
            return $http.get("books/isUnique/"+name).then( function(res) {
                return res.data;
            });
        }

    }
});
services.service("genreService", function ($http, $log, $q) {
    return {
        fetchGenres: function () {
            return $http.get("/genres")
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (error) {
                        $log.error("ошибка получения данных ");
                        return $q.reject(error);
                    }
                )
        },
        addGenre: function (genre) {
            return $http.post("/genres", genre);
        },
        updateGenre: function (genre) {
            return $http.put("/genres", genre);
        },
        deleteGenre: function(genre){
            return $http.delete("genres/"+genre.id);
        },
        checkUnique: function(name){
            return $http.get("genres/isUnique/"+name).then( function(res) {
                return res.data;
            });
        },
        checkUsed: function(id){
            return $http.get("genres/isUsed/"+id).then(function(res){
               return res.data;
            });
        }

    }
});

services.service("authorService", function ($http, $log, $q) {
    return {
        fetchAuthors: function () {
            return $http.get("/authors")
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        $log.error('ошибка получения данных');
                        return $q.reject(errResponse);
                    }
                );
        },
        addAuthor: function (author) {
            return $http.post("/authors", author)
        },
        deleteAuthor: function(author){
            return $http.delete("/authors/"+author.id)
        },
        updateAuthor: function (author) {
            return $http.put("/authors", author)
        },
        checkUsed: function(id){
            return $http.get("authors/isUsed/"+id).then(function(res){
                return res.data;
            });
        },
        checkUnique: function(name){
            return $http.get("authors/isUnique/"+name).then( function(res) {
                return res.data;
            });
        },
    }
});

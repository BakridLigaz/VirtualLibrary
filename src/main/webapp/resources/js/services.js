var services = angular.module("Services", ['ngFileUpload'])
services.service("bookService", function ($http, $log, $q, Upload) {
    return {
        addBook: function (book, image, content) {
            var loadBook = function () {
                $http.post("/books", book)
            };
            image.upload = Upload.upload({
                url: "/books/addImage",
                file: image,
                fields: {name: book.name}

            });
            image.upload.progress(function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                $log.debug('Добавление обложки : ' + progressPercentage + '% ' + evt.config.file.name);
            });
            content.upload = Upload.upload({
                url: "/books/addContent",
                file: content,
                fields: {name: book.name}
            });
            content.upload.progress(function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                $log.debug('Добавление контента: ' + progressPercentage + '% ' + evt.config.file.name);
            });
            return $q.all([loadBook(), image.upload, content.upload]);
        },
        getBookById: function (id) {
            return $http.get("/books/getById/:id").success(function (book) {
                return book;
            });
        },
        getBookByName: function (name) {
            return $http.get("/books/getByName/:name").then(
                function (response) {
                    return response.data;
                },
                function (error) {
                    $log.error("ошибка получения данных ");
                    return $q.reject(error);
                }
            );
        },
        fetchBooks: function () {
            return $http.get("/books")
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
        getBooksByAuthor: function (author) {
            return $http.get("/books/getByAuthor", author)
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
        getBooksByGenre: function (genre) {
            return $http.get("/books/getByGenre", genre)
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
        getBooksBySearch: function (searchQuery) {
            return $http.get("/books/getBySearch/:searchQuery").then(
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
            return $http.delete("/books/:id")
                .then(
                    function (response) {
                        $log.debug(response.data);
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
        }

    }
});
services.service("genreService", function ($http, $log, $q) {
    return {
        getGenre: function (id) {
            $http.get("/genres/:id").success(function (genre) {
                return genre;
            });
        },
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
            return $http.put("/genres", genre)
                .then(
                    function (response) {
                        $log.debug(response.data);
                    },
                    function (errResponse) {
                        $log.error('ошибка обновления жанра');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
});

services.service("authorService", function ($http, $log, $q) {
    return {
        getAuthor: function (id) {
            $http.get("/authors/:id").success(function (author) {
                return author;
            });
        },
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
            return $http.post("/authors", author);
        },
        updateAuthor: function (author) {
            return $http.put("/authors", author)
                .then(
                    function (response) {
                        $log.debug(response.data);
                    },
                    function (errResponse) {
                        $log.error('ошибка обновления автора');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
});

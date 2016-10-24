var Directives = angular.module("Directives",['Services']);
Directives.directive('ngUnique',function(genreService,bookService,authorService){
    return{
        restrict: 'A',
        require: 'ngModel',
        link : function(scope,element,attrs,ngModel){
            element.bind('change keyup',function(event){
                if(!ngModel||!element.val()){return};
                var type = attrs.ngUnique;
                var oper;
                switch (type){
                    case "genre":
                        oper = genreService.checkUnique(element.val());
                        break;
                    case "author":
                        oper = authorService.checkUnique(element.val());
                        break;
                    case "book":
                        oper = bookService.checkUnique(element.val());
                        break;
                }
                    oper.then(function(unique){
                            ngModel.$setValidity('unique',unique);
                    });
            });
        }
    }
});
Directives.directive('ngUsed',function(genreService,bookService,authorService){
    return{
        restrict: 'A',
        link : function(scope,element,attrs){
            var type = attrs.typeEntity;
            var id = attrs.ngUsed;
            var oper;
            switch (type){
                case "genre":
                    oper = genreService.checkUsed(id);
                    break;
                case "author":
                    oper = authorService.checkUsed(id);
                    break;
            }
            oper.then(function(result){
                if(result){
                    element.attr('disabled',true);
                    element.attr('title','Элемент нельзя удалить, поскольку он используется');
                }
            })
        }
    }
});
Directives.directive('addList',function(genreService,authorService,bookService){
   return{
       restrict: 'E',
       link: function(scope,element,attrs,ngModel){
           var typeList = attrs.list;
           switch (typeList){
               case "genres":
                   genreService.fetchGenres().then(function (list) {
                       scope.genreList = list;
                   });
                   break;
               case  "authors":
                   authorService.fetchAuthors().then(function(list){
                       scope.authorList = list;
                   });
                   break;
               case "books":
                   bookService.fetchBooks().then(function(list){
                       scope.bookList = list;
                   });
                   break;
           }

       },
       templateUrl: function(element,attrs){
           var typeList = attrs.list;
           switch (typeList){
               case "genres":
                   return 'static/html/templates/genrelist.html';
               case  "authors":
                   return 'static/html/templates/authorlist.html';
               case "books":
                   return 'static/html/templates/booklist.html';
           }
       }
   }
});
Directives.directive('datePic',function(){
    return{
        restrict:'A',
        link: function(scope,element,attrs,ngModel){
            var picker = $(element).datepicker(
                    {changeYear:true,
                        dateFormat:'dd-mm-yy',
                    yearRange: "1700:2016",
                    maxDate: new Date(),
                    changeMonth:true},
                $.datepicker.regional["ru"]
            );
            element.bind('change',function(event){
                scope.book.publish_date = picker.datepicker('getDate');
            });
        }
    }
});
Directives.directive('checkListSize',function(bookService){
    return {
        restrict:'A',
        link: function(scope,element,attr){
            var typeList = attr.list;
            switch (typeList){
                case "genres":
                    bookService.checkBookListSizeByGenre(attr.checkListSize)
                        .then(
                            function(result){
                                scope.listSize = result.data;
                            }
                        );
                    break;
                case  "authors":
                    bookService.checkBookListSizeByAuthor(attr.checkListSize)
                        .then(
                            function(result){
                                scope.listSize = result.data;
                            }
                        );
                    break;
                case "search":
                    break;
            }

        }
    }
});

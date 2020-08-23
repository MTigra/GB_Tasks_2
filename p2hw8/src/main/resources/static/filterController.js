var app = angular.module('app', ['angularUtils.directives.dirPagination']);
var baseUrl = 'http://localhost:8189/store/api/books/'

app.controller('filterController', function ($scope, $http) {
    fetchData = () => $http.get(baseUrl,
        {
            params: {
                p: $scope.currentPage,
                genre: $scope.genres
            }
        })
        .then((resp) => {
                $scope.pageCount = resp.data.pageCount;
                $scope.books = resp.data.books;
                $scope.currentPage = resp.data.currentPage + 1;
                $scope.isFirst = resp.data.first;
                $scope.isLast = resp.data.last;
            }
        );


    $scope.pageCount = 0
    $scope.currentPage = 1;
    $scope.books = {}
    $scope.isFirst = false;
    $scope.isLast = false;
    fetchData()


    $scope.onPageChanged = function (newPage) {
        console.log("pagechanged" + newPage)
        $scope.currentPage = newPage
        fetchData();
    };

    $scope.filterApplied = function () {
        console.log("submit")
        $scope.currentPage = 1
        $scope.genres = $('input:checked').map(function () {
            return  this.value.toString();
        }).get()
        console.log(typeof $scope.genres)
        fetchData()
    }

    $scope.getNumbers = function (num) {
        return new Array(num)
    }

});
const app = angular.module('app', ['angularUtils.directives.dirPagination']);
const baseUrl = 'http://localhost:8189/books'

app.controller('filterController', function ($scope, $http) {
    fetchData = () => $http.get(baseUrl)
        .params({
            p: $scope.currentPage,
            genre: $scope.genre
        })
        .then((resp) => {
                $scope.pageCount = resp.data.pageCount;
                        $scope.books = resp.data.books;
                $scope.currentPage = resp.data.currentPage;
            }
        );


    $scope.pageCount = {}
    $scope.books = {}
    fetchData()


    $scope.pageChanged = function (newPage) {
        fetchData();
    };

    $scope.filterApplied = function (){
        fetchData()
    }

});
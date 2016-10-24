var app = angular.module("app", []);

app.controller('mainController', ['$scope','$http', function($scope,$http) {
    $http.get("rest/articles")
    .then(function(response) {
        $scope.articles = response.data;
    });
}]);

var app = angular.module("app", []);

app.controller('mainController', ['$scope','$http', function($scope,$http) {
    $http.get("http://127.0.0.1:8080/rest/articles/name?thin=true")
    .then(function(response) {
        alert(response);
    });
}]);

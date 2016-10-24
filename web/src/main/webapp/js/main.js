var app = angular.module("app", [])
    .service('articleService', function () {
        return {
            format: function (date) {
                return date.dayOfMonth + '.' + date.monthValue + '.' + date.year;
            }
        };
    })
    .controller('mainController', ['$scope', '$http', 'articleService', function ($scope, $http, articleService) {
        $scope.articleService = articleService;

        $http.get("rest/articles")
            .then(function (response) {
                $scope.articles = response.data;
            });

    }]);


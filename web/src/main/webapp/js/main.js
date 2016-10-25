(function() {
    $(".nav-articles").addClass("active-nav");

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


            var desiredTag = getParameterByName('tag');
            var url = desiredTag == null ? "rest/articles" : "rest/articles?tag=" + desiredTag;

            $http.get(url)
                .then(function (response) {
                    $scope.articles = response.data;
                    $scope.filterTag = desiredTag;
                });


            function getParameterByName(name) {
                name = name.replace(/[\[\]]/g, "\\$&");
                var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                    results = regex.exec(window.location.href);
                if (!results) return null;
                if (!results[2]) return '';
                return decodeURIComponent(results[2].replace(/\+/g, " "));
            }
        }])
})();
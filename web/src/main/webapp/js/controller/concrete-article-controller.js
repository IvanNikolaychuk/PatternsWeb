angular.module("app")
    .controller('articleController', ['$scope', '$http', 'articleService',
        function ($scope, $http, articleService) {
            var $mainCtrl = this;
            $mainCtrl.articleService = articleService;
            var $locationPath = window.location.pathname;
            var $articleName = $locationPath.substring($locationPath.lastIndexOf('/') + 1, $locationPath.length);

            $http.get("rest/articles/" + $articleName)
                .then(function (response) {
                    $mainCtrl.article = response.data;
                    $mainCtrl.code = response.data.articleCode;
                });
        }]);
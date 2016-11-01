angular.module("app")
    .controller('articleController', ['$scope', '$http', 'articleService', 'tabsMaker',
        function ($scope, $http, articleService, tabsMaker) {
            var $mainCtrl = this;
            $mainCtrl.articleService = articleService;
            var $locationPath = window.location.pathname;
            var $articleName = $locationPath.substring($locationPath.lastIndexOf('/') + 1, $locationPath.length);

            $http.get("rest/articles/" + $articleName)
                .then(function (response) {
                    tabsMaker.make(response.data.articleCode);
                    $mainCtrl.article = response.data;
                });
        }]);
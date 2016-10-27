angular.module("app")
    .controller('articleController', ['$scope', '$http', 'articleService',
        function ($scope, $http, articleService) {
            var $mainCtrl = this;
            $mainCtrl.articleService = articleService;
            var $articleName = window.location.pathname.replace(new RegExp('/', 'g'), "");

            $http.get("rest/articles/" + $articleName)
                .then(function (response) {
                    $mainCtrl.article = response.data;
                    $mainCtrl.code = response.data.articleCode;
                });
        }]);
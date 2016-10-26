angular.module("app")
    .controller('mainController', ['$scope', '$http', 'articleService', 'tagService',
        function ($scope, $http, articleService, tagService) {
            var $mainCtrl = this;
            $mainCtrl.articleService = articleService;
            $mainCtrl.tagService = tagService;


            var desiredTag = getParameterByName('tag');
            var url = desiredTag == null ? "rest/articles" : "rest/articles?tag=" + desiredTag;

            $http.get(url)
                .then(function (response) {
                    $mainCtrl.articles = response.data;
                    $mainCtrl.filterTag = desiredTag;
                });


            function getParameterByName(name) {
                name = name.replace(/[\[\]]/g, "\\$&");
                var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                    results = regex.exec(window.location.href);
                if (!results) return null;
                if (!results[2]) return '';
                return decodeURIComponent(results[2].replace(/\+/g, " "));
            }
        }]);
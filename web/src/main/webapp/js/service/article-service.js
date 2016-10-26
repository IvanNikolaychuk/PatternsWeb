angular.module("app").service('articleService', function () {
    function format(date) {
        return date.dayOfMonth + '.' + date.monthValue + '.' + date.year;
    }

    function selected(articleName) {
        window.location = window.location.origin + "/" + articleName;
    }

    return {
        format : format,
        selected : selected
    };
});
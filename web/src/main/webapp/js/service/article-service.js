angular.module("app").service('articleService', function () {
    function format(date) {
        if (!date) {
            return '';
        }

        return date.dayOfMonth + '.' + date.monthValue + '.' + date.year;
    }

    function selected(articleName) {
        window.location = window.location.origin + "/" + articleName;
    }

    function toMainPage() {
        window.location = window.location.origin;
    }

    return {
        format : format,
        selected : selected,
        toMainPage : toMainPage
    };
});
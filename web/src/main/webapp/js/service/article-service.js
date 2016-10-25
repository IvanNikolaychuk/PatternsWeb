angular.module("app").service('articleService', function () {
    function format(date) {
        return date.dayOfMonth + '.' + date.monthValue + '.' + date.year;
    }
    return {
        format : format
    };
});
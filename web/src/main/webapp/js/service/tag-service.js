angular.module("app").service('tagService', function () {
    function clicked(tagName) {
        window.location = window.location.origin + "?tag=" + tagName;
    }
    return {
        clicked : clicked
    }
});
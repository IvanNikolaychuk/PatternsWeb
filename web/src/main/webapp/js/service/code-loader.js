angular.module("app").service('tabsMaker', function () {
    function make(code) {
        var $tabs = $(".tabs");

        for(var counter = 0; counter < $tabs.length; counter++) {
            var $header = $($tabs[counter]).children(".header")[0];
            var $content = $($tabs[counter]).children(".content")[0];
            var $singleClasses = code.sections[counter].singleClasses;

            var i = 0;
            for (i = 0; i < $singleClasses.length; i++) {
                var className = $singleClasses[i].name;
                $($header).append(
                    "<li>" +
                    "<a href='#" + className + "'>" + className + "</a>" +
                    "</li>");
            }

            for (i = 0; i < $singleClasses.length; i++) {
                $($content).append(
                    "<div id='" + $singleClasses[i].name + "'>" +
                    "<pre><p>" + $singleClasses[i].code + "</p></pre>" +
                    "</div>");
            }

            $($tabs[counter]).tabs()
        }
    }

    return {
        make : make
    };
});
$(function () {
    $("#logo").click(function () {
        window.location.href = window.location.origin;
    });

    $(".nav-articles").click(function () {
        window.location.href = window.location.origin;
    });

    $(".nav-about-me").click(function () {
        window.location.href = window.location.origin + '/about';
    });

    var $initialActiveNav = $(".active-nav");

    $(".nav").hover(function () {
        $(".active-nav").removeClass("active-nav");

        $("header").mouseout(function () {
            $(".active-nav").removeClass("active-nav");
            $initialActiveNav.addClass("active-nav");
        });
    });
});

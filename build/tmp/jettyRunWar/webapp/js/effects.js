$(document).ready(function () {
    (".list").mouseout(function () {
        $(".list span").attr("data-hidden", "hidden");
    });
    (".list").mouseover(function () {
        $(".list span").attr("data-hidden", "show");
    });
}); 
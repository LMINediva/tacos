$(document).ready(function () {
    $.ajax({
        url: "/design/recent"
    }).then(function (data) {
        if (data.length > 0) {
            $.get('mustache/recents.mst', function (template) {
                let rendered = Mustache.render(template, data);
                $('#recents').html(rendered);
            });
        } else {
            $('#recents').html("<p>目前还没有墨西哥卷饼被设计出来…" +
                "第一个创造美味的杰作!</p>");
        }
    });
});
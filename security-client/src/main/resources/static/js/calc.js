$(document).ready(function () {
    $('#calc').click(function (e) {
        e.preventDefault()
        var nums = $("#num").val()
        $.ajax({
            url: "ms-data",
            type: $('#calcForm').attr('method'),
            data: nums,
            success: function (e) { $("#result").show(); $("#result").text(e) },
            error: function (e) { alert("no funciono ):"); console.log(e) }
        });
    });
})
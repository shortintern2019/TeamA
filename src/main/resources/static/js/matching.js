$('#send-button').on('click', function() {

    if ($("[name=role]:checked").length == 0) {
        alert("相談を聞く側か、される側か選択してください。");
        return
    }

    if ($("[name=genre]:checked").length == 0) {
        alert("相談するジャンルを1つ以上選択してくだい");
        return
    }

    $('#matching-preference-form').submit();
});
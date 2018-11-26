function getIdChess(){

    var information = {"id": $("#idInput").value};
    console.log(information["id"]);
    /*
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/ajax/getChessById",
        data : JSON.stringify(information),
        dataType : "json",
        timeout : 10000,
        success : function(data) {
            console.log("SUCCCCESSS IT SUCKS, TO MUCH PRESS.");
        },
        error : function(data) {
            console.log("ERROR BIG FELLA.");
        },
        done : function(e) {
            console.log("FINISHED.tttt");
        }
    });
    */
});

$(document).ready(function(){



});

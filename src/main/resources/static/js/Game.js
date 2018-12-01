
function create_board_spots() {
  $("#chessBoard").empty();
  for (let i = 0; i < 8; i++) {
    $("#chessBoard").append("<div class='c-row' id='chess-row-" + i + "' ></div>");
    for (let j = 0; j < 8; j++) {
      if ((i + j) % 2 == 0) {
        $("#chess-row-" + i).append("<div class='c-piece light-brown-background' id='chess-piece-" + i + "-" + j + "' >&nbsp;</div>");
      }
      else {
        $("#chess-row-" + i).append("<div class='c-piece dark-brown-background' id='chess-piece-" + i + "-" + j + "' >&nbsp;</div>");
      }

    }
  }
}

function write_board(string_board) {
    //let map = {"K": }
    let b_arr = string_board.split("\n");
    for (let i = 0; i < 8; i++) { b_arr[i] = b_arr[i].split(""); }

    for (let i = 7; i > -1; i--) {
        for (let j = 7; j > -1; j--) {
            if (b_arr[i][j] == " ") {
              $("#chess-piece-" + i + "-" + j).html("&nbsp;");
            }
            else {
              $("#chess-piece-" + i + "-" + j).html(b_arr[i][j]);
            }
        }
    }
}

$(document).ready( function() {
    var information = {"id": $("#chessId").text()};

    console.log("9af");
    console.log(information["id"]);
    console.log("9af");

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/ajax/getChessById",
        data : JSON.stringify(information),
        dataType : "json",
        timeout : 10000,
        success : function(data) {
            console.log(data);
        },
        error : function(data) {
            console.log("ERROR BIG FELLA.");
        },
        done : function(e) {
            console.log("FINISHED.tttt");
        }
    });

    create_board_spots();
    //write_board("RNBQKBNR\nPPPPPPPP\n        \n        \n        \n        \npppppppp\nrnbqkbnr");
});

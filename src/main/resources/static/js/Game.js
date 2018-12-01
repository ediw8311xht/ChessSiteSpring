
function create_board_spots() {
  $("#chessBoard").empty();
  for (let i = 7; i > -1; i--) {
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

    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            if (b_arr[i][j] == " " || b_arr[i][j] == ".") {
              $("#chess-piece-" + i + "-" + j).html("&nbsp;");
            }
            else {
              $("#chess-piece-" + i + "-" + j).html(b_arr[i][j]);
            }
        }
    }
}

function get_board() {
    let board = "";
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            board += $("#chess-piece-" + i + "-" + j).text();
        }
        if (i == 7) {
            return board;
        }
        board += "\n";
    }
}




var last_touch = false;
var current_touch = false;
var information = {};

function on_submit_move() {
    $.get("/ajax/movePiece?id=" + information["id"] +
          "&move1=" + last_touch[0].toString() + last_touch[1].toString() +
          "&move2=" + current_touch[0].toString() + current_touch[1].toString(),
          function(data) { console.log(data); }
      );
}

function on_cancel_move() {

}

function click_move(event) {
    console.log(last_touch);
    let id_arr = $(event.currentTarget).attr("id").split("-");
    if (last_touch) {
        if (last_touch[0] === id_arr[2] && last_touch[1] === id_arr[3]) {
            console.log(5555);
            last_touch = false;
        }
        else {
            console.log($("#chess-piece-" + last_touch[0] + "-" + last_touch[1]).text());
            $("#" + $(event.currentTarget).attr("id")).html($("#chess-piece-" + last_touch[0] + "-" + last_touch[1]).html());
            $("#chess-piece-" + last_touch[0] + "-" + last_touch[1]).html("&nbsp;");
            current_touch = [id_arr[2], id_arr[3]];
            $(".c-piece").off();
            $("#submitMove").one("click", on_submit_move);
            //$("#cancelMove").one("click", on_cancel_move);
        }
    }
    else {
      last_touch = [id_arr[2], id_arr[3], $(event.currentTarget).text()];
    }
}

function start_game() {
    $(".c-piece").on("click", click_move);
}

$(document).ready( function() {
    information["id"] = $("#chessId").text();

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
            write_board(data["board"]);
            start_game();
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

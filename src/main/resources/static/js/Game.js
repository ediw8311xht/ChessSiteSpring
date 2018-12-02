
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



function reverse_move() {
    $("#chess-piece-" + last_touch[0] + "-" + last_touch[1]).html(last_touch[2]);
    $("#chess-piece-" + current_touch[0] + "-" + current_touch[1]).html(current_touch[2]);
}

function on_submit_move() {
    $.get("/ajax/movePiece?id=" + information["id"] +
          "&move1=" + last_touch[0].toString() + last_touch[1].toString() +
          "&move2=" + current_touch[0].toString() + current_touch[1].toString(),
          function(data) {
              submit_cancel_toggle("off");
              if (!data) {
                  reverse_move();
              }
              start_game();
          }
     );
}

function on_cancel_move() {
    submit_cancel_toggle("off");
    reverse_move();
    start_game();
}

function click_move(event) {
    console.log(last_touch);
    let id_arr = $(event.currentTarget).attr("id").split("-");
    if (last_touch) {
        if (last_touch[0] === id_arr[2] && last_touch[1] === id_arr[3]) {
            last_touch = false;
        }
        else {
            console.log($("#chess-piece-" + last_touch[0] + "-" + last_touch[1]).text());
            current_touch = [id_arr[2], id_arr[3], $(event.currentTarget).html()];
            $("#" + $(event.currentTarget).attr("id")).html($("#chess-piece-" + last_touch[0] + "-" + last_touch[1]).html());
            $("#chess-piece-" + last_touch[0] + "-" + last_touch[1]).html("&nbsp;");
            $(".c-piece").off();
            submit_cancel_toggle("on");
        }
    }
    else {
      last_touch = [id_arr[2], id_arr[3], $(event.currentTarget).html()];
    }
}

function submit_cancel_toggle(on_off) {
    if (on_off === "on") {
        $(document.body).append("<button id='submitMove'>Submit</button>");
        $(document.body).append("<button id='cancelMove'>Cancel</button>");
        $("#submitMove").one("click", on_submit_move);
        $("#cancelMove").one("click", on_cancel_move);
    }
    else if (on_off === "off") {
        $("#submitMove").remove();
        $("#cancelMove").remove();
    }
}

function start_game() {
    last_touch = false;
    current_touch = false;
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

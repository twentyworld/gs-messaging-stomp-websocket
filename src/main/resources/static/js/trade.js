/**
 * Created by wanglingna on 17/8/9.
 */
window.onload=function(){
    editHeight();
}
window.onresize = function(){
    editHeight();
}

function editHeight(){
    var clientHeight = document.body.clientHeight;
    var candlestick = document.getElementById('candlestick');
    var order_book = document.getElementById('order_book');
    var market_trades = document.getElementById('market_trades');
    candlestick.style.height = clientHeight-market_trades.clientHeight + "px";
    order_book.style.height = clientHeight-market_trades.clientHeight + "px";
}

function orderSubmit(ops){
    if(ops==1) {
        //buy
        var params;
        var price = document.getElementById("order_bid_price").value;
        var quantity = document.getElementById("order_bid_origin_volume").value;
        var traderId = document.getElementById("traderId").innerHTML;
        params = {"price":price,"quantity":quantity,"traderId":traderId};
        console.log(params);

    }else {
        //sell

    }
}

var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            //showGreeting(JSON.parse(greeting).content);
            greeting = JSON.parse(greeting.body)
            showGreeting(greeting.content);

        });
    });
}

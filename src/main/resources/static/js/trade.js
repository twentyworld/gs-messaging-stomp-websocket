/**
 * Created by wanglingna on 17/8/9.
 */
window.onload=function(){
    editHeight();
    getDatabySymbol();
    connect();
}
window.onresize = function(){
    editHeight();
}
var socket = new SockJS('/gs-guide-websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', function (greeting) {
        //showGreeting(JSON.parse(greeting).content);
        // greeting = JSON.parse(greeting.body)
        // showGreeting(greeting.record[0].time);
    });
});

function editHeight(){
    var clientHeight = document.body.clientHeight;
    var candlestick = document.getElementById('candlestick');
    var order_book = document.getElementById('order_book');
    var market_trades = document.getElementById('market_trades');
    candlestick.style.height = clientHeight-market_trades.clientHeight + "px";
    order_book.style.height = clientHeight-market_trades.clientHeight + "px";
}

// function orderSubmit(ops){
//     if(ops==1) {
//         //buy
//         var params;
//         var price = document.getElementById("order_bid_price").value;
//         var quantity = document.getElementById("order_bid_origin_volume").value;
//         var traderId = document.getElementById("traderId").innerHTML;
//         params = {"price":price,"quantity":quantity,"traderId":traderId};
//
//
//     }else {
//         //sell
//
//     }
// }


function getDatabySymbol() {
    var symbol = getUrlParam('symbol');
    $('#symbolFlag1').val(symbol);
    $('#symbolFlag2').val(symbol);
    $.ajax({
        url:'http://localhost:9001/getRecordBySymbol',
        type:'post',
        data: {"symbol":symbol},
        dataType:'json',
        success:function (data) {
            $(data).each(function(index,obj){
                var oldHtml = $("#tradeHistory").html();
                var newHtml = '<tr><td class="time text-left col-xs-7"><div style="display: block">'+obj.times+'</div></td> <td class="my text-left col-xs-2"><div style="display:none;"><i class="fa fa-star"></i></div></td> <td class="price text-right col-xs-7 text-down"><div style="display: block">'+obj.price+'</div></td> <td class="volume text-right col-xs-8"><div style="display: block">'+obj.quantity+'</div></td> </tr>';
                $("#tradeHistory").html(oldHtml+newHtml);
            })
        }
    });
    $.ajax({
        url:'http://localhost:9001/getOrderBookBySymbol',
        type:'post',
        data: {"symbol":symbol},
        dataType:'json',
        success:function (data) {
            console.log(data);
            $(data).each(function(index,obj){
                var price = parseFloat(obj.price.toFixed(4));
                var quantity = parseInt(obj.quantity);
                var value = (price * quantity).toFixed(4);
                if(obj.isBuy == 1){
                    var oldHtml = $('#bidOrder').html();
                    var newHtml = '<tr><td class="amount col-xs-8">'+value+'</td> <td class="volume col-xs-8">'+obj.quantity+'</td> <td class="price col-xs-8 text-up"><div style="display: block;"> <b>'+price+'</b> </div></td> </tr>';
                    $('#bidOrder').html(oldHtml+newHtml);
                }else{
                    var price = parseFloat(obj.price.toFixed(4));
                    var quantity = parseInt(obj.quantity);
                    var value = (price * quantity).toFixed(4);
                    var oldHtml = $('#askOrder').html();
                    var newHtml = '<tr><td class="price col-xs-8 text-left text-down"><div style="display: block;"><b>'+price+'</b> </div></td> <td class="volume col-xs-8 text-left">'+obj.quantity+'</td> <td class="amount col-xs-8 text-left">'+value+'</td> </tr>';
                    $('#askOrder').html(oldHtml+newHtml);
                }
            })
        }
    });


}
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
function sendOrder() {
    stompClient.send("/app/addOrder", {}, JSON.stringify({
        'name': $("#name").val()}));
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
            showGreeting(greeting.record[0].time);
        });
    });
}

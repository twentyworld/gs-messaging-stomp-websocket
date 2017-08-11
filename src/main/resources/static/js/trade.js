/**
 * Created by wanglingna on 17/8/9.
 */
var symbol = getUrlParam('symbol');
window.onload=function(){
    editHeight();
    getOrderBook();
    getOrderHistory();
    connect();
    showHeader();
    $('#order_bid_price').attr("disabled",true);
    $('#order_bid_origin_volume').attr("disabled",true);
    $('#order_ask_price').attr("disabled",true);
    $('#order_ask_origin_volume').attr("disabled",true);
    $('#order_ask_total').attr("readOnly", true);
    $('#order_bid_total').attr("readOnly", true);

}
window.onresize = function(){
    editHeight();
}

function showHeader() {
    $.ajax({
        url:'http://localhost:9001/getRandomId',
        tyep:'get',
        dataType:'json',
        success:function(traderId){
            console.log(traderId.id);
            $('#traderId').text(traderId.id);
        }
    });
    $.ajax({
        url: 'http://localhost:9001/getFluctuationRange',
        type: 'get',
        dataType: 'json',
        success: function (market){
            $(market).each(function (index, obj) {
                if(symbol==obj.symbol){
                    $('#market_name').html(obj.symbol);
                    $('#market_change').html(obj.range  + '%');
                    $('#market_volume').html(obj.quantity);
                }
            });

        }
    })
}
function editHeight(){
    var clientHeight = document.body.clientHeight;
    var candlestick = document.getElementById('candlestick');
    var order_book = document.getElementById('order_book');
    var market_trades = document.getElementById('market_trades');
    candlestick.style.height = clientHeight-market_trades.clientHeight + "px";
    order_book.style.height = clientHeight-market_trades.clientHeight + "px";
}

function getOrderBook() {
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

//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}
function getMyDate(str){
    var oDate = new Date(str),
        //oYear = oDate.getFullYear(),
        //oMonth = oDate.getMonth()+1,
        //oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};


function getOrderHistory() {
    $.ajax({
        url:'http://localhost:9001/getRecordBySymbol',
        type:'post',
        data: {"symbol":symbol},
        dataType:'json',
        success:function (data) {
            $(data).each(function(index,obj){
                var time = getMyDate(obj.times);
                var price = obj.price.toFixed(4);
                var oldHtml = $("#tradeHistory").html();
                var newHtml = '<tr><td class="time text-left col-xs-7"><div style="display: block">'+time+'</div></td> <td class="my text-left col-xs-2"><div style="display:none;"><i class="fa fa-star"></i></div></td> <td class="price text-right col-xs-7 text-down"><div style="display: block">'+price+'</div></td> <td class="volume text-right col-xs-8"><div style="display: block">'+obj.quantity+'</div></td> </tr>';
                $("#tradeHistory").html(oldHtml+newHtml);
            })
        }
    });
}
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function forbidInput(value,isBuy) {
    if(isBuy == 1){
        if (value== "IOC"||value=="marketOrders") {
            $('#order_bid_price').attr("disabled",true);
        }else if (value=="FOK"||value=="GTC") {
            $('#order_bid_price').attr("disabled",false);
            $('#order_bid_origin_volume').attr("disabled",false);
        }else {
            $('#order_bid_price').attr("disabled",true);
            $('#order_bid_origin_volume').attr("disabled",true);
        }
    }else {
        if(value== "IOC"||value== "marketOrders") {
            $('#order_ask_price').attr("disabled",true);
        }else if (value=="FOK"||value=="GTC") {
            $('#order_ask_price').attr("disabled",false);
            $('#order_ask_origin_volume').attr("disabled",false);
        }else {
            $('#order_ask_price').attr("disabled",true);
            $('#order_ask_origin_volume').attr("disabled",true);
        }
    }
}
function sendOrder(ops) {
    // alert('您的订单已提交!');
    if(ops==1){
        var userId = $('#traderId').html();
        //alert(userId);
        var isBuy = $('#bid_ops').val();
        //alert(isBuy);
        var symbol = getUrlParam('symbol');
        //alert(symbol);
        var price = $('#order_bid_price').val();
        //alert(price);
        var amount = $('#order_bid_origin_volume').val();
        //alert(amount);
        var strategy = $('#bid_strategy option:selected').val();
        //alert(strategy);
        stompClient.send("/app/addOrder", {}, JSON.stringify({
            'id':userId,
            'isBuy':isBuy,
            'price':price,
            'quantity':amount,
            'symbol':symbol,
            'strategy':strategy
        }));
        addMyOrder(isBuy,price,amount,$('#order_bid_total').val());
        $('#order_bid_price').val('');
        $('#order_bid_origin_volume').val('');
        $('#order_bid_total').val('');

    }
    if(ops==0){
        var userId = $('#traderId').html();
        //alert(userId);
        var isBuy = $('#ask_ops').val();
        //alert(isBuy);
        var symbol = getUrlParam('symbol');
        //alert(symbol);
        var price = $('#order_ask_price').val();
        //alert(price);
        var amount = $('#order_ask_origin_volume').val();
        //alert(amount);
        var strategy = $('#ask_strategy option:selected').val();
        //alert(strategy);
        stompClient.send("/app/addOrder", {}, JSON.stringify({
            'id':userId,
            'isBuy':isBuy,
            'price':price,
            'quantity':amount,
            'symbol':symbol,
            'strategy':strategy
        }));
        addMyOrder(isBuy,price,amount,$('#order_ask_total').val());
        $('#order_ask_price').val('');
        $('#order_ask_origin_volume').val('');
        $('#order_ask_total').val('');

    }

}
function setConnected(connected) {
    $("#order_book").prop("disabled", connected);

}
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/addOrder', function (data) {
            console.log(data);
            var jsons = JSON.parse(data.body);
            var record = jsons.record;
            var orderBookBid = jsons.orderBookBid;
            var orderBookAsk = jsons.orderBookAsk;
            $('#bidOrder').html('');
            renewOrderBook(orderBookBid);
            $('#askOrder').html('');
            renewOrderBook(orderBookAsk);
            $("#tradeHistory").html('');
            renewOrderHistory(record);
        });
        stompClient.subscribe('/user/'+ $('#traderId').html() + '/message', function (data) {
            var jsons = JSON.parse(data.body);
            if(jsons.reject=="true"){
                alert("Your Order Transaction failed!");


            }else {
                alert("Your Order Transaction was Successful!");
                var rowRed = "rowRed";
                $("#myOrders").children().children().has("td").addClass(rowRed);
            }
        });
    });
}

function computeTotal(isBuy) {
    if(isBuy==1) {
        var price = parseFloat($('#order_bid_price').val());
        var amount = parseInt($('#order_bid_origin_volume').val());
        var total = 0;
        if(price>0&&amount>0) {
            total = (price * amount).toFixed(2);
        }
        $('#order_bid_total').val(total);
    }else {
        var price = parseFloat($('#order_ask_price').val());
        var amount = parseInt($('#order_ask_origin_volume').val());
        var total = 0;
        if(price>0&&amount>0) {
            total = (price * amount).toFixed(2);
        }
        $('#order_ask_total').val(total);
    }

}

function renewOrderHistory(record) {
    $(record).each(function(index,obj){
        var time = getMyDate(obj.times);
        var price = obj.price.toFixed(4);
        var oldHtml = $("#tradeHistory").html();
        var newHtml = '<tr><td class="time text-left col-xs-7"><div style="display: block">'+time+'</div></td> <td class="my text-left col-xs-2"><div style="display:none;"><i class="fa fa-star"></i></div></td> <td class="price text-right col-xs-7 text-down"><div style="display: block">'+price+'</div></td> <td class="volume text-right col-xs-8"><div style="display: block">'+obj.quantity+'</div></td> </tr>';
        $("#tradeHistory").html(oldHtml+newHtml);
    })
}

function renewOrderBook(orderBook) {
    $(orderBook).each(function(index,obj){
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

function addMyOrder(isBuy, price, amount, total){
    var status = isBuy == 1 ? 'Buy' : 'Sell';
    var oldHtml = $("#myOrders").html();
    var newHtml = '<tr><td class="time text-left col-xs-7"><div style="display: block">'+status+'</div></td> <td class="my text-left col-xs-2"><div style="display:none;"><i class="fa fa-star"></i></div></td> <td class="price text-right col-xs-3 text-down"><div style="display: block">'+price+'</div></td> <td class="price text-right col-xs-5 text-down"><div style="display: block">'+amount+'</div></td> <td class="volume text-right col-xs-8"><div style="display: block">'+total+'</div></td> </tr>';
    $("#myOrders").html(oldHtml+newHtml);

}
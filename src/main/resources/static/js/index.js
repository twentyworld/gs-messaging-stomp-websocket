/**
 * Created by wanglingna on 17/8/7.
 */
window.onload = function () {
    connect();
};
$(function () {
    $.ajax({
        url: 'http://localhost:9001/getFluctuationRange',
        type: 'get',
        dataType: 'json',
        success: function (change) {
            $(change).each(function (index, obj) {
                // alert(index + "-" + obj.symbol + "-" + obj.range+ "-" + obj.latestPrice+ "-" + obj.quantity);

                if(obj.range >= 0){
                    console.log(obj.range);
                    var range = (obj.range).toFixed(2) + "%";
                    console.log(range);
                    var oldHtml = $('#riseBoard').html();
                    var newHtml = '<tr class="stock_bar2"><td class="stock_code"><a href="/trade?symbol='+obj.symbol+'">'+obj.symbol+'</a></td> <td class="stock_price">'+obj.latestPrice+'</td> <td class="stock_change rise">'+range+'</td> <td class="stock_volume">'+obj.quantity+'</td></tr>'
                    $('#riseBoard').html(oldHtml+newHtml);

                }else{
                    console.log(obj.range);
                    var range = (obj.range).toFixed(2) + "%";
                    console.log(range);
                    var oldHtml = $('#dropBoard').html();
                    var newHtml = '<tr class="stock_bar2"><td class="stock_code"><a href="/trade?symbol='+obj.symbol+'">'+obj.symbol+'</a></td> <td class="stock_price">'+obj.latestPrice+'</td> <td class="stock_change drop">'+range+'</td> <td class="stock_volume">'+obj.quantity+'</td></tr>'
                    $('#dropBoard').html(oldHtml+newHtml);
                }
            });

        }
    })

})
function setConnected(connected) {
    $("#riseBoard").prop("disabled", connected);

}
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/addOrder', function (data) {
            var jsons = JSON.parse(data.body);
            var stockRange = jsons.stockRange;
            $('#riseBoard').html('');
            $('#dropBoard').html('');
            console.log(stockRange);
            renewRiseBoard(stockRange);
        });

    });
}
function renewRiseBoard(stockRange) {
    $(stockRange).each(function (index, obj) {
        var symbol = obj.symbol;
        var range = obj.range;
        var latestPrice = obj.latestPrice;
        var quantity = obj.quantity;
        if(range >= 0){
            var change = range.toFixed(2) + "%";
            var oldHtml = $('#riseBoard').html();
            var newHtml = '<tr class="stock_bar2"><td class="stock_code"><a href="/trade?symbol='+obj.symbol+'">'+obj.symbol+'</a></td> <td class="stock_price">'+obj.latestPrice+'</td> <td class="stock_change rise">'+change+'</td> <td class="stock_volume">'+obj.quantity+'</td></tr>'
            $('#riseBoard').html(oldHtml+newHtml);

        }else{
            var change = range.toFixed(2) + "%";
            var oldHtml = $('#dropBoard').html();
            var newHtml = '<tr class="stock_bar2"><td class="stock_code"><a href="/trade?symbol='+obj.symbol+'">'+obj.symbol+'</a></td> <td class="stock_price">'+obj.latestPrice+'</td> <td class="stock_change drop">'+change+'</td> <td class="stock_volume">'+obj.quantity+'</td></tr>'
            $('#dropBoard').html(oldHtml+newHtml);
        }
    });

}
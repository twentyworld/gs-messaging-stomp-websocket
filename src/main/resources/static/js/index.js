/**
 * Created by wanglingna on 17/8/7.
 */



$(function () {
    $.ajax({
        url: 'http://localhost:9001/getStockUpsAndDowns',
        type: 'get',
        dataType: 'json',
        success: function (change) {
            $(change).each(function (index, obj) {
                // alert(index + "-" + obj.symbol + "-" + obj.range+ "-" + obj.latestPrice+ "-" + obj.quantity);
                if(obj.range >= 0){
                    var oldHtml = $('#riseBoard').html();
                    var newHtml = '<tr class="stock_bar2"><td class="stock_code"><a href="/trade?symbol='+obj.symbol+'">'+obj.symbol+'</a></td> <td class="stock_price">'+obj.latestPrice+'</td> <td class="stock_change rise">'+obj.range+'</td> <td class="stock_volume">'+obj.quantity+'</td></tr>'
                    $('#riseBoard').html(oldHtml+newHtml);

                }else{
                    var oldHtml = $('#dropBoard').html();
                    var newHtml = '<tr class="stock_bar2"><td class="stock_code"><a href="/trade?symbol='+obj.symbol+'">'+obj.symbol+'</a></td> <td class="stock_price">'+obj.latestPrice+'</td> <td class="stock_change drop">'+obj.range+'</td> <td class="stock_volume">'+obj.quantity+'</td></tr>'
                    $('#dropBoard').html(oldHtml+newHtml);
                }
            });

        }
    })

})
layui.extend({
	admin: '{/}../../static/js/admin'
});
layui.use(['laydate', 'jquery', 'admin'], function() {
    var laydate = layui.laydate,
        $ = layui.jquery,
        admin = layui.admin;
    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });
});
var table;
layui.use(['table', 'jquery','form', 'admin'], function() {

	table = layui.table,
		$ = layui.jquery,
		form = layui.form,
		admin = layui.admin;

    layer.prompt({
        formType: 1,
        title: '请输入三级密码'
    }, function(value, index, elem){
        $.ajax({
            url:"../../../sj/draw/pwd?thirdPwd="+value
            ,type:"GET"
            ,processData:false
            ,contentType:false
            ,success:function(data_resp){
                if('R000'==data_resp.resultCode){
                    layer.close(index);
                    initWeb();
                }else {
                    layer.msg(data_resp.resultMsg, {
                        icon: 5,
                        time: 1000
                    });
                }
            }
            ,error:function(e){
                layer.msg("服务异常，请稍后再试！", {
                    icon: 5,
                    time: 1000
                });
            }
        });
    });

    function initWeb() {
        $.ajax({
            url:"../../../vip//web/init"
            ,type:"GET"
            ,processData:false
            ,contentType:false
            ,success:function(data_resp){
                if('R000'==data_resp.resultCode){
                    $('#t1').text(data_resp.data.balanceYuan);
                    $('#t2').text(data_resp.data.bWebInYuan);
                    $('#t3').text(data_resp.data.registCoin);
                    $('#t4').text(data_resp.data.point);
                }else {
                    layer.msg(data_resp.resultMsg, {
                        icon: 5,
                        time: 1000
                    });
                }
            }
            ,error:function(e){
                layer.msg("服务异常，请稍后再试！", {
                    icon: 5,
                    time: 1000
                });
            }
        });

    }
});


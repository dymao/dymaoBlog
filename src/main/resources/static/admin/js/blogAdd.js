layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'layedit', 'laydate'],
    function() {
        var form = layui.form(),
            layer = parent.layer === undefined ? layui.layer: parent.layer,
            laypage = layui.laypage,
            layedit = layui.layedit,
            laydate = layui.laydate,
            $ = layui.jquery;
        var editIndex = layedit.build('news_content');
        var addNewsArray = [],addNews;
        form.on("submit(addNews)",
            function(data) {
                if (window.sessionStorage.getItem("add")) {
                    addNewsArray = JSON.parse(window.sessionStorage.getItem("addNews"));
                }
                var isShow = data.field.show == "on" ? "checked": "",
                    newsStatus = data.field.shenhe == "on" ? "审核通过": "待审核";
                addNews = '{"newsName":"' + $(".newsName").val() + '",';
                addNews += '"newsId":"' + new Date().getTime() + '",';
                addNews += '"newsLook":"' + $(".newsLook option").eq($(".newsLook").val()).text() + '",';
                addNews += '"newsTime":"' + $(".newsTime").val() + '",';
                addNews += '"newsAuthor":"' + $(".newsAuthor").val() + '",';
                addNews += '"isShow":"' + isShow + '",';
                addNews += '"newsStatus":"' + newsStatus + '"}';
                addNewsArray.unshift(JSON.parse(addNews));
                window.sessionStorage.setItem("addNews", JSON.stringify(addNewsArray));
                var index = top.layer.msg('数据提交中，请稍候', {
                    icon: 16,
                    time: false,
                    shade: 0.8
                });
                setTimeout(function() {
                        top.layer.close(index);
                        top.layer.msg("文章添加成功！");
                        layer.closeAll("iframe");
                        parent.location.reload();
                    },
                    2000);
                return false;
            })
    })
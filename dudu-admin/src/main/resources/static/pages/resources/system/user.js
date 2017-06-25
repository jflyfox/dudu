var oper = {
    width: 600,
    height: 600,
    form: document.form1,
    jqgrid: function () {

        var fields = $("form").serializeArray();
        var jsonData = {};
        jQuery.each(fields, function (i, field) {
            jsonData[field.name] = field.value;
        });

        $('#jqGrid').jqGrid('setGridParam', {
            postData: jsonData
        }).trigger('reloadGrid');

        return false;
    },
    list: function () {
        oper.jqgrid();
    },
    view: function (id) {
        var url = 'system/user/view/' + id;
        var title = '查看用户';
        Iframe(url, this.width, this.height, title, false, false, false, EmptyFunc);
    },
    add: function () {
        var url = 'system/user/add';
        var title = '添加用户';
        Iframe(url, this.width, this.height, title);
    },
    edit: function (id) {
        var url = 'system/user/edit/' + id;
        var title = '修改用户';
        Iframe(url, this.width, this.height, title);
    },
    del: function (id) {
        var title = '确认要删除该用户信息？';
        var url = 'system/user/delete/' + id;
        Confirm(title, function () {
            ajax_post(url, null, function (data) {
                if (data.code == 0) {
                    oper.list();
                } else {
                    ErrorInfo('操作失败：' + data.msg);
                }
            });
        });
    }
    ,auth : function(id) {
        var url = 'system/user/auth/'+id;
        var title = '授权角色';
        Iframe(url, 320, 400, title);
    }
};

// 初始化
jQuery(function ($) {
    // 加载jqgrid
    var editStr = $('#jqGridEdit').html();
    $('#jqGrid').jqGrid({
        url: "system/user/jqgrid",
        mtype: "POST",
        styleUI: 'Bootstrap',
        datatype: "json",
        colModel: [
            {label: "id", name: 'id', width: 75, hidden: true, key: true},
            {label: "部门", name: 'departName', width: 120, sortable: true, sortable: false},
            {label: "用户名", name: 'username', width: 120, sortable: true},
            {label: "真实姓名", name: 'realname', width: 120, sortable: true},
            {label: "用户类型", name: 'usertype', width: 120, sortable: true,formatter: function(cellValue, options, rowObject) {
                var str = "";
                if(cellValue==1) {
                    str = "管理员";
                } else if(cellValue==2){
                    str = "普通用户";
                } else if(cellValue==3){
                    str = "前台用户";
                } else if(cellValue==4){
                    str = "第三方用户";
                } else if(cellValue==5){
                    str = "API用户";
                }
                return str;
            }},
            {label: "手机号", name: 'tel', width: 120, sortable: true},
            {label: "更新时间", name: 'updateTime', width: 240},
            {label: "更新人", name: 'updateName', width: 160, sortable: false},
            {label: "创建时间", name: 'createTime', width: 240},
            {label: "创建人", name: 'createName', width: 160, sortable: false},
            {
                name: '操作', index: '', width: 280, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    var replaceStr = "\\[id\\]";
                    var replaceShowStr = "\\[show\\]";
                    var buttonStr = editStr;
                    try {
                        buttonStr = buttonStr.replace(/\r\n/g, "");
                        buttonStr = buttonStr.replace(/\n/g, "");
                        buttonStr = buttonStr.replace(new RegExp(replaceStr, 'gm'), rowObject.id);
                        if(rowObject.usertype==1) {
                            buttonStr = buttonStr.replace(new RegExp(replaceShowStr,'gm'),"none" );
                        } else {
                            buttonStr = buttonStr.replace(new RegExp(replaceShowStr,'gm'),"" );
                        }
                    } catch (e) {
                        alert(e.message);
                    }
                    return buttonStr;
                }
            }
        ],
        rownumbers: true,
        sortname: 'id',
        viewrecords: true,
        autowidth: true,
        width: 1050,
        height: 630,
        rowNum: 20,
        caption: "用户列表",
        pager: "#jqGridPager"
    });

    // 宽高自适应
    $("#jqGrid").setGridHeight($(window).height() - 250);
    $("#tree").height($(window).height() - 150);
    $(window).resize(function () {
        $(window).unbind("onresize");
        $("#jqGrid").setGridHeight($(window).height() - 250).jqGrid('setGridWidth', $('#data_content').width() - 5);
        $("#tree").height($(window).height() - 150);
        $(window).bind("onresize", this);
    });

    $('#jqGrid').jqGrid('navGrid', "#jqGridPager", {
        search: false, // show search button on the toolbar
        add: false,
        edit: false,
        del: false,
        refresh: true,
        view: false
    });

    $('#jqGrid').navButtonAdd('#jqGridPager',
        {
            buttonicon: "glyphicon-plus",
            title: "新增",
            caption: "新增",
            position: "first",
            onClickButton: function () {
                oper.add();
            }
        });
});

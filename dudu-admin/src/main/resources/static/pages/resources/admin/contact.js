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
        var url = 'admin/contact/view/' + id;
        var title = '查看联系人';
        Iframe(url, this.width, this.height, title, false, false, false, EmptyFunc);
    },
    add: function () {
        var url = 'admin/contact/add';
        var title = '添加联系人';
        Iframe(url, this.width, this.height, title);
    },
    edit: function (id) {
        var url = 'admin/contact/edit/' + id;
        var title = '修改联系人';
        Iframe(url, this.width, this.height, title);
    },
    del: function (id) {
        var title = '确认要删除该联系人信息？';
        var url = 'admin/contact/delete/' + id;
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
};

// 初始化
jQuery(function ($) {
    // 加载jqgrid
    var editStr = $('#jqGridEdit').html();
    $('#jqGrid').jqGrid({
        url: dudu.ctx + "/admin/contact/jqgrid",
        mtype: "POST",
        styleUI: 'Bootstrap',
        datatype: "json",
        colModel: [
            {label: "id", name: 'id', width: 75, hidden: true, key: true},
            {label: "姓名", name: 'name', width: 120, sortable: true},
            {label: "手机号", name: 'phone', width: 120, sortable: true},
            {label: "Email", name: 'email', width: 120, sortable: true},
            {label: "地址", name: 'addr', width: 120, sortable: true},
            {label: "生日", name: 'birthday', width: 120, sortable: true},
            {label: "说明", name: 'remark', width: 120, sortable: true},
            {label: "是否启用", name: 'enable', width: 120, sortable: true, hidden: true},
            {label: "更新时间", name: 'updateTime', width: 240},
            {label: "更新人", name: 'updateName', width: 160, sortable: false},
            {label: "创建时间", name: 'createTime', width: 240},
            {label: "创建人", name: 'createName', width: 160, sortable: false},
            {
                name: '操作', index: '', width: 200, fixed: true, sortable: false, resize: false,
                formatter: function (cellvalue, options, rowObject) {
                    var replaceStr = "\\[id\\]";
                    var buttonStr = editStr;
                    try {
                        buttonStr = buttonStr.replace(/\r\n/g, "");
                        buttonStr = buttonStr.replace(/\n/g, "");
                        buttonStr = buttonStr.replace(new RegExp(replaceStr, 'gm'), rowObject.id);
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
        caption: "联系人列表",
        pager: "#jqGridPager"
    });

    // 宽高自适应
    $(window).resize(function () {
        $(window).unbind("onresize");
        $("#jqGrid").setGridHeight($(this).height() - 180).jqGrid('setGridWidth', $('#data_wrapper').width() - 5);
        $(window).bind("onresize", this);
    }).resize();

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

var vm = new Vue({
    el:'#data_wrapper',
    data:{
        showList: true,
        title: null,
        menu:{
            parentName:null,
            parentId:0,
            type:1,
            orderNum:0
        }
    },
    methods: {
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.menu = {parentName:null,parentId:0,type:1,orderNum:0};
        },
        update: function (event) {
            var menuId = getSelectedRow();
            if(menuId == null){
                return ;
            }

            $.get("../sys/menu/info/"+menuId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.menu = r.menu;
            });
        },
        del: function (event) {
            var menuIds = getSelectedRows();
            if(menuIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: "../sys/menu/delete",
                    contentType: "application/json",
                    data: JSON.stringify(menuIds),
                    success: function(r){
                        if(r.code === 0){
                            alert('操作成功', function(index){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.menu.menuId == null ? "../sys/menu/save" : "../sys/menu/update";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.menu),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
        }
    }
});
var oper = {
    width : 400,
    height : 500,
    form : document.form1,
    jqgrid : function() {

        var fields = $("form").serializeArray();
        var jsonData = {};
        jQuery.each( fields, function(i, field){
            jsonData[field.name]=field.value;
        });

        $('#jqGrid').jqGrid('setGridParam', {
            postData : jsonData
        }).trigger('reloadGrid');

        return false;
    },
    list : function() {
        oper.jqgrid();
    },
    view : function(id) {
        var url = 'system/dict/view/'+id;
        var title = '查看数据字典类型';
        Iframe(url, this.width, this.height, title, false, false, false, EmptyFunc);
    },
    add : function() {
        var url = 'system/dict/add';
        var title = '添加数据字典类型';
        Iframe(url, this.width, this.height, title);
    },
    edit : function(id) {
        var url = 'system/dict/edit/'+id;
        var title = '修改数据字典类型';
        Iframe(url, this.width, this.height, title);
    },
    del : function(id) {
        var title = '确认要删除该数据字典类型信息？';
        var url = 'system/dict/delete/'+id;
        Confirm(title, function() {
            ajax_post(url,null, function (data) {
                if(data.code==0){
                    oper.list();
                } else {
                    ErrorInfo('操作失败：'+data.msg);
                }
            });
        });
    }
};

// 初始化
jQuery(function($) {
    // 加载jqgrid
    var editStr = $('#jqGridEdit').html();
    $('#jqGrid').jqGrid({
        url:"system/dict/jqgrid",
        mtype: "POST",
        styleUI : 'Bootstrap',
        datatype: "json",
        colModel: [
            {label: "id",name: 'id',width: 75,hidden:true,key:true},
            {label: "名称",name: 'name',width: 120,sortable:true},
            {label: "类型",name: 'type',width: 120,sortable:true},
            {label: "备注",name: 'remark',width: 120,sortable:true},
            {label: "是否启用",name: 'enable',width: 120,sortable:true,hidden:true},
            {label : "更新时间",name: 'updateTime',width: 240},
            {label : "更新人",name: 'updateName',width: 160,sortable:false},
            {label : "创建时间",name: 'createTime',width: 240},
            {label : "创建人",name: 'createName',width: 160,sortable:false},
            {
                name: '操作', index: '', width: 200, fixed: true, sortable: false, resize: false,
                formatter: function(cellvalue, options, rowObject) {
                    var replaceStr = "\\[id\\]";
                    var buttonStr = editStr;
                    try{
                        buttonStr = buttonStr.replace(/\r\n/g,"");
                        buttonStr = buttonStr.replace(/\n/g,"");
                        buttonStr = buttonStr.replace(new RegExp(replaceStr,'gm'),rowObject.id );
                    }catch(e) {
                        alert(e.message);
                    }
                    return buttonStr ;
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
        caption: "数据字典类型列表",
        pager: "#jqGridPager"
    });

    // 宽高自适应
    $("#jqGrid").setGridHeight($(window).height() - 250);
    $("#tree").height($(window).height() - 150);
    $(window).resize(function(){
        $(window).unbind("onresize");
        $("#jqGrid").setGridHeight($(window).height() - 250).jqGrid('setGridWidth', $('#data_content').width() - 5);
        $("#tree").height($(window).height() - 150);
        $(window).bind("onresize", this);
    });

    $('#jqGrid').jqGrid('navGrid',"#jqGridPager", {
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
        onClickButton: function(){
            oper.add();
        }
    });
});

var oper = {
    width : 600,
    height : 600,
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
        var url = 'system/config/view/'+id;
        var title = '查看系统配置表';
        Iframe(url, this.width, this.height, title, false, false, false, EmptyFunc);
    },
    add : function() {
        var type = $('[name="type"]').val();
        var operType = $('[name="operType"]').val();
        var url = 'system/config/add' + "?operType=" + operType + "&type=" + type;
        var title = '添加系统配置表';
        Iframe(url, this.width, this.height, title);
    },
    edit : function(id) {
        var operType = $('[name="operType"]').val();
        var url = 'system/config/edit/' + id + "?operType=" + operType;
        var title = '修改系统配置表';
        Iframe(url, this.width, this.height, title);
    },
    del : function(id) {
        var title = '确认要删除该系统配置表信息？';
        var url = 'system/config/delete/'+id;
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
    ,type : function(){
        var operType = $('#operType').val();
        if(operType==1){
            $('#operType').val(2);
            $('#typeText').val('切换到参数设置');
            $('[name="type"]').hide();
            $("#jqGrid").setGridParam().hideCol("value").hideCol("code").hideCol("typeName");
        } else {
            $('#operType').val(1);
            $('#typeText').val('切换到类型配置');
            $('[name="type"]').show();
            $("#jqGrid").setGridParam().showCol("value").showCol("code").showCol("typeName");
        }
        resetForm();
        $("#jqGrid").setGridHeight($(window).height() - 250).jqGrid('setGridWidth', $('#data_content').width() - 5);
        oper.list();
    }
};

// 初始化
jQuery(function($) {
    //显示Menu索引
    showMenu('page_config');

    // 加载jqgrid
    var editStr = $('#jqGridEdit').html();
    $('#jqGrid').jqGrid({
        url:"system/config/jqgrid",
        mtype: "POST",
        styleUI : 'Bootstrap',
        datatype: "json",
        colModel: [
            {label: "id",name: 'id',width: 75,hidden:true,key:true},
            {label: "名称",name: 'name',width: 200,sortable:true},
            {label: "键",name: 'key',width: 200,sortable:true},
            {label: "值",name: 'value',width: 200,sortable:true},
            {label: "编码",name: 'code',width: 120,sortable:true},
            {label: "类型",name: 'typeName',width: 120,sortable:true},
            {label: "排序号",name: 'sort',width: 120,sortable:true},
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
        caption: "系统配置表列表",
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

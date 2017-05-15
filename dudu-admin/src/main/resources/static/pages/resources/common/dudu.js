var dudu = {
    select: function (_url, $select, _name, _value, _selected) {
        dudu._get(_url, function (result) {
            if (result.code == 0) {
                _selected = _selected || null;
                var arr = result.data || null;
                if (arr != null && arr.length > 0) {
                    var optionStr = "";
                    for (var p in arr) {
                        optionStr += '<option value="';
                        optionStr += arr[p][_value];
                        optionStr += '" ';
                        optionStr += (arr[p][_value] == _selected) ? 'selected' : '';
                        optionStr += '>';
                        optionStr += arr[p][_name];
                        optionStr += '</option>';
                    }
                    $select.append(optionStr);
                }
            } else {
                ErrorInfo('select操作失败：' + result.msg);
            }
        });
    }
    , setValue: function (_url, $element, _value) {
        dudu._get(_url, function (result) {
            if (result.code == 0) {
                var value = result.data[_value];
                if ($element.is('input')) {
                    $element.val($element.val() + value);
                } else if ($element.is('span') || $element.is('dict')) {
                    $element.html($element.html() + value);
                } else {
                    $element.text($element.text() + value);
                }
            } else {
                ErrorInfo('select操作失败：' + result.msg);
            }
        });
    }
    // 数据字典列表
    , dictSelect: function ($select, _dictType, _selected) {
        var url = "system/dictdetail/data?dictType=" + _dictType;
        var name = "name";
        var value = "id";
        dudu.select(url, $select, name, value, _selected);
    }
    // 数据字典Code列表
    , dictCodeSelect: function ($select, _dictType, _selected) {
        var url = "system/dictdetail/data?dictType=" + _dictType;
        var name = "name";
        var value = "code";
        dudu.select(url, $select, name, value, _selected);
    }
    , dictValue: function ($element, _id) {
        var _url = "system/dictdetail/data/" + _id;
        dudu.setValue(_url, $element, "name");
    }
    , dictCode: function ($element, _dictType, _code) {
        var _url = "system/dictdetail/data/0?dictType=" + _dictType + "&code=" + _code;
        dudu.setValue(_url, $element, "name");
    }
    , _get: function (_url, succ_callback) {
        jQuery.ajax({
            type: 'GET',
            url: _url,
            data: null,
            success: succ_callback,
            error: function (html) {
                var flag = (typeof console != 'undefined');
                if (flag) console.log("服务器忙，提交数据失败，代码:" + html.status + "，请联系管理员！");
                alert("服务器忙，提交数据失败，代码:" + html.status + "，请联系管理员！");
            }
        });
    }
    , _post: function (_url, _param, succ_callback) {
        jQuery.ajax({
            type: 'GET',
            url: _url,
            data: _param,
            success: succ_callback,
            error: function (html) {
                var flag = (typeof console != 'undefined');
                if (flag) console.log("服务器忙，提交数据失败，代码:" + html.status + "，请联系管理员！");
                alert("服务器忙，提交数据失败，代码:" + html.status + "，请联系管理员！");
            }
        });
    }
};

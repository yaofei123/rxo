$(document).ready(function () {
    var dictItemList = [];


    var $dictTable = $('#dict_table');
    var _dict_table = $dictTable.bootstrapTable({
        method: 'POST',
        url: "/admin/dict/dictionaryBusiness/list",
        dataField: "records",
        cache: false,               //是否使用缓存，默认为true
        striped: true,              //是否显示行间隔色
        pagination: true,           //是否显示分页
        pageSize: 10,               //每页的记录行数
        pageNumber: 1,              //初始化加载第一页，默认第一页
        pageList: [10, 20, 50],     //可供选择的每页的行数
        search: true,               //是否显示表格搜索
        showRefresh: true,          //是否显示刷新按钮
        clickToSelect: true,        //是否启用点击选中行
        toolbar: "#dictToolbar", //工具按钮用哪个容器
        sidePagination: "server",   //分页方式：client客户端分页，server服务端分页
        queryParams: function (params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的
            var queryParams = {};
            var condition = {};
            condition.name = params.search;

            queryParams.page = params.offset / params.limit;
            queryParams.limit = params.limit;
            queryParams.searchCondition = condition;
            return JSON.stringify(queryParams);
        },   //查询参数组织方式
        idField: "id",
        showToggle: true,           //是否显示 切换试图（table/card）按钮
        showColumns: true,          //是否显示 内容列下拉框
        iconSize: 'outline',
        icons: {
            refresh: 'glyphicon-repeat',
            toggle: 'glyphicon-list-alt',
            columns: 'glyphicon-list'
        },
        columns: [{
            checkbox: true
        }, {
            field: "id",
            title: "ID",
            align: "center",
            valign: "middle"
        }, {
            field: "name",
            title: "业务名称",
            align: "center",
            valign: "middle"
        }, {
            field: "code",
            title: "业务编码",
            align: "center",
            valign: "middle"
        }],
        formatNoMatches: function () {
            return '无符合条件的记录';
        },
        detailView: true, // 嵌套表
        //注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
            var parentId = row.id;
            var current_table = $detail.html('<table></table>').find('table');
            $(current_table).bootstrapTable({
                method: 'POST',
                url: "/admin/dict/dictionaryType/list",
                dataField: "records",
                cache: false,               //是否使用缓存，默认为true
                striped: true,              //是否显示行间隔色
                pagination: true,           //是否显示分页
                pageSize: 10,               //每页的记录行数
                pageNumber: 1,              //初始化加载第一页，默认第一页
                pageList: [10, 20, 50],     //可供选择的每页的行数
                search: true,               //是否显示表格搜索
                showRefresh: true,          //是否显示刷新按钮
                clickToSelect: true,        //是否启用点击选中行
                toolbar: "#dictToolbar", //工具按钮用哪个容器
                sidePagination: "server",   //分页方式：client客户端分页，server服务端分页
                queryParams: function (params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的
                    var queryParams = {};
                    var condition = {};
                    condition.name = params.search;
                    condition.businessId = parentId;

                    queryParams.page = params.offset / params.limit;
                    queryParams.limit = params.limit;
                    queryParams.searchCondition = condition;
                    return JSON.stringify(queryParams);
                },   //查询参数组织方式
                idField: "id",
                showToggle: true,           //是否显示 切换试图（table/card）按钮
                showColumns: true,          //是否显示 内容列下拉框
                iconSize: 'outline',
                icons: {
                    refresh: 'glyphicon-repeat',
                    toggle: 'glyphicon-list-alt',
                    columns: 'glyphicon-list'
                },
                columns: [{
                    checkbox: true
                }, {
                    field: "id",
                    title: "ID",
                    align: "center",
                    valign: "middle"
                }, {
                    field: "name",
                    title: "字典类型名称",
                    align: "center",
                    valign: "middle"
                }, {
                    field: "code",
                    title: "字典类型编码",
                    align: "center",
                    valign: "middle"
                }],
                formatNoMatches: function () {
                    return '无符合条件的记录';
                }
            });
        }
    });

    $('#dict_btn_create').click(function () {
        $("#modal_form_dict").find(".form-control").val("");
        $('#modal_form_dict').modal();

    });

    // $("#wizard").steps();
    $("#form").steps({
        bodyTag: "fieldset",
        onStepChanging: function (event, currentIndex, newIndex) {
            // Always allow going backward even if the current step contains invalid fields!
            if (currentIndex > newIndex) {
                return true;
            }

            // Forbid suppressing "Warning" step if the user is to young
            if (newIndex === 3 && Number($("#age").val()) < 18) {
                return false;
            }

            var form = $(this);

            // Clean up if user went backward before
            if (currentIndex < newIndex) {
                // To remove error styles
                $(".body:eq(" + newIndex + ") label.error", form).remove();
                $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
            }

            // Disable validation on fields that are disabled or hidden.
            form.validate().settings.ignore = ":disabled,:hidden";

            // Start validation; Prevent going forward if false
            return form.valid();
        },
        onStepChanged: function (event, currentIndex, priorIndex) {
            // Suppress (skip) "Warning" step if the user is old enough.
            if (currentIndex === 2 && Number($("#age").val()) >= 18) {
                $(this).steps("next");
            }

            // Suppress (skip) "Warning" step if the user is old enough and wants to the previous step.
            if (currentIndex === 2 && priorIndex === 3) {
                $(this).steps("previous");
            }

            if (currentIndex === 2) {
                lastPage();
            }
        },
        onFinishing: function (event, currentIndex) {
            var form = $(this);

            // Disable validation on fields that are disabled.
            // At this point it's recommended to do an overall check (mean ignoring only disabled fields)
            form.validate().settings.ignore = ":disabled";

            // Start validation; Prevent form submission if false
            return form.valid();
        },
        onFinished: function (event, currentIndex) {
            var form = $(this);

            // Submit form input
            form.submit();
        }
    }).validate({
        errorPlacement: function (error, element) {
            error.insertAfter(element.parent());

        },
        rules: {
            confirm: {
                equalTo: "#password"
            }
        }
    });

    //业务类型选择框
    $('#businessSelect').click(function () {
        $.ajax({
            method: "GET",
            url: "/admin/dict/dictionaryBusiness/listAll",
            dataType: "json",
            success: function (data) {
                if (data.length === 0) {
                    $('#businessSelectWarning').css('display', 'block');
                }
                $.each(data, function (index, business) {
                    $("#dictionaryBusinessItem").append("<li><a href='#' value='" + business.code + "'>" + business.name + "</a></li>");
                });
            },

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }
        });
    });

    //字典类型选择框
    $('#dictTypeSelect').click(function () {
        var businessId = $('#businessId').text();
        if (businessId === null || businessId === '') {
            $('#typeSelectWarning').css('display', 'block');
            return;
        }

        $.ajax({
            method: "GET",
            url: "/admin/dict/dictionaryType/listAll/" + businessId,
            success: function (data) {
                if (data.length === 0) {
                    $('#typeSelectWarning').css('display', 'block');
                }
                $.each(data, function (index, business) {
                    $("#dictTypeItem").append("<li><a href='#' value='" + business.code + "'>" + business.name + "</a></li>");
                });
            },

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }
        });
    });


    function lastPage() {
        var typeId = $('#typeId').text();
        if (typeId === null || typeId === '') {
            return;
        }

        $.ajax({
            method: "GET",
            url: "/admin/dict/dictionaryItem/listAll/" + typeId,
            success: function (data) {
                if (data.length === 0) {
                    $('#typeSelectWarning').css('display', 'block');
                }
                $.each(data, function (index, business) {
                    $("#dictTypeItem").append("<li><a href='#' value='" + business.code + "'>" + business.name + "</a></li>");
                });
            },

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
            }
        });
    }


    var $dictionaryItemTable = $('#dictionary_item_table');
    var _dictionary_item_table = $dictionaryItemTable.bootstrapTable({
        toolbar: "#dictItemToolbar",
        columns: [{
            title: "序号",
            align: "center",
            valign: "middle",
            formatter: function (value, row, index) {
                return index;
            }
        }, {
            field: "name",
            title: "字典值",
            align: "center",
            valign: "middle",
            formatter: function (value, row, index) {
                return '<input type="text" class="form-control" name="itemName">' + value;
            }
        }, {
            title: "操作",
            align: "center",
            valign: "middle",
            formatter: function (value, row, index) {
                return '<button type="button" id="dict_btn_create" class="btn btn-del"> <i class="glyphico glyphicon-remove" aria-hidden="true">删除</i> </button>';
            }
        }]
    });

    $('#dict_item_btn_create').click(function () {
        var dictItem = {
            "name": ""
        };
        $dictionaryItemTable.bootstrapTable('append', dictItem);
    });

    _dictionary_item_table.on("click", ".btn-del", function () {

        var arrSelections = $dictionaryItemTable.bootstrapTable('getSelections');
    });


});

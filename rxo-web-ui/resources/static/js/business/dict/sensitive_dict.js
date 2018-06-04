(function (document, window, $) {
    'use strict';

    (function () {
        var $keywordTable = $('#sensitive-table');
        var _keyword_table = $keywordTable.bootstrapTable({
            method: 'POST',
            url: "/business/sensitive/sensitiveDict/list",
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
            toolbar: "#keywordToolbar", //工具按钮用哪个容器
            sidePagination: "server",   //分页方式：client客户端分页，server服务端分页
            queryParams: function (params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的
                var queryParams = {};
                var condition = {};
                condition.dict = params.search;

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
                field: "dict",
                title: "名称",
                align: "center",
                valign: "middle"
            }, {
                field: "createTime",
                title: "创建时间",
                align: "center",
                valign: "middle",
                formatter: function (value, row, index) {
                    var result = '';
                    if (value) {
                        result = moment(value).format('YYYY-MM-DD HH:mm:ss');
                    }
                    return result;
                }
            }, {
                field: "updateTime",
                title: "更新时间",
                align: "center",
                valign: "middle",
                formatter: function (value, row, index) {
                    var result = '';
                    if (value) {
                        result = moment(value).format('YYYY-MM-DD HH:mm:ss');
                    }
                    return result;
                }
            }],
            formatNoMatches: function () {
                return '无符合条件的记录';
            }
        });

        //删除
        $('#keyword_btn_delete').click(function () {
            var arrSelections = $keywordTable.bootstrapTable('getSelections');
            if (arrSelections.length <= 0) {
                toastr.warning('请选择有效操作行');
                return;
            }
            if (arrSelections.length > 1) {
                toastr.warning('只能选择一行进行编辑！');
                return;
            }

            swal({
                title: "您确定要删除这条信息吗",
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "删除",
                closeOnConfirm: false
            }, function () {
                var selectItemIds = "";
                jQuery.each(arrSelections, function (i, selection) {
                    if (selectItemIds === "") {
                        selectItemIds = selection.id;
                    } else {
                        selectItemIds = selectItemIds + "," + selection.id;
                    }
                });
                $.ajax({
                    type: "DELETE",
                    url: "/business/sensitive/sensitiveDict/" + selectItemIds,
                    data: {"": JSON.stringify(arrSelections)},
                    success: function (data, status) {
                        if (status === "success") {
                            swal({
                                title: "删除成功！",
                                text: "您已经永久删除了这条信息",
                                type: "success",
                                allowOutsideClick: true
                            });
                            $keywordTable.bootstrapTable('refresh');
                        }
                    },
                    error: function () {
                        toastr.error('删除失败！');
                    },
                    complete: function () {

                    }

                });
            });
        });


        //更新数据

        var updateKeyword = {};

        $('#keyword_btn_update').click(function () {
            var arrSelections = $keywordTable.bootstrapTable('getSelections');
            if (arrSelections.length > 1) {
                toastr.warning('只能选择一行进行编辑！');
                return;
            }
            if (arrSelections.length <= 0) {
                toastr.warning('请选择有效行！');
                return;
            }
            $('#modal_form_update').modal();
            $('#update_id').val(arrSelections[0].id);
            $("#update_dict").val(arrSelections[0].dict);
            $('#update_createtime').val(moment(arrSelections[0].createTime).format('YYYY-MM-DD HH:mm:ss'));
            updateKeyword.id = arrSelections[0].id;
            updateKeyword.dict = arrSelections[0].dict;
            updateKeyword.createTime = arrSelections[0].createTime;

        });
        $('#update_keyword_save').click(function () {
            updateKeyword.dict = $('#update_dict').val();

            $.ajax({
                type: "PUT",
                url: "/business/sensitive/sensitiveDict/" + updateKeyword.id,
                async: false,
                cache: false,	//禁用缓存
                data: JSON.stringify(updateKeyword),	//传入已封装的参数
                dataType: "json",
                contentType: "application/json;charset=UTF-8",
                success: function (result) {
                    toastr.success("更新成功");
                    $('#modal_form_update').modal("hide");
                    $keywordTable.bootstrapTable('refresh');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    var errorResponse = $.parseJSON(jqXHR.responseText);
                    toastr.error('更新数据失败！' + errorResponse.message);
                }
            });
        });


        //创建数据
        $("#keyword_btn_create").click(function () {
            $("#modal_form_create").find(".form-control").val("");
            $('#modal_form_create').modal();
        });
        $('#create_keyword_save').click(function () {
            var createKeyword = {};
            createKeyword.dict = $('#create_dict').val();

            $.ajax({
                type: "POST",
                url: "/business/sensitive/sensitiveDict",
                async: false,
                cache: false,	//禁用缓存
                data: JSON.stringify(createKeyword),	//传入已封装的参数
                dataType: "json",
                contentType: "application/json;charset=UTF-8",
                success: function (result) {
                    toastr.success("新增成功");
                    $('#modal_form_create').modal("hide");
                    $keywordTable.bootstrapTable('refresh');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    var errorResponse = $.parseJSON(jqXHR.responseText);
                    toastr.error('新增数据失败！' + errorResponse.message);
                }
            });
        });

    })();
})(document, window, jQuery);

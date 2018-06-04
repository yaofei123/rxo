$(document).ready(function () {


    var $roleTable = $('#role_table');
    var _role_table = $roleTable.bootstrapTable({
        method: 'POST',
        url: "/admin/role/list",
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
            title: "资源名称",
            align: "center",
            valign: "middle"
        }],
        formatNoMatches: function () {
            return '无符合条件的记录';
        }
    });

    //资源权限操作
    $('#role_btn_resource').click(function () {
        var arrSelections = $roleTable.bootstrapTable('getSelections');
        if (arrSelections.length <= 0) {
            toastr.warning('请选择有效操作行');
            return;
        }
        if (arrSelections.length > 1) {
            toastr.warning('只能选择一行进行编辑！');
            return;
        }
        $("#modal_form_role_resource").find(".form-control").val("");
        $('#modal_form_role_resource').modal();
    });


    $('#jstree1').jstree({
        'core': {
            'check_callback': true
        },
        'plugins': ['types', 'dnd'],
        'types': {
            'default': {
                'icon': 'fa fa-folder'
            },
            'html': {
                'icon': 'fa fa-file-code-o'
            },
            'svg': {
                'icon': 'fa fa-file-picture-o'
            },
            'css': {
                'icon': 'fa fa-file-code-o'
            },
            'img': {
                'icon': 'fa fa-file-image-o'
            },
            'js': {
                'icon': 'fa fa-file-text-o'
            }

        }
    });

    $('#using_json').jstree({
        'core': {
            'data': [
                'Empty Folder',
                {
                    'text': 'Resources',
                    'state': {
                        'opened': true
                    },
                    'children': [
                        {
                            'text': 'css',
                            'children': [
                                {
                                    'text': 'animate.css',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'bootstrap.css',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'main.css',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'style.css',
                                    'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        },
                        {
                            'text': 'js',
                            'children': [
                                {
                                    'text': 'bootstrap.js',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'hplus.min.js',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'jquery.min.js',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'jsTree.min.js',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'custom.min.js',
                                    'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        },
                        {
                            'text': 'html',
                            'children': [
                                {
                                    'text': 'layout.html',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'navigation.html',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'navbar.html',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'footer.html',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'sidebar.html',
                                    'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        }
                    ]
                },
                'Fonts',
                'Images',
                'Scripts',
                'Templates',
            ]
        }
    });

});

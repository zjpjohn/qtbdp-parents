$(function () {
    var $tbody = $('tbody'),
        $searchBtn = $('#searchBtn'),
        searchInp = $('#searchInp');


    var settings = {
        container:"#datatable_ajax",
        url: "/api/crawlers/role",
        columns: [
            { "data": "id" },
            { "data": "name" },
            { "data": "username" },
            { "data": "createTime" },
            {"data":"auditStatus"},
            { "data": null }
        ],
        columnDefs:[{
            targets: 5,
            render: function (data, type, row, meta) {
                var isUsed,
                    color;
                if(row.auditStatus == "审核已通过"){
                    isUsed = "审核已通过";
                    color = "red";
                }else {
                    isUsed = "审核";
                    color = "blue";
                }
                return '<a href="/institution/roleInfo?id=' + row.id + '" class="btn btn-sm green btn-outline filter-submit revise" data-value="'+ row.id +'">查看</a>' +
                    '<button class="btn btn-sm '+ color + ' btn-outline filter-cancel isUsed"  data-target="#completeModal"  data-status="'+ row.auditStatus + '" value="'+ row.id +'">'+ isUsed + '</button>';
            }
        },
            { "orderable": false, "targets": 4 }
        ]
    };

    Common.ajaxTable(settings);


    // //审核操作
    $tbody.on('click','.isUsed',function () {
        var id = $(this).val(),
            status = $(this).data("status");
        if(status == "审核已通过"){
            return;
        }else {
            Common.Modal("服务商规则审核",id,"/api/crawlers/role/auditCrawlersRole","post");
        }
    });






});

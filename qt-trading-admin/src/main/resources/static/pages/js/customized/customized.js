$(function () {
    var $tbody = $('tbody'),
        $searchBtn = $('#searchBtn'),
        searchInp = $('#searchInp');


    var settings = {
        container:"#datatable_ajax",
        url: "/api/customized",
        columns: [
            { "data": "id" },
            { "data": "name" },
            { "data": "introduction" },
            { "data": "price" },
            { "data": "createId" },
            { "data": "createTime" },
            {"data":"auditStatus"},
            { "data": null }
        ],
        columnDefs:[{
        targets: 7,
        render: function (data, type, row, meta) {
            var isUsed,
                color;
            if(row.auditStatus == 1){
                isUsed = "审核已通过";
                color = "red";
            }else {
                isUsed = "审核";
                color = "blue";
            }
            return '<a href="/customized/roleInfo?id=' + row.id + '" class="btn btn-sm green btn-outline filter-submit revise" data-value="'+ row.id +'">查看</a>' +
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

        if(status == 1){
            return;
        }else {
            Common.Modal("爬虫规则定制审核",id,"/api/customized/audit","post");
        }
    });






});

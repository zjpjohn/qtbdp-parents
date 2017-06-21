$(function () {
    var $tbody = $('tbody'),
        $searchBtn = $('#searchBtn'),
        searchInp = $('#searchInp');


    var settings = {
        container:"#datatable_ajax",
        url: "/api/product?userId=0",
        columns: [
            { "data": "id" },
            { "data": "dataTypeProps" },
            { "data": "dataType" },
            { "data": "designation" },
            { "data": "username" },
            { "data": "addtime" },
            { "data": null }
        ],
        columnDefs:[{
            targets: 5,
            render: function (data, type, row, meta) {
                var isUsed,
                    color;
                if(row.isUsed == 1){
                    isUsed = "下架";
                    color = "red";
                }else {
                    isUsed = "上架";
                    color = "blue";
                }
                return '<a href="/save?id=' + row.id + '" class="btn btn-sm green btn-outline filter-submit revise" data-value="'+ row.id +'">修改</a>' +
                    '<button class="btn btn-sm '+ color + ' btn-outline filter-cancel isUsed" value=" '+ row.id +' ">'+ isUsed + '</button>';
            }
        },
            { "orderable": false, "targets": 4 }
        ]
    };

    // Common.ajaxTable(settings);






});

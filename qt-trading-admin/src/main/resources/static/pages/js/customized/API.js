$(function () {
    var $tbody = $('tbody'),
        $searchBtn = $('#searchBtn'),
        searchInp = $('#searchInp');


    var settings = {
        container:"#datatable_ajax",
        url: "/api/demand",
        columns: [
            { "data": "id" },
            { "data": "apiName" },
            { "data": "apiPrice" },
            { "data": "contacts" },
            { "data": "phone" },
            { "data": null }
        ],
        columnDefs:[{
            targets: 5,
            render: function (data, type, row, meta) {
                return '<a href="/customized/apiDemandInfo?id=' + row.id + '" class="btn btn-sm green btn-outline filter-submit revise" data-value="'+ row.id +'">查看</a>'
            }
        },
            { "orderable": false, "targets": 4 }
        ]
    };

    Common.ajaxTable(settings);


});

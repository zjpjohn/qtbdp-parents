$(function () {
    var $tbody = $('tbody'),
        $searchBtn = $('#searchBtn'),
        searchInp = $('#searchInp');


    var settings = {
        container:"#datatable_ajax",
        url: "/api/feedback",
        columns: [
            { "data": "id" },
            { "data": "feedbackType" },
            { "data": "addtime" },
            { "data": "mark" },
            { "data": null }
        ],
        columnDefs:[{
            targets: 4,
            render: function (data, type, row, meta) {
                return '<a href="/customized/feedbackInfo?id=' + row.id + '" class="btn btn-sm green btn-outline filter-submit revise" data-value="'+ row.id +'">查看</a>'
            }
        },
            { "orderable": false, "targets": 4 }
        ]
    };

    Common.ajaxTable(settings);


});

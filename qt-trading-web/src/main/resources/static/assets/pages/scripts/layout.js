/**
 * Created by dell on 2017/6/14.
 */
var Layout = function () {

    var handleSidebarMenuActiveLink = function (mode, el, $state) {
        var url = location.pathname.toLowerCase();
        var menu = $('#page-sidebar-menu');
        if(!menu) return ;

        if (mode === 'click' || mode === 'set') {
            el = $(el);
        } else if (mode === 'match') {
            menu.find('li > a').each(function () {
                var state = $(this).attr('ui-sref');
                if ($state && state) {
                    if ($state.is(state)) {
                        el = $(this);
                        return;
                    }
                } else {
                    var path = $(this).attr('href');
                    if (path) {
                        path = path.toLowerCase();
                        if (path.length > 1 && url.substr(1, path.length - 1) == path.substr(1)) {
                            el = $(this);
                            return;
                        }
                    }
                }
            });
        }

        if (!el || el.size() == 0) {
            return;
        }

        if (el.attr('href') == 'javascript:;' || el.attr('href') == '#') {
            return;
        }

        menu.find('li.active').removeClass('active');
        el.parents('li').each(function () {
            $(this).addClass('active');
        });
    };

    return {
        initSidebar: function ($state) {
            handleSidebarMenuActiveLink('match', null, $state);
        }
    } ;
}();

jQuery(document).ready(function() {
    Layout.initSidebar();
});
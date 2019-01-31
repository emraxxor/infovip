/**
 * Basic scroll implementation for infovip
 * @author Attila Barna
 */
(function ($) {
    $.fn.iscroll = function () {

        var $win = this;
        
        var options = $.extend({
            offset: 100,
            scrollId : null,
            top: function () {},
            bottom: function () {},
            init: function () {}
        }, arguments[0] || {});

        $(document).ready(function () {
            options.init.call(this);
        });

        $win.scroll(function () {
            if ($win.scrollTop() == 0) {
                options.top.call(this);
            }
            else if ($win.scrollTop() + options.offset >= $(document).height() - $(window).height()) {
                options.bottom.call(this);
            }
        });
    };
}(jQuery));
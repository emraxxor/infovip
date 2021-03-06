/**
 *  @author Template
 */
var DefaultTheme = easejs.Class('DefaultTheme').extend(Controller,{
	
	'override virtual __construct' : function(node) {
		this.__super();
	 },
	 
	'public static create' : function() {
		 return new DefaultTheme();
	 },

	'public template' : function() {
		    var $wn = jQuery(window),
	        $document = jQuery(document),
	        $body = jQuery('body');
		    
		    var checkData = function (data, value) {
		        return typeof data === 'undefined' ? value : data;
		    };

		    
		    // bg image
	        var $bgImg = jQuery('[data-bg-img]');

	        $bgImg.css('background-image', function () {
	            return 'url("' + jQuery(this).data('bg-img') + '")';
	        }).addClass('bg--img').removeAttr('data-bg-img').attr('data-rjs', 2);

	        
	        // tooltip
	        var $tooltip = jQuery('[data-toggle="tooltip"]');

	        if ( $tooltip.length ) {
	            $tooltip.tooltip();
	        }

	        // sticky
	        var $sticky = jQuery('[data-trigger="sticky"]');
	        
	        $sticky.each(function () {
	            $sticky.sticky({
	                zIndex: 999
	            });
	        });
	        
	        // counter
	        var $counterUp = jQuery('[data-trigger="counterup"]');
            
	        if ( $counterUp.length ) {
	            $counterUp.counterUp({
	                delay: 10,
	                time: 1000
	            });
	        }
	        
	        // zoom
	        var $zoomImg = jQuery('[data-trigger="zoom"]');

	        if ( $zoomImg.length ) {
	            $zoomImg.zoom();
	        }

	        
	        // count down
	        var $countDown = jQuery('[data-countdown]');
            
	        $countDown.each(function () {
	            var $t = jQuery(this);
	            
	            $t.countdown($t.data('countdown'), function(e) {
	                jQuery(this).html( '<ul>' + e.strftime('<li><strong>%D</strong><span>DAYS</span></li><li><strong>%H</strong><span>HOURS</span></li><li><strong>%M</strong><span>MINUTES</span></li><li><strong>%S</strong><span>SECONDS</span></li>') + '</ul>' );
	            });
	        });


	        // box controls
	        var $boxControls = jQuery('.box--controls');

	        if ( $boxControls.length ) {
	            $boxControls.on('click', '[data-action]', function (e) {
	                e.preventDefault();
	                e.$el = jQuery(this);
	                e.$siblingOwl = e.$el.parent().siblings('.owl-carousel');

	                if ( e.$siblingOwl.length ) e.$siblingOwl.trigger( e.$el.data('action') + '.owl.carousel' );
	            });
	        }


		 	// header
	        var $header = jQuery('.header--section'),
            $headerNavbar = jQuery('.header--navbar'),
            $headerSearch2 = jQuery('.header--search.style--2');

			$headerSearch2.on('click', 'button', function (e) {
				var $parent = jQuery(this).parents('.header--search');

				if ( !$parent.hasClass('open') ) {
					e.preventDefault();
					$parent.addClass('open');
				}

				setTimeout(function () {
					$document.on('click.hs', function (e) {
						e.$target = jQuery( e.target );

						if ( e.$target.not('.header--search').length === 0 || e.$target.parents('.header--search').length === 0 ) {
							$parent.removeClass('open');
							$document.off('click.hs');
						}
					});
				}, 200);
			});

		 
		    // scroll
		    var scroll = jQuery('[data-trigger="smoothScroll"]');

	        scroll.on('click', function (e) {
	            e.preventDefault();

	            e.$el = jQuery(this);
	            e.$target = this.hash;

	            jQuery('html, body').animate({
	                scrollTop: jQuery(e.$target).offset().top - 60
	            }, 1200);
	        });
		 
	        // totop
	        jQuery('#backToTop').on('click', 'a', function (e) {
	            e.preventDefault();

	            jQuery('html, body').animate({
	                scrollTop: 0
	            }, 1200);
	        });
	  },
	
	
	 'public static onload' : function() {
		    var $wn = jQuery(window),
	        $document = jQuery(document),
	        $body = jQuery('body');
		    
	        // body scroll
		    var isBodyScrolling = function () {
	            if ( $wn.scrollTop() > 1 ) {
	                $body.addClass('isScrolling');
	            } else {
	                $body.removeClass('isScrolling');
	            }
	        };

	        isBodyScrolling();
	        $wn.on('scroll', isBodyScrolling);

	        // adjust row
	        var $adjustRow = jQuery('.AdjustRow');
	        
	        if ( $adjustRow.length ) {
	            $adjustRow.isotope({
	                layoutMode: 'fitRows'
	            });
	        }

	        // masonry
	        var $masonryRow = jQuery('.MasonryRow');
	        
	        if ( $masonryRow.length ) {
	            $masonryRow.isotope();
	        }

	        // header
	        var $headerNavbar = jQuery('.header--navbar');

	        var $el = jQuery('.buddy-finder--widget').siblings('.widget--title');

	        $el.on('click', function () {
	            var $t = jQuery(this);

	            if ( $t.hasClass('selected') ) $t.attr('contenteditable', 'true').focus();
	            if ( !$t.hasClass('selected') ) $t.addClass('selected');

	            setTimeout(function () {
	                jQuery(document).on('click', function (e) {
	                    if ( jQuery(e.target).not('.widget--title.selected').length === 1 ) { $t.removeClass('selected'); $t.removeAttr('contenteditable', 'true'); }
	                });
	            }, 200);
	        });


	        // preloader
	        var $preloader = jQuery('#preloader');

	        if ( $preloader.length ) {
	            $preloader.fadeOut('slow');
	        }

	        // scroll
	        var $scrollRevealGroup = jQuery('[data-scroll-reveal="group"]'),
	            scrollReveal = '';

	        if ( typeof ScrollReveal === "function" ) {
	            scrollReveal = ScrollReveal();

	            scrollReveal
	                .reveal('[data-scroll-reveal="left"]', {origin: 'left', mobile: false, duration: 800})
	                .reveal('[data-scroll-reveal="right"]', {origin: 'right', mobile: false, duration: 800})
	                .reveal('[data-scroll-reveal="bottom"]', {duration: 800});

	            $scrollRevealGroup.each(function () {
	                scrollReveal.reveal(jQuery(this).children(), {duration: 800}, 150);
	            });
	        }
	  }
});

jQuery(window).on('load',function(){
	DefaultTheme.onload();
});

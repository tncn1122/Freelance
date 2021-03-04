
$(document).ready(function(){
	"use strict";
  var maxLength = 200;
 
	var window_width 	 = $(window).width(),
	window_height 		 = window.innerHeight,
	header_height 		 = $(".default-header").height(),
	header_height_static = $(".site-header.static").outerHeight(),
	fitscreen 			 = window_height - header_height;

  $(".show-read-more").each(function(){
    var myStr = $(this).text();
    if($.trim(myStr).length > maxLength){
      var newStr = myStr.substring(0, maxLength);
      $(this).empty().html(newStr)
      var link = $(this).siblings(".title").find(".jobName").attr('href');
      $(this).append(' <a href = "' + link +'"> xem thêm... </a>')
    }
  });
	$(".fullscreen").css("height", window_height)
	$(".fitscreen").css("height", fitscreen);

    if(document.getElementById("default-select")){
          $('select').niceSelect();
    };

    if(document.getElementById("default-selects")){
          $('select').niceSelect();
    };   

    if(document.getElementById("default-selects2")){
          $('select').niceSelect();
    };        

    $('.img-pop-up').magnificPopup({
        type: 'image',
        gallery:{
        enabled:true
        }
    });


    $('.play-btn').magnificPopup({
        type: 'iframe',
        mainClass: 'mfp-fade',
        removalDelay: 160,
        preloader: false,
        fixedContentPos: false
    });


  // Initiate superfish on nav menu
  $('.nav-menu').superfish({
    animation: {
      opacity: 'show'
    },
    speed: 400
  });

  // Mobile Navigation
  if ($('#nav-menu-container').length) {
    var $mobile_nav = $('#nav-menu-container').clone().prop({
      id: 'mobile-nav'
    });
    $mobile_nav.find('> ul').attr({
      'class': '',
      'id': ''
    });
    $('body').append($mobile_nav);
    $('body').prepend('<button type="button" id="mobile-nav-toggle"><i class="lnr lnr-menu"></i></button>');
    $('body').append('<div id="mobile-body-overly"></div>');
    $('#mobile-nav').find('.menu-has-children').prepend('<i class="lnr lnr-chevron-down"></i>');

    $(document).on('click', '.menu-has-children i', function(e) {
      $(this).next().toggleClass('menu-item-active');
      $(this).nextAll('ul').eq(0).slideToggle();
      $(this).toggleClass("lnr-chevron-up lnr-chevron-down");
    });

    $(document).on('click', '#mobile-nav-toggle', function(e) {
      $('body').toggleClass('mobile-nav-active');
      $('#mobile-nav-toggle i').toggleClass('lnr-cross lnr-menu');
      $('#mobile-body-overly').toggle();
    });

    $(document).click(function(e) {
      var container = $("#mobile-nav, #mobile-nav-toggle");
      if (!container.is(e.target) && container.has(e.target).length === 0) {
        if ($('body').hasClass('mobile-nav-active')) {
          $('body').removeClass('mobile-nav-active');
          $('#mobile-nav-toggle i').toggleClass('lnr-cross lnr-menu');
          $('#mobile-body-overly').fadeOut();
        }
      }
    });
  } else if ($("#mobile-nav, #mobile-nav-toggle").length) {
    $("#mobile-nav, #mobile-nav-toggle").hide();
  }

  // Smooth scroll for the menu and links with .scrollto classes
  $('.nav-menu a, #mobile-nav a, .scrollto').on('click', function() {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      if (target.length) {
        var top_space = 0;

        if ($('#header').length) {
          top_space = $('#header').outerHeight();

          if( ! $('#header').hasClass('header-fixed') ) {
            top_space = top_space;
          }
        }

        $('html, body').animate({
          scrollTop: target.offset().top - top_space
        }, 1500, 'easeInOutExpo');

        if ($(this).parents('.nav-menu').length) {
          $('.nav-menu .menu-active').removeClass('menu-active');
          $(this).closest('li').addClass('menu-active');
        }

        if ($('body').hasClass('mobile-nav-active')) {
          $('body').removeClass('mobile-nav-active');
          $('#mobile-nav-toggle i').toggleClass('lnr-times lnr-bars');
          $('#mobile-body-overly').fadeOut();
        }
        return false;
      }
    }
  });


    $(document).ready(function() {

    $('html, body').hide();

        if (window.location.hash) {

        setTimeout(function() {

        $('html, body').scrollTop(0).show();

        $('html, body').animate({

        scrollTop: $(window.location.hash).offset().top

        }, 1000)

        }, 0);

        }

        else {

        $('html, body').show();

        }

    });
  

  // Header scroll class
  $(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
      $('#header').addClass('header-scrolled');
    } else {
      $('#header').removeClass('header-scrolled');
    }
  })


    $('.active-relatedjob-carusel').owlCarousel({
        items:1,
        autoplay:true,
        loop:true,
        margin:30,
        dots: true
    });

    $('.active-review-carusel').owlCarousel({
        items:2,
        margin:30,
        autoplay:true,
        loop:true,
        dots: true,       
            responsive: {
            0: {
                items: 1
            },
            480: {
                items: 1,
            },
            768: {
                items: 2,
            }
        }
    });

    $('.active-popular-post-carusel').owlCarousel({
        items:2,
        margin:30,
        autoplay:true,
        loop:true,
        dots: true,       
            responsive: {
            0: {
                items: 1
            },
            480: {
                items: 1,
            },
            768: {
                items: 1,
            },
            961: {
                items: 2,
            }
        }
    });




    //  Start Google map 

            // When the window has finished loading create our google map below

            if(document.getElementById("map")){
            
            google.maps.event.addDomListener(window, 'load', init);
        
            function init() {
                // Basic options for a simple Google Map
                // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
                var mapOptions = {
                    // How zoomed in you want the map to start at (always required)
                    zoom: 11,

                    // The latitude and longitude to center the map (always required)
                    center: new google.maps.LatLng(40.6700, -73.9400), // New York

                    // How you would like to style the map. 
                    // This is where you would paste any style found on Snazzy Maps.
                    styles: [{"featureType":"water","elementType":"geometry","stylers":[{"color":"#e9e9e9"},{"lightness":17}]},{"featureType":"landscape","elementType":"geometry","stylers":[{"color":"#f5f5f5"},{"lightness":20}]},{"featureType":"road.highway","elementType":"geometry.fill","stylers":[{"color":"#ffffff"},{"lightness":17}]},{"featureType":"road.highway","elementType":"geometry.stroke","stylers":[{"color":"#ffffff"},{"lightness":29},{"weight":0.2}]},{"featureType":"road.arterial","elementType":"geometry","stylers":[{"color":"#ffffff"},{"lightness":18}]},{"featureType":"road.local","elementType":"geometry","stylers":[{"color":"#ffffff"},{"lightness":16}]},{"featureType":"poi","elementType":"geometry","stylers":[{"color":"#f5f5f5"},{"lightness":21}]},{"featureType":"poi.park","elementType":"geometry","stylers":[{"color":"#dedede"},{"lightness":21}]},{"elementType":"labels.text.stroke","stylers":[{"visibility":"on"},{"color":"#ffffff"},{"lightness":16}]},{"elementType":"labels.text.fill","stylers":[{"saturation":36},{"color":"#333333"},{"lightness":40}]},{"elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"transit","elementType":"geometry","stylers":[{"color":"#f2f2f2"},{"lightness":19}]},{"featureType":"administrative","elementType":"geometry.fill","stylers":[{"color":"#fefefe"},{"lightness":20}]},{"featureType":"administrative","elementType":"geometry.stroke","stylers":[{"color":"#fefefe"},{"lightness":17},{"weight":1.2}]}]
                };

                // Get the HTML DOM element that will contain your map 
                // We are using a div with id="map" seen below in the <body>
                var mapElement = document.getElementById('map');

                // Create the Google Map using our element and options defined above
                var map = new google.maps.Map(mapElement, mapOptions);

                // Let's also add a marker while we're at it
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(40.6700, -73.9400),
                    map: map,
                    title: 'Snazzy!'
                });
            }
    }
	!function(t){function n(i){if(e[i])return e[i].exports;var o=e[i]={i:i,l:!1,exports:{}};return t[i].call(o.exports,o,o.exports,n),o.l=!0,o.exports}var e={};n.m=t,n.c=e,n.d=function(t,e,i){n.o(t,e)||Object.defineProperty(t,e,{configurable:!1,enumerable:!0,get:i})},n.n=function(t){var e=t&&t.__esModule?function(){return t.default}:function(){return t};return n.d(e,"a",e),e},n.o=function(t,n){return Object.prototype.hasOwnProperty.call(t,n)},n.p="",n(n.s=0)}([function(t,n,e){e(1),t.exports=e(4)},function(t,n,e){"use strict";var i=Object.assign||function(t){for(var n=1;n<arguments.length;n++){var e=arguments[n];for(var i in e)Object.prototype.hasOwnProperty.call(e,i)&&(t[i]=e[i])}return t};e(2);var o=e(3);!function(t){function n(t){return t=i({},c,t),function(t){return["nfc-top-left","nfc-top-right","nfc-bottom-left","nfc-bottom-right"].indexOf(t)>-1}(t.positionClass)||(console.warn("An invalid notification position class has been specified."),t.positionClass=c.positionClass),t.onclick&&"function"!=typeof t.onclick&&(console.warn("Notification on click must be a function."),t.onclick=c.onclick),"number"!=typeof t.showDuration&&(t.showDuration=c.showDuration),(0,o.isString)(t.theme)&&0!==t.theme.length||(console.warn("Notification theme must be a string with length"),t.theme=c.theme),t}function e(t){return t=n(t),function(){var n=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},e=n.title,i=n.message,c=r(t.positionClass);if(!e&&!i)return console.warn("Notification must contain a title or a message!");var a=(0,o.createElement)("div","ncf",t.theme);if(!0===t.closeOnClick&&a.addEventListener("click",function(){return c.removeChild(a)}),t.onclick&&a.addEventListener("click",function(n){return t.onclick(n)}),t.displayCloseButton){var s=(0,o.createElement)("button");s.innerText="X",!1===t.closeOnClick&&s.addEventListener("click",function(){return c.removeChild(a)}),(0,o.append)(a,s)}if((0,o.isString)(e)&&e.length&&(0,o.append)(a,(0,o.createParagraph)("ncf-title")(e)),(0,o.isString)(i)&&i.length&&(0,o.append)(a,(0,o.createParagraph)("nfc-message")(i)),(0,o.append)(c,a),t.showDuration&&t.showDuration>0){var l=setTimeout(function(){c.removeChild(a),0===c.querySelectorAll(".ncf").length&&document.body.removeChild(c)},t.showDuration);(t.closeOnClick||t.displayCloseButton)&&a.addEventListener("click",function(){return clearTimeout(l)})}}}function r(t){var n=document.querySelector("."+t);return n||(n=(0,o.createElement)("div","ncf-container",t),(0,o.append)(document.body,n)),n}var c={closeOnClick:!0,displayCloseButton:!1,positionClass:"nfc-top-right",onclick:!1,showDuration:3500,theme:"success"};t.createNotification?console.warn("Window already contains a create notification function. Have you included the script twice?"):t.createNotification=e}(window)},function(t,n,e){"use strict";!function(){function t(t){this.el=t;for(var n=t.className.replace(/^\s+|\s+$/g,"").split(/\s+/),i=0;i<n.length;i++)e.call(this,n[i])}if(!(void 0===window.Element||"classList"in document.documentElement)){var n=Array.prototype,e=n.push,i=n.splice,o=n.join;t.prototype={add:function(t){this.contains(t)||(e.call(this,t),this.el.className=this.toString())},contains:function(t){return-1!=this.el.className.indexOf(t)},item:function(t){return this[t]||null},remove:function(t){if(this.contains(t)){for(var n=0;n<this.length&&this[n]!=t;n++);i.call(this,n,1),this.el.className=this.toString()}},toString:function(){return o.call(this," ")},toggle:function(t){return this.contains(t)?this.remove(t):this.add(t),this.contains(t)}},window.DOMTokenList=t,function(t,n,e){Object.defineProperty?Object.defineProperty(t,n,{get:e}):t.__defineGetter__(n,e)}(Element.prototype,"classList",function(){return new t(this)})}}()},function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var i=n.partial=function(t){for(var n=arguments.length,e=Array(n>1?n-1:0),i=1;i<n;i++)e[i-1]=arguments[i];return function(){for(var n=arguments.length,i=Array(n),o=0;o<n;o++)i[o]=arguments[o];return t.apply(void 0,e.concat(i))}},o=(n.append=function(t){for(var n=arguments.length,e=Array(n>1?n-1:0),i=1;i<n;i++)e[i-1]=arguments[i];return e.forEach(function(n){return t.appendChild(n)})},n.isString=function(t){return"string"==typeof t},n.createElement=function(t){for(var n=arguments.length,e=Array(n>1?n-1:0),i=1;i<n;i++)e[i-1]=arguments[i];var o=document.createElement(t);return e.length&&e.forEach(function(t){return o.classList.add(t)}),o}),r=function(t,n){return t.innerText=n,t},c=function(t){for(var n=arguments.length,e=Array(n>1?n-1:0),c=1;c<n;c++)e[c-1]=arguments[c];return i(r,o.apply(void 0,[t].concat(e)))};n.createParagraph=function(){for(var t=arguments.length,n=Array(t),e=0;e<t;e++)n[e]=arguments[e];return c.apply(void 0,["p"].concat(n))}},function(t,n){}]);

             

        $( function() {
          $( "#datepicker" ).datepicker({
            dateFormat: 'dd/mm/yy'
          });
        } );

		$(document).ready(function() {
            $('#mc_embed_signup').find('form').ajaxChimp();
			
        }); 
		const myNotification = window.createNotification({
				closeOnClick: true,
				positionClass: 'nfc-bottom-right'
						});
		$('.favme').click(function() {
			$(this).toggleClass('active');
			var oReq = new XMLHttpRequest();
			oReq.onreadystatechange = function(){
				if(this.readyState === 4){
					if(this.status === 202){
						//console.log("ok");
						//myNotification({
						
  						//message: 'Thao Tác Thành Công' });
					}
				}
			}
			var jobId = $(this).attr("id");
			var url = "http://localhost:9999/FREELANCE/favorite/" + jobId + ".htm";
			oReq.open("get", url, true);
			oReq.send();
		});
		
		function confirmAction(theForm, url) { 
		    if (confirm("Bạn có muốn thực hiện thao tác này?")) {
		        theForm.action = url;          
		        return true;                      
		    }
		    return false;
		}

 });

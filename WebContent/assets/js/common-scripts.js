/*---LEFT BAR ACCORDION----*/
$(function() {
    $('#nav-accordion').dcAccordion({
        eventType: 'click',
        autoClose: true,
        saveState: true,
        disableLink: true,
        speed: 'slow',
        showCount: false,
        autoExpand: true,
//        cookie: 'dcjq-accordion-1',
        classExpand: 'dcjq-current-parent'
    });
});

var Script = function () {


//    sidebar dropdown menu auto scrolling

    jQuery('#sidebar .sub-menu > a').click(function () {
        var o = ($(this).offset());
        diff = 250 - o.top;
        if(diff>0)
            $("#sidebar").scrollTo("-="+Math.abs(diff),500);
        else
            $("#sidebar").scrollTo("+="+Math.abs(diff),500);
    });



//    sidebar toggle

    $(function() {
        function responsiveView() {
            var wSize = $(window).width();
            if (wSize <= 768) {
                $('#container').addClass('sidebar-close');
                $('#sidebar > ul').hide();
            }

            if (wSize > 768) {
                $('#container').removeClass('sidebar-close');
                $('#sidebar > ul').show();
            }
        }
        $(window).on('load', responsiveView);
        $(window).on('resize', responsiveView);
    });

    $('.fa-bars').click(function () {
        if ($('#sidebar > ul').is(":visible") === true) {
            $('#main-content').css({
                'margin-left': '0px'
            });
            $('#sidebar').css({
                'margin-left': '-210px'
            });
            $('#sidebar > ul').hide();
            $("#container").addClass("sidebar-closed");
        } else {
            $('#main-content').css({
                'margin-left': '210px'
            });
            $('#sidebar > ul').show();
            $('#sidebar').css({
                'margin-left': '0'
            });
            $("#container").removeClass("sidebar-closed");
        }
    });
    
/*
// custom scrollbar
    $("#sidebar").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '3', cursorborderradius: '10px', background: '#404040', spacebarenabled:false, cursorborder: ''});

    $("html").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '6', cursorborderradius: '10px', background: '#404040', spacebarenabled:false,  cursorborder: '', zindex: '1000'});
*/

// widget tools

    jQuery('.panel .tools .fa-chevron-down').click(function () {
        var el = jQuery(this).parents(".panel").children(".panel-body");
        if (jQuery(this).hasClass("fa-chevron-down")) {
            jQuery(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
            el.slideUp(200);
        } else {
            jQuery(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
            el.slideDown(200);
        }
    });

    jQuery('.panel .tools .fa-times').click(function () {
        jQuery(this).parents(".panel").parent().remove();
    });


//    tool tips

    $('.tooltips').tooltip();

//    popovers

    $('.popovers').popover();



// custom bar chart

    if ($(".custom-bar-chart")) {
        $(".bar").each(function () {
            var i = $(this).find(".value").html();
            $(this).find(".value").html("");
            $(this).find(".value").animate({
                height: i
            }, 2000)
        })
    }
    
 // datepicker
    $(function() {
		var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
		$.datepicker.setDefaults({
			dateFormat : 'yy-mm-dd',
			setDate : new Date(),
			changeMonth: true, 
	        changeYear: true,
	        container : container,
			todayHighlight : true,
			autoclose : true,
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true
	    });
		$('input[name=pf_startdate]').datepicker({
			onSelect: function(selected) {
				$('input[name=pf_enddate]').datepicker('option','minDate', selected)
				}
		});
		$('input[name=pf_enddate]').datepicker({
			onSelect: function(selected) {
				$('input[name=pf_startdate]').datepicker('option','maxDate', selected)
				}
		});
    });
    
 // Autocomplete
    $(function() {
    	
    });
    

 // 업로드 파일 추가/삭제
    $(function() {
    	var rows = 1;
    	var filelist = null;
    	$('input#fileUpload').on('change', function() {
    		filelist = document.getElementById($(this).attr('id')).files;
    		for (var i=0; i<filelist.length; i++) {
    			if (rows == 10) {
    				break;
    			}
    			$('#fileList').append(
    					'<li>'+filelist[i].name
    					+' ('+filelist[i].type+', '+(filelist[i].size/(1024*1024)).toFixed(2)+'MB) '
    					+'<a id="mediaRemove"><i class="fa fa-times"></i></a></li>'
    					);
    			rows++;
    		}
    	});
    	$('#fileList').on('click', '#mediaRemove', function() {
    		var finfo = $(this).parent().parent().first().text();
    		var fname= finfo.substr(0, finfo.lastIndexOf(' ('));
    		alert(fname);
    		/*for (var i=0; i<filelist.length; i++) {}*/
    		/*$(this).parent('li').empty().remove();
    		rows--;*/
    	});
    });
    
    

}();
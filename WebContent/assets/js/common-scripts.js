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

/*
 // datepicker
    $(function() {
    	var start_date_input = $('input[name="pf_startdate"]'); //our date input has the name "date"
		var end_date_input = $('input[name="pf_enddate"]'); //our date input has the name "date"
		var container = $('.bootstrap-iso form').length > 0 ? $(
				'.bootstrap-iso form').parent() : "body";
		var options = {
			format : 'yy-mm-dd',
			container : container,
			todayHighlight : true,
			autoclose : true,
		};
		start_date_input.datepicker(options);
		end_date_input.datepicker(options);
    });
    
    $(function() {
    	$('#fileUpload').on('change', function() {
    		$(this).siblings('#fileName').val($(this)[0].files[0].name);
    	});
    });
*/

 // 업로드할 파일 삭제
    $(function() {
    	var rows = 1;
    	$('input#fileUpload').on('change', function() {
    		var filelist = document.getElementById($(this).attr('id')).files;
    		for(var i=0; i<filelist.length; i++) {
    			if (rows == 10) {
    				break;
    			}
    			$('#fileList').append('<li id="media">'+filelist[i].name+' <a id="mediaRemove"><i class="fa fa-times"></i></a></li>');
    			rows++;
    		};
    	});
    	$('#mediaRemove').on('click', function() {
    		$(this).parent().empty.remove();
    		rows--;
    	});
    });
    
 // 공동 작업자 찾기
    $(function() {
    	var userList = new Array();
    	var prof_name;
    	var prof_nick;
    	
    	$("#findUser button").on("click", function() {
    		var input = $("#findUser input").text();
    		var request = $.ajax({
				type : "POST",
				url : "/coworker_search",
				data : {name : input},
				dataType : "xml"
			});
			
			request.done(function(xml) {
				if ($(xml).find("user").length > 0) {
					var findList = $("#findList");
					$(xml).find("user").each(function(i) {
						prof_name = $(this).find("name").text();
						prof_nick = $(this).find("nick").text();
						findList.append("<li><a href=\"#\">"+prof_name+"("+prof_nick+")</a></li>");
					});
					findList.find("a").on("click", function() {
						userList.push(prof_nick);
					});					
				} else {
					$("#findList").append("<li>검색 결과가 없습니다.</li>");
				}
			});
    	});
    	
    	$("#coworkerConfirm").on("click", function() {
    		$("pf_coworker").text(userList);
    	});
    });    

}();
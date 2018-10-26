(function($){
	$.fn.extend({
		"initTable":function(options){
			var defaults = {
				sAjaxSource:"",
				aoColumns:"",
				fnServerParams:"",
				aoColumnDefs:""
			}
			var efunboxVars = $.extend(defaults,options);
			
			return $(this).DataTable({
				 "sDom": '<"H">t<"F"lip>',
				 "processing": true,
			     "serverSide": true,
			     "bDestroy":true,
			     "sAjaxSource": efunboxVars.sAjaxSource,
			     "sServerMethod": "POST",
	             "fnServerParams": efunboxVars.fnServerParams,
		         "aoColumns": efunboxVars.aoColumns, 
		         "aoColumnDefs":efunboxVars.aoColumnDefs,
				 "oLanguage": {
	              "sProcessing": "正在加载中......",
	              "sLengthMenu": "每页显示 _MENU_ 条记录",
	              "sZeroRecords": "对不起，查询不到相关数据！",
	              "sEmptyTable": "表中无数据存在！",
	              "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	              "sInfoEmpty": "当前显示 0 到 _END_ 条，共 _TOTAL_ 条记录",
	              "sInfoFiltered": "",
	              "sSearch": "搜索",
	              "oPaginate": {
	                  "sFirst": "首页",
	                  "sPrevious": "<<",
	                  "sNext": ">>",
	                  "sLast": "末页"
	              }
	          }
			});
		},
		"selectTableRow":function(table){
			$(this).find('tbody').on( 'click', 'tr', function () {
		        if ($(this).hasClass('selected')) {
		            $(this).removeClass('selected');
		        }
		        else {
		        	table.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		        }
		    });
		},
		"addAndUpdateRow":function(url){
			var modal = $(this).modal('show');
			$(modal).load(url,function(){
				var btn = $(this).find("button.btn-primary");
				$(btn).on("click",function(){
					var check = check_fun();
					if(!check){
						return false;
					}
					var form = $(modal).find("form");
					 $(form).ajaxSubmit(function(data) {
				         $('#example').dataTable().fnDraw();
				         
				         if(data == 888){
				        	 $.globalMessenger().post({
									message: '重复提交，本次操作不做任何处理，请稍后重新操作！',
									type: 'error',
									id: "Only-one-message",
									hideAfter: 10,
									hideOnNavigate: true
								});
				         }else{
				        	 if(data>=0){
				        		 $.globalMessenger().post({
				        			 message: '操作成功！',
				        			 type: 'success',
				        			 id: "Only-one-message",
				        			 hideAfter: 2,
				        			 hideOnNavigate: true
				        		 });
				        	 }else if(data == -999){
				        		 $.globalMessenger().post({
				        			 message: '重复的记录',
				        			 type: 'error',
				        			 id: "Only-one-message",
				        			 hideAfter: 2,
				        			 hideOnNavigate: true
				        		 });
				        	 }
				        	 
				        	 else{
				        		 $.globalMessenger().post({
				        			 message: '错误：'+data,
				        			 type: 'error',
				        			 id: "Only-one-message",
				        			 hideAfter: 2,
				        			 hideOnNavigate: true
				        		 });
				        	 }
				         }
				         
				         
				         
				     });
				});
			});
		},
		"deleteRow":function(url){
			var modal = $(this).modal('show');
			$('.btn-danger').click(function(){
				$.post(url, function(data) {
					$('#example').dataTable().fnDraw();
					if(data>=0){
						$.globalMessenger().post({
							message: '操作成功！',
							type: 'success',
							id: "Only-one-message",
							hideAfter: 2,
							hideOnNavigate: true
						});
					}else{
						$.globalMessenger().post({
							message: '错误：'+data,
							type: 'error',
							id: "Only-one-message",
							hideAfter: 2,
							hideOnNavigate: true
						});
					}
				});
			})
		}
	});
})(jQuery);
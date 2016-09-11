$(function() {
	//获取时间
	(function() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var week = date.getDay();

		var weeks = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期天六'];

		$('.date').html('今天是' + year + '年' + month + '月' + day + '日' + ' ' + weeks[week]);

	})();

	$('#tabs').tabs({
		fit: true,
		border: false,
	})


	$('#nav a').click(function() {
		var href = $(this).attr('name');
		var text = $(this).html();
		addTabs(text,href);
	})


	function createFrame(url){
		var href='<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url +'.html'+ '" style="width:100%;height:99%;"></iframe>';
		return href;
	}

	function addTabs(subtitle,url){
		if($('#tabs').tabs('exists',subtitle)){
			$('#tabs').tabs('select',subtitle);
		}else{
			$('#tabs').tabs('add',{
				title:subtitle,
				content:createFrame(url),
				closable:true,
			})
		}
	}


})
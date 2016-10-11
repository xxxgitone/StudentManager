$(function() {
	$('#GRCJ').datagrid({
		//url: 'query/Query_student_all.action',
		fit: true,
		fitColumns: true,
		striped: true,
		rownumbers: true,
		border: false,
		pagination: true,
		pageNumber: 1,
		sortName: 'date',
		sortOrder: 'desc',
		toolbar: '#GRCJ-tool',
		columns: [
		[
				{
					field: 'years',
					title: '学年',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				}, {
					field: 'terms',
					title: '学期',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				}, {
					field: 'course',
					title: '课程',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'grade',
					title: '成绩',
					width: 100,
					editor: {
//						type:'text',
						type: 'datebox',
						options: {
							required: true,
						},
					},
				},{
					field: 'sinfo',
					title: '备注信息',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				}
			]
		],
	});
	
	tool={
		serach1: function() {
		    $('#GRCJ').datagrid('load',{
		    	//values : $.trim($('input[name="search"]').val()),
		    	//propertys:'sname'
		    });
		},
		serach2: function() {
		    $('#GRCJ').datagrid('load',{
		    	//values : $.trim($('input[name="search"]').val()),
		    	//propertys:'sname'
		    });
		},
		serach3: function() {
		    $('#GRCJ').datagrid('load',{
		    	//values : $.trim($('input[name="search"]').val()),
		    	//propertys:'sname'
		    });
		},
	}
	
})
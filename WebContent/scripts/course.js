$(function() {

	$('#searchValue').focus(function(){
		$('#searchValue').val('');
	});
	//修改
	$('#course').datagrid({
		url: 'query/Query_course_all.action',
		fit: true,
		fitColumns: true,
		striped: true,
		rownumbers: true,
		border: false,
		pagination: true,
		pageNumber: 1,
		sortName: 'date',
		sortOrder: 'desc',
		toolbar: '#course-tool',
		columns: [
			[{
					field: 'cno',
					title: '课程编号',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'cname',
					title: '课程名',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'credit',
					title: '学分',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'theoryhour',
					title: '理论课时',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'practicehour',
					title: '实践课时',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'ctype',
					title: '课程类型',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				}, {
					field: 'cacademy',
					title: '所属学院',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'cinfo',
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
		//双击修改
		onDblClickCell: function(rowIndex) {
			if (tool.editRow != undefined) {
				$('#course').datagrid('endEdit', tool.editRow);
			}

			if (tool.editRow == undefined) {
				$('#save,#redoText').show();
				$('#redoSelect').hide();
				$('#course').datagrid('beginEdit', rowIndex);
				tool.editRow = rowIndex;
			}

		},
		//编辑器关闭后，即保存或保存修改后的
		onAfterEdit: function(rowIndex, rowData, changes) {
			$('#save,#redoText').hide();
			$('#redoSelect').show();

			
			 var inserted = $('#course').datagrid('getChanges', 'inserted');
			 var updated = $('#course').datagrid('getChanges', 'updated');	
			 var info=url='';
			 
			 
			 if (inserted.length > 0) {
				 url='save/SaveAction_course.action';
				 info="新增";
			 }
			 if (updated.length > 0) {
				 url='save/UpdateAction_course.action';
				 info='修改';
			 }
			 
			 $.ajax({
			 		url: url,
			 		info: info,
			 		data:{
			 			cname:rowData.cname,
			 			cno:rowData.cno,
			 			cinfo:rowData.cinfo,
			 			credit:rowData.credit,
			 			theoryhour:rowData.theoryhour,
			 			practicehour:rowData.practicehour,
			 			ctype:rowData.ctype,
			 			cacademy:rowData.cacademy
			 		},
			 		beforeSend: function() {
			 			
			 			$('#course').datagrid('loading');
			 		},
			 		success: function(data) {
			 			if (data>0) {
			 				$('#course').datagrid('loaded');
			 				$('#course').datagrid('load');
			 				$('#course').datagrid('unselectAll');
			 				$.messager.show({
			 					title: '提示',
			 					msg: data + '个课程被' + info + '成功！',
			 				});
			 				tool.editRow = undefined;
			 			}else{
			 				$('#course').datagrid('loaded');
			 				$('#course').datagrid('load');
			 				$('#course').datagrid('unselectAll');
			 				$.messager.show({
			 					title: '提示',
			 					msg: info + '失败！',
			 				});
			 				tool.editRow = undefined;
			 			}
			 		}
			 	});



		}
	});

	tool = {
		editRow: undefined,
		
		serach: function() {
			 $('#course').datagrid('load',{
			    	values : $.trim($('input[name="search"]').val()),
			    	propertys:'cname'
			    });
		},
		add: function() {
			$('#save,#redoText').show();
			$('#redoSelect').hide();

			if (this.editRow == undefined) {
				$('#course').datagrid('insertRow', {
					index: 0,
					row: {

					}
				});
				$('#course').datagrid('beginEdit', 0);

				this.editRow = 0;
			}



		},

		edit: function() {
			var rows = $('#course').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告', '一次只能修改一条', 'warning');
			} else if (rows.length <= 0) {
				$.messager.alert('警告', '请选择一条修改项', 'warning');
			} else if (rows.length == 1) {
				var index = $('#course').datagrid('getRowIndex', rows[0]);

				if (tool.editRow != undefined) {
					$('#course').datagrid('endEdit', tool.editRow);

				}
				if (tool.editRow == undefined) {
					$('#save,#redoText').show();
					$('#redoSelect').hide();
					$('#course').datagrid('beginEdit', index);
					tool.editRow = index;
					$('#course').datagrid('unselectRow', index);
				}

			}



		},
		remove: function() {
			var rows = $('#course').datagrid('getSelections');
			var cno=rows[0].cno;
			if (rows.length <= 0) {
				$.messager.alert('警告', '请选择要删除的项', 'warning');
			} else {
				$.messager.confirm('警告', '你确定要删除所选项吗？', function(t) {
					if (t) {
						 $.ajax({
						 	url: 'delete/DeleteAction_course.action',
						 	data: {
						 		cno:cno,
						 	},
						 	beforeSend: function() {
						 		$('#course').datagrid('loading');
						 	},
						 	success: function(data) {
						 		if (data>0) {
						 			$('#course').datagrid('loaded');
					 				$('#course').datagrid('load');
					 				$('#course').datagrid('unselectAll');
						 			$.messager.show({
						 				title: '提示',
						 				msg: data + '个课程被删除成功！',
						 			});
						 		}else{
						 			$('#course').datagrid('loaded');
					 				$('#course').datagrid('load');
					 				$('#course').datagrid('unselectAll');
						 			$.messager.show({
						 				title: '提示',
						 				msg:'删除失败！',
						 			});
						 		}
						 	},
						 })
					}
				})
			}
		},
		save: function() {
			$('#course').datagrid('endEdit', this.editRow);
		},
		redoText: function() {
			$('#save,#redoText').hide();
			$('#redoSelect').show();
			$('#course').datagrid('rejectChanges');
			this.editRow=undefined;
		},
		redoSelect: function() {
			$('#course').datagrid('unselectAll');
		},
	}


})
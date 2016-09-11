$(function() {

	$('#searchValue').focus(function(){
		$('#searchValue').val('');
	});
	//修改
	$('#student').datagrid({
		url: 'query/Query_student_all.action',
		fit: true,
		fitColumns: true,
		striped: true,
		rownumbers: true,
		border: false,
		pagination: true,
		pageNumber: 1,
		sortName: 'date',
		sortOrder: 'desc',
		toolbar: '#student-tool',
		columns: [
			[{
					field: 'sno',
					title: '自动学号',
					width: 100,
					sortable: true,
					checkbox: true,
				}, {
					field: 'sname',
					title: '姓名',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				}, {
					field: 'pass',
					title: '登录密码',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				}, {
					field: 'ssex',
					title: '性别',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'sbirth',
					title: '出生日期',
					width: 100,
					editor: {
						type:'text',
//						type: 'datebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'sid',
					title: '身份证号',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'cid',
					title: '班级',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'spolitics',
					title: '政治面貌',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'saddr',
					title: '家庭住址',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
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
		//双击修改
		onDblClickCell: function(rowIndex) {
			if (tool.editRow != undefined) {
				$('#student').datagrid('endEdit', tool.editRow);
			}

			if (tool.editRow == undefined) {
				$('#save,#redoText').show();
				$('#redoSelect').hide();
				$('#student').datagrid('beginEdit', rowIndex);
				tool.editRow = rowIndex;
			}

		},
		//编辑器关闭后，即保存或保存修改后的
		onAfterEdit: function(rowIndex, rowData, changes) {
			$('#save,#redoText').hide();
			$('#redoSelect').show();
			
			 var inserted = $('#student').data('getChanges', 'inserted');
			 var updated = $('#student').data('getChanges', 'updated');	
			 var info='';
			 if (inserted.length > 0) {
			 	$.ajax({
			 		
			 		url: 'save/SaveAction_student.action',
			 		
			 		info: '新增',
			 		
			 		data:{
			 			row:rowData,
			 		},
			 		
			 		beforeSend: function() {
			 			alert('before');
			 			$('#student').datagrid('loading');
			 		},
			 		
			 		success: function(data) {
			 			if (data) {
			 				$('#student').datagrid('loaded');
			 				$('#student').datagrid('load');
			 				$('#student').datagrid('unselectAll');
			 				$.messager.show({
			 					title: '提示',
			 					msg: data + '个用户被' + info + '成功！',
			 				});
			 				tool.editRow = undefined;
			 			}
			 		}
			 	})

			 }
			 if (updated.length > 0) {
			 	$.ajax({
			 		
			 		url: 'save/UpdateAction_student.action',
			 		info: '修改',
			 		data:{
			 			row:rowData,
			 		},
			 		beforeSend: function() {
			 			alert('before2');
			 			$('#student').datagrid('loading');
			 		},
			 		success: function(data) {
			 			if (data) {
			 				$('#student').datagrid('loaded');
			 				$('#student').datagrid('load');
			 				$('#student').datagrid('unselectAll');
			 				$.messager.show({
			 					title: '提示',
			 					msg: data + '个用户被' + info + '成功！',
			 				});
			 				tool.editRow = undefined;
			 			}
			 		}
			 	});

			 }


		}
	});

	tool = {
		editRow: undefined,
		serach: function() {
			$('#box').datagrid('load', {
				searchName: $.trim($('input[name="search"]').val()),
			});
		},
		add: function() {
			$('#save,#redoText').show();
			$('#redoSelect').hide();

			if (this.editRow == undefined) {
				$('#student').datagrid('insertRow', {
					index: 0,
					row: {

					}
				});
				$('#student').datagrid('beginEdit', 0);

				this.editRow = 0;
			}



		},

		edit: function() {
			var rows = $('#student').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告', '一次只能修改一条', 'warning');
			} else if (rows.length <= 0) {
				$.messager.alert('警告', '请选择一条修改项', 'warning');
			} else if (rows.length == 1) {
				var index = $('#student').datagrid('getRowIndex', rows[0]);

				if (tool.editRow != undefined) {
					$('#student').datagrid('endEdit', tool.editRow);

				}
				if (tool.editRow == undefined) {
					$('#save,#redoText').show();
					$('#redoSelect').hide();
					$('#student').datagrid('beginEdit', index);
					tool.editRow = index;
					$('#student').datagrid('unselectRow', index);
				}

			}



		},
		remove: function() {
			var rows = $('#student').datagrid('getSelections');
			if (rows.length <= 0) {
				$.messager.alert('警告', '请选择要删除的项', 'warning');
			} else {
				$.messager.confirm('警告', '你确定要删除所选项吗？', function(t) {
					if (t) {
						var ids = [];
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						// $.ajax({
						// 	url: '',
						// 	type: 'post',
						// 	data: {
						// 		ids.join(','),
						// 	},
						// 	beforeSend: function() {
						// 		$('#student').datagrid('loading');
						// 	},
						// 	success: function(data) {
						// 		if (data) {
						// 			$('#student').datagrid('loaded');
						// 			$('#student').datagrid('load');
						// 			$('#student').datagrid('unselectAll');
						// 			$.messager.show({
						// 				title: '提示',
						// 				msg: data + '个用户被删除成功！',
						// 			});
						// 		}
						// 	},
						// })
					}
				})
			}
		},
		save: function() {
			$('#student').datagrid('endEdit', this.editRow);
		},
		redoText: function() {
			$('#save,#redoText').hide();
			$('#redoSelect').show();
			$('#student').datagrid('rejectChanges');
			this.editRow=undefined;
		},
		redoSelect: function() {
			$('#student').datagrid('unselectAll');
		},
	}


})
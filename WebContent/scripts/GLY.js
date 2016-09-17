$(function() {

	$('#searchValue').focus(function(){
		$('#searchValue').val('');
	});
	//修改
	$('#manager').datagrid({
		url: 'query/Query_manager_all.action',
		fit: true,
		fitColumns: true,
		striped: true,
		rownumbers: true,
		border: false,
		pagination: true,
		pageNumber: 1,
		sortName: 'date',
		sortOrder: 'desc',
		toolbar: '#manager-tool',
		columns: [
			[{
					field: 'mname',
					title: '用户名',
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
				},{
					field: 'minfo',
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
				$('#manager').datagrid('endEdit', tool.editRow);
			}

			if (tool.editRow == undefined) {
				$('#save,#redoText').show();
				$('#redoSelect').hide();
				$('#manager').datagrid('beginEdit', rowIndex);
				tool.editRow = rowIndex;
			}

		},
		//编辑器关闭后，即保存或保存修改后的
		onAfterEdit: function(rowIndex, rowData, changes) {
			$('#save,#redoText').hide();
			$('#redoSelect').show();

			
			 var inserted = $('#manager').data('getChanges', 'inserted');
			 var updated = $('#manager').data('getChanges', 'updated');	
			 var info=url='';
			 
			 
			 if (inserted.length > 0) {
				 url='save/SaveAction_manager.action';
				 info="新增";
			 }
			 if (updated.length > 0) {
				 url='save/UpdateAction_manager.action';
				 info='修改';
			 }
			 
			 $.ajax({
			 		url: url,
			 		info: info,
			 		data:{
			 			mname:rowData.mname,
			 			pass:rowData.pass,
			 			minfo:rowData.minfo,
			 		},
			 		beforeSend: function() {
			 			
			 			$('#manager').datagrid('loading');
			 		},
			 		success: function(data) {
			 			if (data>0) {
			 				$('#manager').datagrid('loaded');
			 				$('#manager').datagrid('load');
			 				$('#manager').datagrid('unselectAll');
			 				$.messager.show({
			 					title: '提示',
			 					msg: data + '个用户被' + info + '成功！',
			 				});
			 				tool.editRow = undefined;
			 			}else{
			 				$('#manager').datagrid('loaded');
			 				$('#manager').datagrid('load');
			 				$('#manager').datagrid('unselectAll');
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
			$('#searchValue').datagrid('load', {
				searchName: $.trim($('input[name="search"]').val()),
			});
		},
		add: function() {
			$('#save,#redoText').show();
			$('#redoSelect').hide();

			if (this.editRow == undefined) {
				$('#manager').datagrid('insertRow', {
					index: 0,
					row: {

					}
				});
				$('#manager').datagrid('beginEdit', 0);

				this.editRow = 0;
			}



		},

		edit: function() {
			var rows = $('#manager').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告', '一次只能修改一条', 'warning');
			} else if (rows.length <= 0) {
				$.messager.alert('警告', '请选择一条修改项', 'warning');
			} else if (rows.length == 1) {
				var index = $('#manager').datagrid('getRowIndex', rows[0]);

				if (tool.editRow != undefined) {
					$('#manager').datagrid('endEdit', tool.editRow);

				}
				if (tool.editRow == undefined) {
					$('#save,#redoText').show();
					$('#redoSelect').hide();
					$('#manager').datagrid('beginEdit', index);
					tool.editRow = index;
					$('#manager').datagrid('unselectRow', index);
				}

			}



		},
		remove: function() {
			var rows = $('#manager').datagrid('getSelections');
			var sno=rows[0].sno;
			if (rows.length <= 0) {
				$.messager.alert('警告', '请选择要删除的项', 'warning');
			} else {
				$.messager.confirm('警告', '你确定要删除所选项吗？', function(t) {
					if (t) {
						 $.ajax({
						 	url: 'delete/DeleteAction_manager.action',
						 	data: {
						 		sno:sno,
						 	},
						 	beforeSend: function() {
						 		$('#manager').datagrid('loading');
						 	},
						 	success: function(data) {
						 		alert(data);
						 		if (data>0) {
						 			$('#manager').datagrid('loaded');
					 				$('#manager').datagrid('load');
					 				$('#manager').datagrid('unselectAll');
						 			$.messager.show({
						 				title: '提示',
						 				msg: data + '个用户被删除成功！',
						 			});
						 		}else{
						 			$('#manager').datagrid('loaded');
					 				$('#manager').datagrid('load');
					 				$('#manager').datagrid('unselectAll');
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
			$('#manager').datagrid('endEdit', this.editRow);
		},
		redoText: function() {
			$('#save,#redoText').hide();
			$('#redoSelect').show();
			$('#manager').datagrid('rejectChanges');
			this.editRow=undefined;
		},
		redoSelect: function() {
			$('#manager').datagrid('unselectAll');
		},
	}


})
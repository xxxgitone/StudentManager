$(function() {

	$('#searchValue').focus(function(){
		$('#searchValue').val('');
	});
	//修改
	$('#teacher').datagrid({
		url: 'query/Query_teacher_all.action',
		fit: true,
		fitColumns: true,
		striped: true,
		rownumbers: true,
		border: false,
		pagination: true,
		pageNumber: 1,
		sortName: 'date',
		sortOrder: 'desc',
		toolbar: '#teacher-tool',
		columns: [
			[{
					field: 'tno',
					title: '自动学号',
					width: 100,
					sortable: true,
					checkbox: true,
				}, {
					field: 'tname',
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
					field: 'tsex',
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
					field: 'tbirth',
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
					field: 'tpolitics',
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
					field: 'tjob',
					title: '职务',
					width: 100,
					editor: {
						type:'text',
//						type: 'validatebox',
//						options: {
//							required: true,
//						},
					},
				},{
					field: 'tacademy',
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
					field: 'tinfo',
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
				$('#teacher').datagrid('endEdit', tool.editRow);
			}

			if (tool.editRow == undefined) {
				$('#save,#redoText').show();
				$('#redoSelect').hide();
				$('#teacher').datagrid('beginEdit', rowIndex);
				tool.editRow = rowIndex;
			}

		},
		//编辑器关闭后，即保存或保存修改后的
		onAfterEdit: function(rowIndex, rowData, changes) {
			$('#save,#redoText').hide();
			$('#redoSelect').show();

			
			 var inserted = $('#teacher').data('getChanges', 'inserted');
			 var updated = $('#teacher').data('getChanges', 'updated');	
			 var info=url='';
			 
			 
			 if (inserted.length > 0) {
				 url='save/SaveAction_teacher.action';
				 info="新增";
			 }
			 if (updated.length > 0) {
				 url='save/UpdateAction_teacher.action';
				 info='修改';
			 }
			 
			 $.ajax({
			 		url: url,
			 		info: info,
			 		data:{
			 			tno:rowData.tno,
			 			pass:rowData.pass,
			 			tname:rowData.tname,
			 			tsex:rowData.tsex,
			 			tbirth:rowData.tbirth,
			 			tpolitics:rowData.tpolitics,
			 			tjob:rowData.tjob,
			 			tacademy:rowData.tacademy,
			 			tinfo:rowData.tinfo,
			 		},
			 		beforeSend: function() {
			 			
			 			$('#teacher').datagrid('loading');
			 		},
			 		success: function(data) {
			 			if (data>0) {
			 				$('#teacher').datagrid('loaded');
			 				$('#teacher').datagrid('load');
			 				$('#teacher').datagrid('unselectAll');
			 				$.messager.show({
			 					title: '提示',
			 					msg: data + '个用户被' + info + '成功！',
			 				});
			 				tool.editRow = undefined;
			 			}else{
			 				$('#teacher').datagrid('loaded');
			 				$('#teacher').datagrid('load');
			 				$('#teacher').datagrid('unselectAll');
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
			$('#box').datagrid('load', {
				searchName: $.trim($('input[name="search"]').val()),
			});
		},
		add: function() {
			$('#save,#redoText').show();
			$('#redoSelect').hide();

			if (this.editRow == undefined) {
				$('#teacher').datagrid('insertRow', {
					index: 0,
					row: {

					}
				});
				$('#teacher').datagrid('beginEdit', 0);

				this.editRow = 0;
			}



		},

		edit: function() {
			var rows = $('#teacher').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告', '一次只能修改一条', 'warning');
			} else if (rows.length <= 0) {
				$.messager.alert('警告', '请选择一条修改项', 'warning');
			} else if (rows.length == 1) {
				var index = $('#teacher').datagrid('getRowIndex', rows[0]);

				if (tool.editRow != undefined) {
					$('#teacher').datagrid('endEdit', tool.editRow);

				}
				if (tool.editRow == undefined) {
					$('#save,#redoText').show();
					$('#redoSelect').hide();
					$('#teacher').datagrid('beginEdit', index);
					tool.editRow = index;
					$('#teacher').datagrid('unselectRow', index);
				}

			}



		},
		remove: function() {
			var rows = $('#teacher').datagrid('getSelections');
			var sno=rows[0].sno;
			if (rows.length <= 0) {
				$.messager.alert('警告', '请选择要删除的项', 'warning');
			} else {
				$.messager.confirm('警告', '你确定要删除所选项吗？', function(t) {
					if (t) {
						 $.ajax({
						 	url: 'delete/DeleteAction_teacher.action',
						 	data: {
						 		sno:sno,
						 	},
						 	beforeSend: function() {
						 		$('#teacher').datagrid('loading');
						 	},
						 	success: function(data) {
						 		alert(data);
						 		if (data>0) {
						 			$('#teacher').datagrid('loaded');
					 				$('#teacher').datagrid('load');
					 				$('#teacher').datagrid('unselectAll');
						 			$.messager.show({
						 				title: '提示',
						 				msg: data + '个用户被删除成功！',
						 			});
						 		}else{
						 			$('#teacher').datagrid('loaded');
					 				$('#teacher').datagrid('load');
					 				$('#teacher').datagrid('unselectAll');
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
			$('#teacher').datagrid('endEdit', this.editRow);
		},
		redoText: function() {
			$('#save,#redoText').hide();
			$('#redoSelect').show();
			$('#teacher').datagrid('rejectChanges');
			this.editRow=undefined;
		},
		redoSelect: function() {
			$('#teacher').datagrid('unselectAll');
		},
	}


})
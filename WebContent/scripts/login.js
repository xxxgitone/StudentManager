var codeHtml = document.getElementById('code');

function createCode() {
	var codes = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	];
	var codeslength = 4;
	var code = '';
	for (var i = 0; i < codeslength; i++) {
		var index = Math.floor(Math.random() * codes.length);
		code += codes[index];
	}
	codeHtml.innerHTML = code;
}

function validateCode() {
	var code_success = document.getElementById('code_success');
	var code_errr = document.getElementById('code_error');
	var form = document.forms[0];
	var checked = form.elements['checked'];
	if (checked.value.length >= 4 && checked.value.toUpperCase() != codeHtml.innerHTML.toUpperCase()) {
		code_success.style.display = 'none';
		code_error.style.display = 'block';
	}
	checked.onkeyup = function(event) {
		event = event || window.event;
		if (checked.value.toUpperCase() == codeHtml.innerHTML.toUpperCase()) {
			code_error.style.display = 'none';
			code_success.style.display = 'block';
		} else if (checked.value.length >= 4 && checked.value.toUpperCase() != codeHtml.innerHTML.toUpperCase()) {
			code_success.style.display = 'none';
			code_error.style.display = 'block';
		}

		if (event.keyCode == 8 && checked.value.length < 4) {
			code_success.style.display = 'none';
			code_error.style.display = 'none';
		}

	}

	var button = document.getElementsByTagName('button')[0];
	button.onclick = function() {
		var form = document.forms[0];
		if (checked.value.toUpperCase() != codeHtml.innerHTML.toUpperCase()) {
			code_error.style.display = 'block';
		} else if (checked.value.toUpperCase() == codeHtml.innerHTML.toUpperCase()) {
			form.submit();
		}

	}

}

createCode();
validateCode();

var reload = document.getElementById('reload');
reload.onclick = function() {
	createCode();
}
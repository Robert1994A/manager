app.service('ResponseUtilService', function() {

	var stack_custom_top = {
		"dir1" : "down",
		"dir2" : "right",
		"push" : "top",
		"spacing1" : 1
	};

	// Custom top position
	function show_stack_custom_top(type, message) {
		var opts = {
			title : "Over here",
			text : message,
			width : "100%",
			cornerclass : "no-border-radius",
			addclass : "stack-custom-top bg-primary",
			stack : stack_custom_top
		};
		switch (type) {
		case 'error':
			opts.title = "Error";
			opts.text = message;
			opts.addclass = "stack-custom-top bg-danger";
			opts.type = "error";
			break;

		case 'info':
			opts.title = "Info";
			opts.text = message;
			opts.addclass = "stack-custom-top bg-info";
			opts.type = "info";
			break;

		case 'success':
			opts.title = "Success";
			opts.text = message;
			opts.addclass = "stack-custom-top bg-success";
			opts.type = "success";
			break;
		}
		new PNotify(opts);
	}

	this.handleReponse = function(data) {
		if (data.status == "OK") {
			show_stack_custom_top('success', data.message);
		} else {
			show_stack_custom_top('error', data.message);
		}
		return 0;
	}
	
	this.generateRandomString = function(){
		    var text = "";
		    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		    for( var i=0; i < 32; i++ )
		        text += possible.charAt(Math.floor(Math.random() * possible.length));
		    return text;
	}
});
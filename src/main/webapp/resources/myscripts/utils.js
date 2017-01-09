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

function handleReponse(data) {
	if (data.status == "OK") {
		show_stack_custom_top('success', data.message);
	} else {
		show_stack_custom_top('error', data.message);
	}
	return 0;
}

$.fn.serializeObject = function(){
    var self = this,
        json = {},
        push_counters = {},
        patterns = {
            "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
            "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
            "push":     /^$/,
            "fixed":    /^\d+$/,
            "named":    /^[a-zA-Z0-9_]+$/
        };


    this.build = function(base, key, value){
        base[key] = value;
        return base;
    };

    this.push_counter = function(key){
        if(push_counters[key] === undefined){
            push_counters[key] = 0;
        }
        return push_counters[key]++;
    };

    $.each($(this).serializeArray(), function(){

        // skip invalid keys
        if(!patterns.validate.test(this.name)){
            return;
        }

        var k,
            keys = this.name.match(patterns.key),
            merge = this.value,
            reverse_key = this.name;

        while((k = keys.pop()) !== undefined){

            // adjust reverse_key
            reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

            // push
            if(k.match(patterns.push)){
                merge = self.build([], self.push_counter(reverse_key), merge);
            }

            // fixed
            else if(k.match(patterns.fixed)){
                merge = self.build([], k, merge);
            }

            // named
            else if(k.match(patterns.named)){
                merge = self.build({}, k, merge);
            }
        }

        json = $.extend(true, json, merge);
    });

    return json;
};

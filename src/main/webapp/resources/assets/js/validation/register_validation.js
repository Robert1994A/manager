$(function() {
	$("#register_form").validate(
			{
				rules : {
					username : {
						required : true,
						digits : true,
						minlength : 13,
						maxlength : 13
					},
					email : {
						required : true,
						email : true,
						minlength : 5
					},
					password: {
						required : true,
						minlength : 5
					},
					repeatPassword : {
						required : true,
						minlength : 5,
						equalTo : password
					},
					repeatEmail : {
						required : true,
						minlength : 5,
						equalTo : email
					}
				},
				highlight : function(element) {
					$(element).closest('.form-group')
							.removeClass('has-success').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error')
							.addClass('has-success');
				},
			});
});
$(function() {
	$("#recover_form").validate(
			{
				rules : {
					newPassword: {
						required : true,
						minlength : 5
					},
					
					retypeNewPassword : {
						required : true,
						minlength : 5,
						equalTo : newPassword
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
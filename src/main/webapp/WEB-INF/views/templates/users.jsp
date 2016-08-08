
<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function() {
			$('#loading').fadeOut(400, "linear");
		}, 300);
	});
</script>

<div class="panel panel-flat">
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>Username</th>
					<th>Email</th>
					<th>Roles</th>
				</tr>
			</thead>
			<tbody infinite-scroll='reddit.nextPage()'
				infinite-scroll-disabled='reddit.busy' infinite-scroll-distance='1'>
				<tr ng-repeat='item in reddit.items'>
					<td>{{item.id}}</td>
					<td>{{item.username}}</td>
					<td>{{item.email}}</td>
					<td>
				<tr ng-repeat="role in item.roles">
					<td>{{role.name}}</td>
				</tr>
				</td>
				</tr>

			</tbody>
		</table>

		<span ng-if="!reddit.lastPage">
			<div ng-show='reddit.busy'>
				<div class="col-lg-5"></div>
				<div id="loading">
					<img src="./resources/assets/images/spinner/loader-dark.gif"
						alt="Loading...">
				</div>
				<div class="col-lg-5"></div>
			</div>
		</span>

		<div ng-show='reddit.lastPage'>
			<div class="alert bg-danger">
				<button type="button" class="close" data-dismiss="alert">
					<span>X</span><span class="sr-only">Close</span>
				</button>
				<span class="text-semibold">No more records to retrieve!</span>
			</div>
		</div>
	</div>
</div>










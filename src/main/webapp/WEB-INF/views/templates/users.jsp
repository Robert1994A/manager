
<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function() {
			$('#loading').fadeOut(400, "linear");
		}, 300);
	});
</script>
<div class="panel panel-flat">
	<div class="panel-heading">
		<h5 class="panel-title">Search in users</h5>
	</div>
	<div class="panel-body">
		<form class="main-search">
			<div class="input-group content-group">
				<div class="has-feedback has-feedback-left">
					<input type="text" class="form-control input-xlg"
						ng-model="searchUserValue" placeholder="Type a user name">
					<div class="form-control-feedback">
						<i class="icon-search4 text-muted text-size-base"></i>
					</div>
				</div>
				<div class="input-group-btn">
					<button type="button" ng-click="searchUser()"
						class="btn btn-primary btn-xlg">Search</button>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-xs-4">
							<label>Per Page: </label> <select name="perPage"
								ng-model="perPage" class="form-control">
								<option value="5">5
								<option>
								<option value="10">10</option>
								<option value="20">25</option>
								<option value="50">50</option>
								<option value="100">100</option>
							</select>
						</div>

						<div class="col-xs-4">
							<label>Sort by: </label> <select name="sortBy" ng-model="sortBy"
								class="form-control">
								<option value="id">ID</option>
								<option value="username">Username</option>
							</select>
						</div>

						<div class="col-xs-4">
							<label>Sort direction: </label> <select name="sortDirection"
								ng-model="sortDirection" class="form-control">
								<option value="ASC">ASC</option>
								<option value="DESC">DESC</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<div class="panel panel-flat">
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<!-- 
					<th>ID</th>
					<th>Username</th>
					 -->
					<th>Email</th>
					<th>Roles</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody infinite-scroll='reddit.nextPage()'
				infinite-scroll-disabled='reddit.busy' infinite-scroll-distance='1'>
				<tr ng-repeat='item in reddit.items'>
					<!-- 
					<td>{{item.id}}</td>
					<td>{{item.username}}</td> -->
					<td>{{item.email}}</td>
					<td>
						<ul ng-repeat="role in item.roles">
							<li>{{role.id}} : <b>{{role.name}}</b></li>
						</ul>
					</td>
					<td><div class="btn-group">
							<button type="button" class="btn btn-default"
								ng-click="viewUserDetails(item.id)">User details</button>
						</div></td>
				</tr>

			</tbody>
		</table>

		<span ng-if="!reddit.lastPage">
			<div ng-show='reddit.busy'>
				<div class="col-lg-4"></div>
				<div id="loading">
					<img src="./resources/assets/images/spinner/loader-dark.gif"
						alt="Loading...">
				</div>
				<div class="col-lg-4"></div>
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










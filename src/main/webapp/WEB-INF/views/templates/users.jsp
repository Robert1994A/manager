
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
		<div class="heading-elements">
			<ul class="icons-list">
				<li><a data-action="collapse"></a></li>
				<li><a data-action="close"></a></li>
			</ul>
		</div>
		<a class="heading-elements-toggle"><i class="icon-more"></i></a>
	</div>

	<div class="panel-body">
		<form action="#" class="main-search">
			<div class="input-group content-group">
				<div class="has-feedback has-feedback-left">
					<input type="text" class="form-control input-xlg"
						value="" placeholder="Type a user name">
					<div class="form-control-feedback">
						<i class="icon-search4 text-muted text-size-base"></i>
					</div>
				</div>

				<div class="input-group-btn">
					<button type="submit" class="btn btn-primary btn-xlg">Search</button>
				</div>
			</div>

			<div class="row search-option-buttons">
				<div class="col-sm-6">
					<ul class="list-inline list-inline-condensed no-margin-bottom">
						<li class="dropdown"><a href="#"
							class="btn btn-link dropdown-toggle" data-toggle="dropdown">
								<i class="icon-stack2 position-left"></i> All categories <span
								class="caret"></span>
						</a>

							<ul class="dropdown-menu">
								<li><a href="#"><i class="icon-question7"></i> Getting
										started</a></li>
								<li><a href="#"><i class="icon-accessibility"></i>
										Registration</a></li>
								<li><a href="#"><i class="icon-reading"></i> General
										info</a></li>
								<li><a href="#"><i class="icon-gear"></i> Your settings</a></li>
								<li><a href="#"><i class="icon-graduation"></i>
										Copyrights</a></li>
								<li class="divider"></li>
								<li><a href="#"><i class="icon-mail-read"></i>
										Contacting authors</a></li>
							</ul></li>
						<li><a href="#" class="btn btn-link"><i
								class="icon-reload-alt position-left"></i> Refine your search</a></li>
					</ul>
				</div>

				<div class="col-sm-6 text-right">
					<ul class="list-inline no-margin-bottom">
						<li><a href="#" class="btn btn-link"><i
								class="icon-make-group position-left"></i> Browse website</a></li>
						<li><a href="#" class="btn btn-link"><i
								class="icon-menu7 position-left"></i> Advanced search</a></li>
					</ul>
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
					<th>ID</th>
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
						<ul ng-repeat="role in item.roles">
							<li>{{role.id}} : <b>{{role.name}}</b></li>
						</ul>
					</td>
				</tr>
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










<div class="panel panel-white">
	<div class="panel-heading">
		<h6 class="panel-title">Users</h6>
		<div class="heading-elements">
			<ul class="icons-list">
				<li><a data-action="collapse"></a></li>
				<li><a data-action="reload"></a></li>
				<li><a data-action="close"></a></li>
			</ul>
		</div>
		<a class="heading-elements-toggle"><i class="icon-more"></i></a>
	</div>

	<div class="row">
		<div class="col-md-6">
			<div class="dropdown  pull-right">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" aria-expanded="false">
					Display <span class="caret"></span>
				</button>
				<ul class="dropdown-menu pull-right">
					<li><label class="cr-styled"> <input type="checkbox"
							ng-model="showUsername"><i class="fa"></i>Username
					</label></li>
					<li><label class="cr-styled"> <input type="checkbox"
							ng-model="showEmail"><i class="fa"></i>Email
					</label></li>
					<li><label class="cr-styled"> <input type="checkbox"
							ng-model="showRoles"><i class="fa"></i>Roles
					</label></li>
					<li><label class="cr-styled"> <input type="checkbox"
							ng-model="showAction"><i class="fa"></i>Action
					</label></li>
				</ul>
			</div>
		</div>
	</div>

	<form class="form-inline" role="form">
		<div class="form-group col-md-4">
			<label>Per page</label> <select class="form-control"
				ng-model="rowLimit">
				<option selected="selected">10</option>
				<option ng-repeat="i in perPages">{{i}}</option>
			</select>
		</div>
		<div class="form-group col-md-4">
			<div class="input-group">
				<span class="input-group-btn">
					<button type="button" class="btn btn-effect-ripple btn-primary">
						<i class="fa fa-search"></i>
					</button>
				</span> <input type="text" id="example-input1-group2" ng-model="searchUser"
					name="example-input1-group2" class="form-control"
					placeholder="Search">
			</div>
		</div>
		<div class="form-group col-md-4">
			<label>Sort By</label> <select ng-model="sortColumn"
				class="form-control">
				<option value="username">Username ASC</option>
				<option value="!username">Username DESC</option>
				<option value="email">Email ASC</option>
				<option value="!email">Email DESC</option>
			</select>
		</div>
	</form>


	<div class="example-box-wrapper">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th><input type="checkbox" ng-model="checked"><i
						class="fa"></i></th>
					<th>Username</th>
					<th>Email</th>
					<th>Roles</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr class="{{checked ? 'active' : ''}}"
					ng-repeat="user in users | filter:searchUser| limitTo:rowLimit | orderBy:sortColumn">
					<td><input type="checkbox" class="custom-checkbox"
						ng-model="checked"></td>
					<td ng-hide="!showUsername">{{user.username}}</td>
					<td ng-hide="!showEmail">{{user.email}}</td>
					<td ng-hide="!showRoles">
						<div class="form-inline">
							<span ng-repeat="role in user.roles">{{role.name}},</span>
						</div>
					</td>
					<td ng-hide="!showAction">
						<div class="form-inline">
							<a class="btn btn-icon btn-warning m-b-5"
								href="#/users/{{user.id}}"> <i class="fa fa-wrench"></i>
							</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>


</div>












<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Users</h3>
            </div>
            <div class="panel-body">
                <!-- Inline Form -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="form-group col-md-6 ">
                                        <button class="btn btn-primary" data-toggle="modal"
                                                data-target="#addUser">Add User
                                        </button>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="dropdown  pull-right">
                                            <button class="btn btn-default dropdown-toggle"
                                                    data-toggle="dropdown" aria-expanded="false">
                                                Display <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu pull-right">
                                                <li><label class="cr-styled"> <input
                                                        type="checkbox" ng-model="showUsername"><i
                                                        class="fa"></i>Username
                                                </label></li>
                                                <li><label class="cr-styled"> <input
                                                        type="checkbox" ng-model="showEmail"><i class="fa"></i>Email
                                                </label></li>
                                                <li><label class="cr-styled"> <input
                                                        type="checkbox" ng-model="showRoles"><i class="fa"></i>Roles
                                                </label></li>
                                                <li><label class="cr-styled"> <input
                                                        type="checkbox" ng-model="showAction"><i
                                                        class="fa"></i>Action
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
												<button type="button"
                                                        class="btn btn-effect-ripple btn-primary">
													<i class="fa fa-search"></i>
												</button>
											</span> <input type="text" id="example-input1-group2"
                                                           ng-model="searchUser" name="example-input1-group2"
                                                           class="form-control" placeholder="Search">
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
                            </div>
                            <!-- panel-body -->
                        </div>
                        <!-- panel -->
                    </div>
                    <!-- col -->
                </div>
                <!-- End row -->
                <div class="row">
                    <ul class="pager">
                        <li class="previous disabled" ng-model="previousPage"><a
                                href="#"><i class="fa fa-long-arrow-left"></i> Previous</a></li>
                        <li class="next" ng-model="nextPage"><a href="#">Next <i
                                class="fa fa-long-arrow-right"></i></a></li>
                    </ul>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <table id="datatable" class="table">
                            <thead>
                            <tr>
                                <th class="col-md-1 col-sm-1 col-xs-1"><label
                                        class="cr-styled"> <input type="checkbox"
                                                                  ng-model="checked"><i class="fa"></i>
                                </label></th>
                                <th class="col-md-2 col-sm-2 col-xs-2">Username</th>
                                <th class="col-md-2 col-sm-2 col-xs-2">Email</th>
                                <th class="col-md-2 col-sm-2 col-xs-2">Roles</th>
                                <th class="col-md-3 col-sm-3 col-xs-3">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="{{checked ? 'active' : ''}}"
                                ng-repeat="user in users | filter:searchUser| limitTo:rowLimit | orderBy:sortColumn">
                                <td><label class="cr-styled"> <input
                                        type="checkbox" ng-model="checked"><i class="fa"></i>
                                </label></td>
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
                                        <button class="btn btn-icon btn-info m-b-5"
                                                data-toggle="modal" data-target="#editUser">
                                            <i class="fa fa-keyboard-o"></i>
                                        </button>

                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Row -->


<!-- Modal add user -->
<div id="addUser" class="modal fade" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">�
                </button>
                <h4 class="modal-title">Add new user</h4>
            </div>
            <form>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="field-1" class="control-label">Email</label> <input
                                    type="text" class="form-control" id="field-1">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="field-1" class="control-label">Username</label> <input
                                    type="text" class="form-control" id="field-1">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="field-2" class="control-label">Roles</label> <select
                                    class="selectpicker" multiple>
                                <option>Mustard</option>
                                <option>Ketchup</option>
                                <option>Relish</option>
                            </select>

                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-info" value="Save changes"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /.modal -->


<!-- Modal edit user -->
<div id="editUser" class="modal fade" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">�
                </button>
                <h4 class="modal-title">Edit user</h4>
            </div>
            <form id="addUser">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="field-1" class="control-label">Email</label> <input
                                    type="text" class="form-control" id="field-1">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="field-1" class="control-label">Username</label> <input
                                    type="text" class="form-control" id="field-1">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="field-2" class="control-label">Roles</label> <input
                                    type="text" class="form-control" id="field-2">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-info" value="Save changes"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /.modal -->

<!-- Plugins css -->
<link href="./resources/assets/modal-effect/css/component.css"
      rel="stylesheet">

<!-- Modal-Effect -->
<script src="./resources/assets/modal-effect/js/classie.js"></script>
<script src="./resources/assets/modal-effect/js/modalEffects.js"></script>




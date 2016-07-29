<!-- Form-validation -->
<div class="row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Details about user{{user.username}}</h3>
            </div>
            <div class="panel-body">
                <div class=" form">
                    <form class="cmxform form-horizontal tasi-form" id="signupForm"
                          method="get" novalidate="novalidate">
                        <div class="form-group ">
                            <label for="username" class="control-label col-lg-2">Username
                            </label>
                            <div class="col-lg-10">
                                <input class="form-control" readonly id="username"
                                       name="username" type="text" value="{{user.username}}">
                            </div>
                        </div>
                        <div ng-repeat="role in user.roles">
                            <div class="form-group ">
                                <label for="username" class="control-label col-lg-2">Role
                                    {{$index}} </label>
                                <div class="col-lg-10">
                                    <input class="form-control " id="username" name="username"
                                           type="text" readonly value="{{role.name}}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <a href="#/users"><i class="ion-arrow-left-a"></i>Go back to
                                users</a>
                        </div>

                    </form>
                </div>
                <!-- .form -->

            </div>
            <!-- panel-body -->
        </div>
        <!-- panel -->
    </div>
    <!-- col -->

</div>
<!-- End row -->



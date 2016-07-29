<div class="wraper container-fluid">
    <!-- Form-validation -->
    <div class="row">
        <div class="col-sm-12">
            <form class="cmxform form-horizontal tasi-form" id="add_formForm"
                  method="get" novalidate="novalidate">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Add new form</h3>
                    </div>

                    <div class="panel-body">
                        <div class=" form">

                            <div class="form-group ">
                                <label class="control-label col-lg-2">Title *</label>
                                <div class="col-lg-10">
                                    <input class="form-control" id="title" name="title" type="text">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="control-label col-lg-2">Description *</label>
                                <div class="col-lg-10">
                                    <input class="form-control" id="description" name="description"
                                           type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-2">Published Date</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control"
                                           placeholder="mm/dd/yyyy" id="publishedDate">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-2">Expired Date</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control"
                                           placeholder="mm/dd/yyyy" id="expiredDate">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="control-label col-lg-2 col-sm-3">Published</label>
                                <div class="col-lg-10 col-sm-9">
                                    <div class="checkbox">
                                        <label class="cr-styled"> <input type="checkbox">
                                            <i class="fa"></i>
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <!-- Start add custom fields -->
                            <div class="form-group" data-ng-repeat="question in questions"
                                 style="padding: 10px; border: 1px solid black">
                                <div class="col-md-8">
                                    <input class="form-control" type="text"
                                           ng-model="question.name" name=""
                                           placeholder="Enter your question">
                                </div>
                                <div class="col-md-2">
                                    <select class="form-control" ng-model="questionType">
                                        <option ng-repeat="question in questionsType">{{question.name}}</option>
                                    </select>
                                </div>
                                <div class="btn-group m-b-10">
                                    <button class="addfields btn btn-default m-b-5"
                                            ng-show="showAddQuestion(question)"
                                            ng-click="addNewQuestion()">
                                        <i class="ion-plus"></i>
                                    </button>
                                    <button type="button" ng-click="removeNewQuestion()"
                                            class="remove btn btn-danger m-b-5">
                                        <i class="ion-trash-b"></i>
                                    </button>
                                </div>

                                <div class="col-md-12" ng-if="questionType == 'Paragraf'">
                                    <input type="text" class="form-control"/>
                                </div>

                                <div class="col-md-12"
                                     ng-if="questionType == 'Multiple Answers'">
                                    <div class="col-md-12">
                                        <div class="col-md-1">1</div>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control"/>
                                        </div>
                                        <div class="btn-group m-b-10">
                                            <button type="button" ng-click="removeNewQuestion()"
                                                    class="remove btn btn-danger m-b-5">
                                                <i class="ion-trash-b"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12" ng-if="questionType == 'Checkbox'">
                                    <div class="col-md-1">
                                        <input type="checkbox" style="width: 16px"
                                               class="checkbox form-control"/>
                                    </div>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control"/>
                                    </div>
                                    <div class="btn-group m-b-10">
                                        <button type="button" ng-click="removeNewQuestion()"
                                                class="remove btn btn-danger m-b-5">
                                            <i class="ion-trash-b"></i>
                                        </button>
                                    </div>
                                </div>

                                <div class="row" ng-if="questionType == 'Radio Buttons'">
                                    <div class="col-md-12">
                                        <div class="col-md-1">
                                            <input type="radio" style="width: 16px"
                                                   class="checkbox form-control"/>
                                        </div>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control"/>
                                        </div>
                                        <div class="btn-group m-b-10">
                                            <button type="button" ng-click="removeNewQuestion()"
                                                    class="remove btn btn-danger m-b-5">
                                                <i class="ion-trash-b"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" ng-if="questionType == 'Dropdown'">
                                    <div class="col-md-12">
                                        <div class="col-md-1">1</div>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control"/>
                                        </div>
                                        <div class="btn-group m-b-10">
                                            <button type="button" ng-click="removeNewQuestion()"
                                                    class="remove btn btn-danger m-b-5">
                                                <i class="ion-trash-b"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" ng-if="questionType == 'Notes'">
                                    <div class="col-md-12 inline">
                                        <label class="cr-styled col-md-1"
                                               ng-repeat="note in notes | limitTo:maximumNote"> <input
                                                type="radio" name="note"> <i class="fa"></i>
                                            {{note.note}}
                                        </label>
                                        <div class="col-md-4"></div>
                                        <input class="form-control" ng-model="maximumNote"
                                               type="number" min="1" max="10"/>
                                        <div class="col-md-4"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End custom filed -->
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <button class="btn btn-success" type="submit">Save</button>
                            <button class="btn btn-default" type="button">Cancel</button>
                        </div>
                    </div>
                </div>
            </form>
            <!-- .form -->

        </div>
        <!-- panel-body -->
    </div>
    <!-- panel -->
</div>
<!-- col -->

<!-- Page Content Ends -->
<!-- ================== -->
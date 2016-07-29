app
    .controller(
        "formsController",
        function ($scope, FormService) {
            var date = new Date();
            var startDate = ('0' + date.getDate()).slice(-2) + '-'
                + ('0' + (date.getMonth() + 1)).slice(-2) + '-'
                + date.getFullYear();
            $('#publishedDate').datepicker({
                format: "dd/mm/yyyy",
                startDate: startDate,
                endDate: "31-12-2100",
                todayBtn: "linked",
                autoclose: true,
                todayHighlight: true
            });
            $('#expiredDate').datepicker({
                format: "dd/mm/yyyy",
                startDate: startDate,
                endDate: "31-12-2100",
                todayBtn: "linked",
                autoclose: true,
                todayHighlight: true
            });

            $scope.notes = [{
                note: "1"
            }, {
                note: "2"
            }, {
                note: "3"
            }, {
                note: "4"
            }, {
                note: "5"
            }, {
                note: "6"
            }, {
                note: "7"
            }, {
                note: "8"
            }, {
                note: "9"
            }, {
                note: "10"
            },]

            $scope.questionsType = [{
                name: "Paragraf",
            }, {
                name: "Dropdown",
            }, {
                name: "Checkbox",
            }, {
                name: "Multiple Answers"
            }, {
                name: "Radio Buttons"
            }, {
                name: "Notes"
            }];

            $scope.questions = [{
                id: 'question1',
                name: 'question1'
            }];

            $scope.addNewQuestion = function () {
                var newItemNo = $scope.questions.length + 1;
                $scope.questions.push({
                    id: 'question' + newItemNo,
                    name: 'question' + newItemNo
                });
            };

            $scope.removeNewQuestion = function () {
                var newItemNo = $scope.questions.length - 1;
                if (newItemNo !== 0) {
                    $scope.questions.pop();
                }
            };

            $scope.showAddQuestion = function (question) {
                return question.id === $scope.questions[$scope.questions.length - 1].id;
            };

            $scope.limitQuestions = 10;

        });

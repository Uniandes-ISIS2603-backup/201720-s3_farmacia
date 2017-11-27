(function (ng) {
    var mod = ng.module("suministroModule");
    mod.constant("suministroContext", "api/Suministros")
    mod.controller('suministroCtrl', ['$scope', '$http', 'suministroContext', '$state', '$stateParams',
        function ($scope, $http, suministroContext, $state, $stateParams) {
            $http.get(suministroContext).then(function (response) {
                $scope.records = response.data;
            });
            if ($stateParams.suministroId !== undefined && $state.params.suministroId !== null) {

                id = $stateParams.suministroId;

                $http.get(suministroContext + '/' + id).then(function (response) {
                    $scope.currentRecord = response.data;
                });
            }
            else{
                $scope.currentRecord = {
                    id: undefined,
                    name:'',
                    info: undefined
                };
                $scope.alerts = [];
            }


            this.createAndUpdate = function (id) {

                 currentRecord = $scope.currentRecord;

                    if (id == null || id == undefined) {

                    return $http.post(suministroContext, currentRecord)
                            .then(function () {
                                $state.go('suministroList');
                            });

                } else {
                    return $http.put(suministroContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                $state.go('suministroList');
                            });
                }
                ;
            };
            


            this.deleteSuministro = function(record) {
                return $http.delete(suministroContext + "/" + record.id)
                            .then(function () {
                                var index = $scope.records.indexOf(record);
                                if (index > -1) {
                                    $scope.records.splice(index, 1);
                                }
                })

           
            }
            
            
            }
    ]);
}
)(angular);
var stationApp = angular.module('stationApp', []);


stationApp.controller('StationListCtrl', ['$scope', '$http', function ($scope, $http) {
    $http.get('../api/stations').success(function(data) {
        $scope.stations = data.stations;
        $scope.cache = data.cache;
        $scope.time = data.time;
    });
}]);

stationApp.controller('ServerCtrl', ['$scope', '$http', function ($scope, $http) {
    $http.get('../api/server/who').success(function(data) {
        $scope.server = data;
        if(data.instances instanceof Array) {
            $scope.instances = data.instances;
        } else {
            $scope.instances = [data.instances];
        }

    });
}]);
'use strict';

var pmModelApp = angular.module('pmModelApp');

pmModelApp.factory('pmModelService', function ($http) {

      function buildModelRequest($scope) {

            return {
                      matchSpread : $scope.selectedEvent.matchSpread,
                      totalPoints : $scope.selectedEvent.totalPoints,
                      homePlayers : $scope.selectedEvent.homePlayers,
                      awayPlayers : $scope.selectedEvent.awayPlayers,
                      eventName : $scope.selectedEvent.eventName,
                      homeTeamName : $scope.selectedEvent.homeTeamName,
                      awayTeamName : $scope.selectedEvent.awayTeamName
                    }
        }

    return {
        getDayEvents : function ($scope, successCallback){
         $http.get('getDayEvents').then(function (result) {
                                $scope.latestTimeStamp = result.data.first;
                                $scope.dayEvents = result.data.second;

        });
        },

        submitLines : function ($scope, successCallback){
         $http.post('submitLines', $scope.dayEvents).then(successCallback, function (result) {
                                $scope.latestTimeStamp = result.data.first;
                                $scope.dayEvents = result.data;
               });
        },


        runModel : function ($scope, successCallback){
         var modelRequest = buildModelRequest($scope)
         $http.post('getMatchPredictions', modelRequest).then(
                        successCallback, function (result) {
                        });
        },


        updateLines : function ($scope, successCallback){
         $http.get('scrapeLines').then(
                        successCallback, function (result) {
                        });
        },

        updateFdPreds : function ($scope, successCallback){
         $http.get('updateFdPreds').then(
                        successCallback, function (result) {
                        });
        },
        updateData : function (successCallback){
         $http.get('updateData').then(
                  successCallback, function (result) {
                  });
        }
    }
});

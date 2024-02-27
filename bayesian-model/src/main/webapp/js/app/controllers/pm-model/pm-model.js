'use strict';

var pmModelApp = angular.module('pmModelApp');

pmModelApp.controller('pmModelController', function ($scope, $http , pmModelService) {

    $scope.getTradingUIHRef = function(){
        return window.location.href;
    }

    $scope.fdModelUrl = window.location.href + "fdModel";
    $scope.pmModelUrl = window.location.href + "pmModel";
    $scope.inputModelUrl = window.location.href.replace("pmModel", "");
    $scope.toFdModelFromPmModelUrl = window.location.href.replace("pmModel", "fdModel");

    $scope.linesLastUpdated = "";
    $scope.linesLastUpdated = "";

    $scope.homePlayers = [];
    $scope.awayPlayers = [];

    $scope.selectedEvent = null;

    pmModelService.getDayEvents($scope);

    $scope.hideInputs = false;

    $scope.loadEventData = function(eventName){

        $scope.homePlayers = eventName.homePlayers
        $scope.awayPlayers = eventName.awayPlayers
        $scope.matchSpread = eventName.matchSpread
        $scope.totalPoints = eventName.totalPoints
        $scope.modelOutput = null
        $scope.playerThreePointsOutput = null
    }

    $scope.eventNameDisplay = function(evt){
        return evt.eventName;
    }
    $scope.asList = function(){
        var lista = []
        for(key in $scope.games){
            lista.append(key)
        }
        return lista

    }
    $scope.resetPlayers = function(){
         pmModelService.getTestPlayers($scope);
         $scope.modelOutput = null;
         $scope.playerThreePointsOutput = null;
         $scope.percShots = null;
    }

    $scope.hideOrShow = function(){
        if($scope.hideInputs){
            return "Show";
        }else{
            return "Hide";
        }
    }

    $scope.switchHideVariable = function(){
        $scope.hideInputs = !$scope.hideInputs;
    }

    $scope.getExpPointsSum = function(listPlayers){
        var sum = 0
        for(var i =0; i< listPlayers.length; i++){
            var playerAvg = 0
            if($scope.modelOutput == null){
                return null;
            }else {
                playerAvg = Math.round(1000 * $scope.modelOutput[listPlayers[i].name][-1]) / 1000;
            }
            sum += playerAvg
        }
        return sum;
    }

    $scope.getExpPminSum = function(listPlayers){
        var sum = 0
        for(var i =0; i< listPlayers.length; i++){
            sum += listPlayers[i].pmin
        }
        return sum;
    }

    $scope.getPercShotsSum = function(listPlayers){
        var sum = 0
        if($scope.percShots != undefined){
            for(var i =0; i< listPlayers.length; i++){
                 sum += $scope.percShots[listPlayers[i].name]
            }
        }
        return Math.round(10000 * sum) / 10000;
    }


    $scope.getPlayerAverage = function(playerName){
        if($scope.modelOutput == null){
            return null;
        }else {
            return Math.round(100 * $scope.modelOutput[playerName][-1]) / 100;
        }
    }

    $scope.populatePlayersLine = function(){
        for(var p in $scope.homePlayers){
            $scope.homePlayers[p].line = Math.round($scope.getPlayerAverage($scope.homePlayers[p].name) - 0.5) + 0.5;
            $scope.homePlayers[p].threePointsLine = 0.5;
        }
        for(var p in $scope.awayPlayers){
            $scope.awayPlayers[p].line = Math.round($scope.getPlayerAverage($scope.awayPlayers[p].name) - 0.5) + 0.5;
            $scope.awayPlayers[p].threePointsLine = 0.5;
        }
    }

    $scope.getOverProbForPlayer = function(player){
        if($scope.modelOutput != null){
            var playerPreds = $scope.modelOutput[player.name]

            var prob = 0;
            for(var item in playerPreds){
                if(item > player.line){
                    prob += playerPreds[item]
                }
            }
            return Math.round(10000 * prob) / 10000;
        }
        return 1;
    }

    $scope.roundPrice = function(number){
        return Math.round(100 * number) / 100;
    }

    $scope.getThreePointersOverProbForPlayer = function(player){
        if($scope.playerThreePointsOutput != null){
            var playerPreds = $scope.playerThreePointsOutput[player.name]

            var prob = 0;
            for(var item in playerPreds){
                if(item > player.threePointsLine){
                    prob += playerPreds[item]
                }
            }
            return Math.round(10000 * prob) / 10000;
        }
        return 1;
    }

    $scope.populatePred = function(){
        for(player in $scope.homePlayers){

        }
    }

    $scope.getPercShotsForPlayer = function(name){
        if($scope.percShots != undefined){
            return $scope.percShots[name];
        }
    }

   $scope.showToast = function (text) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(text)
                .position('top right')
                .hideDelay(2000)
        );
    };

   $scope.updateData = function(){
         pmModelService.updateData(
             function (result) {
                 $scope.dayEvents = result.data.second;
                 $scope.latestTimeStamp = result.data.first;
                 window.alert("Data has been reset to the csv values")
             }
         )
    }

    $scope.submitLines = function(){
        pmModelService.submitLines($scope ,
            function (result){
                    $scope.dayEvents = result.data.first;
                    $scope.linesLastUpdated = result.data.second;
                    window.alert("Lines Submitted")
            }
        )
    }


    $scope.updateLines = function(){
             pmModelService.updateLines($scope,
                 function (result) {
                    $scope.dayEvents = result.data.first;
                    $scope.linesLastUpdated = result.data.second;
                    window.alert("Lines Updated")
                 }
             )
    }

    $scope.updateFdPreds = function(){
             pmModelService.updateFdPreds($scope,
                 function (result) {
                    window.alert("Running R scripts")
                 }
             )
    }

    $scope.runModel = function(){
         pmModelService.runModel($scope,
             function (result) {
                $scope.modelOutput = result.data['modelOutput']
                $scope.playerThreePointsOutput = result.data['playerThreePoints']
                $scope.percShots = result.data['percShots']
                $scope.populatePlayersLine()
             }
         )
     };
})
;

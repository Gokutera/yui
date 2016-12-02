/**
 * Created by Administrator on 2016/11/10.
 */
// var yoi = angular.module('yoi', []);

yoi.controller('chatController', function($scope, postService){
    $scope.texts = new Array();
    $scope.backText = "你好";
    $scope.send = function(){
        var temp = {};
        temp['type']='send';
        temp['text']=$scope.text;
        $scope.texts.push(temp);

        var urlTulin = "http://www.tuling123.com/openapi/api";
        var text = $scope.text;
        var data = {
            "key": "35537d57e57f469489bf5884b5a53f6b",
            "info": text,
            "userid":"123456"
        };
        sendMsg(urlTulin, data);
    }



    function sendMsg(urlTulin, data){
        postService.post(urlTulin, data).success(function(data){
            var temp = {};
            temp['type']='get';
            temp['text']=data.text;
            $scope.texts.push(temp);
            $scope.text = null;
            $scope.backText = data.text;
        });
    }
})

yoi.service('postService', function ($http) {
    this.post = function (url, paramsData) {
        return $http({
            method: 'post',
            url: url,
            params: paramsData,
            responseType: "json"
        });
    }
});
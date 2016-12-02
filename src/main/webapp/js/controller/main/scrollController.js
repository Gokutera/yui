yoi.controller('scrollController', ['$scope', '$location', '$anchorScroll', 'postChatService', function($scope, $location, $anchorScroll, postChatService){
    $scope.gotoIdeas = function(value){
        switch(value){
            case 1:
                $location.hash('home');
                break;
            case 2:
                $location.hash('about');
                break;
            case 3:
                $location.hash('ideas');
                break;
            case 4:
                $location.hash('contact');
                break;
            default:
                alert("error");
        }
        $anchorScroll();
    };

    $scope.backText = "你好";
    $scope.showLogin = false;
    $scope.send = function(){
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
        postChatService.post(urlTulin, data).success(function(data){
            $scope.backText = data.text;
            if(data.text == "欢迎回来，主人样"){
                $scope.showLogin = true;
            }
        });
    }
}]);

yoi.service('postChatService', function ($http) {
    this.post = function (url, paramsData) {
        return $http({
            method: 'post',
            url: url,
            params: paramsData,
            responseType: "json"
        });
    }
});
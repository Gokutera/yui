/**
 * Created by Administrator on 2016/11/9.
 */
yoi.controller('mainController', function($scope, userService, $cookieStore, localStorageService){
    $scope.name="Wang";
    $scope.getName = function(){
        var param={
            id:$scope.id
        }
        userService.post("/getName", param).success(function(data){
            $scope.name = data;
        });
    }

    $scope.insert = function(){
        var param = {
            id:$scope.id,
            name:$scope.name,
            passwd:$scope.passwd,
            realname:$scope.realname
        }
        userService.post("/user/insert", param).success(function(data){
            console.log(data);
        })
    }

    $scope.register = function(){
        var adminUserId = $scope.adminUserId;
        var adminName = $scope.adminName;
        var adminPasswd = encryptStringBase64AndSHAL($scope.adminPasswd);
        var param={
            adminUserId:adminUserId,
            adminName:adminName,
            adminPasswd:adminPasswd
        }
        userService.post("/user/register", param).success(function(data){
            console.log(data);
        })
    }

    $scope.logindetails={
        username:'',
        password:''
    };

    $scope.login = function(logindetails){
        var params = {
            username:'',
            password:''
        };
        params.username = logindetails.username;
        params.password = encryptStringBase64AndSHAL(logindetails.password);
        userService.postJson("/user/login", params).success(function(data){
            if(data.result != "登录失败"){
                alert(data.result);
                var now = getMinFromNow(1);
                localStorageService.cookie.set('admin', data.result, now);
                window.location.href="#/chat";
            }
        })
    };

    $scope.getCookies = function(){
        alert(localStorageService.cookie.get('admin'));
    }

    $scope.deleteCookies = function(){
        localStorageService.cookie.remove('admin');
        alert("OK");
    }
})

yoi.service('userService', function ($http) {
    this.post = function (url, paramsData) {
        return $http({
            method: 'post',
            url: url,
            params: paramsData
        });
    }

    this.postJson = function(url, paramsData){
        return $http({
           method:'post',
            url:url,
            params:paramsData,
            responseType:'json'
        });
    }
});
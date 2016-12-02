/**
 * Created by Administrator on 2016/11/22.
 */
var yoi = angular.module('yoi', ['ui.router','ngCookies', 'LocalStorageModule']);

yoi.config(function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise("/")
    $stateProvider
        .state('home',{
            url:"/",
            templateUrl:'/views/index.html'
        })
        .state('welcome',{
            url:"/welcome",
            templateUrl:'/views/welcome.html',
            controller:'mainController'
        })
        .state('chat',{
            url:"/chat",
            templateUrl:'/views/chat.html',
            controller:'chatController',
            resolve:{
                auth:["$q", "localStorageService", function($q, localStorageService){
                    var userInfo = localStorageService.cookie.get('admin');
                    if(userInfo){
                        return $q.when(userInfo);
                    }else{
                        return $q.reject({authenticated:false});
                    }
                }]
            }
        })
        .state('mainpage',{
            url:"/mainpage",
            templateUrl:'/views/mainpage.html',
            controller:'scrollController',
            resolve:{
                auth:["$q", "localStorageService", function($q, localStorageService){
                    var userInfo = localStorageService.cookie.get('admin');
                    if(userInfo){
                        return $q.when(userInfo);
                    }else{
                        return $q.reject({authenticated:false});
                    }
                }]
            }
        })
})

yoi.run(["$rootScope", "$location", function($rootScope, $location){
    $rootScope.$on('$stateChangeSuccess',function(userInfo){
        console.log(userInfo);
    })
    $rootScope.$on('$stateChangeError', function(event, current, previous, eventObj){
        if(current.url != "/welcome"){
            console.log("error");
            window.location.href="#/welcome";
        }
    });
}]);
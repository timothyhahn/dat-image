var app = angular.module('app', ['ngRoute', 'wu.masonry']);

app.controller('AppCtrl', function($http) {
    var app = this;
    $http.get('/api/dat').success(function (data) {
        app.dats = data.dats;
    });

    app.addDat = function() {
	    var title = document.getElementById('newTitle').value;
        $http.post('/api/dat', {'title': title, 'image': 'http://placekitten.com/200/200?image='})
            .success(function (data) {
                app.dats.push(data);
                document.getElementById('newTitle').value = '';
            });
    };

    app.voteDat = function(dat) {
        $http.post('/api/dat/vote/' + dat.id, dat).success(function (data)
            {
                dat.score = data.score;
                dat.image = data.image;
                dat.title = data.title;
            });
    };
});

app.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                when('/dat', {
                    templateUrl: 'partials/dat-list.html',
                    controller: 'AppCtrl'
                }).
                otherwise({
                    redirectTo: '/dat'
                });
        }]);


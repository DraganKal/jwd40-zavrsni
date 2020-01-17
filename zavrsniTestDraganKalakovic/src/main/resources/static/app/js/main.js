var wafepaApp = angular.module("wafepaApp",["ngRoute"]);

wafepaApp.controller("ZadaciCtrl", function($scope, $http, $location){
	var url = "/api/zadaci";
	var urlSprintovi = "/api/sprintovi";
	var urlStanja = "/api/stanja";
	
	$scope.zadaci = [];
	$scope.sprintovi = [];
	$scope.stanja = [];
	
	$scope.zadatak = {};
	$scope.zadatak.ime = "";
	$scope.zadatak.zaduzeni = "";
	$scope.zadatak.bodovi = "";
	$scope.zadatak.stanjeId = "";
	$scope.zadatak.sprintId = "";
	
	$scope.sParams = {};
	$scope.sParams.ime = "";
	$scope.sParams.sprintId = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	
	
	
	var getZadaci = function(){
			
			var config = {params:{}};
			
			if($scope.sParams.ime != ""){
				config.params.ime = $scope.sParams.ime;
			}
			
			if($scope.sParams.sprintId != ""){
				config.params.sprintId = $scope.sParams.sprintId;
			}
			
			config.params.pageNum = $scope.pageNum;
			
			var promise = $http.get(url, config);
			promise.then(
				function success(res){
					$scope.totalPages = res.headers("totalPages");
					$scope.zadaci = res.data;
				},
				function error(){
					alert("Neuspesno dobavljanje zadataka!");
				}
			);
		}
	
	getZadaci();
	
	var getSprintovi = function(){
		var promise = $http.get(urlSprintovi);
		promise.then(
			function success(res){
				$scope.sprintovi = res.data;
			},
			function error(res){
				alert("Neuspesno dobaljanje sprintova!");
			}
		);
	}
	getSprintovi();
	
	var getStanja = function(){
		var promise = $http.get(urlStanja);
		promise.then(
			function success(res){
				$scope.stanja = res.data;
			},
			function error(res){
				alert("Neuspesno dobaljanje stanja!");
			}
		);
	}
	getStanja();
	
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getZadaci();
	}
	
	$scope.doClear = function(){
		$scope.sParams.ime = "";
		$scope.sParams.sprintId = "";
		getZadaci();
	}
	
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getZadaci();
	}
	
	$scope.doAdd = function(){
		$http.post(url, $scope.zadatak).then(
			function success(res){
				$scope.zadatak.ime = "";
				$scope.zadatak.zaduzeni = "";
				$scope.zadatak.bodovi = "";
				$scope.zadatak.stanjeId = "";
				$scope.zadatak.sprintId = "";
				getZadaci();
		
			},
			function error(){
				alert("Neuspesno dodavanje zadatka!");
			}
		);
	}
	
	$scope.doObrisi = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getZadaci();
			},
			function error(){
				alert("Neuspesno brisanje zadatka.");
			}
		);
	}
	$scope.goToEdit = function(id){
		$location.path("/zadaci/edit/" + id);
	}
	
	$scope.doPredji = function(id){
		var promise = $http.post(url + "/" + id);
		promise.then(
			function success(){
				alert("Uspešno ste promenili stanje.")
				getZadaci();
			},
			function error(){
				alert("Neuspesna kupovina.");
				getZadaci();
			}
		);
			
	}
	
});


wafepaApp.controller("EditZadatakCtrl", function($scope, $http, $routeParams, $location){
	
	var urlZadatak = "/api/zadaci/" + $routeParams.id;
	var urlSprintovi = "/api/sprintovi";
	var urlStanja = "/api/stanja";
	
	
	$scope.sprintovi = [];
	$scope.stanja = [];

	$scope.zadatak = {};
	$scope.zadatak.ime = "";
	$scope.zadatak.zaduzeni = "";
	$scope.zadatak.bodovi = "";
	$scope.zadatak.stanjeId = "";
	$scope.zadatak.sprintId = "";
	

	
	
	var getSprintovi = function(){
		var promise = $http.get(urlSprintovi);
		promise.then(
			function success(res){
				$scope.sprintovi = res.data;
				getStanja();
			},
			function error(res){
				alert("Neuspesno dobavljajne sprintova!");
			}
		);
	}
	var getStanja = function(){
		var promise = $http.get(urlStanja);
		promise.then(
			function success(res){
				$scope.stanja = res.data;
				getZadatak();
			},
			function error(res){
				alert("Neuspesno dobaljanje stanja!");
			}
		);
	}
	
	var getZadatak = function(){
		$http.get(urlZadatak).then(
			function success(res){
				$scope.zadatak = res.data;
			},
			function error(){
				alert("Neuspesno dobavljanje zadatka!");
			}
		);
	}
	getSprintovi();
	
	$scope.doEdit = function(){
		$http.put(urlZadatak, $scope.zadatak).then(
			function success(){
				$location.path("/zadaci");
			},
			function error(){
				alert("Nesto je poslo po zlu.");
			}
		);
	}
});












wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/zadaci.html',
			controller: "ZadaciCtrl"
		})
		.when('/zadaci', {
			templateUrl : '/app/html/zadaci.html'
		})
		.when('/zadaci/add', {
			templateUrl : '/app/html/add-zadatak.html'
		})
		.when('/zadaci/edit/:id', {
			templateUrl : '/app/html/edit-zadatak.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
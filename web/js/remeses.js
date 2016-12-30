function RemesaResourceProvider() {
  var _baseUrl;
  this.setBaseUrl = function(baseUrl) {
    _baseUrl = baseUrl;
  };
  this.$get=['$http','$q',function($http,$q) {
    return new RemesaResource($http,$q,_baseUrl);
  }];
}

function RemesaResource($http,$q,baseUrl) {
  
  /*this.get=function(idRemesa) {
        var defered=$q.defer();
	var promise=defered.promise;
        $http({
          method: 'GET',          
          url: baseUrl + '/api/Remesa/'+idRemesa
        }).success(function(data, status, headers, config) {            
	    defered.resolve(data);
        }).error(function(data, status, headers, config) {            
	    if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });
	return promise;
  };*/
    
  this.list=function(tipus) {
        tipus = typeof tipus !== 'undefined' ? tipus : "";
	var defered=$q.defer();
	var promise=defered.promise;
        var parameters="";
        if (tipus!=="") parameters="?tipus="+tipus;
                
        $http({
          method: 'GET',          
          url: baseUrl +'/api/Remesa'+parameters
        }).success(function(data, status, headers, config) {           
            console.log("DATA ->"+data);
	    defered.resolve(data);
            
        }).error(function(data, status, headers, config) {            
	    if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data+" params="+parameters);
            }
        });
	return promise;
  };
  this.insert = function(remesa) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'POST',
            url: baseUrl + '/api/Remesa',
            data: remesa
        }).success(function(data, status, headers, config) {
            defered.resolve(data);
        }).error(function(data, status, headers, config) {
            if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });

        return promise;
    };
    /*this.update = function(idRemesa, remesa) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'PUT',
            url: baseUrl + '/api/Remesa/' + idRemesa,
            data: remesa
        }).success(function(data, status, headers, config) {
            defered.resolve(data);
        }).error(function(data, status, headers, config) {
            if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });

        return promise;
    };*/
    /*this.delete = function(idRemesa) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'DELETE',
            url: baseUrl + '/api/Remesa/' + idRemesa
        }).success(function(data, status, headers, config) {
            defered.resolve(data);
        }).error(function(data, status, headers, config) {
            if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });

        return promise;
    };*/
}

app.provider("remesaResource",RemesaResourceProvider);

app.constant("baseUrl", ".");
app.config(['baseUrl', 'remesaResourceProvider',
  function(baseUrl, remesaResourceProvider) {
    remesaResourceProvider.setBaseUrl(baseUrl);
  }
]);

/*app.config(['$routeProvider',function($routeProvider) {  
  
  $routeProvider.when('/remesa/llistat', {
    templateUrl: "llistat_remeses   .html",
    controller: "RemesaListController",
    resolve: {
      persones:['remesaResource','$route',function(remesaResource) {
        return remesaResource.list();
      }]
    }
  });
    
  $routeProvider.when('/remesa/edit/:idRemesa', {
            templateUrl: "detall_remesa.html",
            controller: "EditRemesaController",
            resolve: {
                remesa: ['remesaResource', '$route', function(remesaResource, $route) {
                        return remesaResource.get($route.current.params.idRemesa);
                    }]
            }
    });    
     
   $routeProvider.when('/remesa/nova', {
            templateUrl: "detall_remesa.html",
            controller: "NewRemesaController"
    });     
  
}]);*/

app.controller("NewRemesaController", ['$scope', 'remesaResource', '$location', function($scope, remesaResource, $location) {
   var result_remesa=""; 
        
   $scope.remesa={
    idRemesa:"",
    concepte:"",
    dataRemesa:new Date(),
    import:0,
    numRebuts:0,    
    selected:false  //USed only to distinguish if the remesa is selected or not in the table
  };
  
  $scope.desar = function() {
            if ($scope.remesaForm.$valid) {
                remesaResource.insert($scope.remesa).then(function() {                    
                        $location.path("/remesa/llistat");
                    
                }, function(bussinessMessages) {
                    $scope.bussinessMessages = bussinessMessages;
                });
            } else {
                alert("Hi ha dades invàlides");
            }
        };       
   
}]);

/*app.controller("EditRemesaController", ['$scope', 'remesa', 'remesaResource', '$location', function($scope, remesa, remesaResource, $location) {

        $scope.opts_tipus_relacio=[
        {
          idRelacio:"SOCI",
          nom:"SOCI"
        },
        {
          idRelacio:"TRANSE",
          nom:"TRANSEUNT"
        },
        {
          idRelacio:"ALUMNE",
          nom:"ALUMNE"
        },	
        {
          idRelacio:"ALTRA",
          nom:"ALTRA"
        }
        ];
        
    
        $scope.remesa = remesa;
        $scope.remesa.telefon=parseInt($scope.remesa.telefon);

        
        $scope.desar = function() {
            if ($scope.form.$valid) {
                 remesaResource.update($scope.remesa.idRemesa, $scope.remesa).then(function() {
                    if ($scope.remesa.tipusRelacioClub==="SOCI"){
                        $location.path("/remesa/llistatSocis");
                    }else if($scope.remesa.tipusRelacioClub==="TRANSE"){
                        $location.path("/remesa/llistatTranseunts");
                    }else if($scope.remesa.tipusRelacioClub==="ALUMNE"){
                        $location.path("/remesa/llistatAlumnes");
                    }else{
                        $location.path("/remesa/llistat");
                    } 
                }, function(bussinessMessages) {
                    $scope.bussinessMessages = bussinessMessages;
                });
            } else {
                alert("Hi ha dades invàlides");
            }
        };       
       
 }]);*/
 
app.controller("RemesaListController", ['$scope', 'remeses', 'remesaResource', function($scope, remeses, remesaResource) {
        $scope.selectedAll=false;
        $scope.remeses = remeses;
        $scope.accio="";
             
        //alert("HOLA");
        /*$scope.changeAccio=function(){
            if ($scope.accio!==null){
                var json_action="{'action':"+$scope.accio+",'idsRemesa':[";
                var any_selection=false;
                for (i=0;i<$scope.remeses.length;i++){
                    if ($scope.remeses[i].selected){
                        json_action+=$scope.remeses[i].idRemesa+",";
                        any_selection=true;
                    }                
                }
                if (any_selection) {
                    json_action = json_action.substring(0, json_action.length - 1);
                    json_action+="]}";
                }else{
                    json_action=null;
                }
                alert(json_action);
            }
        };
        $scope.borrar = function(idRemesa) {
            remesaResource.delete(idRemesa).then(function() {
                remesaResource.list().then(function(remeses) {
                    $scope.remeses = remeses;
                }, function(bussinessMessages) {
                    $scope.bussinessMessages = bussinessMessages;
                });
            }, function(bussinessMessages) {
                $scope.bussinessMessages = bussinessMessages;
            });
        };
        $scope.changeSelection=function(){
            //$scope.selectedAll=!$scope.selectedAll;
            for (i=0;i<$scope.remeses.length;i++){
                $scope.remeses[i].selected=$scope.selectedAll;
            }
        };*/

    }]);
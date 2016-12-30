var app = angular.module("app",['ngRoute','ui.bootstrap']);
//var app = angular.module("modalFormApp", ['ui.bootstrap']);
function PersonaResourceProvider() {
  var _baseUrl;
  this.setBaseUrl = function(baseUrl) {
    _baseUrl = baseUrl;
  };
  this.$get=['$http','$q',function($http,$q) {
    return new PersonaResource($http,$q,_baseUrl);
  }];
}

function PersonaResource($http,$q,baseUrl) {
  
  this.get=function(idPersona) {
        var defered=$q.defer();
	var promise=defered.promise;
        $http({
          method: 'GET',          
          url: baseUrl + '/api/Persona/'+idPersona
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
    
  this.list=function(tipus) {
        tipus = typeof tipus !== 'undefined' ? tipus : "";
	var defered=$q.defer();
	var promise=defered.promise;
        var parameters="";
        if (tipus!=="") parameters="?tipus="+tipus;
                
        $http({
          method: 'GET',          
          url: baseUrl +'/api/Persona'+parameters
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
  this.insert = function(persona) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'POST',
            url: baseUrl + '/api/Persona',
            data: persona
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
    this.update = function(idPersona, persona) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'PUT',
            url: baseUrl + '/api/Persona/' + idPersona,
            data: persona
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
    this.delete = function(idPersona) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'DELETE',
            url: baseUrl + '/api/Persona/' + idPersona
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
}

app.provider("personaResource",PersonaResourceProvider);

app.constant("baseUrl", ".");
app.config(['baseUrl', 'personaResourceProvider',
  function(baseUrl, personaResourceProvider) {
    personaResourceProvider.setBaseUrl(baseUrl);
  }
]);

app.config(['$routeProvider',function($routeProvider) {
  
  $routeProvider.when('/', {
    templateUrl: "main.html",
    controller: "MainControler"
  }); 
  
  $routeProvider.when('/persona/llistat', {
    templateUrl: "llistat.html",
    controller: "PersonaListController",
    resolve: {
      persones:['personaResource','$route',function(personaResource) {
        return personaResource.list();
      }]
    }
  });
  $routeProvider.when('/persona/llistatTranseunts', {
    templateUrl: "llistat.html",
    controller: "PersonaListController",
    resolve: {
      persones:['personaResource','$route',function(personaResource) {
        return personaResource.list("TRANSE");
      }]
    }
  });
  $routeProvider.when('/persona/llistatSocis', {
    templateUrl: "llistat.html",
    controller: "PersonaListController",
    resolve: {
      persones:['personaResource','$route',function(personaResource) {
        return personaResource.list("SOCI");
      }]
    }
  });
  $routeProvider.when('/persona/llistatAlumnes', {
    templateUrl: "llistat.html",
    controller: "PersonaListController",
    resolve: {
      persones:['personaResource','$route',function(personaResource) {
        return personaResource.list("ALUMNE");
      }]
    }
  });
  $routeProvider.when('/persona/edit/:idPersona', {
            templateUrl: "detall.html",
            controller: "EditPersonaController",
            resolve: {
                persona: ['personaResource', '$route', function(personaResource, $route) {
                        return personaResource.get($route.current.params.idPersona);
                    }]
            }
    });
    
     
   $routeProvider.when('/persona/nova', {
            templateUrl: "detall.html",
            controller: "NewPersonaController"
        });
  $routeProvider.when('/remesa/llistat', {
    templateUrl: "llistat_remeses.html",
    controller: "RemesaListController",
    resolve: {
      remeses:['remesaResource','$route',function(remesaResource) {
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
            templateUrl: "form_remeses.html",
            controller: "NewRemesaController"
    });
    
  $routeProvider.otherwise({
        redirectTo: '/'
  });   
  
}]);

app.directive('caTooltip', function() {
    return {
        // Restrict it to be an attribute in this case
        restrict: 'A',
        // responsible for registering DOM listeners as well as updating the DOM
        link: function(scope, element, attrs) {
            //$(element).tooltip(scope.$eval(attrs.toolbarTip));
	    $(element).tooltip();
        }
    };
});
app.directive('ngConfirmClick', [
        function(){
            return {
                link: function (scope, element, attr) {
                    var msg = attr.ngConfirmClick || "Are you sure?";
                    var clickAction = attr.confirmedClick;
                    element.bind('click',function (event) {
                        if ( window.confirm(msg) ) {
                            scope.$eval(clickAction)
                        }
                    });
                }
            };
 }])

app.controller("NewPersonaController", ['$scope', 'personaResource', '$location', function($scope, personaResource, $location) {
   var result_persona=""; 
      
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
         
        
   $scope.persona={
    nif:"",
    nom:"",
    cognoms:"",        
    telefon:"",
    email:"",
    dataNaixement:undefined,
    dataAlta:undefined,
    dataBaixa:undefined,
    comentaris:undefined,
    tipusRelacioClub:undefined, //Soci, transeünt, alumne,  altre
    
    selected:false,  //USed only to distinguish if the person is selected or not in the table
    
    addressData:{
	carrer:undefined,
	poblacio:undefined,
	provincia:undefined,
 	cp:undefined,
    },

    dadesBancaries:{
        iban:undefined,        
        entitat:undefined,
        oficina:undefined,
        dc:undefined,
        compte:undefined               
    },
    
    marcadorsInteres: {
	deBaixa:false,  //Activat quan donem de baixa	
	ambAlerta:false,  //Activat quan té alguna alerta d'interés. Ex. Falta DNI, Falta títol, adreça o qualsevol altra cosa.
	raoAlerta:undefined
    }
    
  
  };
  
  $scope.desar = function() {
            if ($scope.form.$valid) {
                personaResource.insert($scope.persona).then(function() {
                    if ($scope.persona.tipusRelacioClub==="SOCI"){
                        $location.path("/persona/llistatSocis");
                    }else if($scope.persona.tipusRelacioClub==="TRANSE"){
                        $location.path("/persona/llistatTranseunts");
                    }else if($scope.persona.tipusRelacioClub==="ALUMNE"){
                        $location.path("/persona/llistatAlumnes");
                    }else{
                        $location.path("/persona/llistat");
                    } 
                }, function(bussinessMessages) {
                    $scope.bussinessMessages = bussinessMessages;
                });
            } else {
                alert("Hi ha dades invàlides");
            }
        };       
   
}]);

app.controller("EditPersonaController", ['$scope', 'persona', 'personaResource', '$location', function($scope, persona, personaResource, $location) {

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
        
    
        $scope.persona = persona;
        $scope.persona.telefon=parseInt($scope.persona.telefon);

        
        $scope.desar = function() {
            if ($scope.form.$valid) {
                 personaResource.update($scope.persona.idPersona, $scope.persona).then(function() {
                    if ($scope.persona.tipusRelacioClub==="SOCI"){
                        $location.path("/persona/llistatSocis");
                    }else if($scope.persona.tipusRelacioClub==="TRANSE"){
                        $location.path("/persona/llistatTranseunts");
                    }else if($scope.persona.tipusRelacioClub==="ALUMNE"){
                        $location.path("/persona/llistatAlumnes");
                    }else{
                        $location.path("/persona/llistat");
                    } 
                }, function(bussinessMessages) {
                    $scope.bussinessMessages = bussinessMessages;
                });
            } else {
                alert("Hi ha dades invàlides");
            }
        };       
       
 }]);
 
app.controller("PersonaListController", ['$scope', 'persones', 'personaResource', function($scope, persones, personaResource) {
        $scope.selectedAll=false;
        $scope.persones = persones;
        $scope.accio="";
        
        $scope.opts_accio=[
        {
          idAccio:"DEL",
          nom:"ESBORRAR"
        },
        {
          idAccio:"NEW_REMESA",
          nom:"NOVA REMESA"
        }        
        ];

        $scope.changeAccio=function(){
            if ($scope.accio!==null){
                var json_action="{'action':"+$scope.accio+",'idsPersona':[";
                var any_selection=false;
                for (i=0;i<$scope.persones.length;i++){
                    if ($scope.persones[i].selected){
                        json_action+=$scope.persones[i].idPersona+",";
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
        $scope.borrar = function(idPersona) {
            personaResource.delete(idPersona).then(function() {
                personaResource.list().then(function(persones) {
                    $scope.persones = persones;
                }, function(bussinessMessages) {
                    $scope.bussinessMessages = bussinessMessages;
                });
            }, function(bussinessMessages) {
                $scope.bussinessMessages = bussinessMessages;
            });
        };
        $scope.changeSelection=function(){
            //$scope.selectedAll=!$scope.selectedAll;
            for (i=0;i<$scope.persones.length;i++){
                $scope.persones[i].selected=$scope.selectedAll;
            }
        };

    }]);
  

app.controller("MainControler", ['$scope',function($scope) {
	$scope.urlCap="cap.html";
	$scope.urlPeu="peu.html";
        $scope.urlMenu="main.html";
}]);

package cat.club.tennis.presentacio.controller;

import cat.club.tennis.domini.Remesa;
import cat.club.tennis.persistencia.BussinessException;
import cat.club.tennis.persistencia.BussinessMessage;
import cat.club.tennis.persistencia.RemesaDAO;
import cat.club.tennis.persones.presentacio.json.JsonTransformer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Controller
public class RemesaController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    RemesaDAO remesaDAO;  
    
    @RequestMapping(value = "/Remesa/{idRemesa}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idRemesa") int idRemesa) {
        try {
            Remesa remesa = remesaDAO.get(idRemesa);
            String jsonEixida = jsonTransformer.toJson(remesa);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonEixida);
            
        } catch (BussinessException ex) {
            List<BussinessMessage> bussinessMessage=ex.getBussinessMessages();
            String jsonEixida = jsonTransformer.toJson(bussinessMessage);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonEixida);
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
        }

    }

    @RequestMapping(value = "/Remesa", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            Remesa remesa = (Remesa) jsonTransformer.fromJson(jsonEntrada, Remesa.class);
            remesaDAO.insert(remesa);
            String jsonEixida = jsonTransformer.toJson(remesa);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonEixida);
            
        } catch (BussinessException ex) {
            List<BussinessMessage> bussinessMessage=ex.getBussinessMessages();
            String jsonEixida = jsonTransformer.toJson(bussinessMessage);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonEixida);
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }

            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    @RequestMapping(value = "/Remesa/AddLinies", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insertLinies(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            //Remesa remesa = (Remesa) jsonTransformer.fromJson(jsonEntrada, Remesa.class);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonEntrada);
            String concepte = (String) jsonObject.get("concepte");
            int idRemesa = (int) jsonObject.get("idRemesa");
            Float importe = (Float) jsonObject.get("importe");
            JSONArray idsPersona= (JSONArray) jsonObject.get("idsPersona");

            //remesaDAO.insert(remesa);
            remesaDAO.addLiniesRemesa(idRemesa, idsPersona, concepte, importe);
            String jsonEixida = "{'status':'OK'}";
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonEixida);
            
        } catch (BussinessException ex) {
            List<BussinessMessage> bussinessMessage=ex.getBussinessMessages();
            String jsonEixida = jsonTransformer.toJson(bussinessMessage);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonEixida);
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }

            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    @RequestMapping(value = "/Remesa", method = RequestMethod.GET, produces = "application/json")
    public void find(@RequestParam(value="tipus",required=false) String tipus,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
             //System.out.println("PPPPP Remesa pipi");
            List<Remesa> remeses = remesaDAO.findAll();
            String jsonEixida = jsonTransformer.toJson(remeses);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonEixida);
            
        } catch (BussinessException ex) {
            List<BussinessMessage> bussinessMessage=ex.getBussinessMessages();
            String jsonEixida = jsonTransformer.toJson(bussinessMessage);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonEixida);
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
        }

    }   
    
    @RequestMapping(value = "/Remesa/{idRemesa}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idRemesa") int idRemesa) {
        try {
            Remesa remesa = (Remesa) jsonTransformer.fromJson(jsonEntrada, Remesa.class);
            remesaDAO.update(idRemesa,remesa);
            String jsonEixida = jsonTransformer.toJson(remesa);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonEixida);
            
        } catch (BussinessException ex) {
            List<BussinessMessage> bussinessMessage=ex.getBussinessMessages();
            String jsonEixida = jsonTransformer.toJson(bussinessMessage);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonEixida);
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }

            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    @RequestMapping(value = "/Remesa/{idRemesa}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idRemesa") int idRemesa) {
        try {
            remesaDAO.delete(idRemesa);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            
        } catch (BussinessException ex) {
            List<BussinessMessage> bussinessMessage=ex.getBussinessMessages();
            String jsonEixida = jsonTransformer.toJson(bussinessMessage);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonEixida);
            } catch (IOException ex1) {
                Logger.getLogger(RemesaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
        }

    }    
    
}

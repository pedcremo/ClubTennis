package cat.club.tennis.presentacio.controller;

import cat.club.tennis.domini.Persona;
import cat.club.tennis.persistencia.BussinessException;
import cat.club.tennis.persistencia.BussinessMessage;
import cat.club.tennis.persistencia.PersonaDAO;
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

@Controller
public class PersonaController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    PersonaDAO personaDAO;  
    
    @RequestMapping(value = "/Persona/{idPersona}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idPersona") int idPersona) {
        try {
            Persona persona = personaDAO.get(idPersona);
            String jsonEixida = jsonTransformer.toJson(persona);
            
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
                Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
        }

    }

    @RequestMapping(value = "/Persona", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            Persona persona = (Persona) jsonTransformer.fromJson(jsonEntrada, Persona.class);
            personaDAO.insert(persona);
            String jsonEixida = jsonTransformer.toJson(persona);
            
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
                Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex1);
            }

            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    
    @RequestMapping(value = "/Persona", method = RequestMethod.GET, produces = "application/json")
    public void find(@RequestParam(value="tipus",required=false) String tipus,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
           
            List<Persona> persones = personaDAO.findAll(tipus);
            String jsonEixida = jsonTransformer.toJson(persones);
            
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
                Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
        }

    }   
    
    @RequestMapping(value = "/Persona/{idPersona}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idPersona") int idPersona) {
        try {
            Persona persona = (Persona) jsonTransformer.fromJson(jsonEntrada, Persona.class);
            personaDAO.update(idPersona,persona);
            String jsonEixida = jsonTransformer.toJson(persona);
            
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
                Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex1);
            }

            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    @RequestMapping(value = "/Persona/{idPersona}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idPersona") int idPersona) {
        try {
            personaDAO.delete(idPersona);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            
        } catch (BussinessException ex) {
            List<BussinessMessage> bussinessMessage=ex.getBussinessMessages();
            String jsonEixida = jsonTransformer.toJson(bussinessMessage);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonEixida);
            } catch (IOException ex1) {
                Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
        }

    }    
    
}

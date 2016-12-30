/* READ http://chathurangat.blogspot.com.es/2012/02/jasperreport-with-spring-mvc-fully.html
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.club.tennis.presentacio.controller;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import cat.club.tennis.persistencia.PersonaDAO;
import cat.club.tennis.domini.Persona;
import cat.club.tennis.persistencia.BussinessException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
 
 
 
@Controller
@RequestMapping("/report/")
public class ReportController {
 
 
    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);
 
    @Autowired
    PersonaDAO personaDao;
 
    @RequestMapping(method = RequestMethod.GET , value = "pdf")
    public ModelAndView generatePdfReport(ModelAndView modelAndView) throws BussinessException{
 
        //logger.debug("--------------generate PDF report----------");
 
        Map<String,Object> parameterMap = new HashMap<String,Object>();
 
        List<Persona> personesList = personaDao.findAll("SOCI");
 
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(personesList);
 
        parameterMap.put("datasource", JRdataSource);
 
        //pdfReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("pdfReport", parameterMap);
 
        return modelAndView;
 
    }//generatePdfReport
 
 
 
    /*@
    RequestMapping(method = RequestMethod.GET , value = "xls")
    public ModelAndView generateXlsReport(ModelAndView modelAndView) throws BussinessException{
 
        //logger.debug("--------------generate XLS report----------");
 
        Map<String,Object> parameterMap = new HashMap<String,Object>();
 
        List<Persona> personesList = personaDao.findAll("SOCI");
 
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(personesList);
 
        parameterMap.put("datasource", JRdataSource);
 
        //xlsReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("xlsReport", parameterMap);
 
        return modelAndView;
 
    }//generatePdfReport
 
 
    @RequestMapping(method = RequestMethod.GET , value = "csv")
    public ModelAndView generateCsvReport(ModelAndView modelAndView) throws BussinessException{
 
        //logger.debug("--------------generate CSV report----------");
 
        Map<String,Object> parameterMap = new HashMap<String,Object>();
 
        List<Persona> personesList = personaDao.findAll("SOCI");
 
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(personesList);
 
        parameterMap.put("datasource", JRdataSource);
 
        //xlsReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("csvReport", parameterMap);
 
        return modelAndView;
 
    }//generatePdfReport
 
 
 
    @RequestMapping(method = RequestMethod.GET , value = "html")
    public ModelAndView generateHtmlReport(ModelAndView modelAndView) throws BussinessException{
 
        //logger.debug("--------------generate HTML report----------");
 
        Map<String,Object> parameterMap = new HashMap<String,Object>();
 
        List<Persona> personesList = personaDao.findAll("SOCI");
 
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(personesList);
 
        parameterMap.put("datasource", JRdataSource);
 
        //xlsReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("htmlReport", parameterMap);
 
        return modelAndView;
 
    }//generatePdfReport
 */
 
}//ReportController
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.club.tennis.scripts;

import cat.club.tennis.domini.*;
import cat.club.tennis.persistencia.PersonaDAO;
import cat.club.tennis.persistencia.impl.PersonaDAOImplJDBC;
import java.util.List;
import java.util.Iterator;
/**
 *
 * @author perecrespomolina
 */
public class Main {
    public static void main(String[] args) throws Exception {
          //Resultat esperat ES84 0049 6874 11 2090003657
          //System.out.println(CalcularIBAN.getIban("ES","0049","6874","","2090003657"));
          PersonaDAO personaDAO=new PersonaDAOImplJDBC();  
          List<Persona> persones = personaDAO.findAll("");
          Iterator<Persona> iterator=persones.iterator();
          while (iterator.hasNext()){
              Persona soci=iterator.next();
              String entitat=soci.getDadesBancaries().getEntitat();
              String oficina=soci.getDadesBancaries().getOficina();
              String compte=soci.getDadesBancaries().getCompte();
              if (compte!=null && compte.length()==10){
                String dcIban=CalcularIBAN.getIban("ES",entitat,oficina,"",compte);
                String dc=CalcularIBAN.calcularDC(entitat,oficina,compte);
                DadesBancaries dbanc=new DadesBancaries();
                dbanc.setIban("ES"+dcIban);
                dbanc.setDc(dc);
                dbanc.setCompte(compte);
                dbanc.setEntitat(entitat);
                dbanc.setOficina(oficina);
                soci.setDadesBancaries(dbanc);
                personaDAO.update(soci.getIdPersona(), soci);
                System.out.println(soci.getCognoms()+" ,"+soci.getNom()+" -> ES"+dcIban+" "+entitat+" "+oficina+" "+dc+" "+compte);
              }else{
                System.out.println(soci.getCognoms()+" ,"+soci.getNom()+" -> "+compte);
              }
          }
          
          
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.club.tennis.domini;

import java.util.List;

/**
 *
 * @author pedcremo
 */
public class Remesa {
    private int idRemesa;
    private String concepte;
    private String dataRemesa;
    
    private List<LiniaRemesa> liniesRemesa; //An array with the details 
    /**
     * @return the idRemesa
     */
    public int getIdRemesa() {
        return idRemesa;
    }

    /**
     * @param idRemesa the idRemesa to set
     */
    public void setIdRemesa(int idRemesa) {
        this.idRemesa = idRemesa;
    }
    
    /**
     * @return the concepte
     */
    public String getConcepte(){
        return concepte;
    }
    /**
     * @param concepte the concepte to set
     */
    public void setConcepte(String concepte){
        this.concepte=concepte;
    }
    /**
     * @return the dataRemesa
     */
    public String getDataRemesa(){
        return dataRemesa;
    }
    /**
     * @param dataRemesa the dataRemesa to set
     */
    public void setDataRemesa(String dataRemesa){
        this.dataRemesa=dataRemesa;
    }
    /**
     * @return the LiniaRemesa array
     */
    public List getLiniesRemesa(){
        return liniesRemesa;
    }
    /**
     * @param liniesRemesa the liniesRemesa to set
     */
    public void setLiniesRemesa(List liniesRemesa){
        this.liniesRemesa=liniesRemesa;
    }
}

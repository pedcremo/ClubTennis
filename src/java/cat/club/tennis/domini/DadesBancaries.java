
package cat.club.tennis.domini;


public class DadesBancaries {

    private String iban;
    private String entitat;
    private String oficina;
    private String dc;
    private String compte;
            
    public DadesBancaries() {
    }

    public DadesBancaries(String iban, String entitat, String oficina,String dc,String compte) {
        this.iban = iban;
        this.entitat = entitat;
        this.oficina = oficina;
        this.dc = dc;
        this.compte = compte;        
    }    
    /**
     * @return the iban
     */
    public String getIban() {
        return iban;
    }
    /**
     * @param iban the iban to set
     */
    public void setIban(String iban) {
        this.iban = iban;
    }
    /**
     * @return the entitat
     */
    public String getEntitat() {
        return entitat;
    }
    /**
     * @param entitat the entitat to set
     */
    public void setEntitat(String entitat) {
        this.entitat = entitat;
    }
    /**
     * @return the oficina
     */
    public String getOficina() {
        return oficina;
    }
    /**
     * @param oficina the oficina to set
     */
    public void setOficina(String oficina) {
        this.oficina = oficina;
    }
    /**
     * @return the dc
     */
    public String getDc() {
        return dc;
    }
    /**
     * @param dc the dc to set
     */
    public void setDc(String dc) {
        this.dc = dc;
    }
    /**
     * @return the compte
     */
    public String getCompte() {
        return compte;
    }
    /**
     * @param compte the compte to set
     */
    public void setCompte(String compte) {
        this.compte = compte;
    }

    
}

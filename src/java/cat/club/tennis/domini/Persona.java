package cat.club.tennis.domini;

//import java.util.Date;

public class Persona {
    private int idPersona;
    private String nom;
    private String cognoms;
    
    private String nif;    
    private String telefon;
    private String email;
    private String dataNaixement;
    private String dataAlta;
    private String dataBaixa;
    private String comentaris;
    private TipusRelacioClub tipusRelacioClub; //Soci, transeunt, alumne, altres ...
    private DadesBancaries dadesBancaries=new DadesBancaries();
    private AddressData addressData=new AddressData();
    private MarcadorsInteres marcadorsInteres=new MarcadorsInteres();

    
    /**
     * @return the idPersona
     */
    public int getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * @param nif the nif to set
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the cognoms
     */
    public String getCognoms() {
        return cognoms;
    }

    /**
     * @param cognoms the cognoms to set
     */
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }
    
    /**
     * @return the telefono
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * @param telefon the telefon to set
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the dataNaixement
     */
    public String getDataNaixement() {
        return dataNaixement;
    }
    /**
     * @param dataNaixement the dataNaixement to set
     */
    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }    
   
    /**
     * @return the dataAlta
     */
    public String getDataAlta() {
        return dataAlta;
    }
    /**
     * @param dataAlta the dataAlta to set
     */
    public void setDataAlta(String dataAlta) {
        this.dataAlta = dataAlta;
    }
    
    /**
     * @return the dataBaixa
     */
    public String getDataBaixa() {
        return dataBaixa;
    }
    /**
     * @param dataBaixa the dataBaixa to set
     */
    public void setDataBaixa(String dataBaixa) {
        this.dataBaixa = dataBaixa;
    }
        
    /**
     * @return the comentaris
     */
    public String getComentaris() {
        return comentaris;
    }

    /**
     * @param comentaris the comentaris to set
     */
    public void setComentaris(String comentaris) {
        this.comentaris = comentaris;
    }
    
    
    /**
     * @return the tipusRelacioClub
     */
    public TipusRelacioClub getTipusRelacioClub() {
        return tipusRelacioClub;
    }
    /**
     * @param tipusRelacioClub the tipusRelacioClub to set
     */
    public void setTipusRelacioClub(TipusRelacioClub tipusRelacioClub) {
        this.tipusRelacioClub = tipusRelacioClub;
    }
    
    /**
     * @return the datosFinancieros
     */
    public DadesBancaries getDadesBancaries() {
        return dadesBancaries;
    }

    /**
     * @param dadesBancaries the dadesBancaries to set
     */
    public void setDadesBancaries(DadesBancaries dadesBancaries) {
        this.dadesBancaries = dadesBancaries;
    }
    /**
     * @return the addressData
     */
    public AddressData getAddressData() {
        return addressData;
    }

    /**
     * @param addressData the addressData to set
     */
    public void setAddressData(AddressData addressData) {
        this.addressData = addressData;
    }
    /**
     * @return the marcadorsInteres
     */
    public MarcadorsInteres getMarcadorsInteres() {
        return marcadorsInteres;
    }

    /**
     * @param marcadorsInteres the marcadorsInteres to set
     */
    public void setMarcadorsInteres(MarcadorsInteres marcadorsInteres) {
        this.marcadorsInteres = marcadorsInteres;
    }
}

package cat.club.tennis.persistencia.impl;

import cat.club.tennis.persistencia.BussinessException;
import cat.club.tennis.persistencia.PersonaDAO;
import cat.club.tennis.domini.Persona;
import cat.club.tennis.domini.TipusRelacioClub;
//import cat.club.tennis.domini.TipoInteres;
//import es.cursoangular.hipotecas.persistencia.BussinessMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.ParseException;

//Per als scripts
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class PersonaDAOImplJDBC implements PersonaDAO {

    @Override
    public void insert(Persona persona) throws BussinessException {

         
        Connection connection = null;

        try {
            connection = getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            //java.util.Date date = sdf.parse(strDate);
            //java.sql.Date sqlDate = new Date(date.getTime());

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `persona` (`nif`, `nom`, `cognoms`,`telefon`,`email`, `dataNaixement`, `dataAlta`, `dataBaixa`, `comentaris`, `tipusRelacioClub`, `carrer`, `poblacio`, `provincia`, `cp`, `iban`, `entitat`, `oficina`, `dc`,`compte`,`deBaixa`,`ambAlerta`,`raoAlerta` ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
             
            preparedStatement.setString(1, persona.getNif());
            preparedStatement.setString(2, persona.getNom());
            preparedStatement.setString(3, persona.getCognoms() );            
            preparedStatement.setString(4, persona.getTelefon() );
            preparedStatement.setString(5, persona.getEmail() );
            
            java.util.Date date = null;
            java.sql.Date sqlDate = null;
            
            try{
                date=sdf.parse(persona.getDataNaixement());
                sqlDate = new Date(date.getTime());
            }catch(ParseException pe){  
                sqlDate=null;
            }catch(NullPointerException ex){
                sqlDate=null;
            }
            
            preparedStatement.setDate(6,sqlDate );
            
            try{
                date = sdf.parse(persona.getDataAlta());
                sqlDate = new Date(date.getTime());
            }catch(ParseException pe){  
                sqlDate=null;
            }catch(NullPointerException ex){
                sqlDate=null;
            }
                        
            preparedStatement.setDate(7,sqlDate );
            
            try{
                date = sdf.parse(persona.getDataBaixa());
                sqlDate = new Date(date.getTime());
            }catch(ParseException pe){  
                sqlDate=null;
            }catch(NullPointerException ex){
                sqlDate=null;
            }
                        
            preparedStatement.setDate(8,sqlDate );
                        
            preparedStatement.setString(9, persona.getComentaris() );
            preparedStatement.setString(10, persona.getTipusRelacioClub().name() );
            
            preparedStatement.setString(11, persona.getAddressData().getCarrer() );
            preparedStatement.setString(12, persona.getAddressData().getPoblacio() );
            preparedStatement.setString(13, persona.getAddressData().getProvincia() );
            preparedStatement.setString(14, persona.getAddressData().getCp() );
            
            preparedStatement.setString(15, persona.getDadesBancaries().getIban());
            preparedStatement.setString(16, persona.getDadesBancaries().getEntitat());
            preparedStatement.setString(17, persona.getDadesBancaries().getOficina());
            preparedStatement.setString(18, persona.getDadesBancaries().getDc());
            preparedStatement.setString(19, persona.getDadesBancaries().getCompte());
            
            preparedStatement.setBoolean(20, persona.getMarcadorsInteres().isDeBaixa());
            preparedStatement.setBoolean(21, persona.getMarcadorsInteres().isAmbAlerta());
            preparedStatement.setString(22, persona.getMarcadorsInteres().getRaoAlerta());
                        
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {

                }
            }
        }
    }

    @Override
    public void update(int idPersona, Persona persona) throws BussinessException {

        Connection connection = null;

        try {
            connection = getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `persona` SET `nif`=?, `nom`=?, `cognoms`=?, `telefon`=?, `email`=?, `dataNaixement`=?, `dataAlta`=?, `dataBaixa`=?, `comentaris`=?, `tipusRelacioClub`=?, `carrer`=?, `poblacio`=?, `provincia`=?, `cp`=?, `iban`=?, `entitat`=?, `oficina`=?, `dc`=?, `compte`=?, `deBaixa`=?, `ambAlerta`=?, `raoAlerta`=? WHERE idPersona=?");
            
            preparedStatement.setString(1, persona.getNif());
            preparedStatement.setString(2, persona.getNom());
            preparedStatement.setString(3, persona.getCognoms() );
            
            preparedStatement.setString(4, persona.getTelefon() );
            preparedStatement.setString(5, persona.getEmail() );
            
            java.util.Date date = null;
            java.sql.Date sqlDate = null;
            
            try{
                sdf.parse(persona.getDataNaixement());
                sqlDate = new Date(date.getTime());
            }catch(ParseException |NullPointerException pe){  
                sqlDate=null;
            }
            
            preparedStatement.setDate(6,sqlDate );
            
            try{
                date = sdf.parse(persona.getDataAlta());
                sqlDate = new Date(date.getTime());
            }catch(ParseException |NullPointerException pe){  
                sqlDate=null;
            }
                        
            preparedStatement.setDate(7,sqlDate );
            
            try{
                date = sdf.parse(persona.getDataBaixa());
                sqlDate = new Date(date.getTime()); 
            }catch(ParseException |NullPointerException pe){  
                sqlDate=null;
            }
                       
            preparedStatement.setDate(8,sqlDate );
            
            preparedStatement.setString(9, persona.getComentaris() );
            preparedStatement.setString(10, persona.getTipusRelacioClub().name() );
            
            preparedStatement.setString(11, persona.getAddressData().getCarrer() );
            preparedStatement.setString(12, persona.getAddressData().getPoblacio() );
            preparedStatement.setString(13, persona.getAddressData().getProvincia() );
            preparedStatement.setString(14, persona.getAddressData().getCp() );
            
            preparedStatement.setString(15, persona.getDadesBancaries().getIban());
            preparedStatement.setString(16, persona.getDadesBancaries().getEntitat());
            preparedStatement.setString(17, persona.getDadesBancaries().getOficina());
            preparedStatement.setString(18, persona.getDadesBancaries().getDc());
            preparedStatement.setString(19, persona.getDadesBancaries().getCompte());
            
            preparedStatement.setBoolean(20, persona.getMarcadorsInteres().isDeBaixa());
            preparedStatement.setBoolean(21, persona.getMarcadorsInteres().isAmbAlerta());
            preparedStatement.setString(22, persona.getMarcadorsInteres().getRaoAlerta());
            preparedStatement.setInt(23,idPersona);
            
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {

                }
            }
        }
    }

    @Override
    public Persona get(int idPersona) throws BussinessException {
        Connection connection = null;

        try {
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persona WHERE idPersona=?");
            
            preparedStatement.setInt(1, idPersona);
            
            ResultSet rst = preparedStatement.executeQuery();

            if (rst.next()) {
                return createPersona(rst);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {

                }
            }
        }
    }

    @Override
    public void delete(int idPersona) throws BussinessException {
        
        /*if (idHipoteca==1) {
            throw new BussinessException(new BussinessMessage(null,"No es posible borrar el seguro médico"));
        }*/
        
        Connection connection = null;

        try {
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM persona WHERE idPersona=?");

            preparedStatement.setInt(1, idPersona);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {

                }
            }
        }
    }

    @Override
    public List<Persona> findAll(String tipusRelacio) throws BussinessException {
        Connection connection = null;
        List<Persona> persones = new ArrayList<>();

        try {
            connection = getConnection();
            String where="where deBaixa=0 ";
            String sql="SELECT * FROM persona ";
            if (tipusRelacio!=null){
                if (tipusRelacio.equals("SOCI")) where+="and tipusRelacioClub='SOCI'";
                if (tipusRelacio.equals("TRANSE")) where+="and tipusRelacioClub='TRANSE'";
                if (tipusRelacio.equals("ALUMNE")) where+="and tipusRelacioClub='ALUMNE'";
            }else{
                where="";
            }
            sql+=where+" order by dataAlta ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();

            while (rst.next()) {
                persones.add(createPersona(rst));
            }

            return persones;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {

                }
            }
        }
    }

    private Persona createPersona(ResultSet rst) {
        try {
            Persona persona = new Persona();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            persona.setIdPersona(rst.getInt("idPersona"));
            persona.setNif(rst.getString("nif"));
            persona.setNom(rst.getString("nom"));
            persona.setCognoms(rst.getString("cognoms"));
                        
            persona.setTelefon(rst.getString("telefon"));
            persona.setEmail(rst.getString("email"));
            
            try{
                if (rst.getDate("dataNaixement")!=null ){
                    String dataStr = formatter.format(rst.getTimestamp("dataNaixement"));                
                    persona.setDataNaixement(dataStr);
                }else{
                    persona.setDataNaixement(null);
                }
            }catch(SQLException se){
                persona.setDataNaixement(null);
            }
            
            if (rst.getDate("dataAlta")!=null ){
                String dataStr = formatter.format(rst.getTimestamp("dataAlta"));                
                persona.setDataAlta(dataStr);                
            }else{
                persona.setDataAlta(null);   
            }
            
            try{
                if (rst.getDate("dataBaixa")!=null ){
                    String dataStr = formatter.format(rst.getTimestamp("dataBaixa"));                
                    persona.setDataBaixa(dataStr);
                }else{
                    persona.setDataBaixa(null);
                }
            }catch(SQLException se){
                persona.setDataBaixa(null);
            }
            persona.setComentaris(rst.getString("comentaris"));
            persona.setTipusRelacioClub(TipusRelacioClub.valueOf(rst.getString("tipusRelacioClub")));
            
            persona.getAddressData().setCarrer(rst.getString("carrer"));
            persona.getAddressData().setPoblacio(rst.getString("poblacio"));
            persona.getAddressData().setProvincia(rst.getString("provincia"));
            persona.getAddressData().setCp(rst.getString("cp"));
            
            persona.getDadesBancaries().setIban(rst.getString("iban"));
            persona.getDadesBancaries().setEntitat(rst.getString("entitat"));
            persona.getDadesBancaries().setOficina(rst.getString("oficina"));
            persona.getDadesBancaries().setDc(rst.getString("dc"));
            persona.getDadesBancaries().setCompte(rst.getString("compte"));
            
            persona.getMarcadorsInteres().setDeBaixa(rst.getBoolean("deBaixa"));
            persona.getMarcadorsInteres().setAmbAlerta(rst.getBoolean("ambAlerta"));
            persona.getMarcadorsInteres().setRaoAlerta(rst.getString("raoAlerta"));
            
           
            
            return persona;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Connection getConnection() {
        try {
           
            InitialContext initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envCtx.lookup("jdbc/tennis");

            return dataSource.getConnection();
        } catch (Exception ex) { //Xapussa que he fet per poder executar els scripts manuals 
            try{
                MysqlDataSource mysqlDS = new MysqlDataSource();
                
                mysqlDS.setURL("jdbc:mysql://localhost:3306/club_tennis");
                mysqlDS.setUser("root");
                mysqlDS.setPassword("4m0mjgds");
                //mysqlDS.setDatabaseName("club_tennis");
                return mysqlDS.getConnection();
            }catch (Exception ex2){
                throw new RuntimeException(ex);
            }
            
            
        }
    }

}

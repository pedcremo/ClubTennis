package cat.club.tennis.persistencia.impl;

import cat.club.tennis.persistencia.BussinessException;
import cat.club.tennis.persistencia.RemesaDAO;
import cat.club.tennis.domini.Remesa;
import cat.club.tennis.domini.LiniaRemesa;
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
import java.util.Iterator;
//Per als scripts
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Array;

public class RemesaDAOImplJDBC implements RemesaDAO {

    @Override
    public void insert(Remesa remesa) throws BussinessException {
       
        Connection connection = null;

        try {
            connection = getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            //java.util.Date date = sdf.parse(strDate);
            //java.sql.Date sqlDate = new Date(date.getTime());

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `remesa` (`concepte`, `dataRemesa` ) VALUES (?, ?)");
             
            preparedStatement.setString(1, remesa.getConcepte());
            
            java.util.Date date = null;
            java.sql.Date sqlDate = null;
            
            try{
                date=sdf.parse(remesa.getDataRemesa());
                sqlDate = new Date(date.getTime());
            }catch(ParseException pe){  
                sqlDate=null;
            }catch(NullPointerException ex){
                sqlDate=null;
            }
            
            preparedStatement.setDate(2,sqlDate );
            
                        
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
    public void update(int idRemesa, Remesa remesa) throws BussinessException {

        Connection connection = null;

        try {
            connection = getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `remesa` SET `concepte`=?, `dataRemesa`=?  WHERE idRemesa=?");
            
            preparedStatement.setString(1, remesa.getConcepte());
           
            java.util.Date date = null;
            java.sql.Date sqlDate = null;
            
            try{
                sdf.parse(remesa.getDataRemesa());
                sqlDate = new Date(date.getTime());
            }catch(ParseException |NullPointerException pe){  
                sqlDate=null;
            }
            
            preparedStatement.setDate(2,sqlDate );
            
            preparedStatement.setInt(3,idRemesa);
            
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
    public Remesa get(int idRemesa) throws BussinessException {
        Connection connection = null;
        Remesa auxRemesa=null;    
        try {
            connection = getConnection();
             
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM remesa WHERE idRemesa=?");
            
            preparedStatement.setInt(1, idRemesa);
            
            ResultSet rst = preparedStatement.executeQuery();

            if (rst.next()) {
                auxRemesa=createRemesa(rst);
                auxRemesa.setLiniesRemesa(getLiniesRemesa(idRemesa));
                return auxRemesa;
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
    public void delete(int idRemesa) throws BussinessException {
        
        /*if (idHipoteca==1) {
            throw new BussinessException(new BussinessMessage(null,"No es posible borrar el seguro médico"));
        }*/
        
        Connection connection = null;

        try {
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM linia_remesa as lWHERE idRemesa=?");

            preparedStatement.setInt(1, idRemesa);

            preparedStatement.executeUpdate();
            
            preparedStatement = connection.prepareStatement("DELETE FROM remesa as lWHERE idRemesa=?");

            preparedStatement.setInt(1, idRemesa);

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
    public void addLiniesRemesa(int idRemesa,ArrayList<String> idsPersona, String concepte, Float importe) throws BussinessException {
        Connection connection = null;
        List<Remesa> remeses = new ArrayList<>();
        try {
            connection = getConnection();
            String inContent="";
            Iterator<String> itr = idsPersona.iterator();
            while (itr.hasNext()) {            
                inContent+=itr.next()+",";
            }
            inContent=inContent.substring(0, inContent.length()-1); //Remove last comma
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persona WHERE idPersona in ("+inContent+")");
            ResultSet rst = preparedStatement.executeQuery();

            while (rst.next()) {
                /*PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `remesa` (`concepte`, `dataRemesa` ) VALUES (?, ?)");
             
                preparedStatement.setString(1, remesa.getConcepte());*/
                PreparedStatement preStatement=connection.prepareStatement("INSERT INTO `linia_remesa` (`cognoms`,`nom`,`tipusRelacioClub`,`adreca`,`cp`,`poblacio`,`oficina`,`entitat`,`dc`,`compte`,`concepte`,`importe,`idRemesa`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                preStatement.setString(1, rst.getString("cognoms"));
                preStatement.setString(2, rst.getString("nom"));
                preStatement.setString(3, rst.getString("tipusRelacioClub") );
                preStatement.setString(4, rst.getString("carrer") );
                preStatement.setString(5, rst.getString("cp") );
                preStatement.setString(6, rst.getString("poblacio") );
                preStatement.setString(7, rst.getString("oficina") );
                preStatement.setString(8, rst.getString("entitat") );
                preStatement.setString(9, rst.getString("dc") );
                preStatement.setString(10, rst.getString("compte") );
                preStatement.setString(11, concepte);
                preStatement.setFloat(12,importe);
                preStatement.setInt(13, idRemesa);
                
                preStatement.executeUpdate();
            }
            //preparedStatement.setArray(1, sqlArray);
            
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
    public List<Remesa> findAll() throws BussinessException {
        Connection connection = null;
        List<Remesa> remeses = new ArrayList<>();

        try {
            connection = getConnection();
            String where=" ";
            String sql="SELECT * FROM remesa ";
            
            sql+=where+" order by dataRemesa DESC";
            System.out.println("findAll remeses "+sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();

            while (rst.next()) {
                remeses.add(createRemesa(rst));
            }
            
            return remeses;
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
    private List<LiniaRemesa> getLiniesRemesa(int idRemesa){
         Connection connection = null;
        
        List<LiniaRemesa> liniesRemesa = new ArrayList<>();
        try {
            connection = getConnection();
           
            String sql="SELECT * FROM linia_remesa WHERE idRemesa=?";
            
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idRemesa);
            ResultSet rst = preparedStatement.executeQuery();

            while (rst.next()) {
                LiniaRemesa lr=new LiniaRemesa();
                lr.setIdLiniaRemesa(rst.getInt("idLiniaRemesa"));                
                lr.setCognoms(rst.getString("cognoms"));
                lr.setNom(rst.getString("nom"));
                lr.setTipusRelacioClub(rst.getString("tipusRelacioClub"));
                lr.setAdreca(rst.getString("adreca"));
                lr.setCp(rst.getString("cp"));
                lr.setPoblacio(rst.getString("poblacio"));
                lr.setOficina(rst.getString("oficina"));
                lr.setEntitat(rst.getString("entitat"));
                lr.setDc(rst.getString("dc"));
                lr.setCompte(rst.getString("compte"));
                //lr.setIban(rst.getString("iban"));
                lr.setConcepte(rst.getString("concepte"));
                lr.setImporte(rst.getFloat("importe"));                
                liniesRemesa.add(lr);               
            }

            return liniesRemesa;
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
    
    private Remesa createRemesa(ResultSet rst) {
        try {
            Remesa remesa = new Remesa();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            remesa.setIdRemesa(rst.getInt("idRemesa"));
            remesa.setConcepte(rst.getString("concepte"));
            
            try{
                if (rst.getDate("dataRemesa")!=null ){
                    String dataStr = formatter.format(rst.getTimestamp("dataRemesa"));                
                    remesa.setDataRemesa(dataStr);
                }else{
                    remesa.setDataRemesa(null);
                }
            }catch(SQLException se){
                remesa.setDataRemesa(null);
            }
                         
            return remesa;
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

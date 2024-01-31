package it.alfasoft.utentidaos;

import it.alfasoft.daosimple.DaoException;
import it.alfasoft.daosimple.DaoImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Named("utentiDAO") // Annotazione per indicare il nome del bean, può essere opzionale a seconda delle esigenze
@ApplicationScoped // Indica che il bean è scoped a livello di applicazione
public class UtentiDAOS extends DaoImpl<Utente,Integer> {

    @Override
    public Utente convertToDto(ResultSet resultSet) throws DaoException {
        Utente u = null;
        try{
            u = new Utente(
                    resultSet.getInt("id_utente"),
                    resultSet.getString("email"),
                    resultSet.getString("password_hash")
            );
            return u;
        }catch(Exception sqle){ sqle.printStackTrace(); throw new DaoException();}
    }

    @Override
    public boolean checkOggetto(Utente utente) throws DaoException {
        if(utente.getEmail()==null){ throw new DaoException("La nuova mail non puo' essere vuota"); }
        if(utente.getPassword()==null){ throw new DaoException("La nuova password non puo' essere vuota");}
        if(! verificaFormatoEmail(utente.getEmail())){ throw new DaoException("Formato email non valido");}
        return true;
    }
    public static boolean verificaFormatoEmail(String formatoDouble) {
        Matcher matcher = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(formatoDouble);
        if (matcher.matches()) { return true; }
        else { return false; }
    }

    @Override
    public Integer getGeneratedKey(Statement statement) throws DaoException {
        try{
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e) { e.printStackTrace(); throw new DaoException(); }
    }

    @Override
    public String getSelectByIdQuery(Integer id) {
        return "SELECT * FROM " + this.getTableName() + " x WHERE x.id_utente = " + id;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM " + this.getTableName();
    }

    @Override
    public String getInsertQuery(Utente utente) {
        return "INSERT INTO " + this.getTableName() + " (email,password_hash) "
                + "VALUES ('" + utente.getEmail() + "','" + utente.getPassword() + "');";
    }

    @Override
    public String getDeleteQuery(Integer id) {
        return "DELETE FROM " + this.getTableName() + " x WHERE x.id_utente = " + id + ";";
    }

    @Override
    public String getUpdateQuery(Integer id, Utente utente) {
        return "UPDATE " + this.getTableName()
                + " x SET x.email = '" + utente.getEmail() + "' , "
                + " x.password_hash = '" + utente.getPassword() + "'"
                + " WHERE x.id_utente = " + id;
    }

    @Override
    public String getReplaceQuery(Integer id, Utente utente) {
        String email = utente.getEmail();
        String password = utente.getPassword();
        StringBuilder queryBuilder = new StringBuilder("UPDATE " + this.getTableName() + " x SET ");
        if(email!=null){queryBuilder.append(" x.email = '" + email + "',");}
        if(password!=null){queryBuilder.append(" x.password_hash = '" + password + "'");}

        //cancella ultima virgola "," se esiste
        int lastIndex = queryBuilder.length() - 1; //lunghezza di ","
        if(lastIndex > 0 && queryBuilder.substring(lastIndex).equals(",")){
            queryBuilder.delete(lastIndex,lastIndex+1);
        }

        queryBuilder.append(" WHERE x.id_utente = " + id);
        return queryBuilder.toString();
    }

    @Override
    public String getSearchByStringQuery(String searchText) {
        StringBuilder qb = new StringBuilder("SELECT * FROM " + this.getTableName() + " x WHERE x.email LIKE '%" + searchText + "%';");
        return qb.toString();
    }

    @Override
    public String getSearchByObjectQuery(Utente searchObj) {
        StringBuilder qb = new StringBuilder("SELECT * FROM " + this.getTableName() + " x WHERE x.email LIKE '%" + searchObj.getEmail() + "%';");
        return qb.toString();
    }

    public int assegnaCategoria(String s, Integer integer) throws DaoException {
        try {
            // Controlla se il ruolo esiste:
            RuoliDAOS ruoliDAO = new RuoliDAOS();
            ruoliDAO.setTableName("food_delivery.ruoli");
            List<Ruolo> ruoliEsistenti = ruoliDAO.read();
            Ruolo role = checkIfCategoryIsAlreadyExistent(ruoliEsistenti, s.toUpperCase());

            if (role != null) {// Il ruolo esiste già
                associazioneCategoria(integer, role);
                return 0;
            } else {// Fai qualcos'altro se il ruolo non esiste
                Ruolo newRole = new Ruolo(ruoliEsistenti.size() + 1, s.toUpperCase(), ("Ruolo per gli utenti " + generateCategoriaName(s)));
                ruoliDAO.create(newRole);
                associazioneCategoria(integer, newRole);
                return 1; // Ritorno desiderato se il ruolo non esisteva
            }
        }catch (Exception e) { e.printStackTrace();throw new DaoException(); }
    }

    public void associazioneCategoria(Integer integer, Ruolo newRole) throws DaoException {
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO food_delivery.utenti_ruoli (id_utente,id_ruolo) VALUES (");
        queryBuilder.append(integer + "," + newRole.getId() + ");");
        try(
                Statement stmt = getConnection().createStatement();
        ){
            stmt.executeUpdate(queryBuilder.toString());
        }catch (Exception e) { throw new DaoException(); }
    }

    public Ruolo checkIfCategoryIsAlreadyExistent(List<Ruolo> ruoliEsistenti, String s){
        return ruoliEsistenti.stream()
                .filter(ruolo -> ruolo.getNomeRuolo().equalsIgnoreCase(s))
                .findFirst()
                .orElse(null);
    }

    public static String generateCategoriaName(String tableName) {
        // Ottieni il nome del DTO basato sul nome della tabella
        String dtoClassName = tableName.substring(tableName.lastIndexOf(".") + 1);

        // Fai la prima lettera maiuscola
        dtoClassName = dtoClassName.substring(0, 1).toUpperCase() + dtoClassName.substring(1);

        // Se l'ultima lettera è 'e', cambia l'ultima lettera in 'a', altrimenti se finisce con 'i', cambia in 'o'
        if (dtoClassName.endsWith("e")) {
            dtoClassName = dtoClassName.substring(0, dtoClassName.length() - 1) + "a";
        } else if (dtoClassName.endsWith("i")) {
            dtoClassName = dtoClassName.substring(0, dtoClassName.length() - 1) + "o";
        }

        return dtoClassName;
    }
}

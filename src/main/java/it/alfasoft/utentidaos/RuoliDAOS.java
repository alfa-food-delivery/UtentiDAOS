package it.alfasoft.utentidaos;

import it.alfasoft.daosimple.DaoException;
import it.alfasoft.daosimple.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RuoliDAOS extends DaoImpl<Ruolo,Integer> {
    @Override
    public String getSelectByIdQuery(Integer id) {
        return "SELECT * FROM " + this.getTableName() + " x WHERE x.id_ruolo = " + id;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM " + this.getTableName();
    }

    @Override
    public String getInsertQuery(Ruolo ruolo) {
        return "INSERT INTO " + this.getTableName() + " x (ruolo,descrizione) "
                + "VALUES ('" + ruolo.getNomeRuolo() + "','" + ruolo.getDescrizioneRuolo() + "');";
    }

    @Override
    public String getDeleteQuery(Integer id) {
        return "DELETE FROM " + this.getTableName() + " x WHERE x.id_ruolo = " + id + ";";
    }

    @Override
    public String getUpdateQuery(Integer id, Ruolo ruolo) {
        return "UPDATE " + this.getTableName()
                + " x SET x.ruolo = '" + ruolo.getNomeRuolo() + "' , "
                + " x.descrizione = '" + ruolo.getDescrizioneRuolo() + "'"
                + " WHERE x.id_ruolo = " + id;
    }

    @Override
    public String getReplaceQuery(Integer id, Ruolo ruolo) {
        String nomeRuolo = ruolo.getNomeRuolo();
        String descrizione = ruolo.getDescrizioneRuolo();
        StringBuilder queryBuilder = new StringBuilder("UPDATE " + this.getTableName() + " x SET ");
        if(nomeRuolo!=null){queryBuilder.append(" x.ruolo = '" + nomeRuolo + "',");}
        if(descrizione!=null){queryBuilder.append(" x.descrizione = '" + descrizione + "'");}

        //cancella ultima virgola "," se esiste
        int lastIndex = queryBuilder.length() - 1; //lunghezza di ","
        if(lastIndex > 0 && queryBuilder.substring(lastIndex).equals(",")){
            queryBuilder.delete(lastIndex,lastIndex+1);
        }

        queryBuilder.append(" WHERE x.id_ruolo = " + id);
        return queryBuilder.toString();
    }

    @Override
    public String getSearchByStringQuery(String searchText) {
        StringBuilder qb = new StringBuilder("SELECT * FROM " + this.getTableName() + " x WHERE x.ruolo LIKE '%" + searchText + "%' ");
        qb.append(" OR x.descrizione LIKE '%" + searchText + "%';");
        return qb.toString();
    }

    @Override
    public String getSearchByObjectQuery(Ruolo searchObj) {
        String nomeRuolo = searchObj.getNomeRuolo();
        String descrizioneRuolo = searchObj.getDescrizioneRuolo();
        //Eccezione : oggetto passato non valido perché è tutto vuoto
        if(nomeRuolo==null && descrizioneRuolo==null){ return "SELECT * FROM " + this.getTableName() + " x WHERE x.id_ruolo = 0";}

        StringBuilder qb = new StringBuilder("SELECT * FROM " + this.getTableName() + " x WHERE" );
        if(nomeRuolo!=null){qb.append(" x.ruolo LIKE '%" + nomeRuolo + "%' AND");}
        if(descrizioneRuolo!=null){qb.append(" x.descrizione LIKE '%" + descrizioneRuolo + "%' AND");}

        // rimuovi l'ultimo "AND" se esiste
        int lastIndex = qb.length() - 4; // lunghezza di " AND"
        if (lastIndex > 0 && qb.substring(lastIndex).equals(" AND")) {
            qb.delete(lastIndex, lastIndex + 4);
        }
        return qb.toString();
    }
    @Override
    public Ruolo convertToDto(ResultSet resultSet) throws DaoException {
        Ruolo r = null;
        try{
            r = new Ruolo(
                    resultSet.getInt("id_ruolo"),
                    resultSet.getString("ruolo"),
                    resultSet.getString("descrizione")
            );
            return r;
        }catch(Exception sqle){ sqle.printStackTrace(); throw new DaoException();}
    }

    @Override
    public boolean checkOggetto(Ruolo ruolo) throws DaoException {
        return true;
    }

    @Override
    public Integer getGeneratedKey(Statement statement) throws DaoException {
        try{
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e) { e.printStackTrace(); throw new DaoException(); }
    }
}

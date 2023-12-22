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
        return "INSERT INTO " + this.getTableName() + " x (ruolo,deacrizione) "
                + "VALUES (" + ruolo.getNomeRuolo() + "," + ruolo.getDescrizioneRuolo() + ");";
    }

    @Override
    public String getDeleteQuery(Integer id) {
        return "DELETE FROM " + this.getTableName() + " x WHERE x.id_ruolo = " + id + ";";
    }

    @Override
    public String getUpdateQuery(Integer id, Ruolo ruolo) {
        return "UPDATE " + this.getTableName()
                + " x SET x.ruolo = " + ruolo.getNomeRuolo()
                + " x.descrizione = " + ruolo.getDescrizioneRuolo()
                + " WHERE x.id_ruolo = " + id;
    }

    @Override
    public String getReplaceQuery(Integer id, Ruolo ruolo) {
        String nomeRuolo = ruolo.getNomeRuolo();
        String descrizione = ruolo.getDescrizioneRuolo();
        StringBuilder queryBuilder = new StringBuilder("UPDATE " + this.getTableName() + " x SET ");
        if(nomeRuolo!=null){queryBuilder.append(" x.ruolo = " + nomeRuolo + ",");}
        if(descrizione!=null){queryBuilder.append(" x.descrizione = " + descrizione);}
        queryBuilder.append(" WHERE x.id_utente = " + id);
        return queryBuilder.toString();
    }

    @Override
    public String getSearchByStringQuery(String s) {
        return null;
    }

    @Override
    public String getSearchByObjectQuery(Ruolo ruolo) {
        return null;
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

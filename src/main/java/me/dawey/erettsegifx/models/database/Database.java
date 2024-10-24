package me.dawey.erettsegifx.models.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.dawey.erettsegifx.models.database.tables.Vizsga;
import me.dawey.erettsegifx.models.database.tables.Vizsgatargy;
import me.dawey.erettsegifx.models.database.tables.Vizsgazo;

import java.sql.SQLException;
import java.util.List;

public class Database {
    private static final String connString = "jdbc:sqlite:adatok.db";
    private Dao<Vizsgazo, Integer> vizsgazoDao;
    private Dao<Vizsgatargy, Integer> vizsgatargyDao;
    private Dao<Vizsga, Integer> vizsgaDao;

    /*
    Adatbazis kapcsolat inicializalasa
     */
    public Database() {
        try {
            ConnectionSource source = getConnectionSource();
            vizsgazoDao = DaoManager.createDao(source, Vizsgazo.class);
            vizsgatargyDao = DaoManager.createDao(source, Vizsgatargy.class);
            vizsgaDao = DaoManager.createDao(source, Vizsga.class);
            createTables(source);
            source.close();
        } catch (SQLException ex) {
            System.out.println("Hiba az adatbazis inicializalasakor! \nUzenet:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Hiba az adatbazis kapcsolat bezarasakor! \nUzenet:" + ex.getMessage());
        }

    }

    /*
    Tablak letrehozasa, ha nem leteznek
     */
    public void createTables(ConnectionSource source) throws SQLException {
        TableUtils.createTableIfNotExists(source, Vizsgazo.class);
        TableUtils.createTableIfNotExists(source, Vizsgatargy.class);
        TableUtils.createTableIfNotExists(source, Vizsga.class);
    }

    /*
    Adatbazis kapcsolat lekerese
     */
    public ConnectionSource getConnectionSource() throws SQLException {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.sqlite.JDBC");
        } catch (Exception ex) {
            System.out.println("Nem található JDBC driver osztály!");
        }
        //return new JdbcConnectionSource("jdbc:mysql://url:3306/table", "user", "pass");
        return new JdbcConnectionSource(connString);
    }

    public List<Vizsgazo> getAllVizsgazok() {
        try {
            return vizsgazoDao.queryForAll();
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok lekeresekor! \nUzenet:" + ex.getMessage());
            return null;
        }
    }

    public List<Vizsgatargy> getAllVizsgatargyak() {
        try {
            return vizsgatargyDao.queryForAll();
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok lekeresekor! \nUzenet:" + ex.getMessage());
            return null;
        }
    }

    public List<Vizsga> getAllVizsgak() {
        try {
            return vizsgaDao.queryForAll();
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok lekeresekor! \nUzenet:" + ex.getMessage());
            return null;
        }
    }

    public void addVizsgazo(Vizsgazo vizsgazo) {
        try {
            vizsgazoDao.create(vizsgazo);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok hozzaadasakor! \nUzenet:" + ex.getMessage());
        }
    }

    public void addVizsgatargy(Vizsgatargy vizsgatargy) {
        try {
            vizsgatargyDao.create(vizsgatargy);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok hozzaadasakor! \nUzenet:" + ex.getMessage());
        }
    }

    public void addVizsga(Vizsga vizsga) {
        try {
            vizsgaDao.create(vizsga);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok hozzaadasakor! \nUzenet:" + ex.getMessage());
        }
    }

    public void updateVizsgazo(Vizsgazo vizsgazo) {
        try {
            vizsgazoDao.update(vizsgazo);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok frissitesekor! \nUzenet:" + ex.getMessage());
        }
    }

    public void updateVizsgatargy(Vizsgatargy vizsgatargy) {
        try {
            vizsgatargyDao.update(vizsgatargy);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok frissitesekor! \nUzenet:" + ex.getMessage());
        }
    }

    public void updateVizsga(Vizsga vizsga) {
        try {
            vizsgaDao.update(vizsga);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok frissitesekor! \nUzenet:" + ex.getMessage());
        }
    }

    public void deleteVizsgazo(Vizsgazo vizsgazo) {
        try {
            vizsgazoDao.delete(vizsgazo);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok torlesekor! \nUzenet:" + ex.getMessage());
        }
    }

    public void deleteVizsgatargy(Vizsgatargy vizsgatargy) {
        try {
            vizsgatargyDao.delete(vizsgatargy);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok torlesekor! \nUzenet:" + ex.getMessage());
        }
    }

    public void deleteVizsga(Vizsga vizsga) {
        try {
            vizsgaDao.delete(vizsga);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatok torlesekor! \nUzenet:" + ex.getMessage());
        }
    }

    public void closeConnection() {
        try {
            vizsgazoDao.getConnectionSource().close();
            vizsgatargyDao.getConnectionSource().close();
            vizsgaDao.getConnectionSource().close();
        } catch (Exception ex) {
            System.out.println("Hiba az adatbazis kapcsolat bezarasakor! \nUzenet:" + ex.getMessage());
        }
    }






}

package me.dawey.erettsegifx.models.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "vizsga")
public class Vizsga {

    /*
    Idegen kulcs a vizsgazora (Táblanév: vizsgazoaz)
     */
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "vizsgazoaz")
    private Vizsgazo vizsgazo;

    /*
    Idegen kulcs a vizsgatargyra (Táblanév: vizsgatargyaz)
     */
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "vizsgatargyaz")
    private Vizsgatargy vizsgatargy;

    @DatabaseField(canBeNull = false)
    private int szobeli;

    @DatabaseField(canBeNull = false)
    private int irasbeli;

    // TODO: Delete if can be got from ReadController
    @DatabaseField(canBeNull = false)
    private String vizsgazoNev;

    // TODO: Delete if can be got from ReadController
    @DatabaseField(canBeNull = false)
    private String vizsgatargyNev;
    public Vizsga() {}

    public Vizsga(Vizsgazo vizsgazo, Vizsgatargy vizsgatargy, int szobeli, int irasbeli) {
        this.vizsgazo = vizsgazo;
        this.vizsgatargy = vizsgatargy;
        this.szobeli = szobeli;
        this.irasbeli = irasbeli;

        this.vizsgazoNev = vizsgazo.getNev();
        this.vizsgatargyNev = vizsgatargy.getNev();
    }
    public String getVizsgazoNev() {
        return vizsgazoNev;
    }
    public String getVizsgatargyNev() {
        return vizsgatargyNev;
    }

    public Vizsgazo getVizsgazo() {
        return vizsgazo;
    }

    public void setVizsgazo(Vizsgazo vizsgazo) {
        this.vizsgazo = vizsgazo;
    }

    public Vizsgatargy getVizsgatargy() {
        return vizsgatargy;
    }

    public void setVizsgatargy(Vizsgatargy vizsgatargy) {
        this.vizsgatargy = vizsgatargy;
    }

    public int getSzobeli() {
        return szobeli;
    }

    public void setSzobeli(int szobeli) {
        this.szobeli = szobeli;
    }

    public int getIrasbeli() {
        return irasbeli;
    }

    public void setIrasbeli(int irasbeli) {
        this.irasbeli = irasbeli;
    }
}

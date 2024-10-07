package me.dawey.erettsegifx.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "vizsgatargy")
public class Vizsgatargy {

    @DatabaseField(generatedId = true)
    private int azon;

    @DatabaseField(canBeNull = false)
    private String nev;

    @DatabaseField(canBeNull = false)
    private int szomax;

    @DatabaseField(canBeNull = false)
    private int irmax;

    public Vizsgatargy() {}

    public Vizsgatargy(String nev, int szomax, int irmax) {
        this.nev = nev;
        this.szomax = szomax;
        this.irmax = irmax;
    }

    public int getAzon() {
        return azon;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getSzomax() {
        return szomax;
    }

    public void setSzomax(int szomax) {
        this.szomax = szomax;
    }

    public int getIrmax() {
        return irmax;
    }

    public void setIrmax(int irmax) {
        this.irmax = irmax;
    }

}

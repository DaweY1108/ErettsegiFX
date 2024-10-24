package me.dawey.erettsegifx.models.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.value.ObservableValue;

@DatabaseTable(tableName = "vizsgazo")
public class Vizsgazo {
    @DatabaseField(generatedId = true)
    private int azon;

    @DatabaseField(canBeNull = false)
    private String nev;

    @DatabaseField(canBeNull = false)
    private String osztaly;

    public Vizsgazo() {}

    public Vizsgazo(String nev, String osztaly) {
        this.nev = nev;
        this.osztaly = osztaly;
    }

    public String getOsztaly() {
        return osztaly;
    }

    public void setOsztaly(String osztaly) {
        this.osztaly = osztaly;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getAzon() {
        return azon;
    }


    public ObservableValue<Vizsgazo> nevProperty() {
        return null;
    }
    @Override
    public String toString() {
        return nev;
    }
}

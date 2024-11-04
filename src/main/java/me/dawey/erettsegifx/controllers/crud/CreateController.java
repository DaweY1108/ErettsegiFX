package me.dawey.erettsegifx.controllers.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import me.dawey.erettsegifx.models.database.tables.Vizsga;
import me.dawey.erettsegifx.models.database.tables.Vizsgatargy;
import me.dawey.erettsegifx.models.database.tables.Vizsgazo;

import static me.dawey.erettsegifx.Main.database;

public class CreateController {

    public TextField vizsgazoNameField;
    public TextField vizsgatargyNameField;
    public TextField vizsgazoClassNameField;
    public TextField szomaxField;
    public TextField irmaxField;
    public TextField vizsgaVizsgazoAzonositoField;
    public TextField vizsgaVizsgatargyAzonositoField;
    public TextField szobeliEredmenyField;
    public TextField irasbeliEredmenyField;
    @FXML
    public void initialize() {
    }

    public void addVizsga(ActionEvent actionEvent) {
        Vizsgazo vizsgazo = new Vizsgazo();
        Vizsgatargy vizsgatargy = new Vizsgatargy();

        // get vizsgazo by azonosito
        try
        {
            vizsgazo = database.getVizsgazo(Integer.parseInt(vizsgaVizsgazoAzonositoField.getText()));
        }
        catch (Exception e)
        {
            System.out.println("Vizsgazo nem talalhato");
        }

        // get vizsgatargy by azonosito
        try
        {
            vizsgatargy = database.getVizsgatargy(Integer.parseInt(vizsgaVizsgatargyAzonositoField.getText()));
        }
        catch (Exception e)
        {
            System.out.println("Vizsgatargy nem talalhato");
        }

        Vizsga vizsga = new Vizsga(vizsgazo, vizsgatargy, Integer.parseInt(szobeliEredmenyField.getText()), Integer.parseInt(irasbeliEredmenyField.getText()));

        database.addVizsga(vizsga);
        vizsgaVizsgazoAzonositoField.setText("");
        vizsgaVizsgatargyAzonositoField.setText("");
        szobeliEredmenyField.setText("");
        irasbeliEredmenyField.setText("");
    }

    public void addVizsgazo(ActionEvent actionEvent) {
        Vizsgazo vizsgazo = new Vizsgazo(vizsgazoNameField.getText(), vizsgazoClassNameField.getText());
        database.addVizsgazo(vizsgazo);
        vizsgazoNameField.setText("");
        vizsgazoClassNameField.setText("");
    }

    public void addVizsgatargy(ActionEvent actionEvent) {
        Vizsgatargy vizsgatargy = new Vizsgatargy(vizsgatargyNameField.getText(), Integer.parseInt(szomaxField.getText()), Integer.parseInt(irmaxField.getText()));
        database.addVizsgatargy(vizsgatargy);
        vizsgatargyNameField.setText("");
        szomaxField.setText("");
        irmaxField.setText("");
    }
}

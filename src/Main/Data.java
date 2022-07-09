/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.beans.property.SimpleStringProperty;

public class Data {

    private final SimpleStringProperty mapel;
    private final SimpleStringProperty waktu;
    private final SimpleStringProperty gdung;
    private final SimpleStringProperty ruang;
    private final SimpleStringProperty dosen;

    public Data(String mapel, String waktu, String gdung, String ruang, String dosen) {
        this.mapel = new SimpleStringProperty(mapel);
        this.waktu = new SimpleStringProperty(waktu);
        this.gdung = new SimpleStringProperty(gdung);
        this.ruang = new SimpleStringProperty(ruang);
        this.dosen = new SimpleStringProperty(dosen);
    }

    public String getMapel() {
        return mapel.get();
    }

    public void setMapel(String mapel) {
        this.mapel.set(mapel);
    }

    public String getWaktu() {
        return waktu.get();
    }

    public void setWaktu(String waktu) {
        this.waktu.set(waktu);
    }

    public String getGdung() {
        return gdung.get();
    }

    public void setGdung(String gdung) {
        this.gdung.set(gdung);
    }

    public String getRuang() {
        return ruang.get();
    }

    public void setRuang(String ruang) {
        this.ruang.set(ruang);
    }

    public String getDosen() {
        return dosen.get();
    }

    public void setDosen(String dosen) {
        this.dosen.set(dosen);
    }

}

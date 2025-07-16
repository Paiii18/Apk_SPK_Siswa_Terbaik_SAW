/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

/**
 *
 * @author USER
 */
public class itemSiswa {
    private int idSiswa;
    private String namaSiswa;

    public itemSiswa(int idSiswa, String namaSiswa) {
        this.idSiswa = idSiswa;
        this.namaSiswa = namaSiswa;
    }

    public int getIdSiswa() {
        return idSiswa;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    @Override
    public String toString() {
        return idSiswa + ". " + namaSiswa; // untuk tampilan di ComboBox
    }
}

/**
 * Created by Kuba on 2016-03-17.
 */
public class Laborka {
    private int liczbaStudentowNieWieoJavie;

    public Laborka(int liczbaStudentow) {
        liczbaStudentowNieWieoJavie = liczbaStudentow;
    }

    public void wypiszStan() {
        String stan = null;
        if (liczbaStudentowNieWieoJavie == 0) {
            stan = "Kazde wie co to Java";
        } else {
            stan = "Jeszcze " + liczbaStudentowNieWieoJavie + " studentow nie wie o Javie";
        }
        System.out.println(stan);
    }

    public void koniec1Roku() {
        liczbaStudentowNieWieoJavie = liczbaStudentowNieWieoJavie / 2;
    }

    public void koniecStudiow() {
        liczbaStudentowNieWieoJavie = 0;
    }

    public static void main(String[] args) {
        Laborka pewnaGrupa = new Laborka(34);
        pewnaGrupa.wypiszStan();
        pewnaGrupa.koniec1Roku();
        pewnaGrupa.wypiszStan();
        pewnaGrupa.koniecStudiow();
        pewnaGrupa.wypiszStan();
    }
}
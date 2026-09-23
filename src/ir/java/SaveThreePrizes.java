package ir.java;
import java.io.IOException;
import java.io.RandomAccessFile;

//این کلاس برای نوشتن جوایز در فایلشان و جلوگیری از تکرار این کار با هر بار شرکت در قرعه کشی است
public class SaveThreePrizes {

    private static final String PrizeFileName ="prizeFile.dat";//فایل جوایز

    public static void main(String[] args){
        try {
            RandomAccessFile rfile=new RandomAccessFile(PrizeFileName,"rw");
            writePrizesInFile(rfile);//نوشتن در فایل جوایز
            rfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Three prizes are successfully written in our file");
    }
    //نوشتن جوایز مسابقه در فایل
    public static void writePrizesInFile(RandomAccessFile rfile) {
        try {
            rfile.writeChars("50 million cash prizes");
            rfile.writeChars("10 million cash prizes");
            rfile.writeChars("One year unlimited internet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

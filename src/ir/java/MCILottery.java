package ir.java;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
//کلاس قرعه کشی جوایز
public class MCILottery {

    private String prize;//جایزه کاربر
    String[] prizeList = new String[100];//آرایه ای به طول 100 که سه عنصر اول آن جوایز و بقیه عناصر خالی هستند

    //خواندن به صورت استاندارد از روی فایل
    public String readFix(RandomAccessFile rfile,int SIZE) throws IOException {
        StringBuilder str= new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            str.append(rfile.readChar());
        }
        str = new StringBuilder(str.toString().trim());
        if (str.toString().equals("")) return null;
        return str.toString();
    }

    //خواندن فایل جوایز
    public void readFromPrizeFile(RandomAccessFile rfile){
        try {
            prizeList[0]=readFix(rfile,22);
            prizeList[1]=readFix(rfile,22);
            prizeList[2]=readFix(rfile,27);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //انجام دادن قرعه کشی
    public String lottery() {
        try {
            RandomAccessFile prizeFile = new RandomAccessFile("prizeFile.dat","rw");//ایجاد فایل جوایز
            readFromPrizeFile(prizeFile);//خواندن از روی فایل جوایز
            Random rand = new Random();
            int randomIndex = rand.nextInt(100);//قرعه کشی بین صد عنصر آرایه prizeList
            prize = prizeList[randomIndex];//مشخص شدن جایزه کاربر
            prizeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prize;
    }
}

package ir.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//این کلاس برای ثبت نام اولیه ده کاربر و جلوگیری از تکرار این ثبت نام با هربار ران شدن برنامه هست
public class SaveTenFirstUsers {
    private static final String UsersFileName ="usersFile.dat";//فایل کاربران
    public static void main(String[] args){

        MCIUser[] users = new MCIUser[10];
        //ثبت نام ده کاربر
        users[0] = new MCIUser("09136717485"  ,"Fateme" );
        users[1] = new MCIUser("09132592484"     , "Reyhane");
        users[2] = new MCIUser("09133458765"   , "Sara");
        users[3] = new MCIUser("09124568976"  , "Ali");
        users[4] = new MCIUser("09135679834" , "Zahra");
        users[5] = new MCIUser("09391185373", "Reza");
        users[6] = new MCIUser("09396794399"  , "Hadi");
        users[7] = new MCIUser("09133592699"  , "Negin");
        users[8] = new MCIUser("09139290409"  , "Sina");
        users[9] = new MCIUser("09137125934"  , "Maryam");

        try {
            File file = new File(UsersFileName);
            file.delete();//بعد از هربار اجرای این برنامه کاربران به غیر از این ده نفر پاک شوند تا شلوغ نشود
            RandomAccessFile rFile = new RandomAccessFile(UsersFileName, "rw");
            for (int i = 0; i < 10; i++) {
                users[i].writeInFile(rFile);//بروزرسانی کاربران
            }
            rFile.close();
            System.out.println("Ten Users are successfully written in usersFile file");

        } catch (FileNotFoundException e) {
            System.err.println("usersFile file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

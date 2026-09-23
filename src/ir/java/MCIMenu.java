package ir.java;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

//منوی اولیه ورود کاربر به برنامه
public class MCIMenu {
    public  void input(){
        try {
            //ایجاد فایل شامل اطلاعات همه یوزرها
            RandomAccessFile rfile=new RandomAccessFile("usersFile.dat","rw");
            Users users=new Users(rfile);
            //قراردادن تک تک یوزرها در کلاس همه آنها (users)
            users.createUsers();

            //چاپ منوی اولیه، گرفتن ورودی مورد نظر کاربر و انتقال او به قسمت دلخواهش
            while (true) {
                System.out.println("""
                        welcome to Hamrahe Avval(mci).
                         enter code *10*0# to register.
                         enter code *10*1# to login.""");
                System.out.print(">>");

                Scanner input = new Scanner(System.in);
                String code = input.nextLine();//دریافت کد ورودی کاربر

                switch (code) {
                    case "*10*0#" -> users.register();//ثبت نام کاربر
                    case "*10*1#" -> users.loginPanel();//ورود کاربر به پنل کاربری اش
                    case "*0#" -> { //بازگشت
                        rfile.close();//وقتی کاربر برنامه را میبندد فایل هم بسته میشود
                        return;
                    }
                    default -> System.out.println("sorry! Your input is not defined...\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

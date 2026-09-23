package ir.java;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

//کلاسی از همه یوزها(همه یوزرها کنارهم در یک آرایه که ویژگی این کلاس است قرار میگیرند و این کلاس را تشکیل میدهند)
public class Users {

    int userCount;//تعداد کاربران موجود در سیستم
    MCIUser[] Users;//آرایه ای از کاربران
    RandomAccessFile rfile;//یک فایل تصادفی برای قرار دادن کاربرها در آن

    //کانستراکتور برای مقدار دهی اولیه ویژگی های کلاس
    public Users(RandomAccessFile rfile) {
        Users=new MCIUser[1000];
        userCount=0;
        this.rfile=rfile;
    }

    //قراردادن یوزرهای نوشته شده در فایل اطلاعات یوزرها در آرایه ای از یوزرها(users)
    public void createUsers() {
        try {
            long len=rfile.length()/120;//طول کل فایل را بر طول هر رکورد تقسیم میکنیم تا تعداد کاربر های موجود تعیین شوند
            for (userCount = 0; userCount < len; userCount++) {
                MCIUser user=new MCIUser();
                user.readFromFile(rfile);
                Users[userCount]=user;//مقداردهی آرایه ای از یوزرها
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //تابع ثبت نام کاربر
    public  void register() throws IOException {
        while (true) {
            MCIUser signup = new MCIUser();

            System.out.print("Register Panel: \n pleas enter your Phone Number:");
            Scanner input = new Scanner(System.in);
            signup.setPhoneNumber(input.nextLine());//گرفتن شماره همراه کاربری که قصد ثبت نام دارد
            if (signup.getPhoneNumber().equals("*0#")){return;}//برای کنسلی و برگشت از این مرحله

            //چک کردن شماره تلفن کاربر و اینکه او قبلا ثبت نام نکرده باشد
            if (signup.check_PhoneNumber(signup.getPhoneNumber()) == 1 && findUser(signup.getPhoneNumber())==-1) {

                System.out.print("pleas enter your User Name:");
                signup.setUserName(input.nextLine());//گرفتن نام کاربری یوزری که قصد ثبت نام دارد
                if (signup.getUserName().equals("*0#")){return;}// برای برگشت از این قسمت

                Users[userCount] = signup;//اضافه کردن کاربر به لیست کاربران
                rfile.seek(120L *userCount);//بردن پوینتر به رکورد کاربر جدید
                signup.writeInFile(rfile);//نوشتن اطلاعات کاربر جدید در فایل کاربران
                userCount++;//افزودن تعداد کاربران

                if (signup.user_panel() == 200) {  //ورود به پنل کاربر و همچنین بازگشت از آن
                    rfile.seek(120L *(userCount-1));
                    Users[userCount-1].writeInFile(rfile);
                    return;
                }

            } else if(signup.check_PhoneNumber(signup.getPhoneNumber()) != 1){  //اشتباه بودن شماره همراه
                System.out.println("your phone number is incorrect ...\n");
            }
            else { //اگر کاربر قبلا ثبت نام کرده باشد
                System.out.println("You are already registered...\n");
            }
        }
    }

    //این تابع برای یافتن یوزری که از قبل ثبت نام کرده و اکنون قصد ورود را دارد ایجاد شده است
    public  int findUser(String PhoneNumber)
    {
        for (int i=0;i<userCount;i++)
        {
            if(PhoneNumber.compareTo(Users[i].getPhoneNumber())==0)
                return i;//اندیس خانه ای که شماره تلفن یکسانی با شماره تلفن وارد شده دارد برمیگرداند
        }
        return -1;//درصورت وجود نداشتن-1 برمیگرداند
    }

    // تابعی برای ورود کاربری که از قبل ثبت نام شده به پنل کاربری اش
    public  void loginPanel() throws IOException {
        while (true) {

            System.out.print("please Enter your PhoneNumber:");
            Scanner input = new Scanner(System.in);
            String inputPhoneNumber = input.nextLine();//گرفتن شماره همراه کاربری که قصد ورود دارد

            if (inputPhoneNumber.equals("*0#")){return;}//برای کنسلی و برگشت

            int k = findUser(inputPhoneNumber);
            if (k < 0) { //اگر کاربر از قبل ثبت نام نکرده باشد
                System.out.println("your PhoneNumber is not found\n");
                return;
            }

            //درصورت وجود داشتن کاربر در لیست کاربران(کاربر قبلا ثبت نام شده باشد)
            if ((inputPhoneNumber.compareTo(Users[k].getPhoneNumber())) == 0) {
                Users[k].printUserInfo();//نمایش مشخصات کاربر
                if (Users[k].user_panel() == 200) { // ورود کاربر به پنل کاربری یا برگشت از آن
                    rfile.seek(120L *k);//بردن پوینتر به رکورد کاربر جدید
                    Users[k].writeInFile(rfile);//بروزرسانی اطلاعات کاربر در فایل کاربران
                    return;
                }
            }
        }
    }
}

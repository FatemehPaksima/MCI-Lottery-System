package ir.java;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
//کلاس هر یوزر
public class MCIUser {

    private String PhoneNumber;//تلفن همراه
    private String UserName;//نام کاربری
    private String PrizeOfUser;//جایزه کاربر
    private int PrizeCountOfUser;//تعداد دفعات شرکت کاربر در قرعه کشی

    //کانستراکتور با تلفن همراه و نام کاربری
    public MCIUser(String phoneNumber, String userName) {
        PhoneNumber = phoneNumber;
        UserName = userName;
    }

    //کانستراکتور بدون ورودی
    public MCIUser() {}

    //دریافت تلفن همراه
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    //دریافت نام کاربری
    public String getUserName() {
        return UserName;
    }
    //مقداردهی تلفن همراه
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    //مقداردهی نام کاربری
    public void setUserName(String userName) {
        UserName = userName;
    }
    //دریافت جایزه های کاربر
    public String getPrizeOfUser() { return PrizeOfUser; }
    //دریافت مقدار عددی تعداد دفعات شرکت در قرعه کشی
    public int getPrizeCountOfUser() { return PrizeCountOfUser; }

    //پنل کاربری یوزر
    public  int user_panel(){
        while (true){
            System.out.println("""
                    Pleas Enter code to continue:
                     enter code *10*355# to Participate in mci(lot).
                     enter code *10*350# to show result.
                     enter code *10*200# to log out.""");
            System.out.print(">>");

            Scanner input = new Scanner(System.in);
            String in=input.nextLine();//گرفتن دستور از کاربر

            switch (in){
                case "*10*355#" :
                    if(validPhoneNumber()){  //چک کردن تلفن همراه با شماره های دائمی همراه اول
                        participateInLottery();//شرکت دادن کاربر در قرعه کشی
                        break;
                    }
                    System.out.println("""
                            only holders of a permanent Hamrahe Avval SIM can participate in lottery
                            Pleas Enter a Hamrahe Avval phoneNumber...
                            """);
                    return 100;
                case "*10*350#" :
                    showResult();//نمایش نتایج قرعه کشی
                    break;
                case "*0#":
                case "*10*200#":
                    return 200;
                default:
                    System.out.println("sorry! Your input is not defined...\n");
            }
        }
    }

    //اين تابع چك ميكند كه همه ی كاراكتر های ورودي عددي هست يا خير
    public  boolean beingNum(String str, int n)
    {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < n; i++)
        {
            if (charArray[i] < '0' || charArray[i] > '9')
                return false;
        }
        return true;
    }

    //این تابع شماره تلفن همراه را چک می کند که فرم مورد نظر مارا داشته باشد(شروع شماره با 09 و 11 رقمی بودن آن)
    public  int check_PhoneNumber(String PhoneNumber){
        if (PhoneNumber.length()==11 && beingNum(PhoneNumber, 11) && PhoneNumber.startsWith("09")){
            return 1;
        }
        return 0;
    }

    //چک کردن شماره تلفن همراه کاربر با پیش شماره های دائمی همراه اول
    public  boolean validPhoneNumber(){
        String preCode = getPhoneNumber().substring(0, 4);
        String[] validCode = {"0993" ,"0992" ,"0991" ,"0919" ,"0918" ,"0917" ,"0916" ,"0915" ,"0914" ,"0913" ,"0912" ,"0911" ,"0910"};
        for (String s : validCode) {
            if (preCode.equals(s)) {
                return true;
            }
        }
        return false;
    }

    //فیکس کردن طول یک رشته برای ذخیره در فایل
    public String setLength(String str,int SIZE){      //set length of str to len
        if(str == null) return "                           ";
        if (str.length()>SIZE){
            return str.substring(0,SIZE);
        }else {
            int numberOfSpace=SIZE-str.length();
            str = str + " ".repeat(numberOfSpace);
        }
        return str;
    }

    //نوشتن اطلاعات هر کاربر در فایل
    public void writeInFile(RandomAccessFile rfile) {
        try {
            rfile.writeChars(setLength(PhoneNumber, 11));
            rfile.writeChars(setLength(UserName, 20));
            rfile.writeChars(setLength(PrizeOfUser, 27));
            rfile.writeInt(PrizeCountOfUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    //خواندن فایل اطلاعات کاربر
    public void readFromFile(RandomAccessFile rfile){
        try {
            PhoneNumber=readFix(rfile,11);//22
            UserName=readFix(rfile,20);//40
            PrizeOfUser=readFix(rfile,27);//54
            PrizeCountOfUser=rfile.readInt();//4
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //چاپ اطلاعات کاربر
    public  void printUserInfo(){
        System.out.println("\nuser name : "+getUserName()+
                "\nphone number : "+getPhoneNumber()+
                "\nprize:"+getPrizeOfUser()+
                "\nNumber of times to participate in the lottery:"+getPrizeCountOfUser()+
                "\n");
    }

    //شرکت دادن کاربر در قرعه کشی همراه اول
    public void participateInLottery(){
        MCILottery mciLottery=new MCILottery();
        //شرط شرکت در قرعه کشی
        if ( PrizeCountOfUser < 2 &&  PrizeOfUser == null ){
            PrizeOfUser=mciLottery.lottery();
            PrizeCountOfUser++;//اضافه کردن تعداد دفعات شرکت کاربر در قرعه کشی
            System.out.println("you participated in mci lottery!!!\n");
        }
        else{
            System.out.println("sorry ; You can't participate in the lottery again!\n");
        }
    }

    //نمایش نتایج قرعه کشی
    public void showResult(){
        if( PrizeOfUser == null) {
            System.out.println("oh! you didn't win any thing.");
            //درصورتی که کاربر یک بار شرکت کرده اما جایزه نبرده
            if( PrizeCountOfUser < 2) System.out.println("but you can participate in lottery again!\n ");
        }
        else {
            System.out.println("congratulations!!! \nyou win "+ PrizeOfUser+"\n");//در صورت برنده شدن او
        }
    }
}



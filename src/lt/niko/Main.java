package lt.niko;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);


        String providedGender = null;
        String gender = null;

        do {
            System.out.println("Iveskite V (vyr.) arba M (mot.)");
            providedGender = scanner.next();
        } while (!providedGender.equals("v") && !providedGender.equalsIgnoreCase("m"));

        if (providedGender.equalsIgnoreCase("V")) {
            gender = "3";
        } else if (providedGender.equalsIgnoreCase("M")) {
            gender = "4";
        }

        int year;
        do {
            System.out.println("Iveskite metus");
            year = scanner.nextInt();
        } while (year < 1900 || year > 2021);

        int month;
        do {
            System.out.println("Iveskite menesi");
            month = scanner.nextInt();
        } while (month > 12);

        int daysInMonth = 0;

        switch (month) {
            case 1:
                daysInMonth = 31;
                break;
            case 2:
                daysInMonth = 28;
                break;
            case 3:
                daysInMonth = 31;
                break;
            case 4:
                daysInMonth = 30;
                break;
            case 5:
                daysInMonth = 31;
                break;
            case 6:
                daysInMonth = 30;
                break;
            case 7:
                daysInMonth = 31;
                break;
            case 8:
                daysInMonth = 31;
                break;
            case 9:
                daysInMonth = 30;
                break;
            case 10:
                daysInMonth = 31;
                break;
            case 11:
                daysInMonth = 30;
                break;
            case 12:
                daysInMonth = 31;
                break;
        }

        if (((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) && month == 2) {
            daysInMonth = 29;
        }
        ;

        int day;
        do {
            System.out.println("Iveskite diena");
            day = scanner.nextInt();
        } while (day > daysInMonth);

//        validating if date is not in the future
        Date date = new Date();

        Date dateToCheck = new SimpleDateFormat("yyyyMMdd").parse(String.format("%04d", year) + String.format("%02d", month) + String.format("%02d", day));
        if(!date.after(dateToCheck)){
            System.out.println("Ivesta data yra ateityje.");
        }

        int count = random.nextInt(999);
        System.out.println("random counter: " + count);

        String personalCode = gender + String.format("%04d", year).substring(2) + String.format("%02d", month) + String.format("%02d", day) + String.format("%02d", count);

        System.out.println("personal code without validation number at the end: " + personalCode);

        int s = 0;
        int t;

        for (int i = 0; i < 10; i++) {
            if (i == 9) {
                t = Integer.parseInt(personalCode.substring(i, i + 1)) * (i - 8);
            } else {
                t = Integer.parseInt(personalCode.substring(i, i + 1)) * (i + 1);
            }
            s = s + t;
        }
        int k;

        if (s % 11 == 10) {
            s = 0;
            for (int i = 0; i < 10; i++) {
                if (i > 6) {
                    t = Integer.parseInt(personalCode.substring(i, i + 1)) * (i - 6);
                } else {
                    t = Integer.parseInt(personalCode.substring(i, i + 1)) * (i + 3);
                }
                s = s + t;
            }
            if (s % 11 == 10) {
                k = 0;
            } else {
                k = s % 11;
            }
        } else {
            k = s % 11;
        }

        System.out.println("validation number k: " + k);

        personalCode = personalCode + Integer.valueOf(k);

        System.out.println("final personal code: " + personalCode);

        scanner.close();
    }


}

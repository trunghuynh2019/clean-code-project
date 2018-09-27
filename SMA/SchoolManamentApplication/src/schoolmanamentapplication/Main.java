/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanamentapplication;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean flag = true;
        SchoolManagement schoolManagement =  new SchoolManagement();
        int choice;
        do {            
            System.out.println("1. Thêm 1 trường");
            System.out.println("2. Xuất danh sách trường");
            System.out.println("Nhập sự lựa chọn của bạn: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    School school = new School();
                    System.out.println("Nhập tên trường: ");
                    sc = new Scanner(System.in);
                    school.name = sc.nextLine();
                    System.out.println("Nhập địa chỉ: ");
                    sc = new Scanner(System.in);
                    school.address = sc.nextLine();
                    System.out.println("Nhập số điện thoại: ");
                    sc = new Scanner(System.in);
                    school.telephoneNumber = sc.nextInt();
                    System.out.println("Nhập số lượng sinh viên: ");
                    sc = new Scanner(System.in);
                    school.numberOfStudent = sc.nextInt();
                    System.out.println("Nhập số lượng giáo viên công tác tại trường: ");
                    sc = new Scanner(System.in);
                    int numberOfTeacher = sc.nextInt();
                    for (int i = 0; i < numberOfTeacher; i++) {
                        System.out.println("Nhập tên giáo viên: ");
                        sc = new Scanner(System.in);
                        school.listTeacher.add(new Teacher(i+"", sc.nextLine(), school.name));
                    }
                    schoolManagement.addSchool(school);
                    break;
                case 2:
                    schoolManagement.displaySchool();
                    break;
                default:
                    
                    break;
            }
        } while (flag);
    }
    
}

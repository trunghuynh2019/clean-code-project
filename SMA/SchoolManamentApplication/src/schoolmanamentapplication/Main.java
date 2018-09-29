/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanamentapplication;

import java.util.Scanner;
import java.util.Vector;

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
        boolean flagForWrongInput = false;
        SchoolManagement schoolManagement = new SchoolManagement();
        int choice;
        int teacherId = 0;
        do {
            try {
                do {
                    System.out.println("\n-------*** School Management System ***-------");
                    System.out.println("1. Thêm 1 trường");
                    System.out.println("2. Xem danh sách trường");
                    System.out.println("3. Tìm kiếm trường theo tên");
                    System.out.println("4. Tìm kiếm giáo viên theo tên");
                    System.out.println("5. Tìm kiếm giáo viên theo id");
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
                                school.listTeacher.add(new Teacher(teacherId + "", sc.nextLine(), school.name));
                                teacherId++;
                            }
                            schoolManagement.addSchool(school);
                            System.out.println("Đã thêm vào");
                            break;
                        case 2:
                            if (schoolManagement.listSchool.size() == 0) {
                                System.out.println("Hiện không có trường nào trong hệ thống");
                                break;
                            }
                            schoolManagement.displaySchool();
                            break;
                        case 3:
                            if (schoolManagement.listSchool.size() == 0) {
                                System.out.println("Hiện không có trường nào trong hệ thống");
                                break;
                            }
                            String findingSchoolName;
                            System.out.println("Nhập tên trường cần tìm: ");
                            sc = new Scanner(System.in);
                            findingSchoolName = sc.nextLine();
                            if (schoolManagement.findSchoolByName(findingSchoolName) == null) {
                                System.out.println("Không có trường này trong hệ thống");
                            } else {
                                System.out.println(schoolManagement.findSchoolByName(findingSchoolName).toString());
                            }
                            break;
                        case 4:
                            if (schoolManagement.listSchool.size() == 0) {
                                System.out.println("Hiện không có trường nào trong hệ thống");
                                break;
                            }
                            String findingTeacherName;
                            System.out.println("Nhập tên giáo viên cần tìm: ");
                            sc = new Scanner(System.in);
                            findingTeacherName = sc.nextLine();
                            Vector<String> listTeacher = new Vector<>();
                            for (int i = 0; i < schoolManagement.listSchool.size(); i++) {
                                Teacher teacher = schoolManagement.listSchool.get(i).findTeacherByName(findingTeacherName);
                                if (teacher != null) {
                                    listTeacher.add(findingTeacherName + "-" + schoolManagement.listSchool.get(i).getName());
                                }
                            }
                            System.out.println(listTeacher.toString());
                            break;
                        case 5:
                            if (schoolManagement.listSchool.size() == 0) {
                                System.out.println("Hiện không có trường nào trong hệ thống");
                                break;
                            }
                            String findingTeacherId;
                            System.out.println("Nhập tên giáo viên cần tìm: ");
                            sc = new Scanner(System.in);
                            findingTeacherId = sc.nextLine();
                            Vector<String> listTeacherFindById = new Vector<>();
                            for (int i = 0; i < schoolManagement.listSchool.size(); i++) {
                                Teacher teacher = schoolManagement.listSchool.get(i).findTeacherById(findingTeacherId);
                                if (teacher != null) {
                                    listTeacherFindById.add(findingTeacherId + "-" + teacher.getName() + "-" + schoolManagement.listSchool.get(i).getName());
                                }
                            }
                            System.out.println(listTeacherFindById.toString());
                            break;
                        default:
                            break;
                    }
                } while (flag);
            } catch (Exception e) {
                System.out.println("Nhập sai");
                flagForWrongInput = true;
            }
        } while (flagForWrongInput);
    }
}

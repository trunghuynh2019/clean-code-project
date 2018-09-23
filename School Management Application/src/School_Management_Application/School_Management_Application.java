package School_Management_Application;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
// Nhap danh sach truong hoc vaf hien thi

public class School_Management_Application {
    private static ArrayList<School> schoolList = new ArrayList<School>();
    private static Scanner sc = new Scanner(System.in);

    public static void insertSchool() {
		int i = 1;
		String name;
		
		System.out.println("Please enter schools' name");
		System.out.println("(type enter after write the name to insert news, type 0 and enter to exit)");
                
                sc = new Scanner(System.in);
		while(true){ 
			System.out.print(i + ". ");
			name = sc.nextLine();
			if(name.equals("0")) {
				break;
			}
			else {
                            School sch = new School();
                            
                            sch.setName(name);
                            schoolList.add(sch);
                            i++;
			}
		}
	}
    
    public static void showSchool(){
        Iterator<School> itr = schoolList.iterator();
        int i = 1;
        
        while (itr.hasNext()) {
            System.out.println(i + ". " + itr.next().getName());
            i++;
        }

    }
    
    public static void main(String[] args) {
        int i;
        
        System.out.println("Welcome to Nhat Project");
        insertSchool();
        while(true){
            System.out.print("(0: Exit, 1: Insert, 2: Show): ");
            i = sc.nextInt();
            if(i == 0){
                break;
            }
            else{
                if(i == 1){
                    insertSchool();
                }
                else{
                    showSchool();
                }
            }
        }
    }
    
}

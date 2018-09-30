package appInterface;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import entity.school;
import entity.teacher;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ListOfTeachers {

	protected Shell shlListOfTeacher;
	private Text txtName;
	private Text txtAddress;
	private Text txtPhone;
	private Table table;
	private Combo combo;
	
	private String name, address, phone, nameSchool;
	private int id;

	/**
	 * create List of Schools
	 */
	
	public Set<teacher> listOfTeachers = new HashSet<teacher>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListOfTeachers window = new ListOfTeachers();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlListOfTeacher.open();
		shlListOfTeacher.layout();
		while (!shlListOfTeacher.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlListOfTeacher = new Shell();
		shlListOfTeacher.setSize(800, 600);
		shlListOfTeacher.setText("LIST OF TEACHER");
		
		Label label = new Label(shlListOfTeacher, SWT.NONE);
		label.setText("Teacher name : ");
		label.setBounds(60, 335, 103, 20);
		
		txtName = new Text(shlListOfTeacher, SWT.BORDER);
		txtName.setBounds(188, 329, 519, 26);
		
		Label label_1 = new Label(shlListOfTeacher, SWT.NONE);
		label_1.setText("Address :");
		label_1.setBounds(60, 385, 70, 20);
		
		txtAddress = new Text(shlListOfTeacher, SWT.BORDER);
		txtAddress.setBounds(188, 379, 519, 26);
		
		Label label_2 = new Label(shlListOfTeacher, SWT.NONE);
		label_2.setText("Phone :");
		label_2.setBounds(60, 432, 70, 20);
		
		txtPhone = new Text(shlListOfTeacher, SWT.BORDER);
		txtPhone.setBounds(188, 429, 519, 26);
		
		Button button = new Button(shlListOfTeacher, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				name = txtName.getText().toString();
				address = txtAddress.getText().toString();
				phone = txtPhone.getText().toString();
				
				//Get data combobox 
				int index = combo.getSelectionIndex();
				nameSchool = combo.getItem(index);
				teacher Teacher = new teacher();
				id = listOfTeachers.size() + 1 ;
				Teacher.setId(id);
				Teacher.setName(name);
				Teacher.setAddress(address);
				Teacher.setPhone(phone);
				Teacher.setNameSchool(nameSchool);
				
				listOfTeachers.add(Teacher);
				
				
				//System.out.println(listOfSchools.size());
				//System.out.println(listOfSchools);
				TableItem item = new TableItem(table, SWT.NONE);
				Iterator<teacher> it = listOfTeachers.iterator();
				teacher tmp = null;
				while(it.hasNext())
				{
					tmp = it.next();
				    item.setText(new String[] { String.valueOf(tmp.getId()), tmp.getName(), tmp.getAddress(), tmp.getPhone(), tmp.getNameSchool() });
					//System.out.println(it.next().getName() +"\t");
					
				}
			}
		});
		button.setText("Add");
		button.setBounds(188, 493, 90, 30);
		
		Button button_1 = new Button(shlListOfTeacher, SWT.NONE);
		button_1.setText("Edit");
		button_1.setBounds(373, 493, 90, 30);
		
		Button button_2 = new Button(shlListOfTeacher, SWT.NONE);
		button_2.setText("Delete");
		button_2.setBounds(544, 493, 90, 30);
		
		table = new Table(shlListOfTeacher, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {	
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tblItem = table.getItems()
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(60, 51, 651, 174);
		
		//Display table
				TableColumn column1 = new TableColumn(table, SWT.LEFT);
			    TableColumn column2 = new TableColumn(table, SWT.LEFT);
			    TableColumn column3 = new TableColumn(table, SWT.LEFT);
			    TableColumn column4 = new TableColumn(table, SWT.LEFT);
			    TableColumn column5 = new TableColumn(table, SWT.LEFT);
			    column1.setText("Id");
			    column2.setText("Teacher Name");
			    column3.setText("Address");
			    column4.setText("Phone");
			    column5.setText("School Name");
			    
			    column1.setWidth(50);
			    column2.setWidth(200);
			    column3.setWidth(300);
			    column4.setWidth(100);
			    column5.setWidth(200);
		
		combo = new Combo(shlListOfTeacher, SWT.DROP_DOWN);
		combo.setTouchEnabled(true);
		combo.setBounds(188, 275, 523, 28);
		
		//List of School
		/**ListOfSchool listOfSchool = new ListOfSchool();
		Iterator<school> it = listOfSchool.listOfSchools.iterator();
		school tmp = null;
		int i = 0;
		while(it.hasNext())
		{
			tmp = it.next();
		    combo.setItem(i,tmp.getName());
		    i++;
			//System.out.println(it.next().getName() +"\t");
			
		}**/
		
		combo.add("Trường Cấp 3 Châu Văn Liêm",0);
		combo.add("Trường Cấp 2 Đoàn Thị Điểm",1);
		combo.add("Trường Cấp 3 Nguyễn Bỉnh Khiêm",2);
		
		Label lblListOfSchools = new Label(shlListOfTeacher, SWT.NONE);
		lblListOfSchools.setText("List of Schools :");
		lblListOfSchools.setBounds(60, 278, 110, 20);

	}
}

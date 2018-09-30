package appInterface;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import entity.school;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;

public class ListOfSchool {

	protected Shell shlListOfSchools;
	private Text txtName;
	private Text txtAddress;
	private Text txtPhone;
	private Table table;
	
	Display display = new Display();
    Shell shell = new Shell(display);

	/**
	 * create List of Schools
	 */
	
	public Set<school> listOfSchools = new HashSet<school>();

	/**
	 * Get content textbox 
	 */
	
	private String name, address, phone;
	private int id;
		/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListOfSchool window = new ListOfSchool();
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
		shlListOfSchools.open();
		shlListOfSchools.layout();
		while (!shlListOfSchools.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
//////////////////////////////////////////////////////////////////////////
//updateTableViewer()                       //
//////////////////////////////////////////////////////////////////////////
	//public void updateTableViewer() {
	
//	}  
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlListOfSchools = new Shell();
		shlListOfSchools.setSize(800, 600);
		shlListOfSchools.setText("LIST OF SCHOOLS");
		
		Menu menu = new Menu(shlListOfSchools, SWT.BAR);
		shlListOfSchools.setMenuBar(menu);
		
		//open School Form
		MenuItem mntmListOfSchools = new MenuItem(menu, SWT.NONE);
		mntmListOfSchools.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new ListOfSchool().open();
			}
		});
		mntmListOfSchools.setSelection(true);
		mntmListOfSchools.setText("List of Schools");
		
		//open Teacher Form 
		MenuItem mntmListOfTeacher = new MenuItem(menu, SWT.NONE);
		mntmListOfTeacher.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new ListOfTeachers().open();
			}
		});
		mntmListOfTeacher.setText("List of Teacher");
		
		Label lblSchoolName = new Label(shlListOfSchools, SWT.NONE);
		lblSchoolName.setBounds(60, 86, 103, 20);
		lblSchoolName.setText("School name : ");
		
		Label lblAddress = new Label(shlListOfSchools, SWT.NONE);
		lblAddress.setBounds(60, 136, 70, 20);
		lblAddress.setText("Address :");
		
		Label lblPhone = new Label(shlListOfSchools, SWT.NONE);
		lblPhone.setBounds(60, 183, 70, 20);
		lblPhone.setText("Phone :");
		
		txtName = new Text(shlListOfSchools, SWT.BORDER);
		txtName.setBounds(188, 80, 519, 26);
		
		txtAddress = new Text(shlListOfSchools, SWT.BORDER);
		txtAddress.setBounds(188, 130, 519, 26);
		
		txtPhone = new Text(shlListOfSchools, SWT.BORDER);
		txtPhone.setBounds(188, 180, 519, 26);
		
		table = new Table(shlListOfSchools, SWT.BORDER | SWT.FULL_SELECTION);
		table.addTouchListener(new TouchListener() {
			public void touch(TouchEvent e) {
			}
		});
		table.setBounds(60, 307, 651, 174);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//Display table
		TableColumn column1 = new TableColumn(table, SWT.LEFT);
	    TableColumn column2 = new TableColumn(table, SWT.LEFT);
	    TableColumn column3 = new TableColumn(table, SWT.LEFT);
	    TableColumn column4 = new TableColumn(table, SWT.LEFT);
	    column1.setText("Id");
	    column2.setText("Name");
	    column3.setText("Address");
	    column4.setText("Phone");
	    column1.setWidth(50);
	    column2.setWidth(200);
	    column3.setWidth(300);
	    column4.setWidth(100);
		
		
		Button btnAdd = new Button(shlListOfSchools, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
								
				name = txtName.getText().toString();
				address = txtAddress.getText().toString();
				phone = txtPhone.getText().toString();
				school School = new school();
				id = listOfSchools.size() + 1 ;
				School.setId(id);
				School.setName(name);
				School.setAddress(address);
				School.setPhone(phone);
				
				//if null list
				listOfSchools.add(School);
				
				
				//System.out.println(listOfSchools.size());
				//System.out.println(listOfSchools);
				TableItem item = new TableItem(table, SWT.NONE);
				Iterator<school> it = listOfSchools.iterator();
				school tmp = null;
				while(it.hasNext())
				{
					tmp = it.next();
				    item.setText(new String[] { String.valueOf(tmp.getId()), tmp.getName(), tmp.getAddress(), tmp.getPhone() });
					//System.out.println(it.next().getName() +"\t");
					
				}
				/** open the shell/window 
			    shell.open();

			     Loop to keep the application opened 
			    while (!shell.isDisposed()) {
			        if (!display.readAndDispatch()) {
			        display.sleep();
			        }
			    }
			    display.dispose();**/
				
			}
		});
		btnAdd.setBounds(188, 244, 90, 30);
		btnAdd.setText("Add");
		
		Button btnEdit = new Button(shlListOfSchools, SWT.NONE);
		btnEdit.setBounds(373, 244, 90, 30);
		btnEdit.setText("Edit");
		
		Button btnDelete = new Button(shlListOfSchools, SWT.NONE);
		btnDelete.setBounds(544, 244, 90, 30);
		btnDelete.setText("Delete");
		
		Label lblListOfSchool = new Label(shlListOfSchools, SWT.NONE);
		lblListOfSchool.setFont(SWTResourceManager.getFont("Arial", 16, SWT.BOLD));
		lblListOfSchool.setBounds(275, 18, 253, 40);
		lblListOfSchool.setText("LIST OF SCHOOLS");

	}
	
}

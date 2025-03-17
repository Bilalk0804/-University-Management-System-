import java.util.*;
class Person{
    
    protected String name;
    protected String address;
    protected String mobileNo;
    public Person(String name,String address,String mobileNo){
      this.name = name;
      this.address = address;
      this.mobileNo = mobileNo;
    }

    public void DisplayDetails(){
        System.out.println("Name -" + name);
        System.out.println("Address - " + address);
        System.out.println("Mobile - " + mobileNo);
    }
}

interface Login{
    void login(String username, String password);
}

interface StudentMethods{
    void GiveExam();
    void TakeCourses();
}

class noNameException extends Exception {
  public noNameException(String message) {
      super(message);
  }
}


class Student extends Person implements Login,StudentMethods{
    public String stuID;
    public String fathersName;
    public String mothersName;
    public char grade;
    public String department;
    public int dueCredits;
   public  int stuFees;
    //constructor definition
    public Student(String stuID,String name,String address,String mobileNo,String fathersName,String mothersName,String department,int dueCredits,int stuFees){
        super(name, address, mobileNo);
        this.stuID = stuID;
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.department = department;
        this.dueCredits=dueCredits;
        this.stuFees=stuFees;
    }

    @Override
    public void login(String username, String password){
        System.out.println("Student "+name+" has logged in with username "+ username +"." );
    }

    @Override
    public void DisplayDetails(){
        System.out.println("STUDENT'S INFORMATION :-");
        System.out.println();
        System.out.println("Student's ID : "+stuID);
        super.DisplayDetails();
        System.out.println("Father's Name : "+fathersName);
        System.out.println("Mother's Name : "+mothersName);
        System.out.println("Department : "+department);
        System.out.println();
    }

    @Override
    public void GiveExam(){
        if(grade!='F' || grade!='f'){
            System.out.println(name + " is eligible for the upcoming mid-semester examinations.");
        }
        else{
            System.out.println(name + " is  not eligible for the upcoming mid-semester examinations.");
        }
        
    }

    @Override
    public void TakeCourses(){
        if (dueCredits==0){
            System.out.println(name + " CAN TAKE ALL AVAILABLE COURSES IN THE CURRENT SEMESTER");
        }
        else{
            System.out.println(name + " CANNOT TAKE ALL AVAILABLE COURSES IN THE CURRENT SEMESTER BACKLOGS CLEAR IS MUST");
        }
       
    }

    public String getName() throws noNameException{
      if (this.name == null ){
        throw new noNameException("No name found in the database.");
      }
      return this.name;
    }
    public int getCredits() throws noNameException{
        if (this.dueCredits < 0 ){
          throw new noNameException("DUE CREDIT CANNOT BE LESS THAN ZERO");
        }
        return this.dueCredits;
      }

    public void setGrade(char grade){
        this.grade = grade;
    }
}

interface FacInterface{
  void TakeExam();
  void AssignGrade(Student st,char g);
}

class Faculty extends Person implements Login,FacInterface{
    public String facID;
    public String subjectTaught;
    public String department;

    public Faculty(String facID,String name,String mobileNo,String address,String department,String subjectTaught){
        super(name, address, mobileNo);
        this.facID = facID;
        this.department = department;
        this.subjectTaught = subjectTaught;
    }

    @Override
    public void DisplayDetails(){
        System.out.println("FACULTY INFORMATION :-");
        System.out.println();
        System.out.println("Faculty ID : " + facID);
        super.DisplayDetails();
        System.out.println("Department : "+department);
        System.out.println("Subject Taught : "+subjectTaught);
    }

    @Override
    public void login(String username, String password){
        System.out.println("Faculty "+name+" has logged in with username "+username +".");
    }
    
    public String getName() throws noNameException{
        if (this.name == null){
            throw new noNameException("No name found in the database.");
        }
        return this.name;
    }

    @Override
    public void TakeExam(){
        System.out.println(name + " will be an invigilator in the upcoming mid-semester examination.");
    }

    @Override
    public void AssignGrade(Student st,char g){
        st.setGrade(g);
        try{
          System.out.println(name+" has assigned grade "+g+" to "+st.getName());
        } catch (noNameException e){
            System.out.println("Error : "+e.getMessage());
        }
        
    }
}


interface AdminInterface{
    void AddStudent(Student s);
    void AddFaculty(Faculty fac);
    void FeeCollection(Student s);
}

class Admin extends Person implements Login,AdminInterface{
    public String users;
    public String roles;
    final int fees =62500;
  
    public Admin(String users,String name,String roles,String mobileNo,String address){
      super(name, address, mobileNo);
      this.users = users;
      this.roles = roles;
      
    }

    @Override
    public void DisplayDetails(){
        System.out.println("ADMINS'S INFORMATION :-");
        System.out.println();
        super.DisplayDetails();
        System.out.println("Users : "+users);
        System.out.println("Roles : "+roles);
    }

    @Override
    public void login(String username, String password){
        System.out.println("Admin "+name+" has logged in with username "+username +".");
    }
  
    @Override
    public void AddFaculty(Faculty fac){
        try {
          System.out.println(fac.getName()+" has been added to Faculty DataBase."); 
        } catch (noNameException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }

    @Override
    public void AddStudent(Student s){
        try{
            System.out.println(s.getName()+" has been added to Student DataBase.");
        } catch (noNameException e){
            System.out.println("Error : "+e.getMessage());
        }
    }

    @Override
    public void FeeCollection(Student s){
        if(s.stuFees==62500){
            System.out.println("Fees has been collected.");
        }
        else{
            System.out.println("fees issue");
        }
    }
}

public class UMS89{
  public static void main(String[] args) {
    Scanner sc =new Scanner(System.in);
      Student student = new Student("23115089", "Shreyansh Shrivastava", "New Rajendra Nagar, Raipur (C.G.)", "8839437685", "Mr. Kirti Chandra Shrivastava", "Dr. Shraddha Shrivastava", "CSE",1200,10);
      Faculty teacher = new Faculty("F1154", "Mr. Nargesh Nagwani", "1190825611", "Raipur, CG", "CSE", "OOPJAVA");
      Admin facAdvisor = new Admin("A1178", "Mrs. Madhukrishna", "Faculty Advisor", "5568762313", "Arang, Delhi");
      System.out.println("____####____University Management System (UMS)____####____");
      System.out.println("IF YOU ARE ADMIN , PLEASE PRESS 1");
      System.out.println("IF YOU ARE STUDENT , PLEASE PRESS 2");
      System.out.println("IF YOU ARE FACULTY , PLEASE PRESS 3");
      System.out.println("");
      int loginNumber=sc.nextInt();
      int methodNumber;

      switch(loginNumber){
        case 1:
        System.out.println("IF YOU WANT TO DISPLAY DETAILS: PRESS 1");
        System.out.println("IF YOU WANT TO ADD FACULTY: PRESS 2");
        System.out.println("IF YOU WANT TO ADD STUDENT: PRESS 3");
        System.out.println("IF YOU WANT TO TAKE FEES: PRESS 4");
        System.out.println("IF YOU WANT TO ADD COURSES: PRESS 5");
        System.out.println("");
        methodNumber=sc.nextInt();
        switch(methodNumber){
            case 1:facAdvisor.DisplayDetails();
            System.out.println("");
            break;
            case 2:facAdvisor.AddFaculty(teacher);
            System.out.println("");
            break;
            case 3:facAdvisor.AddStudent(student);
            System.out.println("");
            break;
            case 4:facAdvisor.FeeCollection(student);
            System.out.println("There Might be issue from our side, Please try again later.");
            System.out.println("");
            break;
            case 5:
            System.out.println("Write the course you want to add:");
            System.out.println("1) Machine Learning");
            System.out.println("2) Data Science");
            System.out.println("3) Deep Learning");
            System.out.println("4) AI");
            System.out.println("5) Quantum Computing");
            break;
            default:System.out.println("GIVEN INPUT MIGHT BE WRONG! PLEASE TRY AGAIN.");
        }
        break;
        case 2:
        System.out.println("ENTER 1 IF YOU WANT TO LOGIN WITH USERNAME AND PASSWORD");
        System.out.println("ENTER 2 IF YOU WANT TO DISPLAY DETAILS");
        System.out.println("ENTER 3 IF YOU WANT TO TAKE COURSES");
        System.out.println("ENTER 4 IF YOU WANT TO GIVE EXAMS");
        System.out.println("");
        methodNumber=sc.nextInt();
        switch(methodNumber){
            case 1: student.login("shrey123", "192837");
                     System.out.println("");
                     break;
            case 2: student.DisplayDetails();
                     System.out.println("");
                     break;
            case 3: student.TakeCourses();
                     System.out.println("");
                     break;
            case 4: student.GiveExam();
                     System.out.println("");
                     break;
            default: System.out.println("PLEASE ENTER CORRECT INPUT");
        }
        break;
        case 3:
        System.out.println("IF YOU WANT TO LOGIN: PRESS 1");
        System.out.println("IF YOU WANT TO DISPLAY DETAILS: PRESS 2");
        System.out.println("IF YOU WANT TO ASSIGN GRADES: PRESS 3");
        System.out.println("IF YOU WANT TO TAKE EXAMS: PRESS 4");
        System.out.println("");
        methodNumber=sc.nextInt();
        switch(methodNumber){
            case 1: teacher.login("NN135", "9875");
            System.out.println("");
            break;
            case 2: teacher.DisplayDetails();
            System.out.println("");
            break;
            case 3:  teacher.AssignGrade(student, 'A');
            System.out.println("");
            break;
            case 4:teacher.TakeExam();
            System.out.println("");
            break;
            default: System.out.println("PLEASE ENTER CORRECT INPUT");
        }
        break;
        default:
        System.out.println("THANK YOU!");
      }

  }
}


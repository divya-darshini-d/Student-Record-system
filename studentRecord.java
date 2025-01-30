import java.util.*;

public class studentRecord {
    private List<Student> StudentList = new ArrayList<>();
    private HashMap<Integer, Student> StudentMap = new HashMap<>();
    private TreeMap<String, Student> studentTreeMap = new TreeMap<>();

    public static void main(String[] args) {
        studentRecord sr = new studentRecord();
        sr.initialize();
        sr.run();
    }

    private void initialize() {
        StudentList.add(new Student(1, "Divya", 'B'));
        studentTreeMap.put("Divya", new Student(1, "Divya", 'B'));
        StudentList.add(new Student(2, "Hema", 'A'));
        studentTreeMap.put("Hema", new Student(2, "Hema", 'A'));
        StudentList.add(new Student(3, "Jani", 'O'));
        studentTreeMap.put("Jani", new Student(3, "Jani", 'O'));
    }

    private void run() {
        Scanner s = new Scanner(System.in);
            boolean running = true;

        while (running) {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Show all Student Records");
            System.out.println("2. Add Student");
            System.out.println("3. Get a student Detail");
            System.out.println("4. Remove Duplicate Elements");
            System.out.println("5. Display Students in Alphabetical Order");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                showAllStudents();
                break;

                case 2:
                addStudent(s);
                break;

                case 3:
                getStudentDetails(s);
                break;

                case 4:
                removeDuplicates();
                break;

                case 5:
                displayInOrder();
                break;

                case 6:
                running = false;
                break;

                default:
                System.out.println("Invalid Choice Entered");
            }
        }
        s.close();
    }

    private void showAllStudents() {
        if (StudentList.size() != 0) {
            System.out.println("\n--- STUDENT RECORDS ---\n");
            for (Student s : StudentList) {
                System.out.println("STUDENT ID: " + s.getId());
                System.out.println("NAME: " + s.getName());
                System.out.println("GRADE: " + s.getGrade());
                System.out.println("----------\n");
            }
        }
        else {
            System.out.println("No Student Record Available");
        }
    }

    private void addStudent(Scanner s) {
        try {
            System.out.println("Enter the Student ID: "); 
            int id = s.nextInt();
            if (id <= 0) {
                throw new IllegalArgumentException("Student ID must be greater than 0.");
            }
            s.nextLine();
          
            System.out.println("Enter the Student Name: ");
            String name = s.nextLine(); 
            if (name.isEmpty()) {
                throw new NullPointerException("Student name cannot be empty.");
            }   
            System.out.println("Enter Grade: ");
            char grade = s.next().charAt(0);
            if ("OABCDF".indexOf(grade) == -1) {
                throw new IllegalArgumentException("Invalid grade. Must be O, A, B, C, D, or F.");
            }    
            Student student = new Student(id, name, grade);
            StudentList.add(student);
            studentTreeMap.put(name, student);
            
            System.out.println("Student Successfully added");
        }

        catch (NullPointerException e) {  
            System.out.println(e.getMessage());
        } 
        catch (IllegalArgumentException e) {  
            System.out.println(e.getMessage());
        } 
        catch (Exception e) {  
            System.out.println("An unexpected error occurred");
        }
    }    

    private void getStudentDetails(Scanner s) {
        System.out.println("Enter the Student ID to be searched: ");
        int id = s.nextInt();

        boolean found = false;

        for (Student a : StudentList) {
            if (a.getId() == id) {
                found = true;
                System.out.println("NAME: " + a.getName());
                System.out.println("GRADE: " + a.getGrade());
            }
        } 

        if (!found) {
            System.out.println("Student Not Found");
        } 
    }

    private void removeDuplicates() {
        StudentMap.clear();
        for (Student s : StudentList) {
            StudentMap.put(s.getId(), s);
        }

        StudentList = new ArrayList<>(StudentMap.values());
        System.out.println("\nDuplicate Student Records Removed.");

    }

    private void displayInOrder() {
        if (studentTreeMap.isEmpty()) {
            System.out.println("\nNo Student Records Available.");
            return;
        }
    
        System.out.println("\n--- Students Sorted by Name ---\n");
        for (Student s : studentTreeMap.values()) {
            System.out.println("STUDENT ID: " + s.getId());
            System.out.println("NAME: " + s.getName());
            System.out.println("GRADE: " + s.getGrade());
            System.out.println("----------\n");
        }
    }

    public class Student {
        private int id;
        private String name;
        private char grade;

        public Student (int id, String name, char grade) {
            this.id = id;
            this.name = name;
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public char getGrade() {
            return grade;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setGrade(char grade) {
            this.grade = grade;
        }
    }
}


public class Student {
    /** 
     * The student's name
     */
    private String name;

    /**
     * The student's grade
     */
    private int grade;

    /**
     * The student's current total points
     */
     private int points;

     /**
      * Creates a new student object
      */
    public Student(String name, int grade, int points){
        this.name = name;
        this.grade = grade;
        this.points = points;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getGrade(){
        return grade;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }
}

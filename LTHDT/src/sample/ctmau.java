
/* Phan Chính Quốc */

package sample;

public class ctmau extends sinhvien {
    /*
    Khai báo thuộc tính và các phương thức khởi tạo, getter, setter của lớp con ctmau
     */
    private double gpa;
    public void setGpa(double gpa){
        this.gpa=gpa;
    }
    public double getGpa(){
        return gpa;
    }
    public ctmau(String name, String id, int year, double gpa){
        super(name, id, year);
        this.gpa=gpa;
    }
    /*
    Khai báo phương thức xetTN dành cho sinh viên học theo hình thức chương trình mẫu (tốt nghiệp nếu GPA > 5.0)
     */
    @Override
    public boolean xetTN() {
        if(gpa > 5){
            return true;
        }else{
            return false;
        }
    }
}
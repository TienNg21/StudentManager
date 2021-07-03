
/* Phan Chính Quốc */

package sample;

public class tinchi extends sinhvien{
    /*
    Khai báo thuộc tính và các phương thức khởi tạo, getter, setter của lớp con tinchi
     */
    private int soTC;
    public void setSoTC(int soTC) {
        this.soTC = soTC;
    }
    public int getSoTC(){
        return soTC;
    }
    public tinchi(String name, String id, int year, int soTC){
        super(name, id, year);
        this.soTC=soTC;
    }
    /*
    Khai báo phương thức xetTN dành cho sinh viên học theo hình thức tín chỉ (tốt nghiệp nếu số tín chỉ > 160)
     */
    @Override
    public boolean xetTN() {
        if(soTC > 160){
            return true;
        }else{
            return false;
        }
    }
}

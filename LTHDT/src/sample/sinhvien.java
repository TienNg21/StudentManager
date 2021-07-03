
/* Phan Chính Quốc */

package sample;

public abstract class sinhvien {
    /*
    Khai báo các thuộc tính của lớp sinhvien
     */
    private String name;
    private String id;
    private int year;
    private String hinhthuc;

    /*
    Khai báo các phương thức getter và setter
     */
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
    public void setHinhthuc(String hinhthuc) {
        this.hinhthuc = hinhthuc;
    }
    public String getTC(){
        return hinhthuc;
    }

    /*
    Khai báo phương thức khởi tạo
     */
    public sinhvien(String name, String id, int year){
        this.name=name;
        this.id=id;
        this.year=year;
    }

    /*
    Khai báo phương thức đa hình xetTN
     */
    public abstract boolean xetTN();
}

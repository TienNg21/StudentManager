
/* Khổng Thế Học */

package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class quanly {
    /*
    Khai báo thuộc tính, phương thức khởi tạo
     */
    private List<sinhvien> list;

    public quanly(){
        list=new ArrayList<>();
    }

    //Phương thức getList: trả về danh sách sinh viên
    public List<sinhvien> getList() {
        return list;
    }

    //Phương thức add: thêm sinh viên vào danh sách
    public void add(sinhvien sv){
        list.add(sv);
    }

    //Phương thức removeAll: xóa tất cả sinh viên trong danh sách
    public void removeAll(){
        for(int i = 0; i < this.getList().size(); i++){
            sinhvien temp = this.getList().get(i);
            this.remove(temp.getId());
        }
    }

    //Phương thức remove: xóa sinh viên có mã số idrm khỏi danh sách
    public void remove(String idrm){
        sinhvien sv;
        sv=this.list.stream().filter(o->o.getId().equals(idrm)).findFirst().orElse(null);
        if(sv==null){
            return;
        }else {
            list.remove(sv);
        }
    }

    //Phương thức searchById: trả về 1 sinh viên có mã số sinh viên là id (tất cả sinh viên đều có mã số khác nhau)
    public sinhvien searchById(String id){
        sinhvien sv;
        sv = this.list.stream().filter(o->o.getId().equals(id)).findFirst().orElse(null);
        return sv;
    }

    //Phương thức searchByName: trả về danh sách sinh viên có tên là name
    public List<sinhvien> searchByName(String name) {
        List<sinhvien> sv;
        sv = this.list.stream().filter(o -> o.getName().equals(name)).collect(Collectors.toList());
        return sv;
    }

    //Phương thức searchByYear: trả về danh sách sinh viên có niêm khóa là year
    public List<sinhvien> searchByYear(int year){
        List<sinhvien> sv;
        sv=this.list.stream().filter(o->o.getYear()==year).collect(Collectors.toList());
        return sv;
    }

    //Phương thức checkId: kiểm tra xem đã tồn tại sinh viên có mã số này chưa
    public boolean checkId(String id){
        if(list.stream().filter(o->o.getId().equals(id)).findFirst().orElse(null)!=null) {
            return false;
        }else {
            return true;
        }
    }
}

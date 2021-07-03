
/* Nguyễn Hữu Tiến */

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


public class Controller implements Initializable {

    /*
    Khai báo các danh sách:
        tatca : tất cả sinh viên
        sua: danh sách sinh viên sửa
        tim: danh sách sinh viên đang được tìm kiếm
        totnghiep: danh sách sinh viên đủ điều kiện tốt nghiệp
     */
    quanly tatca = new quanly();
    quanly tim = new quanly();
    quanly totnghiep = new quanly();


    /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~
    -                        -
     MÀN HÌNH THÊM SINH VIÊN
    -                        -
    ~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    //Khai báo các TextField để nhập thông tin
    @FXML private TextField Name;
    @FXML private TextField Id;
    @FXML private TextField Year;
    @FXML private TextField SoTC;
    @FXML private TextField Gpa;
    //Khai báo RadioButton để chọn hình thức học
    @FXML private RadioButton CTMau;
    @FXML private RadioButton TinChi;

    //Phương thức xacNhanThemSV: thêm sinh viên vào danh sách khi nhấn Button "Xác nhận"
    public void xacNhanThemSV(){
        //Khai báo một danh sách tạm gồm tất cả sinh viên
        List<sinhvien> temp = tatca.getList();

        /* Nếu chưa chọn hoặc chọn cả hai CheckBox thì bắt nhập lại */
        if((CTMau.isSelected() && TinChi.isSelected()) || (!CTMau.isSelected() && !TinChi.isSelected())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập thông tin không đúng");
            alert.setHeaderText("Bạn đang nhập thông tin vê hình thức học không hợp lệ");
            alert.setContentText("Vui lòng chọn chính xác!");
            alert.showAndWait();

            // Xóa dấu tich trên CheckBox
            CTMau.setSelected(false);
            TinChi.setSelected(false);
        }
        /* Nếu chọn Chương trình mẫu */
        else if(CTMau.isSelected() && !TinChi.isSelected()){
            // Nếu chưa nhập tên hoặc mã số sinh viên hoặc niêm khóa hoặc GPA thì bắt nhập tiếp
            if(Name.getText().equals("") || Id.getText().equals("") || Year.getText().equals("") || Gpa.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nhập thông tin không đúng");
                alert.setHeaderText("Bạn đang nhập thiếu thông tin sinh viên");
                alert.setContentText("Vui lòng nhập lại!!!");
                alert.showAndWait();
            }
            //Nếu đã nhập đủ
            else{
                String name = Name.getText();
                String id = Id.getText();
                String y = Year.getText();
                int year = Integer.parseInt(y);
                String z = Gpa.getText();
                double gpa = Double.parseDouble(z);
                //Nếu GPA không thuộc [0;10] bắt nhập lại
                if(gpa < 0 || gpa > 10){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Nhập thông tin không đúng");
                    alert.setHeaderText("Bạn đang nhập điểm sinh viên không hợp lệ");
                    alert.setContentText("Vui lòng chọn chính xác!");
                    alert.showAndWait();
                    return;
                }
                //Kiểm tra xem có bị trùng mã số sinh viên không
                for(int i = 0; i < temp.size(); i++){
                    sinhvien sv = temp.get(i);
                    //nếu trùng bắt nhập lại
                    if(sv.getId().equals(id)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Nhập thông tin không đúng");
                        alert.setHeaderText("Bạn đang nhập trùng mã số sinh viên");
                        alert.setContentText("Vui lòng chọn chính xác!");
                        alert.showAndWait();
                        return;
                    }
                }
                //nếu không trùng thì thêm sinh viên vào danh sách
                ctmau sv = new ctmau(name, id, year, gpa);
                sv.setHinhthuc("Chương trình mẫu");
                tatca.add(sv);

                //Xóa các dữ liệu trên TextField và bỏ tick trên RadioButton
                Name.clear();
                Id.clear();
                Year.clear();
                CTMau.setSelected(false);
                SoTC.clear();
                Gpa.clear();

                //Thông báo nếu nhập thông tin thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nhập thông tin thành công");
                alert.setHeaderText("Bạn đã nhập thông tin thành công");
                alert.setContentText("Nhấn OK để tiếp tục");
                alert.showAndWait();
            }
        }
        /* Nếu chọn Tín chỉ */
        else{
            // Nếu chưa nhập tên hoặc mã số sinh viên hoặc niêm khóa hoặc GPA thì bắt nhập tiếp
            if(Name.getText().equals("") || Id.getText().equals("") || Year.getText().equals("") || TinChi.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nhập thông tin không đúng");
                alert.setHeaderText("Bạn đang nhập thiếu thông tin sinh viên");
                alert.setContentText("Vui lòng nhập lại!!!");
                alert.showAndWait();
            // Nếu đã nhập đủ
            }else{
                String name = Name.getText();
                String id = Id.getText();
                String y = Year.getText();
                int year = Integer.parseInt(y);
                String x = SoTC.getText();
                int soTC = Integer.parseInt(x);

                // kiểm tra xem có trùng mã số sinh viên không
                for(int i = 0; i < temp.size(); i++){
                    sinhvien sv = temp.get(i);
                    //nếu có bắt nhập lại
                    if(sv.getId().equals(id)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Nhập thông tin không đúng");
                        alert.setHeaderText("Bạn đang nhập trùng mã số sinh viên");
                        alert.setContentText("Vui lòng chọn chính xác!");
                        alert.showAndWait();
                        return;
                    }
                }
                //nếu không thêm sinh viên vào danh sách
                tinchi sv = new tinchi(name, id, year, soTC);
                sv.setHinhthuc("Tín chỉ");
                tatca.add(sv);

                //Xóa dữ liệu trên TextField và bỏ dấu tick trên CheckBox
                Name.clear();
                Id.clear();
                Year.clear();
                TinChi.setSelected(false);
                SoTC.clear();
                Gpa.clear();

                //Thông báo nhập thông tin thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nhập thông tin thành công");
                alert.setHeaderText("Bạn đã nhập thông tin thành công");
                alert.setContentText("Nhấn OK để tiếp tục");
                alert.showAndWait();
            }

        }

    }
    /* Kết thúc màn hình thêm sinh viên */


    /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~
    -                        -
     MÀN HÌNH XÓA SINH VIÊN
    -                        -
    ~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    /* Khai báo TextField để nhập mã số sinh viên cần xóa */
    @FXML private TextField Xoa_mssv;

    //Phương thức xacNhanXoaSV: xóa sinh viên khi nhấn Button
    public void xacNhanXoaSV(){
        String xoa_mssv = Xoa_mssv.getText();
        //Nếu có mã số sinh viên trong danh sách sinh viên
        if(!tatca.checkId(xoa_mssv)){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Xóa thông tin sinh viên");
            alert.setHeaderText("Đã xóa thành công");
            alert.setContentText("Nhấn OK để tiếp tục");
            alert.showAndWait();
            //Xóa sinh viên và clear TextField
            tatca.remove(xoa_mssv);
            Xoa_mssv.clear();
        }
        //Nếu không có mã số sinh viên này trong danh sách
        else{
            //Thông báo không có sinh viên
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Xóa thông tin sinh viên");
            alert.setHeaderText("Không có sinh viên mang mã số này");
            alert.setContentText("Vui lòng nhập lại");
            alert.showAndWait();

            //Clear TextField
            Xoa_mssv.clear();
        }
    }
    /* Kết thúc màn hình xóa sinh viên */


    /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    -                             -
     MÀN HÌNH DANH SÁCH SINH VIÊN
    -                             -
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    //Khai báo TextField để lấy mã số sinh viên cần sửa
    @FXML private TextField getMSSV;

    //Khai báo TextField để lấy thông tin cần sửa
    @FXML private TextField fixName;
    @FXML private TextField fixId;
    @FXML private TextField fixYear;
    @FXML private TextField fixSoTC;
    @FXML private TextField fixGpa;

    //Khai báo bảng để hiển thị thông tin sinh viên cần sửa
    @FXML private TableView<sinhvien> fixtable;

    //Khai báo các cột tên, mã số, niên khóa, hình thức học, số tín chỉ, GPA
    @FXML private TableColumn<sinhvien, String> fixnameColumn;
    @FXML private TableColumn<sinhvien, String> fixidColumn;
    @FXML private TableColumn<sinhvien, Integer> fixyearColumn;
    @FXML private TableColumn<sinhvien, String> fixhinhthuchocColumn;
    @FXML private TableColumn<sinhvien, Integer> fixsotinchiColumn;
    @FXML private TableColumn<sinhvien, Double> fixgpaColumn;

    //Khai báo một đối tượng làm trung gian để sửa
    sinhvien svsua;
    List<sinhvien> fixlist;

    //Khai báo Phương thức xacNhanSua: xác nhận lấy sinh viên có mã số đã nhập để sửa
    public void xacNhanSua(){
        //Clear TextField
        fixName.clear();
        fixId.clear();
        fixYear.clear();
        fixSoTC.clear();
        fixGpa.clear();


        //Nếu đã nhập mã số sinh viên để sửa
        if(!getMSSV.getText().trim().isEmpty()){
            String id = getMSSV.getText();

            svsua = tatca.searchById(id);
            //Nếu không tìm thấy sinh viên nào có mã số đó
            if(svsua == null){
                //Hiển thị thông báo
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Không tim thấy sinh viên");
                alert.setHeaderText("Mã số sinh viên bạn nhập không có sinh viên thỏa mãn");
                alert.setContentText("Vui lòng nhập lại!!!");
                alert.showAndWait();
                return;
            }
            fixtable.refresh();
            //Hiển thị dữ liệu về sinh viên vừa sửa ra table
            fixlist = FXCollections.observableArrayList(
                    svsua
            );
            fixnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            fixidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            fixyearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            fixhinhthuchocColumn.setCellValueFactory(new PropertyValueFactory<>("TC"));
            fixsotinchiColumn.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            fixgpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            fixtable.setItems((ObservableList<sinhvien>)fixlist);
        }
        //Nếu chưa nhập mã số sinh viên
        else {
            //Thông báo và bắt nhập lại
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập thông tin không đúng");
            alert.setHeaderText("Bạn đang nhập thiếu thông tin MSSV sinh viên");
            alert.setContentText("Vui lòng nhập lại!!!");
            alert.showAndWait();
        }
    }

    //Phương thức suaTen: sửa tên sinh viên khi nhấn Button sửa theo tên
    public void suaTen(){
        //Nếu chưa xác nhận sửa sinh viên nào
        if(svsua == null){
            //Thông báo và bắt nhập lại
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập sinh viên cần sửa");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
            return;
        }
        //Nếu đã xác nhận sửa sinh viên nào

        //Tiếp tục kiểm tra nếu chưa nhập tên sau khi sửa
        if(fixName.getText().trim().isEmpty()){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập tên sinh viên");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
        }
        //Nếu đã nhập tên sau khi sửa
        else{
            String name = fixName.getText();
            //Sửa tên
            svsua.setName(name);
            //Thông báo sửa thành công
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sửa thông tin thành công");
            alert.setHeaderText("Bạn đã sửa thông tin tên sinh viên thành công");
            alert.setContentText("Nhấn OK để tiếp tục");
            alert.showAndWait();

            fixtable.refresh();
            //Cập nhật thông tin ra table
            fixlist = FXCollections.observableArrayList(
                    svsua
            );

            fixnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            fixidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            fixyearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            fixhinhthuchocColumn.setCellValueFactory(new PropertyValueFactory<>("TC"));
            fixsotinchiColumn.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            fixgpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            fixtable.setItems((ObservableList<sinhvien>)fixlist);

            //Clear các TextField
            fixName.clear();
            fixId.clear();
            fixYear.clear();
            fixSoTC.clear();
            fixGpa.clear();
        }
    }

    //Phương thức suaMSSV: sửa mã số sinh viên đã nhập khi nhấp Button sửa theo MSSV
    public void suaMSSV(){
        //Nếu chưa xác nhận sửa sinh viên nào
        if(svsua == null){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập sinh viên cần sửa");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
            return;
        }
        //Nếu chưa nhập mã số sinh viên mới
        if(fixId.getText().trim().isEmpty()){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập mã số sinh viên");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
        }
        //Nếu đã nhập mã số sinh viên
        else{
            fixtable.refresh();
            String id = fixId.getText();
            //Sửa mã số sinh viên
            svsua.setId(id);
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sửa thông tin thành công");
            alert.setHeaderText("Bạn đã sửa thông tin mã số sinh viên thành công");
            alert.setContentText("Nhấn OK để tiếp tục");
            alert.showAndWait();

            //Cập nhật thông tin ra table
            fixlist = FXCollections.observableArrayList(
                    svsua
            );
            fixnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            fixidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            fixyearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            fixhinhthuchocColumn.setCellValueFactory(new PropertyValueFactory<>("TC"));
            fixsotinchiColumn.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            fixgpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            fixtable.setItems((ObservableList<sinhvien>)fixlist);

            //Clear TextField
            fixName.clear();
            fixId.clear();
            fixYear.clear();
            fixSoTC.clear();
            fixGpa.clear();
        }
    }

    //Phương thuc suaKhoa: sửa niên khóa sinh viên khi nhấp Button sửa theo niên khóa
    public void suaKhoa(){
        //Nếu chưa xác nhận sửa sinh viên nào
        if(svsua == null){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập sinh viên cần sửa");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
            return;
        }
        //Nếu chưa nhập niên khóa mới cần sửa
        if(fixYear.getText().trim().isEmpty()){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập niên khóa sinh viên");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
        }
        //Nếu đã nhập niên khóa mới cần sửa
        else{
            fixtable.refresh();
            String y = fixYear.getText();
            int year = Integer.parseInt(y);
            //Sửa niên khóa sinh viên
            svsua.setYear(year);

            //Thông báo sửa thành công
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sửa thông tin thành công");
            alert.setHeaderText("Bạn đã sửa thông tin niêm khóa sinh viên thành công");
            alert.setContentText("Nhấn OK để tiếp tục");
            alert.showAndWait();

            //Cập nhật thông tin ra table
            fixlist = FXCollections.observableArrayList(
                    svsua
            );
            fixnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            fixidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            fixyearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            fixhinhthuchocColumn.setCellValueFactory(new PropertyValueFactory<>("TC"));
            fixsotinchiColumn.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            fixgpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            fixtable.setItems((ObservableList<sinhvien>)fixlist);

            //Clear TextField
            fixName.clear();
            fixId.clear();
            fixYear.clear();
            fixSoTC.clear();
            fixGpa.clear();
        }
    }

    //Khai báo phương thức suaSoTC: sửa số tín chỉ nếu nhấp Button sửa số tín chỉ
    public void suaSoTC(){
        //Nếu chưa xác nhận sửa sinh viên nào
        if(svsua == null){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập sinh viên cần sửa");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
            return;
        }
        //Nếu sinh viên đó học theo chương trình mẫu (không có số tín chỉ)
        if(svsua instanceof ctmau){
            //Thông báo không sửa được
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Sinh viên bạn nhập không học theo tín chỉ");
            alert.setContentText("Nếu cần chỉnh sửa, vui lòng xóa sinh viên đi và nhập lại");
            alert.showAndWait();
            return;
        }
        //Nếu chưa nhập số tín chỉ mới cần sửa
        if(fixSoTC.getText().trim().isEmpty()){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập số tín chỉ sinh viên");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
        }
        //Nếu đã nhập
        else{
            String tc = fixSoTC.getText();
            int soTC = Integer.parseInt(tc);
            //Sửa thông tin số tín chỉ
            ((tinchi) svsua).setSoTC(soTC);
            //Thông báo sửa thành công
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sửa thông tin thành công");
            alert.setHeaderText("Bạn đã sửa thông tin số tín chỉ sinh viên thành công");
            alert.setContentText("Nhấn OK để tiếp tục");
            alert.showAndWait();

            fixtable.refresh();
            //Cập nhật thông tin ra table
            fixlist = FXCollections.observableArrayList(
                    svsua
            );
            fixnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            fixidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            fixyearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            fixhinhthuchocColumn.setCellValueFactory(new PropertyValueFactory<>("TC"));
            fixsotinchiColumn.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            fixgpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            fixtable.setItems((ObservableList<sinhvien>)fixlist);

            //Clear TextField
            fixName.clear();
            fixId.clear();
            fixYear.clear();
            fixSoTC.clear();
            fixGpa.clear();
        }
    }

    //Khai báo phương thức suaGPa: sửa GPA sinh viên khi nhấp Button sửa GPA
    public void suaGpa(){
        //Nếu chưa xác nhận sinh viên cần sửa
        if(svsua == null){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập sinh viên cần sửa");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
            return;
        }
        //Nếu sinh viên cần sửa học theo tín chỉ (không có GPA)
        if(svsua instanceof tinchi){
            //Thông báo không sửa được
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Sinh viên bạn nhập không học theo chương trình mẫu");
            alert.setContentText("Nếu cần chỉnh sửa, vui lòng xóa sinh viên đi và nhập lại");
            alert.showAndWait();
            return;
        }

        //Nếu chưa nhập GPA mới cần sửa
        if(fixGpa.getText().trim().isEmpty()){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập không hợp lệ");
            alert.setHeaderText("Bạn chưa nhập GPA sinh viên");
            alert.setContentText("Vui lòng nhập lại!");
            alert.showAndWait();
        }
        //Nếu đã nhập
        else{
            String gpaText = fixGpa.getText();
            double gpa = Double.parseDouble(gpaText);
            //Nếu điểm mới nhập không hợp lệ
            if(gpa < 0 || gpa > 10){
                //Thông báo
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nhập thông tin không đúng");
                alert.setHeaderText("Bạn đang nhập điểm sinh viên không hợp lệ");
                alert.setContentText("Vui lòng chọn chính xác!");
                alert.showAndWait();
                return;
            }
            //Nếu điểm mới nhập hợp lệ, sửa GPA sinh viên
            ((ctmau) svsua).setGpa(gpa);
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sửa thông tin thành công");
            alert.setHeaderText("Bạn đã sửa thông tin GPA sinh viên thành công");
            alert.setContentText("Nhấn OK để tiếp tục");
            alert.showAndWait();
            fixtable.refresh();

            //Cập nhật thông tin ra table
            fixlist = FXCollections.observableArrayList(
                    svsua
            );
            fixnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            fixidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            fixyearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            fixhinhthuchocColumn.setCellValueFactory(new PropertyValueFactory<>("TC"));
            fixsotinchiColumn.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            fixgpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            fixtable.setItems((ObservableList<sinhvien>)fixlist);
            //Clear TextField
            fixName.clear();
            fixId.clear();
            fixYear.clear();
            fixSoTC.clear();
            fixGpa.clear();
        }
    }
    /* Kết thúc màn hình sửa thông tin sinh viên */


    /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    -                             -
     MÀN HÌNH DANH SÁCH SINH VIÊN
    -                             -
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    //Khai báo table để hiển thị danh sách sinh viên
    @FXML private TableView<sinhvien> table;

    //Khai báo các cột để hiển thị danh sách
    @FXML private TableColumn<sinhvien, String> nameColumn;
    @FXML private TableColumn<sinhvien, String> idColumn;
    @FXML private TableColumn<sinhvien, Integer> yearColumn;
    @FXML private TableColumn<sinhvien, String> hinhthuchocColumn;
    @FXML private TableColumn<sinhvien, Integer> sotinchiColumn;
    @FXML private TableColumn<sinhvien, Double> gpaColumn;

    //Phương thức inDSSV: in danh sách ra table khi nhấn Button Cập nhật danh sách
    public void inDSSV(){
        //reset Table
        table.refresh();
        //khai báo list danh sách để chứa tất cả sinh viên
        List<sinhvien> list = tatca.getList();



        //Nếu không in danh sách ra table
        list = FXCollections.observableArrayList(
                tatca.getList()
        );
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        hinhthuchocColumn.setCellValueFactory(new PropertyValueFactory<>("TC"));
        sotinchiColumn.setCellValueFactory(new PropertyValueFactory<>("soTC"));
        gpaColumn.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        table.setItems((ObservableList<sinhvien>) list);

        //Nếu danh sách trống
        if(list.size() == 0){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Danh sách sinh viên trống");
            alert.setHeaderText("Bạn cần thêm sinh viên để có thể hiển thị danh sách");
            alert.setContentText("Nhấn OK để tiếp tục");
            alert.showAndWait();
            return;
        }
    }

    /* Kết thúc màn hinh danh sách sinh viên */


    /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    -                                       -
     MÀN HÌNH DANH SÁCH SINH VIÊN TỐT NGHIỆP
    -                                       -
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    //Khai báo bảng để hiển thị danh sách tốt nghiệp
    @FXML private TableView<sinhvien> table1;

    //Khai báo các cột để hiển thị thông tin
    @FXML private TableColumn<sinhvien, String> nameColumn1;
    @FXML private TableColumn<sinhvien, String> idColumn1;
    @FXML private TableColumn<sinhvien, Integer> yearColumn1;
    @FXML private TableColumn<sinhvien, String> hinhthuchocColumn1;
    @FXML private TableColumn<sinhvien, Integer> sotinchiColumn1;
    @FXML private TableColumn<sinhvien, Double> gpaColumn1;

    //Phương thức XetTN: Hiển thị danh sách tốt nghiệp ra table khi nhấn Button cập nhật danh sách tốt nghiệp
    public void XetTN(){
        //reset table
        table1.refresh();
        //Khai báo list tạm để chứa tất cả sinh viên
        List<sinhvien> temp = tatca.getList();

        //Thêm tất cả các sinh viên đủ điều kiện tốt nghiệp vào totnghiep
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i) instanceof tinchi){
                if(((tinchi) temp.get(i)).xetTN()){
                    totnghiep.add(temp.get(i));
                }
            }
            if(temp.get(i) instanceof ctmau){
                if(((ctmau) temp.get(i)).xetTN()){
                    totnghiep.add((temp.get(i)));
                }
            }
        }

        //Khai báo list DSTN chứa các sinh viên đủ điều kiện tốt nghiệp
        List<sinhvien> DSTN = totnghiep.getList();

        //Nếu danh sách không rỗng, hiển thị danh sách
        DSTN = FXCollections.observableArrayList(
                totnghiep.getList()
        );
        nameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn1.setCellValueFactory(new PropertyValueFactory<>("id"));
        yearColumn1.setCellValueFactory(new PropertyValueFactory<>("year"));
        hinhthuchocColumn1.setCellValueFactory(new PropertyValueFactory<>("TC"));
        sotinchiColumn1.setCellValueFactory(new PropertyValueFactory<>("soTC"));
        gpaColumn1.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        table1.setItems((ObservableList<sinhvien>) DSTN);

        //Nếu danh sách tốt nghiệp rỗng - không có sinh viên đủ điều kiện tốt nghiệp
        if(DSTN.size() == 0){
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Danh sách sinh viên tốt nghiệp trống");
            alert.setHeaderText("Không có sinh viên nào đủ điều kiện tốt nghiệp");
            alert.setContentText("Nhấn OK để tiếp tục");
            alert.showAndWait();
            return;
        }

        //reset danh sách tốt nghiệp
        for(int i = 0; i < temp.size(); i++){
            sinhvien sv = temp.get(i);
            totnghiep.remove(sv.getId());
        }
    }
    /* Kết thúc màn hình in danh sách tốt nghiệp */


    /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    -                           -
     MÀN HÌNH TÌM KIẾM SINH VIÊN
    -                           -
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    //Khai báo các TexTField để nhập thông tin cần tìm kiếm
    @FXML private TextField findName;
    @FXML private TextField findId;
    @FXML private TextField findYear;
    //Khai báo table để hiện thông tin sau khi tìm kiếm
    @FXML private TableView<sinhvien> table2;
    //Khai báo các côt để hiển thị thông tin
    @FXML private TableColumn<sinhvien, String> nameColumn2;
    @FXML private TableColumn<sinhvien, String> idColumn2;
    @FXML private TableColumn<sinhvien, Integer> yearColumn2;
    @FXML private TableColumn<sinhvien, String> hinhthuchocColumn2;
    @FXML private TableColumn<sinhvien, Integer> sotinchiColumn2;
    @FXML private TableColumn<sinhvien, Double> gpaColumn2;

    //Khai báo danh sách sinh viên listFind
    List<sinhvien> listFind;

    //Khai báo phương thức timTheoTen: tìm sinh viên có tên nhập ở findName và in ra table
    public void timTheoTen(){
        String name = findName.getText();
        //Nếu đã nhập tên sinh viên cần tìm kiếm
        if(!findName.getText().trim().isEmpty()){
            //Khai báo một list tạm
            List<sinhvien> temp;
            //tìm sinh viên có tên thỏa mãn và thêm vào temp
            temp = tatca.searchByName(name);

            //Nếu không tìm thấy
            if(temp.size() == 0){
                //Thông báo
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Không tim thấy sinh viên");
                alert.setHeaderText("Tên sinh viên bạn nhập không có sinh viên thỏa mãn");
                alert.setContentText("Vui lòng nhập lại!!!");
                alert.showAndWait();
            }
            //Nếu tìm thấy, thêm vào các sinh viên đó vào tim
            for(int i = 0; i < temp.size(); i++){
                sinhvien sv = temp.get(i);
                tim.add(sv);
            }

            //Hiển thị các sinh viên vừa tìm được ra table
            listFind = FXCollections.observableArrayList(
                tim.getList()
            );
            nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
            idColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));
            yearColumn2.setCellValueFactory(new PropertyValueFactory<>("year"));
            hinhthuchocColumn2.setCellValueFactory(new PropertyValueFactory<>("TC"));
            sotinchiColumn2.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            gpaColumn2.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            table2.setItems((ObservableList<sinhvien>)listFind);

            //Reset danh sách tim kiếm
            for(int i = 0; i < temp.size(); i++){
                sinhvien sv = temp.get(i);
                tim.remove(sv.getId());
            }
            //Clear TextField
            findId.clear();
            findYear.clear();
        }
        //Nếu chưa điền tên cần nhập
        else{
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập thông tin không đúng");
            alert.setHeaderText("Bạn đang nhập thiếu thông tin tên sinh viên");
            alert.setContentText("Vui lòng nhập lại!!!");
            alert.showAndWait();
        }
    }

    //Phương thức timTheoMSSV: trả về sinh viên có mã số đã nhập ở TextField và in ra table
    public void timTheoMSSV(){
        String id = findId.getText();
        //Nếu đã nhập mã số cần tìm
        if(!findId.getText().trim().isEmpty()){
            //Khai báo một sinh viên tạm
            sinhvien temp;
            //Tìm sinh viên có mã số thỏa mãn
            temp = tatca.searchById(id);
            //Nếu không tìm thấy
            if(temp == null){
                //Thông báo
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Không tim thấy sinh viên");
                alert.setHeaderText("Mã số sinh viên bạn nhập không có sinh viên thỏa mãn");
                alert.setContentText("Vui lòng nhập lại!!!");
                alert.showAndWait();
            }
            //Nếu tìm thấy, thêm sinh viên vào danh sách tim
            tim.add(temp);

            //Hiển thị danh sách ra table
            listFind = FXCollections.observableArrayList(
                    tim.getList()
            );
            nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
            idColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));
            yearColumn2.setCellValueFactory(new PropertyValueFactory<>("year"));
            hinhthuchocColumn2.setCellValueFactory(new PropertyValueFactory<>("TC"));
            sotinchiColumn2.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            gpaColumn2.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            table2.setItems((ObservableList<sinhvien>)listFind);

            //Clear TextField
            findName.clear();
            findYear.clear();
            tim.remove(id);
        }
        //Nếu chưa nhập mã số cần tìm
        else{
            //Thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập thông tin không đúng");
            alert.setHeaderText("Bạn đang nhập thiếu thông tin MSSV sinh viên");
            alert.setContentText("Vui lòng nhập lại!!!");
            alert.showAndWait();
        }
    }

    //Phương thức tìm theo niên khóa: Tìm các sinh viên có niên khóa thỏa mãn vừa nhập rồi in ra table
    public void timTheoYear(){
        String year = findYear.getText();
        //Nếu đã nhập niên khóa cần tìm
        if(!findYear.getText().trim().isEmpty()){
            int y = Integer.parseInt(year);
            //khai báo một danh sách sinh viên tạm
            List<sinhvien> temp;
            //thêm tất cả các sinh viên thỏa mãn vào danh sách tạm
            temp = tatca.searchByYear(y);
            //Nếu không có sinh viên thỏa mãn
            if(temp.size() == 0){
                //Thông báo
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Không tim thấy sinh viên");
                alert.setHeaderText("Niên khóa bạn nhập không có sinh viên thỏa mãn");
                alert.setContentText("Vui lòng nhập lại!!!");
                alert.showAndWait();
            }
            //Nếu có sinh viên thỏa mãn, thêm vào danh sách tim
            for(int i = 0; i < temp.size(); i++){
                sinhvien sv = temp.get(i);
                tim.add(sv);
            }
            //Hiển thị thông tin vừa tìm kiếm  ra màn hình
            listFind = FXCollections.observableArrayList(
                    tim.getList()
            );
            nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
            idColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));
            yearColumn2.setCellValueFactory(new PropertyValueFactory<>("year"));
            hinhthuchocColumn2.setCellValueFactory(new PropertyValueFactory<>("TC"));
            sotinchiColumn2.setCellValueFactory(new PropertyValueFactory<>("soTC"));
            gpaColumn2.setCellValueFactory(new PropertyValueFactory<>("gpa"));
            table2.setItems((ObservableList<sinhvien>)listFind);

            //Clear TextField
            findName.clear();
            findId.clear();

            //Reset danh sách tìm
            for(int i = 0; i < temp.size(); i++){
                sinhvien sv = temp.get(i);
                tim.remove(sv.getId());
            }
        }
        //Nếu chưa nhập niên khóa cần tìm kiếm
        else{
            //Thông báo
            System.out.println("ok");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nhập thông tin không đúng");
            alert.setHeaderText("Bạn đang nhập thiếu thông tin niên khóa sinh viên");
            alert.setContentText("Vui lòng nhập lại!!!");
            alert.showAndWait();
        }
    }

    //Khai báo phương thức initialize
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

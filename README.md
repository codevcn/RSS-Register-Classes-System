# Hệ thống đăng ký môn học cho học sinh, sinh viên

> Website đăng ký môn học cho Học Viện Cơ Sở Ptithcm

## Mục lục:
- [Công nghệ sử dụng](#used-techs)
- [Mẫu giao diện người dùng](#ui-template)
- [Cấu trúc thư mục trong dự án](#folder-structure)
  - [Các thư mục](#folder-structure-folders)
  - [Các file](#folder-structure-files)
- [Quy trình chạy dự án](#app-running-procedure)

## Công nghệ sử dụng: <a name="used-techs"></a>
- Ngôn ngữ lập trình:
  - Java 
- Trình quản lí thư viện bên thứ 3:
  - Maven (mvn)
- Server:
  - Spring Boot
- Client:
  - Reactjs
- Hệ quản trị cơ sở dữ liệu:
  - SQL Server

## Mẫu Giao diện người dùng: <a name="ui-template"></a>
Webstie đăng ký môn học của trường (uis)

## Cấu trúc thư mục trong dự án: <a name="folder-structure"></a>

### Các thư mục: <a name="folder-structure-folders"></a>
- `src`:
  - Đây là thư mục chứa toàn bộ code của dự án, các việc làm như viết code, tạo thư mục mới, tạo file mới, vân vân... đều được thực hiện bên trong thư mục src này
- `configs`:
  - Đây là thư mục chứa các file dùng để cấu hình cho mọi thứ trong dự án, ví dụ: cấu hình CORS, cấu hình Env, vân vân...
- `controllers`:
  - Đây là thư mục chứa các file controller (các trình điều khiển để xử lí các cuộc gọi api)
- `models`:
  - Đây là thư mục chứa các file model (các thực thể cho các đối tượng trong cơ sở dữ liệu)
- `services`:
  - Đây là thư mục chứa các class và method phục vụ cho từng nghiệp vụ cụ thể của ứng dụng
- `utils`:
  - Đây là thư mục chứa các file dùng chung, tức là các file này khai báo các hàm hoặc class để tái sử dụng ở nhiều nơi khác nhau trong dự án 

### Các file: <a name="folder-structure-files"></a>
- file `App.java`:
  - Đây là file gốc của toàn bộ dự án, file này chịu trách nhiệm chạy dự án, tất cả các file ở các thư mục trên sẽ được import vào file này để chạy.
  - File này có thể được xem là file gốc rễ của dự án, là file cao nhất của dự án
- file `application.properties`:
  - Đây là file chứa các dữ liệu nhạy cảm hoặc cấu hình của dự án, ví dụ như thông tin đăng nhập vào cơ sở dữ liệu, các khóa bí mật của thư viện bên thứ 3, vân vân...
- file `pom.xml`:
  - Đây là hồ sơ của dự án (bộ mặt của dự án), tức là file khai báo các thư viện được sử dụng trong dự án, mô tả cho dự án, tên dự án, phiên bản dự án, các cấu hình để chạy dự án trên môi trường development hoặc production, vân vân...
- file `run.cmd`:
  - Đây là file để original command để chạy Spring Boot

## Quy trình chạy dự án: <a name="app-running-procedure"></a>
Khi mọi người kéo code mới nhất từ github về máy, chạy 2 lệnh sau trong CLI (cmd hoặc terminal) và đợi Maven cài xong các phụ thuộc và chạy app:
- cmd:
```
cd ./RSS-Register-Classes-System
run.cmd
```
  - terminal:
```
cd ./RSS-Register-Classes-System
./run
```
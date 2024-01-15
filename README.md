# Hệ thống đăng ký môn học cho học sinh, sinh viên

> Hệ thống được xây dựng trên nền tảng web

## Mục lục:
- [Công nghệ sử dụng](#used-techs)
- [Cấu trúc thư mục trong dự án](#folder-structure)
  - [Các thư mục](#folder-structure-folders)
  - [Các file](#folder-structure-files)
- [Quy trình chạy dự án](#app-running-procedure)

## Công nghệ sử dụng: <a name="used-techs"></a>
- Ngôn ngữ lập trình:
  - Java (Spring Framework)
- Trình quản lí thư viện bên thứ 3:
  - Maven (mvn)
- Server:
  - Spring MVC
- Client:
  - *Unkown*
- Hệ quản trị cơ sở dữ liệu:
  - NoSQL (MongoDB)

## Cấu trúc thư mục trong dự án: <a name="folder-structure"></a>

### Các thư mục: <a name="folder-structure-folders"></a>
- `src`:
  - Đây là thư mục chứa toàn bộ code của dự án, các việc làm như viết code, tạo thư mục mới, tạo file mới, vân vân... đều được thực hiện bên trong thư mục src này
- `configs`:
  - Đây là thư mục chứa các file dùng để cấu hình cho mọi thứ trong dự án, ví dụ: cấu hình kết nối đến DB, cấu hình để sử dụng thư viện bên thứ 3, vân vân...
- `controllers`:
  - Đây là thư mục chứa các file controller (các trình điều khiển để xử lí các cuộc gọi api)
- `models`:
  - Đây là thư mục chứa các file model (các thực thể cho các đối tượng trong cơ sở dữ liệu)
- `routes`:
  - Đây là thư mục chứa các file tuyến đường, tức là các tuyến đường api (hay còn gọi là api route), các tuyến đường này sẽ là nơi để client gọi api vào, và tại điểm cuối của tuyến đường thì controller sẽ xử lí cuộc gọi api
- `services`:
  - Đây là thư mục chứa các hàm được dùng trong controller
  - Mình ko nên viết mã hết vào các controller mà mình cố gắng tách mã ra thành các hàm nếu có thể và để nó vào các file trong thư mục services
- `utils`:
  - Đây là thư mục chứa các file dùng chung, tức là các file này khai báo các hàm hoặc class để tái sử dụng ở nhiều nơi khác nhau trong dự án 
  - Thư mục này khác với thư mục services vì thư mục services chỉ dùng để đựng các hàm được tách ra từ việc viết code ở controller
- `views` (thư mục này có thể bị xóa hoặc thay đổi trong tương lai):
  - Đây là thư mục chứa tất cả thành phần UI của dự án này bao gồm các view, các component, và hơn thế nữa...
  - Người làm Frontend sẽ để các file UI vào thư mục này

### Các file: <a name="folder-structure-files"></a>
- file `App.java` ở thư mục src:
  - Đây là file gốc của toàn bộ dự án, file này chịu trách nhiệm chạy dự án, tất cả các file ở các thư mục trên sẽ được import vào file này để chạy.
  - File index.js này có thể được xem là file gốc rễ của dự án, là file cao nhất của dự án
- file `.env`:
  - Đây là file chứa các dữ liệu nhạy cảm của dự án, ví dụ như thông tin đăng nhập vào cơ sở dữ liệu, các khóa bí mật của thư viện bên thứ 3, vân vân...
- file `pom.xml`:
  - Đây là hồ sơ của dự án (bộ mặt của dự án), tức là file khai báo các thư viện được sử dụng trong dự án, mô tả cho dự án, tên dự án, phiên bản dự án, các cấu hình để chạy dự án trên môi trường development hoặc production, vân vân...

## Quy trình chạy dự án: <a name="app-running-procedure"></a>
Khi mọi người kéo code mới nhất từ github về máy, chạy 2 lệnh sau trong CLI (cmd hoặc terminal) và đợi Maven cài xong các phụ thuộc và chạy app:
- cmd:
```
cd ../RSS
run.cmd
```
  - terminal:
```
cd ../RSS
./run
```
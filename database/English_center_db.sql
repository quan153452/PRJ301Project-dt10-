IF DB_ID('English_Center') IS NOT NULL
DROP DATABASE English_Center
GO

CREATE DATABASE English_Center
GO

USE English_Center
GO

CREATE TABLE GiangVien (
    MaGV INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(15),
    Email VARCHAR(100),
    TrinhDo NVARCHAR(50)
)

CREATE TABLE HocSinh (
    MaHS INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(100) NOT NULL,
    NgaySinh DATE,
    SoDienThoai VARCHAR(15),
    Email VARCHAR(100)
)

CREATE TABLE KhoaHoc (
    MaKhoaHoc INT PRIMARY KEY IDENTITY(1,1),
    TenKhoaHoc NVARCHAR(100) NOT NULL,
    DiemSanDauVao DECIMAL(2,1),
    DiemTranDauVao DECIMAL(2,1),
    HocPhi DECIMAL(18,2),
    ThoiLuongBuoi INT
)

CREATE TABLE LopHoc (
    MaLop INT PRIMARY KEY IDENTITY(1,1),
    TenLop NVARCHAR(50) NOT NULL,
    MaKhoaHoc INT,
    MaGV INT,
    NgayKhaiGiang DATE,
    TrangThai NVARCHAR(20),
    FOREIGN KEY (MaKhoaHoc) REFERENCES KhoaHoc(MaKhoaHoc),
    FOREIGN KEY (MaGV) REFERENCES GiangVien(MaGV)
)

CREATE TABLE DangKy (
    MaHS INT,
    MaLop INT,
    NgayDangKy DATE DEFAULT GETDATE(),
    PRIMARY KEY (MaHS, MaLop),
    FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS),
    FOREIGN KEY (MaLop) REFERENCES LopHoc(MaLop)
)

CREATE TABLE LichHoc (
    MaLich INT PRIMARY KEY IDENTITY(1,1),
    MaLop INT,
    ThuTrongTuan NVARCHAR(20),
    GioBatDau TIME,
    GioKetThuc TIME,
    PhongHoc NVARCHAR(20),
    FOREIGN KEY (MaLop) REFERENCES LopHoc(MaLop)
)

INSERT INTO GiangVien VALUES
(N'Nguyen Van Minh','0901111111','minh@center.com',N'IELTS 8.0'),
(N'Tran Thi Lan','0902222222','lan@center.com',N'TESOL')

INSERT INTO HocSinh VALUES
(N'Le Minh Anh','2008-05-12','0910000001','a@gmail.com'),
(N'Tran Hoang Nam','2007-09-22','0910000002','b@gmail.com'),
(N'Nguyen Thu Trang','2008-01-15','0910000003','c@gmail.com'),
(N'Pham Gia Bao','2007-11-30','0910000004','d@gmail.com'),
(N'Do Khanh Linh','2008-03-10','0910000005','e@gmail.com'),
(N'Hoang Minh Duc','2007-12-02','0910000006','f@gmail.com'),
(N'Vu Thanh Huyen','2008-07-18','0910000007','g@gmail.com'),
(N'Bui Quang Huy','2007-04-21','0910000008','h@gmail.com'),
(N'Nguyen Phuong Thao','2008-02-28','0910000009','i@gmail.com'),
(N'Dang Tuan Kiet','2007-08-16','0910000010','k@gmail.com')

INSERT INTO KhoaHoc VALUES
(N'IELTS Foundation',0,4,3500000,24),
(N'IELTS Intermediate',4,6,4500000,24)

INSERT INTO LopHoc VALUES
(N'IELTS-F01',1,1,'2026-04-01',N'Dang day'),
(N'IELTS-I01',2,2,'2026-04-05',N'Dang day')

INSERT INTO DangKy VALUES
(1,1,GETDATE()),
(2,1,GETDATE()),
(3,1,GETDATE()),
(4,1,GETDATE()),
(5,1,GETDATE()),
(6,2,GETDATE()),
(7,2,GETDATE()),
(8,2,GETDATE()),
(9,2,GETDATE()),
(10,2,GETDATE())

INSERT INTO LichHoc VALUES
(1,N'Thu 2','18:00','20:00',N'A101'),
(1,N'Thu 4','18:00','20:00',N'A101'),
(2,N'Thu 3','18:00','20:00',N'B201'),
(2,N'Thu 5','18:00','20:00',N'B201')

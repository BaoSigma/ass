create database Duan;
go;
use Duan;
go;
﻿Create table Nhanvien(
maNV VARCHAR(5) PRIMARY KEY,
hoTen NVARCHAR(50),
namSinh DATE,
sdt varchar(10),
email varchar(355),
matKhau varchar(100),
chucVu nvarchar(50))


CREATE TABLE Calam(
maCL nvarchar(30) primary key,
maNV VARCHAR(5) FOREIGN KEY REFERENCES Nhanvien(maNV),
hoTenNV NVARCHAR(50))

CREATE TABLE Hoadon(
maHD VARCHAR(5) PRIMARY KEY,
maNV VARCHAR(5) FOREIGN KEY REFERENCES Nhanvien(maNV),
ghiChu NVARCHAR(50),
ngayTao DATE,
)

CREATE TABLE TheDD(
ID int identity(1,1) PRIMARY KEY,
trangThai nvarchar(50),
maHD VARCHAR(5) FOREIGN KEY REFERENCES Hoadon(maHD)
)

CREATE TABLE LoaiSanPham(
maLSP int identity(1,1) primary key,
tenLDU NVARCHAR(50))

CREATE TABLE SanPham(
maSP VARCHAR(5) PRIMARY KEY,
maLSP int FOREIGN KEY REFERENCES LoaiSanPham(maLSP),
tenDU nvarchar(50),
giaDU money)

CREATE TABLE Chitiethoadon (
maCT int identity(1,1) primary key,
maHD VARCHAR(5) FOREIGN KEY REFERENCES Hoadon(maHD),
maSP VARCHAR(5) FOREIGN KEY REFERENCES SanPham(maSP),
soLuong int,
giaTien money
)
CREATE PROCEDURE Insert_NhanVien
    @hoTen NVARCHAR(50),
    @namSinh DATE,
    @sdt VARCHAR(10),
    @email VARCHAR(355),
    @matKhau VARCHAR(100),
    @chucVu NVARCHAR(50)
AS
BEGIN
    DECLARE @newMaNV VARCHAR(5)
    DECLARE @lastNum INT

    SELECT @lastNum = 
        CAST(SUBSTRING(MAX(maNV), 3, 3) AS INT)
    FROM Nhanvien

    SET @lastNum = ISNULL(@lastNum, 0) + 1
    SET @newMaNV = 'NV' + RIGHT('000' + CAST(@lastNum AS VARCHAR), 3)

    INSERT INTO Nhanvien(maNV, hoTen, namSinh, sdt, email, matKhau, chucVu)
    VALUES (@newMaNV, @hoTen, @namSinh, @sdt, @email, @matKhau, @chucVu)
END

CREATE PROCEDURE Insert_Calam
    @maCL NVARCHAR(30),
    @maNV VARCHAR(5),
    @hoTenNV NVARCHAR(50)
AS
BEGIN
    INSERT INTO Calam(maCL, maNV, hoTenNV)
    VALUES (@maCL, @maNV, @hoTenNV)
END

CREATE PROCEDURE Insert_HoaDon
    @maNV VARCHAR(5),
    @ghiChu NVARCHAR(50),
    @ngayTao DATE
AS
BEGIN
    DECLARE @newMaHD VARCHAR(5)
    DECLARE @lastNum INT

    SELECT @lastNum = 
        CAST(SUBSTRING(MAX(maHD), 3, 3) AS INT)
    FROM Hoadon

    SET @lastNum = ISNULL(@lastNum, 0) + 1
    SET @newMaHD = 'HD' + RIGHT('000' + CAST(@lastNum AS VARCHAR), 3)

    INSERT INTO Hoadon(maHD, maNV, ghiChu, ngayTao)
    VALUES (@newMaHD, @maNV, @ghiChu, @ngayTao)
END

CREATE PROCEDURE Insert_TheDD
    @trangThai NVARCHAR(50),
    @maHD VARCHAR(5)
AS
BEGIN
    INSERT INTO TheDD(trangThai, maHD)
    VALUES (@trangThai, @maHD)
END

CREATE PROCEDURE Insert_LoaiSanPham
    @tenLDU NVARCHAR(50)
AS
BEGIN
    INSERT INTO LoaiSanPham(tenLDU)
    VALUES (@tenLDU)
END

CREATE PROCEDURE Insert_SanPham
    @maLSP INT,
    @tenDU NVARCHAR(50),
    @giaDU MONEY
AS
BEGIN
    DECLARE @newMaSP VARCHAR(5)
    DECLARE @lastNum INT

    SELECT @lastNum = 
        CAST(SUBSTRING(MAX(maSP), 3, 3) AS INT)
    FROM SanPham

    SET @lastNum = ISNULL(@lastNum, 0) + 1
    SET @newMaSP = 'SP' + RIGHT('000' + CAST(@lastNum AS VARCHAR), 3)

    INSERT INTO SanPham(maSP, maLSP, tenDU, giaDU)
    VALUES (@newMaSP, @maLSP, @tenDU, @giaDU)
END

CREATE PROCEDURE Insert_ChiTietHoaDon
    @maHD VARCHAR(5),
    @maSP VARCHAR(5),
    @soLuong INT,
    @giaTien MONEY
AS
BEGIN
    INSERT INTO Chitiethoadon(maHD, maSP, soLuong, giaTien)
    VALUES (@maHD, @maSP, @soLuong, @giaTien)
END

EXEC Insert_NhanVien N'Nguyễn Văn A', '1990-01-01', '0911111111', 'a@gmail.com', 'mk123', N'Quản lý';
EXEC Insert_NhanVien N'Lê Thị B', '1992-05-12', '0922222222', 'b@gmail.com', 'mk456', N'Nhân viên';
EXEC Insert_NhanVien N'Trần Văn C', '1995-07-23', '0933333333', 'c@gmail.com', 'mk789', N'Nhân viên';
EXEC Insert_NhanVien N'Phạm Thị D', '1998-09-15', '0944444444', 'd@gmail.com', 'mk101', N'Nhân viên';
EXEC Insert_NhanVien N'Huỳnh Văn E', '2000-12-30', '0955555555', 'e@gmail.com', 'mk202', N'Phục vụ';

EXEC Insert_Calam N'CL001', 'NV001', N'Nguyễn Văn A';
EXEC Insert_Calam N'CL002', 'NV002', N'Lê Thị B';
EXEC Insert_Calam N'CL003', 'NV003', N'Trần Văn C';
EXEC Insert_Calam N'CL004', 'NV004', N'Phạm Thị D';
EXEC Insert_Calam N'CL005', 'NV005', N'Huỳnh Văn E';

EXEC Insert_HoaDon 'NV001', N'Hóa đơn bán hàng 1', '2025-05-30';
EXEC Insert_HoaDon 'NV002', N'Hóa đơn bán hàng 2', '2025-05-30';
EXEC Insert_HoaDon 'NV003', N'Hóa đơn bán hàng 3', '2025-05-31';
EXEC Insert_HoaDon 'NV004', N'Hóa đơn bán hàng 4', '2025-05-31';
EXEC Insert_HoaDon 'NV005', N'Hóa đơn bán hàng 5', '2025-05-31';

EXEC Insert_HoaDon 'NV001', N'Hóa đơn bán hàng 1', '2025-05-30';
EXEC Insert_HoaDon 'NV002', N'Hóa đơn bán hàng 2', '2025-05-30';
EXEC Insert_HoaDon 'NV003', N'Hóa đơn bán hàng 3', '2025-05-31';
EXEC Insert_HoaDon 'NV004', N'Hóa đơn bán hàng 4', '2025-05-31';
EXEC Insert_HoaDon 'NV005', N'Hóa đơn bán hàng 5', '2025-05-31';

EXEC Insert_TheDD N'Đã điểm danh', 'HD001';
EXEC Insert_TheDD N'Chưa điểm danh', 'HD002';
EXEC Insert_TheDD N'Đã điểm danh', 'HD003';
EXEC Insert_TheDD N'Chưa điểm danh', 'HD004';
EXEC Insert_TheDD N'Đã điểm danh', 'HD005';

EXEC Insert_LoaiSanPham N'Nước ngọt';
EXEC Insert_LoaiSanPham N'Cà phê';
EXEC Insert_LoaiSanPham N'Trà sữa';
EXEC Insert_LoaiSanPham N'Sinh tố';
EXEC Insert_LoaiSanPham N'Nước suối';

EXEC Insert_SanPham 1, N'Coca Cola', 15000;
EXEC Insert_SanPham 2, N'Cà phê sữa đá', 18000;
EXEC Insert_SanPham 3, N'Trà sữa trân châu', 25000;
EXEC Insert_SanPham 4, N'Sinh tố xoài', 30000;
EXEC Insert_SanPham 5, N'LaVie', 10000;

EXEC Insert_ChiTietHoaDon 'HD001', 'SP001', 2, 30000;
EXEC Insert_ChiTietHoaDon 'HD002', 'SP002', 1, 18000;
EXEC Insert_ChiTietHoaDon 'HD003', 'SP003', 3, 75000;
EXEC Insert_ChiTietHoaDon 'HD004', 'SP004', 1, 30000;
EXEC Insert_ChiTietHoaDon 'HD005', 'SP005', 5, 50000;


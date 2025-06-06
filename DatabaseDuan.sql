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
ID int identity(1,1) primary key,
Buoi NVARCHAR(30),
maNV VARCHAR(5) FOREIGN KEY REFERENCES Nhanvien(maNV),
hoTen NVARCHAR(50))

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
giaDU float)

CREATE TABLE Chitiethoadon (
maCT int identity(1,1) primary key,
maHD VARCHAR(5) FOREIGN KEY REFERENCES Hoadon(maHD),
maSP VARCHAR(5) FOREIGN KEY REFERENCES SanPham(maSP),
soLuong int,
giaTien float
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

EXEC Insert_NhanVien 
    @hoTen = ?, 
    @namSinh = ?, 
    @sdt = ?, 
    @email = ?, 
    @matKhau = ?, 
    @chucVu = ?

EXEC Insert_Calam 
    @maCL = ?, 
    @maNV = ?, 
    @hoTenNV = ?

EXEC Insert_HoaDon 
    @maNV = ?, 
    @ghiChu = ?, 
    @ngayTao = ?

EXEC Insert_TheDD 
    @trangThai = ?, 
    @maHD = ?

EXEC Insert_LoaiSanPham 
    @tenLDU = ?

EXEC Insert_SanPham 
    @maLSP = ?, 
    @tenDU = ?, 
    @giaDU = ?

EXEC Insert_ChiTietHoaDon 
    @maHD = ?, 
    @maSP = ?, 
    @soLuong = ?, 
    @giaTien = ?


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

CREATE TABLE Doanhthu(
ID int identity(1,1),
maNV VARCHAR(5) FOREIGN KEY REFERENCES Nhanvien(maNV),
soDonDaTao NVARCHAR(30),
tongThuNhap money)

CREATE TABLE Calam(
buoi nvarchar(30) primary key,
maNV VARCHAR(5) FOREIGN KEY REFERENCES Nhanvien(maNV),
hoTenNV NVARCHAR(50))

CREATE TABLE Hoadon(
maHD VARCHAR(5) PRIMARY KEY,
maNV VARCHAR(5) FOREIGN KEY REFERENCES Nhanvien(maNV),
ghiChu NVARCHAR(50),
ngayTao DATE,
thanhTien money)

CREATE TABLE TheDD(
ID int identity(1,1) PRIMARY KEY,
trangThai nvarchar(50),
maHD VARCHAR(5) FOREIGN KEY REFERENCES Hoadon(maHD)
)

CREATE TABLE LoaiDoUong(
maLDU int identity(1,1) primary key,
tenLDU NVARCHAR(50))

CREATE TABLE DoUong(
maDU VARCHAR(5) PRIMARY KEY,
maLDU int FOREIGN KEY REFERENCES LoaiDoUong(maLDU),
tenDU nvarchar(50),
giaDU money)

CREATE TABLE Chitiethoadon (
maCT int identity(1,1) primary key,
maHD VARCHAR(5) FOREIGN KEY REFERENCES Hoadon(maHD),
maDU VARCHAR(5) FOREIGN KEY REFERENCES DoUong(maDU),
soLuong int,
giaTien money,
thanhTien money)

CREATE PROCEDURE ThemNhanVien
    @hoTen NVARCHAR(50),
    @namSinh DATE,
    @sdt VARCHAR(10),
    @email VARCHAR(355),
    @matKhau VARCHAR(100),
    @chucVu NVARCHAR(50)
AS
BEGIN
    DECLARE @maCuoi VARCHAR(5)
    DECLARE @soThuTu INT
    DECLARE @maNVmoi VARCHAR(5)

    -- Lấy mã cuối cùng (cao nhất) từ bảng Nhanvien
    SELECT @maCuoi = MAX(maNV) FROM Nhanvien

    -- Tách số từ mã cũ
    IF @maCuoi IS NULL
        SET @soThuTu = 1
    ELSE
        SET @soThuTu = CAST(SUBSTRING(@maCuoi, 3, 3) AS INT) + 1

    -- Ghép mã mới dạng NV001
    SET @maNVmoi = 'NV' + RIGHT('000' + CAST(@soThuTu AS VARCHAR), 3)

    -- Thêm dòng mới
    INSERT INTO Nhanvien (maNV, hoTen, namSinh, sdt, email, matKhau, chucVu)
    VALUES (@maNVmoi, @hoTen, @namSinh, @sdt, @email, @matKhau, @chucVu)
END



	CREATE PROCEDURE ThemHoaDon
    @maNV VARCHAR(5),
    @ghiChu NVARCHAR(50),
    @ngayTao DATE,
    @thanhTien MONEY
AS
BEGIN
    DECLARE @maCuoi VARCHAR(5);
    DECLARE @soThuTu INT;
    DECLARE @maHDmoi VARCHAR(5);

    -- Lấy mã hóa đơn cuối cùng
    SELECT @maCuoi = MAX(maHD) FROM Hoadon;

    -- Tính số tiếp theo
    IF @maCuoi IS NULL
        SET @soThuTu = 1;
    ELSE
        SET @soThuTu = CAST(SUBSTRING(@maCuoi, 3, 3) AS INT) + 1;

    -- Sinh mã mới dạng HD001
    SET @maHDmoi = 'HD' + RIGHT('000' + CAST(@soThuTu AS VARCHAR), 3);

    -- Thêm hóa đơn mới
    INSERT INTO Hoadon (maHD, maNV, ghiChu, ngayTao, thanhTien)
    VALUES (@maHDmoi, @maNV, @ghiChu, @ngayTao, @thanhTien);
END;



	CREATE PROCEDURE ThemDoanhThu
    @maNV VARCHAR(5),
    @soDonDaTao NVARCHAR(30),
    @tongThuNhap MONEY
AS
BEGIN
    INSERT INTO Doanhthu(maNV, soDonDaTao, tongThuNhap)
    VALUES (@maNV, @soDonDaTao, @tongThuNhap);
END;

CREATE PROCEDURE ThemCaLam
    @buoi NVARCHAR(30),
    @maNV VARCHAR(5),
    @hoTenNV NVARCHAR(50)
AS
BEGIN
    INSERT INTO Calam(buoi, maNV, hoTenNV)
    VALUES (@buoi, @maNV, @hoTenNV);
END;

CREATE PROCEDURE ThemTheDD
    @trangThai NVARCHAR(50),
    @maHD VARCHAR(5)
AS
BEGIN
    INSERT INTO TheDD(trangThai, maHD)
    VALUES (@trangThai, @maHD);
END;

CREATE PROCEDURE ThemLoaiDoUong
    @tenLDU NVARCHAR(50)
AS
BEGIN
    INSERT INTO LoaiDoUong(tenLDU)
    VALUES (@tenLDU);
END;

CREATE PROCEDURE ThemDoUong
    @maDU VARCHAR(5),
    @maLDU INT,
    @tenDU NVARCHAR(50),
    @giaDU MONEY
AS
BEGIN
    INSERT INTO DoUong(maDU, maLDU, tenDU, giaDU)
    VALUES (@maDU, @maLDU, @tenDU, @giaDU);
END;

CREATE PROCEDURE ThemChiTietHoaDon
    @maHD VARCHAR(5),
    @maDU VARCHAR(5),
    @soLuong MONEY,
    @giaTien MONEY,
    @thanhTien MONEY
AS
BEGIN
    INSERT INTO Chitiethoadon(maHD, maDU, soLuong, giaTien, thanhTien)
    VALUES (@maHD, @maDU, @soLuong, @giaTien, @thanhTien);
END;

EXEC ThemDoanhThu 
    @maNV = '?',
    @soDonDaTao = '?',
    @tongThuNhap = '?';

	EXEC ThemCaLam
    @buoi = '?',
    @maNV = '?',
    @hoTenNV = '?';


	EXEC ThemTheDD
    @trangThai ='?',
    @maHD = '?',
    @soLuong = '?',
    @giaTien = '?',
    @thanhTien = '?';

	EXEC ThemLoaiDoUong
    @tenLDU = '?';

	EXEC ThemDoUong
    @maDU = '?',
    @maLDU = '?',
    @tenDU = '?',
    @giaDU = '?';

	EXEC ThemChiTietHoaDon
    @maHD = '?',
    @maDU = '?',
    @soLuong = '?',
    @giaTien = '?',
    @thanhTien = '?';

	EXEC ThemNhanVien 
    @hoTen = '?',
    @namSinh ='?',
    @sdt = '?',
    @email = '?',
    @matKhau = '?',
    @chucVu = '?';

	EXEC ThemHoaDon 
	@maNV = '?',
    @ghiChu = '?',
    @ngayTao = '?',
    @thanhTien = '?';

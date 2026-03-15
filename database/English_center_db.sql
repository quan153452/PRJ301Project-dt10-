IF DB_ID('English_Center') IS NOT NULL
DROP DATABASE English_Center
GO

CREATE DATABASE English_Center
GO

USE English_Center
GO

CREATE TABLE Accounts(
    AccountID INT PRIMARY KEY IDENTITY(1,1),
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Role VARCHAR(20) CHECK (Role IN ('admin','teacher','student'))
)
GO

CREATE TABLE Teachers(
    TeacherID INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(100) NOT NULL,
    Phone VARCHAR(15),
    Email VARCHAR(100),
    Qualification NVARCHAR(50),
    AccountID INT UNIQUE,
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID) ON DELETE CASCADE
)
GO

CREATE TABLE Students(
    StudentID INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(100) NOT NULL,
    BirthDate DATE,
    Phone VARCHAR(15),
    Email VARCHAR(100),
    AccountID INT UNIQUE,
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID) ON DELETE CASCADE
)
GO

CREATE TABLE Courses(
    CourseID INT PRIMARY KEY IDENTITY(1,1),
    CourseName NVARCHAR(100) NOT NULL,
    MinEntryScore DECIMAL(3,1),
    MaxEntryScore DECIMAL(3,1),
    TuitionFee DECIMAL(18,2),
    TotalLessons INT
)
GO

CREATE TABLE PlacementTests(
    TestID INT PRIMARY KEY IDENTITY(1,1),
    StudentID INT,
    TestDate DATE DEFAULT GETDATE(),
    OverallScore DECIMAL(3,1),
    SuggestedCourseID INT,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE,
    FOREIGN KEY (SuggestedCourseID) REFERENCES Courses(CourseID)
)
GO

CREATE TABLE Classes(
    ClassID INT PRIMARY KEY IDENTITY(1,1),
    ClassName NVARCHAR(50),
    CourseID INT,
    TeacherID INT,
    StartDate DATE,
    Status NVARCHAR(20) CHECK (Status IN (N'Dang day',N'Hoan thanh',N'Huy')),
    FOREIGN KEY (CourseID) REFERENCES Courses(CourseID),
    FOREIGN KEY (TeacherID) REFERENCES Teachers(TeacherID)
)
GO

CREATE TABLE Enrollments(
    StudentID INT,
    ClassID INT,
    EnrollmentDate DATE DEFAULT GETDATE(),
    PRIMARY KEY (StudentID,ClassID),
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE,
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID) ON DELETE CASCADE
)
GO

CREATE TABLE Schedules(
    ScheduleID INT PRIMARY KEY IDENTITY(1,1),
    ClassID INT,
    DayOfWeek NVARCHAR(20),
    StartTime TIME,
    EndTime TIME,
    Room NVARCHAR(20),
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID) ON DELETE CASCADE
)
GO

CREATE TABLE Attendance(
    AttendanceID INT PRIMARY KEY IDENTITY(1,1),
    ClassID INT,
    StudentID INT,
    StudyDate DATE,
    Status NVARCHAR(20) CHECK (Status IN (N'Present',N'Absent',N'Late')),
    Note NVARCHAR(200),
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID) ON DELETE CASCADE,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE
)
GO

CREATE TABLE Payments(
    PaymentID INT PRIMARY KEY IDENTITY(1,1),
    StudentID INT,
    ClassID INT,
    Amount DECIMAL(18,2),
    PaymentDate DATE DEFAULT GETDATE(),
    Status NVARCHAR(20) CHECK (Status IN (N'Paid',N'Unpaid')),
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID) ON DELETE CASCADE,
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID) ON DELETE CASCADE
)
GO


INSERT INTO Accounts VALUES
('admin','123','admin'),
('teacher1','123','teacher'),
('teacher2','123','teacher'),
('student1','123','student'),
('student2','123','student'),
('student3','123','student'),
('student4','123','student'),
('student5','123','student'),
('student6','123','student'),
('student7','123','student'),
('student8','123','student'),
('student9','123','student'),
('student10','123','student')
GO

INSERT INTO Teachers VALUES
(N'Nguyen Van Minh','0901111111','minh@center.com',N'IELTS 8.0',2),
(N'Tran Thi Lan','0902222222','lan@center.com',N'TESOL',3)
GO

INSERT INTO Students VALUES
(N'Le Minh Anh','2008-05-12','0910000001','a@gmail.com',4),
(N'Tran Hoang Nam','2007-09-22','0910000002','b@gmail.com',5),
(N'Nguyen Thu Trang','2008-01-15','0910000003','c@gmail.com',6),
(N'Pham Gia Bao','2007-11-30','0910000004','d@gmail.com',7),
(N'Do Khanh Linh','2008-03-10','0910000005','e@gmail.com',8),
(N'Hoang Minh Duc','2007-12-02','0910000006','f@gmail.com',9),
(N'Vu Thanh Huyen','2008-07-18','0910000007','g@gmail.com',10),
(N'Bui Quang Huy','2007-04-21','0910000008','h@gmail.com',11),
(N'Nguyen Phuong Thao','2008-02-28','0910000009','i@gmail.com',12),
(N'Dang Tuan Kiet','2007-08-16','0910000010','k@gmail.com',13)
GO

INSERT INTO Courses VALUES
(N'IELTS Foundation',0,4,3500000,24),
(N'IELTS Intermediate',4,6,4500000,24)
GO

INSERT INTO Classes VALUES
(N'IELTS-F01',1,1,'2026-04-01',N'Dang day'),
(N'IELTS-I01',2,2,'2026-04-05',N'Dang day')
GO

INSERT INTO Enrollments VALUES
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
GO

INSERT INTO Schedules VALUES
(1,N'Thu 2','18:00','20:00',N'A101'),
(1,N'Thu 4','18:00','20:00',N'A101'),
(2,N'Thu 3','18:00','20:00',N'B201'),
(2,N'Thu 5','18:00','20:00',N'B201')
GO

SELECT * FROM Accounts
SELECT * FROM Teachers
SELECT * FROM Students
SELECT * FROM Classes
GO

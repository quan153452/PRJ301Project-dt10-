IF DB_ID('English_Center') IS NOT NULL
DROP DATABASE English_Center
GO

CREATE DATABASE English_Center
GO

USE English_Center
GO

CREATE TABLE Teachers(
TeacherID INT PRIMARY KEY IDENTITY(1,1),
FullName NVARCHAR(100) NOT NULL,
Phone VARCHAR(15),
Email VARCHAR(100),
Qualification NVARCHAR(50)
)

CREATE TABLE Students(
StudentID INT PRIMARY KEY IDENTITY(1,1),
FullName NVARCHAR(100) NOT NULL,
BirthDate DATE,
Phone VARCHAR(15),
Email VARCHAR(100)
)

CREATE TABLE Courses(
CourseID INT PRIMARY KEY IDENTITY(1,1),
CourseName NVARCHAR(100),
MinEntryScore DECIMAL(2,1),
MaxEntryScore DECIMAL(2,1),
TuitionFee DECIMAL(18,2),
TotalLessons INT
)

CREATE TABLE PlacementTests(
TestID INT PRIMARY KEY IDENTITY(1,1),
StudentID INT,
TestDate DATE DEFAULT GETDATE(),
OverallScore DECIMAL(2,1),
SuggestedCourseID INT,
FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
FOREIGN KEY (SuggestedCourseID) REFERENCES Courses(CourseID)
)

CREATE TABLE Classes(
ClassID INT PRIMARY KEY IDENTITY(1,1),
ClassName NVARCHAR(50),
CourseID INT,
TeacherID INT,
StartDate DATE,
Status NVARCHAR(20),
FOREIGN KEY (CourseID) REFERENCES Courses(CourseID),
FOREIGN KEY (TeacherID) REFERENCES Teachers(TeacherID)
)

CREATE TABLE Enrollments(
StudentID INT,
ClassID INT,
EnrollmentDate DATE DEFAULT GETDATE(),
PRIMARY KEY (StudentID,ClassID),
FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
FOREIGN KEY (ClassID) REFERENCES Classes(ClassID)
)

CREATE TABLE Schedules(
ScheduleID INT PRIMARY KEY IDENTITY(1,1),
ClassID INT,
DayOfWeek NVARCHAR(20),
StartTime TIME,
EndTime TIME,
Room NVARCHAR(20),
FOREIGN KEY (ClassID) REFERENCES Classes(ClassID)
)

CREATE TABLE Attendance(
AttendanceID INT PRIMARY KEY IDENTITY(1,1),
ClassID INT,
StudentID INT,
StudyDate DATE,
Status NVARCHAR(20),
Note NVARCHAR(200),
FOREIGN KEY (ClassID) REFERENCES Classes(ClassID),
FOREIGN KEY (StudentID) REFERENCES Students(StudentID)
)

CREATE TABLE Accounts(
AccountID INT PRIMARY KEY IDENTITY(1,1),
Username VARCHAR(50),
Password VARCHAR(100),
Role VARCHAR(20),
TeacherID INT,
StudentID INT,
FOREIGN KEY (TeacherID) REFERENCES Teachers(TeacherID),
FOREIGN KEY (StudentID) REFERENCES Students(StudentID)
)

CREATE TABLE Payments(
PaymentID INT PRIMARY KEY IDENTITY(1,1),
StudentID INT,
ClassID INT,
Amount DECIMAL(18,2),
PaymentDate DATE,
Status NVARCHAR(20),
FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
FOREIGN KEY (ClassID) REFERENCES Classes(ClassID)
)

INSERT INTO Teachers VALUES
(N'Nguyen Van Minh','0901111111','minh@center.com',N'IELTS 8.0'),
(N'Tran Thi Lan','0902222222','lan@center.com',N'TESOL')

INSERT INTO Students VALUES
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

INSERT INTO Courses VALUES
(N'IELTS Foundation',0,4,3500000,24),
(N'IELTS Intermediate',4,6,4500000,24)

INSERT INTO Classes VALUES
(N'IELTS-F01',1,1,'2026-04-01',N'Dang day'),
(N'IELTS-I01',2,2,'2026-04-05',N'Dang day')

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

INSERT INTO Schedules VALUES
(1,N'Thu 2','18:00','20:00',N'A101'),
(1,N'Thu 4','18:00','20:00',N'A101'),
(2,N'Thu 3','18:00','20:00',N'B201'),
(2,N'Thu 5','18:00','20:00',N'B201')

INSERT INTO Accounts VALUES
('admin','123','admin',NULL,NULL),
('teacher1','123','teacher',1,NULL),
('teacher2','123','teacher',2,NULL)

SELECT * FROM Teachers
SELECT * FROM Students
SELECT * FROM Courses
SELECT * FROM Classes
SELECT * FROM Enrollments
SELECT * FROM Schedules
SELECT * FROM Accounts

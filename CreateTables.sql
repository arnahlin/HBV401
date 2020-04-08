CREATE TABLE IF NOT EXISTS Guest( 
name VARCHAR(100),
kennitala VARCHAR(12) PRIMARY KEY,
reservationID INT
);

CREATE TABLE IF NOT EXISTS Hotel( 
hotelID INT PRIMARY KEY,
name VARCHAR(100), 
location VARCHAR(50),
rooms INT
);

CREATE TABLE IF NOT EXISTS Room( 
roomID INT,
hotelID INT, 
price INT, 
booked char(1),
available char(1),
PRIMARY KEY(roomID, hotelID),
FOREIGN KEY(hotelID) REFERENCES HOTEL(hotelID)
);

CREATE TABLE IF NOT EXISTS Reservation( 
reservationID INT,
guestName VARCHAR(100) REFERENCES Guest(name),
checkIn DATE,
checkOut DATE,
roomID INT,
PRIMARY KEY(reservationID),
FOREIGN KEY(roomID) REFERENCES Room(roomID)
);

.mode column
.headers on
.tables

SELECT * FROM Guest;
SELECT * FROM Hotel;
SELECT * FROM Room;
SELECT * FROM Reservation;


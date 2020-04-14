CREATE TABLE IF NOT EXISTS Guest( 
name VARCHAR(100),
kennitala VARCHAR(12),
reservationID INT,
PRIMARY KEY(kennitala, reservationID),
FOREIGN KEY(reservationID) REFERENCES Reservation(reservationID)
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
available char(1),
roomType VARCHAR(6),
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


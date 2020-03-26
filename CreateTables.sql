CREATE TABLE IF NOT EXISTS Guest( 
name VARCHAR(100),
kennitala VARCHAR(12) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Hotel( 
hotelID INT PRIMARY KEY,
name VARCHAR(100), 
location VARCHAR(50),
rooms INT
);

CREATE TABLE IF NOT EXISTS Room( 
roomID INT,
hotelNr INT REFERENCES Hotel(hotelID), 
price INT, 
booked char(1),
available char(1),
PRIMARY KEY(roomID, hotelNr),
FOREIGN KEY(hotelNr) REFERENCES HOTEL(hotelID)
);

CREATE TABLE IF NOT EXISTS Reservation( 
reservationID INT,
guestName VARCHAR(100) REFERENCES Guest(name),
occupancy DATE,
roomID INT,
PRIMARY KEY(reservationID),
FOREIGN KEY(roomID) REFERENCES Room(roomID)
);

CREATE TABLE IF NOT EXISTS Occupancy( 
checkIn DATE,
checkOut DATE
);

.mode column
.headers on
.tables


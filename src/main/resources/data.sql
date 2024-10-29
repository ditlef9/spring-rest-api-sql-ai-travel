--
-- Script for H2 database
--

-- Insert users
INSERT INTO user_details (id, name, birth_date, password) VALUES
(1001, 'Liam Beachman', '1989-03-25', 'liamPass123'),
(1002, 'Emma Safari', '1991-11-15', 'emmaSecure456'),
(1003, 'Noah Roadtripper', '1993-06-30', 'noahPW789'),
(1004, 'Olivia Explorer', '1987-04-22', 'oliviaSecure987');


--UPDATE user_details_seq SET next_val = (SELECT MAX(id) FROM user_details) + 1;


-- Insert vacation interests with logical connections
INSERT INTO INTEREST (ID, USER_ID, DESCRIPTION) VALUES
(1001, 1001, 'Beach relaxation'),
(1002, 1001, 'Swimming in the ocean'),
(1003, 1001, 'Snorkeling adventures'),
(1004, 1001, 'Cruise vacations'),
(1005, 1002, 'Wildlife safari'),
(1006, 1002, 'Bird watching'),
(1007, 1002, 'Camping in nature'),
(1008, 1002, 'Historical sites'),
(1009, 1003, 'Road trips'),
(1010, 1003, 'Island hopping'),
(1011, 1003, 'Camping under the stars'),
(1012, 1003, 'Food and wine tours'),
(1013, 1004, 'Cultural festivals'),
(1014, 1004, 'Scuba diving'),
(1015, 1004, 'Exploring national parks');

--UPDATE interests_seq SET next_val = (SELECT MAX(id) FROM interests_seq) + 1;
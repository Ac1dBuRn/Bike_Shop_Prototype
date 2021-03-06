drop table if exists employee;
drop table if exists orders;
drop table if exists order_line;
drop table if exists payment;
drop table if exists product;
drop table if exists product_category;


-----------------------------------------------------------------
--                   CREATE TABLES
-----------------------------------------------------------------
CREATE TABLE EMPLOYEE
(
EMPLOYEE_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
FIRST_NAME VARCHAR(30) NOT NULL,
LAST_NAME VARCHAR(30) NOT NULL,
SSN VARCHAR(15) NOT NULL,
HIRE_DATE DATE NOT NULL,
TERMINATION_DATE DATE
);

CREATE TABLE PAYMENT
(
PAYMENT_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
PAYMENT_STATUS VARCHAR(30) NOT NULL,
PAYMENT_DATE DATE NOT NULL,
TAX_PERCENTAGE DOUBLE NOT NULL,
CASH_PAYMENT_AMT DOUBLE,
CC_NAME CHAR(30),
CC_NUM CHAR(20),
CC_TYPE CHAR(15),
CC_EXPIRATION_DATE DATE
);

CREATE TABLE PRODUCT_CATEGORY
(
PRODUCT_CATEGORY_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
NAME VARCHAR(20) NOT NULL,
DESCRIPTION VARCHAR(200) NOT NULL
);

CREATE TABLE PRODUCT
(
PRODUCT_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
PRODUCT_CATEGORY_ID INTEGER NOT NULL,
NAME VARCHAR(30) NOT NULL,
PRICE DOUBLE NOT NULL,
WEIGHT DOUBLE NOT NULL,
DESCRIPTION VARCHAR(200),
FOREIGN KEY(PRODUCT_CATEGORY_ID) REFERENCES PRODUCT_CATEGORY(PRODUCT_CATEGORY_ID)
);

CREATE TABLE ORDERS
(
ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
PAYMENT_ID INTEGER,
EMPLOYEE_ID INTEGER,
FOREIGN KEY(PAYMENT_ID) REFERENCES PAYMENT(PAYMENT_ID),
FOREIGN KEY(EMPLOYEE_ID) REFERENCES EMPLOYEE(EMPLOYEE_ID)
);

CREATE TABLE ORDER_LINE
(
ORDER_LINE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
ORDER_ID INTEGER NOT NULL,
PRODUCT_ID INTEGER NOT NULL,
QUANTITY INTEGER NOT NULL,
COST_PER_PRODUCT DOUBLE NOT NULL,
FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID),
FOREIGN KEY(ORDER_ID) REFERENCES ORDERS(ORDER_ID)
);


-----------------------------------------------------------------
--                   INSERT DATA - 20 records per table (ugh)
-----------------------------------------------------------------

insert into employee (first_name, last_name, ssn, hire_date) values("Joe", "Smith", "152516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("John", "Doe", "252516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Jane", "Smith", "352516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Jane", "Doe", "452516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Ashley", "Smitherson", "552516584", '01-01-2000');

insert into employee (first_name, last_name, ssn, hire_date) values("Richard", "Smith", "652516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Larry", "Smith", "752516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Bob", "Smith", "852516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Leon", "Smith", "952516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Leonardo", "Smith", "112516584", '01-01-2000');

insert into employee (first_name, last_name, ssn, hire_date) values("Janet", "Smith", "212516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Jimbo", "Smith", "222516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Bill", "Smith", "232516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Peter", "Smith", "232516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Lauren", "Smith", "252516584", '01-01-2000');

insert into employee (first_name, last_name, ssn, hire_date) values("Cathie", "Smith", "262516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Michele", "Smith", "272516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Michael", "Smith", "282516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Jerry", "Smith", "292516584", '01-01-2000');
insert into employee (first_name, last_name, ssn, hire_date) values("Peter", "Pan", "302516584", '01-01-2000');
---------------------------
---------------------------
insert into product_category (name, description) values("Bicycles", "Two wheels One person");
insert into product_category (name, description) values("Water Bottles", "You drink from it");
insert into product_category (name, description) values("Tires", "Replacement tire");
insert into product_category (name, description) values("Gloves", "Grip and Sweat gloves");
insert into product_category (name, description) values("Attachmemts", "Attachments such as a water bottle holder");

insert into product_category (name, description) values("Hand Brakes", "Replacement hand brakes");
insert into product_category (name, description) values("Wheel Brakes", "Replacement wheel brakes");
insert into product_category (name, description) values("Kickstands", "Replacement kickstands");
insert into product_category (name, description) values("Pedals", "Replacement pedals");
insert into product_category (name, description) values("Clothing", "Fitness clothing");

insert into product_category (name, description) values("Baskets", "Baskets for front and back");
insert into product_category (name, description) values("Shoes", "Sport Shoes");
insert into product_category (name, description) values("Car Racks", "Car rack attachments");
insert into product_category (name, description) values("Fenders", "Tire Fenders");
insert into product_category (name, description) values("Pedometers", "Measures distance travelled");

insert into product_category (name, description) values("SAFETY_EQUIPMENT", "From helmets to knee pads");
insert into product_category (name, description) values("REFLECTIVE", "Light and reflective gear");
insert into product_category (name, description) values("BELL", "Bells, whistles, and horns");
insert into product_category (name, description) values("Security Locks", "Security locks");
insert into product_category (name, description) values("Pumps", "Tire pumps");
---------------------------
---------------------------
insert into product (name, price, weight, description, product_category_id) values("Trek 7", 699.99, 26, "Best bike", 1);
insert into product (name, price, weight, description, product_category_id) values("Contigo No Drip", 9.99, 0.8, "A water bottle", 2);
insert into product (name, price, weight, description, product_category_id) values("Tiger Tire", 39.99, 0.9, "A tire", 3);
insert into product (name, price, weight, description, product_category_id) values("Geode Gloves", 29.99, 0.2, "Anti-Sweat Gloves", 4);
insert into product (name, price, weight, description, product_category_id) values("Spam Bottle Attachment", 12.99, 0.4, "Water Bottle Attachment", 5);

insert into product (name, price, weight, description, product_category_id) values("Blake's Hand Brake", 24.99, 0.8, "Hand Brake", 6);
insert into product (name, price, weight, description, product_category_id) values("Blake's Wheel Brake", 24.99, 1, "Wheel Brake", 7);
insert into product (name, price, weight, description, product_category_id) values("Kyle's Kickstand", 19.99, 1, "Universal Kickstand", 8);
insert into product (name, price, weight, description, product_category_id) values("Penny Pedals", 29.99, 1.2, "Steel Grip Pedals", 9);
insert into product (name, price, weight, description, product_category_id) values("Blue Under Armour Shirt", 29.99, 1.1, "Under Armour Shirt", 10);

insert into product (name, price, weight, description, product_category_id) values("Wendy's Wire Basket", 15.99, 3.4, "Wire Basket", 11);
insert into product (name, price, weight, description, product_category_id) values("Brandon's Biking Shoes", 399.99, 26, "Biking Shoes", 12);
insert into product (name, price, weight, description, product_category_id) values("Billy Bob Sports Bike Rack", 299.99, 19.5, "Typical Bike Rack", 13);
insert into product (name, price, weight, description, product_category_id) values("Fanny's Fender", 39.99, 26, "Anti-Splash Fender", 14);
insert into product (name, price, weight, description, product_category_id) values("Healthy Tip Pedometer", 29.99, 0.6, "Pedometer", 15);

insert into product (name, price, weight, description, product_category_id) values("Hermits Helmet", 29.99, 1.5, "Hard Plastic Helmet", 16);
insert into product (name, price, weight, description, product_category_id) values("Ripley's Red Reflector", 5.99, 0.1, "Red Reflector", 17);
insert into product (name, price, weight, description, product_category_id) values("Barry's Bell", 8.99, 0.3, "Ring Ring", 18);
insert into product (name, price, weight, description, product_category_id) values("Steve's Security Lock", 19.99, 1.1, "Security Lock", 19);
insert into product (name, price, weight, description, product_category_id) values("Timmy's Tire Pump", 29.99, 2.6, "Standard Tire Pump", 20);
---------------------------
---------------------------

--5
insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "01-01-2010", 7, 100.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 1);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(1, 1, 699.99, 1);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("REJECTED", "01-01-2010", 7, 0.00, "Fake Customer", "12345678912", "Mastercard", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 2);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(2, 3, 699.99, 1);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "01-02-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 3);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(3, 1, 699.99, 1);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "03-05-2010", 7, 9.99, null, null, null, null);
insert into orders (employee_id, payment_id) values(2, 4);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(4, 2, 9.99, 2);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "03-08-2010", 7, 0.00, "Fake Customer", "12345678912", "Mastercard", "06-30-2014");
insert into orders (employee_id, payment_id) values(2, 5);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(5, 1, 9.99, 2);


--5
insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "04-01-2010", 7, 0.00, "Fake Customer", "12345678912", "Mastercard", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 6);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(6, 1, 39.99, 3);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "04-02-2010", 7, 0.00, "Fake Customer", "12345678912", "Mastercard", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 7);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(7, 1, 29.99, 4);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(7, 1, 699.99, 1);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(7, 1, 39.99, 3);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "04-03-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 8);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(8, 1, 12.99, 5);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "04-12-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 9);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(9, 1, 15.99, 11);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "04-22-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 10);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(10, 1, 29.99, 9);


--5
insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "05-11-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 11);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(11, 1, 29.99, 10);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "05-14-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 12);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(12, 1, 399.99, 12);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "06-01-2010", 7, 0.00, "Fake Customer", "12345678912", "Mastercard", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 13);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(13, 1, 299.99, 13);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "06-08-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 14);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(14, 1, 39.99, 14);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "06-12-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 15);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(15, 1, 29.99, 15);


--5
insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "07-01-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 16);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(16, 1, 29.99, 16);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "08-01-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 17);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(17, 1, 5.99, 17);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "08-08-2010", 7, 0.00, "Fake Customer", "12345678912", "Visa", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 18);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(18, 1, 8.99, 18);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "09-09-2010", 7, 0.00, "Fake Customer", "12345678912", "Mastercard", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 19);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(19, 1, 19.99, 19);

insert into payment (payment_status, payment_date, tax_percentage, cash_payment_amt, cc_name, cc_num, cc_type, cc_expiration_date)
 values("PAID", "10-01-2010", 7, 0.00, "Fake Customer", "12345678912", "Mastercard", "06-30-2014");
insert into orders (employee_id, payment_id) values(1, 20);
insert into order_line (order_id, quantity, cost_per_product, product_id) values(20, 1, 29.99, 20);

--liquibase formatted sql

--changeset Andrii Sereda:v2-2 runOnChange:true
INSERT INTO taxi_drivers (name, surname, company_id, age, driving_experience, salary, cars) VALUES
    ('John', 'Doe', 1, 30, 10, 5000, 'Toyota Corolla, Mitsubishi Outlander'),
    ('Jane', 'Smith', 1, 28, 8, 4800, 'Honda Civic, Nissan Juke'),
    ('Robert', 'Brown', 1, 45, 20, 5500, 'Ford Focus, BMW 3 Series'),
    ('Emily', 'Davis', 1, 35, 12, 5200, 'Audi A4, Volvo XC60'),
    ('Michael', 'Wilson', 1, 50, 25, 6000, 'Mercedes-Benz C-Class, Tesla Model S'),
    ('Sarah', 'Miller', 1, 32, 9, 4700, 'Mazda CX-5, Subaru Forester'),
    ('William', 'Taylor', 1, 40, 15, 5300, 'Volkswagen Passat, Toyota Camry'),
    ('David', 'Anderson', 1, 38, 17, 5400, 'Chevrolet Malibu, Honda Accord'),
    ('Richard', 'Thomas', 1, 33, 11, 5100, 'Hyundai Sonata, Kia Sportage'),

    ('Jessica', 'Martinez', 2, 29, 7, 4600, 'Ford Escape, Jeep Cherokee'),
    ('Daniel', 'Lee', 2, 37, 14, 5200, 'GMC Terrain, Subaru Outback'),
    ('Laura', 'Clark', 2, 26, 5, 4300, 'Honda CR-V, Nissan Rogue'),

    ('Matthew', 'Garcia', 3, 34, 10, 5000, 'Toyota RAV4, Mitsubishi Eclipse Cross'),
    ('Olivia', 'Hernandez', 3, 31, 8, 4800, 'Ford Edge, Chevrolet Traverse'),

    ('James', 'Martinez', 4, 42, 16, 5500, 'Audi Q5, Volvo XC40'),

    ('Emma', 'Roberts', 5, 36, 13, 5100, 'Hyundai Tucson, Mazda CX-3'),
    ('Liam', 'Walker', 5, 41, 18, 5400, 'Jeep Grand Cherokee, Ford Explorer'),
    ('Sophia', 'Hall', 5, 27, 6, 4500, 'Chevrolet Equinox, Honda HR-V'),

    ('Mason', 'Young', 6, 39, 16, 5200, 'Nissan Murano, Volkswagen Tiguan'),
    ('Isabella', 'King', 7, 30, 9, 4700, 'Subaru Ascent, Kia Sorento'),
    ('Logan', 'Scott', 8, 44, 19, 5600, 'Toyota Highlander, Ford Expedition');

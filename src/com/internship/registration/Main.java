package com.internship.registration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        RegistrationDAO registrationDAO = new RegistrationDAO(null);
        Scanner scanner = new Scanner(System.in);
        boolean flag= true;

        while (flag) {
            System.out.println("Choose operation:");
            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:{
                    createRecord(registrationDAO, scanner);
                    break;
                }
                case 2:{
                    readRecords(registrationDAO);
                    break;
                }
                case 3:{
                    updateRecord(registrationDAO, scanner);
                    break;
                    }
                case 4:{
                    deleteRecord(registrationDAO, scanner);
                    break;
                }
                case 5:
                {
                    System.out.println("Exiting...");
                    flag=false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void createRecord(RegistrationDAO registrationDAO, Scanner scanner) throws ClassNotFoundException {
        Registration newRegistration = new Registration();
        System.out.println("Enter Name:");
        newRegistration.setName(scanner.next());
        System.out.println("Enter Email:");
        newRegistration.setEmail(scanner.next());
        System.out.println("Enter contact number");
        newRegistration.setContactNumber(scanner.next());

        registrationDAO.createRecord(newRegistration);
    }

    private static void readRecords(RegistrationDAO registrationDAO) throws ClassNotFoundException {
        List<Registration> registrations = registrationDAO.readRecords();
        System.out.println("All Records:");
        for (Registration registration : registrations) {
            System.out.println(registration);
        }
    }

    private static void updateRecord(RegistrationDAO registrationDAO, Scanner scanner) throws ClassNotFoundException {
        System.out.println("Enter ID of the record to update:");
        int recordId = scanner.nextInt();

        Registration existingRegistration = getRegistrationById(registrationDAO, recordId);

        if (existingRegistration != null) {
            System.out.println("Enter new Name:");
            existingRegistration.setName(scanner.next());
            System.out.println("Enter new Email:");
            existingRegistration.setEmail(scanner.next());
            System.out.println("Enter new Contact Number:");
            existingRegistration.setContactNumber(scanner.next());

            registrationDAO.updateRecord(existingRegistration);
        } else {
            System.out.println("Record not found for update.");
        }
    }

    private static void deleteRecord(RegistrationDAO registrationDAO, Scanner scanner) throws ClassNotFoundException {
        System.out.println("Enter ID of the record to delete:");
        int recordId = scanner.nextInt();

        Registration existingRegistration = getRegistrationById(registrationDAO, recordId);

        if (existingRegistration != null) {
            registrationDAO.deleteRecord(recordId);
            System.out.println("Record deleted successfully!");
        } else {
            System.out.println("Record not found for deletion.");
        }
    }

    private static Registration getRegistrationById(RegistrationDAO registrationDAO, int recordId) throws ClassNotFoundException {
        List<Registration> registrations = registrationDAO.readRecords();
        for (Registration registration : registrations) {
            if (registration.getId() == recordId) {
                return registration;
            }
        }
        return null;
    }
}
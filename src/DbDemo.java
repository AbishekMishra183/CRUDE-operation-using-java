
import java.sql.*;
import java.util.Scanner;
import static java.lang.System.exit;
import java.util.InputMismatchException;

public class DbDemo {

    public static void dbMenus() {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("\n\t\t\t ********* Welcome to my Database *********");
                System.out.println("\t\t\t ----- Please select the DB Operation below -----\n");
                System.out.println("\t\t\t1 - Create Data ");
                System.out.println("\t\t\t2 - Read Data ");
                System.out.println("\t\t\t3 - Delete Data ");
                System.out.println("\t\t\t4 - Update Data ");
                System.out.println("\t\t\t5 - Exit");
                System.out.print("\t\t\t\tSelect any option: ");

                int userInput = sc.nextInt();
                validInput = true; // If we get a valid integer, set this to true

                switch (userInput) {
                    case 1:
                        System.out.println("You've selected Menu 1. Let's add some data");
                        createOperation(sc);
                        break;
                    case 2:
                        System.out.println("You selected option 2");
                        ReadOperation(sc);
                        break;
                    case 3:
                        System.out.println("You selected option 3");
                        DeleteOperation(sc);
                        break;
                    case 4:
                        System.out.println("You selected option 4");
                        UpdateOperation(sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        exit(0);
                    default:
                        System.out.println("Invalid option. Please select between 1 and 5.");
                        validInput = false; // Invalid option, so set this to false to repeat the loop
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // Clear the invalid input
            }
        }
    }

    private static void createOperation(Scanner sc) {
        final String uname = "root";
        final String pass = "";

        try {
            System.out.print("\t\t\tEnter student ID number: ");
            int id = sc.nextInt();
            System.out.print("\t\t\tEnter student name: ");
            String name = sc.next();
            System.out.print("\t\t\tEnter student roll: ");
            String roll = sc.next();

            String query = "INSERT INTO tbl1_student (id, name, roll) values (" + id + ",'" + name + "','" + roll + "')";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", uname, pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);

            if (rows == 1) {
                System.out.println("\t\t\t" + rows + " row(s) affected.");
            } else {
                System.out.println("\t\t\tSomething went wrong.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            sc.next(); // Clear the invalid input
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }


    private static void ReadOperation(Scanner sc)
    {
        final String uname = "root";
        final String pass = "";
        final String query = "SELECT * FROM tbl1_student";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", uname, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n\t\t\tID\tName\tRoll");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String roll = rs.getString("roll");
                System.out.println("\t\t\t" + id + "\t" + name + "\t" + roll);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void DeleteOperation(Scanner sc)
    {
        final String uname = "root";
        final String pass = "";

        try {
            System.out.print("\t\t\tEnter student ID number to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM tbl1_student WHERE id = " + id;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", uname, pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);

            if (rows == 1) {
                System.out.println("\t\t\t" + rows + " row(s) deleted.");
            } else {
                System.out.println("\t\t\tNo matching records found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid student ID.");
            sc.next(); // Clear the invalid input
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

    }

    private static void UpdateOperation(Scanner sc)
    {
        final String uname = "root";
        final String pass = "";

        try {
            System.out.print("\t\t\tEnter student ID number to update: ");
            int id = sc.nextInt();
            System.out.print("\t\t\tEnter new student name: ");
            String name = sc.next();
            System.out.print("\t\t\tEnter new student roll: ");
            String roll = sc.next();

            String query = "UPDATE tbl1_student SET name = '" + name + "', roll = '" + roll + "' WHERE id = " + id;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1", uname, pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);

            if (rows == 1) {
                System.out.println("\t\t\t" + rows + " row(s) updated.");
            } else {
                System.out.println("\t\t\tNo matching records found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            sc.next(); // Clear the invalid input
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        dbMenus();
    }
    }


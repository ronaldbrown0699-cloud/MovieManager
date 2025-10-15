import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        int ticketsSold = 0;
        int totalIncome = 0;
        int totalPossible;
        boolean selection = false;


        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter the number of seats in each row: ");
        int seats = scanner.nextInt();

        // Create a 2D array for seats
        String[][] view = new String[rows][seats];
        // Initialize all seats to "S"
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                view[i][j] = "S";
            }
        }
        int totalSeats = rows * seats;
        int ticketPrice;

        do {

            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            int rowSelect;

            if(choice == 1){
                printCinema(view);
            }
            if (choice == 2) {
                boolean seatChosen = false;  // reset inside option 2

                do {
                    System.out.print("Enter a row number: ");
                    rowSelect = scanner.nextInt();

                    System.out.print("Enter a seat number in that row: ");
                    int seatSelect = scanner.nextInt();

                    if(rowSelect < 1 || rowSelect > rows || seatSelect < 1 || seatSelect > seats) {
                        System.out.println("Wrong input!");
                        continue;
                    }

                    if (view[rowSelect - 1][seatSelect - 1].equals("B")) {
                        System.out.println("That ticket has already been purchased!");
                    } else {
                        // Valid selection
                        view[rowSelect - 1][seatSelect - 1] = "B";
                        ticketsSold++;
                        seatChosen = true;  // exit loop after this

                        // Price logic
                        if (totalSeats <= 60) {
                            ticketPrice = 10;
                        } else {
                            int frontRows = rows / 2;
                            if (rowSelect <= frontRows) {
                                ticketPrice = 10;
                            } else {
                                ticketPrice = 8;
                            }
                        }

                        totalIncome += ticketPrice;
                        System.out.println("Ticket price: $" + ticketPrice);
                    }

                } while (!seatChosen);  // keep looping only if seat was invalid
            }

            if(choice == 3){
                System.out.println();
                System.out.println("Number of purchased tickets: " + ticketsSold);
                double percentage = (double) ticketsSold / totalSeats * 100;
                System.out.printf("Percentage: %.2f%%\n", percentage );
                System.out.println("Current income: $" + totalIncome);
                if(totalSeats <= 60){
                    ticketPrice = 10;
                    totalIncome += ticketPrice;
                    System.out.println("Total income: $" + (totalSeats * ticketPrice));
                } else {
                    int frontRows = rows /2;
                    int backrows = rows - frontRows;
                    totalPossible = (frontRows * seats * 10) + (backrows * seats * 8);
                    System.out.println("Total income: $" + totalPossible);
                }
            }
            if(choice == 0){
                isRunning = false;
            }
        } while(isRunning);

    }

    // Method to print the cinema
    public static void printCinema(String[][] view) {
        int rows = view.length;
        int seats = view[0].length;

        System.out.println("Cinema:");
        System.out.print("  ");
        for (int j = 1; j <= seats; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(view[i][j] + " ");
            }
            System.out.println();
        }
    }
}

import Controllers.RentalController;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        RentalController rentalController = new RentalController();

        try {
            System.out.print("Ingrese la fecha de salida del vehículo en formato YYYY-MM-DD: ");
            LocalDate exitDate = LocalDate.parse(reader.nextLine());
            rentalController.setExitDate(exitDate);

            System.out.print("Ingrese la fecha estimada de regreso en formato YYYY-MM-DD: ");
            LocalDate estimateDate = LocalDate.parse(reader.nextLine());
            rentalController.setEstimateReturnDate(estimateDate);

            // Calcula el tiempo total
            rentalController.calculateTotalDays();
            int totalDays = rentalController.getTotalDays();

            // Mensaje de cada día de alquiler
            for (int i = 1; i <= totalDays; i++) {
                System.out.println("Día " + i + " de " + totalDays);
            }

            System.out.print("Ingrese la fecha real de regreso del vehículo en formato YYYY-MM-DD: ");
            LocalDate realDate = LocalDate.parse(reader.nextLine());
            rentalController.setReturnDate(realDate);

            // Calcula días extras y el total a pagar
            rentalController.calculateExtraDays();
            int extraDays = rentalController.getExtraDays();

            // Precio por día de alquiler
            double pricePerDay = 1.50;
            double billingTotal = rentalController.calculateBillingTotal(pricePerDay);

            System.out.println("Días extras: " + extraDays);
            System.out.println("El total por el alquiler del vehículo es: $" + billingTotal);
        } catch (Exception e) {
            System.out.println("Error: Ingrese una fecha válida en formato YYYY-MM-DD.");
        }

        reader.close();
    }
}

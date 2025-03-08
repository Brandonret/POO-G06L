package Controllers;
import java.time.Duration;
import java.time.LocalDate;

public class RentalController {
    // Variables privadas para el control de fechas
    private LocalDate exitDate;
    private LocalDate estimateReturnDate;
    private LocalDate returnDate;

    // Variables para los días de alquiler y retraso
    private int totalDays;
    private int extraDays;

    // Métodos getters y setters para encapsulación
    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public void setEstimateReturnDate(LocalDate estimateReturnDate) {
        this.estimateReturnDate = estimateReturnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getExtraDays() {
        return extraDays;
    }

    // Calcula la cantidad total de días de alquiler
    public void calculateTotalDays() {
        if (exitDate != null && estimateReturnDate != null) {
            this.totalDays = Math.toIntExact(Duration.between(this.exitDate.atStartOfDay(), this.estimateReturnDate.atStartOfDay()).toDays());
        }
    }

    // Calcula los días de retraso si existen
    public void calculateExtraDays() {
        if (estimateReturnDate != null && returnDate != null) {
            this.extraDays = Math.toIntExact(Duration.between(this.estimateReturnDate.atStartOfDay(), this.returnDate.atStartOfDay()).toDays());
            if (this.extraDays < 0) {
                this.extraDays = 0;
            }
        }
    }

    // Calcula el costo total del alquiler incluyendo días extras
    public double calculateBillingTotal(double pricePerDay) {
        double total = pricePerDay * totalDays;

        // Calcular el costo extra según la cantidad de días de retraso
        if (this.extraDays > 0) {
            if (this.extraDays <= 3) {
                total += this.extraDays * 5.00; // $5 por día si el retraso es de 1 a 3 días
            } else if (this.extraDays <= 7) {
                total += this.extraDays * 8.00; // $8 por día si el retraso es de 4 a 7 días
            } else {
                total += this.extraDays * 10.00; // $10 por día si el retraso es mayor a 7 días
            }
        }

        return total;
    }
}

package by.epam.khlopava.hotel.entity;

import java.time.LocalDate;

public class CreditCard extends Entity {

    private String cardNumber;
    private LocalDate expDate;
    private String cardHolder;
    private User cardUser; //

    public CreditCard(String cardNumber, LocalDate expDate, String cardHolder) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cardHolder = cardHolder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
}

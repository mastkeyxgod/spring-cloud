package ru.mastkey.deposit.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long depositId;

    private BigDecimal amount;

    private Long billId;

    private OffsetDateTime depositDate;

    private String email;

    public Deposit(BigDecimal amount, Long billId, OffsetDateTime depositDate, String email) {
        this.amount = amount;
        this.billId = billId;
        this.depositDate = depositDate;
        this.email = email;
    }

    public Deposit() {
    }

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OffsetDateTime getCreationTime() {
        return depositDate;
    }

    public void setCreationTime(OffsetDateTime creationTime) {
        this.depositDate = creationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositId=" + depositId +
                ", amount=" + amount +
                ", billId=" + billId +
                ", depositDate=" + depositDate +
                ", email='" + email + '\'' +
                '}';
    }
}

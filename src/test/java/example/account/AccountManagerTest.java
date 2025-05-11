package example.account;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.*;


public class AccountManagerTest {

    Customer customer = new Customer();
    AccountManager accountManager = new AccountManagerImpl();


    @Test
    void givenValidAmount_whenDeposit_thenCustomerBalanceIncreases() {
        // Arrange
        customer.setBalance(100);

        // Act
        accountManager.deposit(customer, 50);

        // Assert
        assertThat(customer.getBalance()).isEqualTo(150);
    }

    @Test
    void givenInvalidAmount_whenDeposit_thenThrowsException() {

        assertThatThrownBy(() ->
                accountManager.deposit(customer, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Deposit amount must be positive");
    }

    @Test
    void givenInvalidAmount_whenWithdraw_thenReturnsInvalidAmount() {
        // Act
        String result = accountManager.withdraw(customer, -100);

        // Assert
        assertThat(result).isEqualTo("Invalid withdrawal amount");
    }

    @Test
    void givenSufficientAmount_WhenWithdraw_ThenReturnsSuccess(){
        // Arrange
        customer.setBalance(1000);

        // Act
        String result = accountManager.withdraw(customer, 500);

        // Assert
        assertThat(result).isEqualTo("success");
    }

    @Test
    void givenInsufficientAmountAndCreditNotAllowed_whenWithdraw_thenReturnsInsufficientAccountBalance() {
        // Arrange
        customer.setBalance(1000);
        customer.setCreditAllowed(false);

        // Act
        String result = accountManager.withdraw(customer, 3000);

        // Assert
        assertThat(result).isEqualTo("insufficient account balance");
    }

    @Test
    void givenInsufficientAmountAndExceedsLimitAndNotVIP_whenWithdraw_thenReturnsMaxCreditExceeded(){
    // Arrange
        customer.setBalance(1000);
        customer.setCreditAllowed(true);
        customer.setVip(false);

        // Act
        String result = accountManager.withdraw(customer, 3000);

        // Assert
        assertThat(result).isEqualTo("maximum credit exceeded");
    }

    @Test
    void givenInsufficientAmountWithinMaxCreditLimit_WhenWithdraw_ThenReturnsSuccess(){
        // Arrange
        customer.setBalance(1000);
        customer.setCreditAllowed(true);
        customer.setVip(false);

        // Act
        String result = accountManager.withdraw(customer, 2000);

        // Assert
        assertThat(result).isEqualTo("success");
    }

    @Test
    void givenInsufficientAmountWithVIP_WhenWithdraw_ThenReturnsSuccess(){
        // Arrange
        customer.setBalance(1000);
        customer.setCreditAllowed(true);
        customer.setVip(true);

        // Act
        String result = accountManager.withdraw(customer, 3000);

        // Assert
        assertThat(result).isEqualTo("success");
    }

}

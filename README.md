# Eviro Bank Java Assessment

- *I Used JetBrains IntelliJ Idea IDE*

## Architecture

Tried going for a layered Architecture

### Presentation Layer

**Main.java**
Everything Facing The End-user Are Found

### Application Layer

**SavingsAccount.java** *&* **CurrentAccount.java**
- These Two Classes Would Form Part Of The Application Layer
- Implements Methods Defined In *AccountService.java*

```java
/**
 * @param accountNum : Account Number Associated With A User
 * @param amountToWithdraw : Amount Of Money To Be Withdrawn By User
 *
 * */
public void withdraw(String accountNum, BigDecimal amountToWithdraw);

/**
 * @param balance Sets The Balance In The Account
 * */
 public void setBalance(BigDecimal balance);

/**
 * @return id Returns The Technical ID
 * */
public int getId();

/**
 * @return String accountNum: Returns The Account Number Associated With The Account
 * */
public String getAccountNumber();

/**
 * @return BigDecimal balance : Returns Balance Of The Account
 * */
public BigDecimal getBalance();
```

### Domain Layer

**AccountService.java**

- Forming The Domain Layer

### Infrastructure Layer

**SystemDB.java**

- All Layers Query Information From Here
- Persists The Data In The DB
- Also is a Singleton Pattern
*Using Object Of The Class*
```java


SystemDB db = SystemDB.getInstance(); // Returns Object OF SystemDB

	/* With This Object You Can Access DB Methods Like */

db.findCurrentAccount(String accNumber);
db.findSavingsAccount(String accNumber);
db.getSavingsAccount(int index);
db.getCurrentAccount(int index);

/* Get All Savings & Current Accounts */

db.currentAccountData();
db.getSavingsData();

```

## Exceptions

I Created My Own Custom Exceptions

```java

// Extends javax.security.auth.login.AccountNotFoundException;
throw new UserAccountNotFound("User Account Not Found"); 

/* OR */

// Extends Exception
throw new WithdrawAmountExceedingAccount("Unable To Withdraw : Insufficient Funds");
```


package Model;

// Singleton
public class LoggedAccount {

	private static Account account;
	public static void setAccount(Account account)
	{
		LoggedAccount.account=account;
	}
	public static Account getAccount()
	{
		return account;
	}
}

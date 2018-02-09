package DAO;

public class balanceBean
{
	String account,saving,current,credit,limit;

	public balanceBean(String account, String saving, String current, String credit, String limit) {
		super();
		this.account = account;
		this.saving = saving;
		this.current = current;
		this.credit = credit;
		this.limit = limit;
	}

	public balanceBean()
	{
		
	}
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSaving() {
		return saving;
	}

	public void setSaving(String saving) {
		this.saving = saving;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "balanceBean [account=" + account + ", saving=" + saving + ", current=" + current + ", credit=" + credit
				+ ", limit=" + limit + ", getAccount()=" + getAccount() + ", getSaving()=" + getSaving()
				+ ", getCurrent()=" + getCurrent() + ", getCredit()=" + getCredit() + ", getLimit()=" + getLimit()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
}

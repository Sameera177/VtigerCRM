package genericUtillities;


/**
 * This enum stores all the tab names of vtiger application
 */
public enum TabNames 
{
	ORGANIZATIONS("Accounts"), CONTACTS("Contacts"), LEADS("Leads");
	
	private String tabName;
	
	private TabNames(String tabName) {
		this.tabName= tabName;
		
	}
	
	public String getTabName()
	{
		return tabName;
	}
}

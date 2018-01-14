package uiComponentScreens;

import common.UIElement;
import common.UIElementType;

public class GoogleHome {

	public UIElement inputBox = new UIElement("lst-ib", "Search Input Box", UIElementType.byId);
	public UIElement searchTermBox = new UIElement("//div[@class='sbsb_a']", "Search Term Box", UIElementType.byXpath);
	public UIElement firstSearchTerm = new UIElement("sbse0", "First Term in Search Box", UIElementType.byId);
	public UIElement searchBtn = new UIElement("//input[@value='Google Search']", "Search Button",
			UIElementType.byXpath);
}

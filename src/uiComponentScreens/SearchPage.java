package uiComponentScreens;

import common.UIElement;
import common.UIElementType;

public class SearchPage {

	public UIElement resultLinks = new UIElement("//div[@class='g']//child::cite[@class='_Rm']", "Result Links",
			UIElementType.byXpath);
	public UIElement resulClicktTitle = new UIElement("//div[@class='rc']//child::h3[@class='r']//child::a[@href]",
			"Result Titles", UIElementType.byXpath);
	public UIElement nextPage = new UIElement("pnnext", "Next Page Button", UIElementType.byId);
}

package nixstudio.Feather.Core;

import imgui.app.Application;

public abstract class StudioWindow{
	
	public GuiMain application;
	
	public StudioWindow(GuiMain main) {
		this.application = main;
	}
	
	public StudioWindow() {
		
	}
	
	public abstract String getTitle();

	public abstract void guiUpdate();

}
